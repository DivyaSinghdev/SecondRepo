/**
 * Copyright (C) 2005 - 2019 Sapio Sciences <support@sapiosciences.com>
 *
 * ====================================================================
 * This software is the property of Sapio Sciences.
 * ====================================================================
 */
package com.velox.training.managers;

import com.velox.api.datarecord.DataRecord;
import com.velox.api.eln.experimententry.EnbEntry.EnbEntryType;
import com.velox.api.eln.experimententry.*;
import com.velox.api.eln.notebookexperiment.NotebookExperiment;
import com.velox.api.eln.notebookexperiment.TemplateExperiment;
import com.velox.api.eln.notebookexperiment.TemplateExperimentInfo;
import com.velox.api.eln.notebookmanager.NotebookExperimentManager;
import com.velox.api.util.ServerException;
import com.velox.sapio.commons.exemplar.context.ExemplarContext;
import com.velox.sapio.commons.exemplar.exception.ExemplarCancelException;
import com.velox.sapio.commons.exemplar.exception.WorkflowException;
import com.velox.sapio.commons.exemplar.procedural.elements.Step;
import com.velox.sapio.commons.exemplar.procedural.elements.eln.EntryStepImpl;
import com.velox.sapio.commons.exemplar.recordmodel.record.RecordModel;
import com.velox.sapio.commons.exemplar.recordmodel.util.RecordModelUtil;
import com.velox.sapioutils.shared.utilities.ListUtils;
import com.velox.training.TrainingManagerBase;
import org.apache.commons.lang3.StringUtils;

import java.lang.reflect.InvocationTargetException;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

/**
 * This is cached manager which should be singleton per context.
 * @author prakash.d
 *
 */
public class TrainingExperimentManager extends TrainingManagerBase {

	public TrainingExperimentManager(ExemplarContext context) {
		super(context);
	}

	/**
	 * * WARNING: rec models should be committed before calling this Creates new experiment table entry if not given and will add the records to it.
	 *
	 * @param recordsToAdd
	 * @param tableEntry
	 * @param entryName
	 * @return
	 * @throws Throwable
	 */
	public <T extends RecordModel> ExperimentTableEntry addRecordsToExperimentTableEntry(NotebookExperiment notebookExperiment,
	                                                                                     ExperimentTableEntry tableEntry,
	                                                                                     Collection<RecordModel> recordsToAdd,
	                                                                                     String dataType,
	                                                                                     Long dependentEntryId,
	                                                                                     Boolean includeExtension,
	                                                                                     Integer nextEntryPosition,
	                                                                                     String entryName,
	                                                                                     ExperimentEntry existingEntry,
	                                                                                     Step activeStep,
	                                                                                     ExperimentEntryPosition experimentEntryPosition)
			throws Throwable {

		if (tableEntry == null) {
			tableEntry = createExperimetTableEntry(notebookExperiment,
			                                       entryName,
			                                       dataType,
			                                       nextEntryPosition,
			                                       experimentEntryPosition != null
			                                       ? experimentEntryPosition.getNotebookExperimentTabId()
			                                       : existingEntry.getNotebookExperimentTabId(),
			                                       experimentEntryPosition != null
			                                       ? experimentEntryPosition.getOrder()
			                                       : existingEntry.getOrder(),
			                                       experimentEntryPosition != null
			                                       ? experimentEntryPosition.getColumnSpan()
			                                       : existingEntry.getColumnSpan(),
			                                       experimentEntryPosition != null
			                                       ? experimentEntryPosition.getColumnOrder()
			                                       : existingEntry.getColumnOrder());
		}
		else if (StringUtils.isBlank(tableEntry.getExperimentEntryName()) && !StringUtils.isBlank(entryName)) {
			tableEntry.setExperimentEntryName(entryName);
		}

		Step tableStep = new EntryStepImpl(notebookExperiment, tableEntry, exemplarContext);
		if (!tableStep.isAvailable()) {
			displayError("Entry " + tableEntry.getExperimentEntryName() + " is not modifiable.");
			throw new ExemplarCancelException();
		}

		if (!ListUtils.isBlank(recordsToAdd)) {
			List<DataRecord> dataRecords = RecordModelUtil.getDataRecordList(recordsToAdd);
			notebookExperiment.addRecordsToTableEntry(tableEntry, dataRecords, user);
			if (includeExtension) {
				addExtensionTypesOfRecords(tableEntry, recordsToAdd, dataType, existingEntry, activeStep);
			}
			if (dependentEntryId != null) {
				tableEntry.addDependency(dependentEntryId);
			}
			notebookExperiment.setExperimentEntry(tableEntry, user);
		}

		return tableEntry;
	}

	/**
	 * @param entryName
	 * @return
	 * @throws RemoteException
	 * @throws ServerException
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 * @throws Exception
	 */
	public ExperimentTableEntry createExperimetTableEntry(NotebookExperiment notebookExperiment,
	                                                      String entryName,
	                                                      String datatypeName,
	                                                      Integer nextEntryPosition,
	                                                      Long notebookExperimentTabId,
	                                                      Integer order,
	                                                      Integer columnSpan,
	                                                      Integer columnOrder) throws Exception {

		ExperimentEntryPosition pos = new ExperimentEntryPosition(notebookExperimentTabId, nextEntryPosition, columnSpan, columnOrder);
		ExperimentEntryCriteria experimentEntryCriteria = new ExperimentEntryCriteria(entryName, datatypeName);
		experimentEntryCriteria.setExperimentEntryPosition(pos);

		ExperimentTableEntry tableEntry = notebookExperiment.addExperimentTableEntry(experimentEntryCriteria, user);
		tableEntry.setRenamable(true);
		return tableEntry;
	}

	/**
	 * Given a list of recmodels and the experiment entry, add all possible extension types of the recmodels to the entry without prompting user.
	 *
	 * @param context
	 * @param createSamplesGrabberPlugin
	 *            TODO
	 * @param recordsDataTypeName
	 *            TODO
	 * @param records
	 * @param entry
	 * @return
	 * @throws Throwable
	 */
	public <T extends RecordModel> List<String> addExtensionTypesOfRecords(ExperimentEntry entry,
	                                                                       Collection<RecordModel> models,
	                                                                       String recordsDataTypeName,
	                                                                       ExperimentEntry existingEntry,
	                                                                       Step activeStep) throws Throwable {

		if (models.stream().anyMatch(model -> !recordsDataTypeName.equals(model.getDataTypeName()))) {
			throw new WorkflowException("addExtensionTypeOfRecord contains records of other datatypes than the datatype specified.");
		}

		// CR-30156 Allow the User to add existing Samples in the existing Experiment Entry
		if (activeStep != null && existingEntry != null) {
			models.addAll(activeStep.getRecords(recordsDataTypeName));
			models = ListUtils.removeDuplicates(models);
		}

		List<String> extensionDataTypesToInclude =
				new ArrayList<String>((extHelper.getExtensionTypeRecordModelMap(models.stream().map(model -> {
					return model.getRootModel();
				}).collect(Collectors.toList())).keySet()));

		if (!extensionDataTypesToInclude.isEmpty()) {
			EnbEntryType entryType = entry.getEnbEntryType();
			if (entryType != null) {
				switch (entryType) {
					case Table: {
						ExperimentTableEntry tableEntry = (ExperimentTableEntry) entry;

						// Remove the existing ExtensionTypes of the entry
						tableEntry.getExtensionTypeList().forEach(curExtensionType -> tableEntry.removeExtensionType(curExtensionType));

						extensionDataTypesToInclude = extensionDataTypesToInclude.stream().distinct().collect(Collectors.toList());
						tableEntry.setExtensionTypeList(extensionDataTypesToInclude);
						break;
					}
					case Form: {
						ExperimentFormEntry formEntry = (ExperimentFormEntry) entry;

						// Remove the existing ExtensionTypes of the entry
						formEntry.getExtensionTypeList().forEach(curExtensionType -> formEntry.removeExtensionType(curExtensionType));

						extensionDataTypesToInclude = extensionDataTypesToInclude.stream().distinct().collect(Collectors.toList());
						formEntry.setExtensionTypeList(extensionDataTypesToInclude);
						break;
					}
					default:
						break;
				}
			}
		}
		return extensionDataTypesToInclude;
	}

	public NotebookExperiment createExperimentFromTemplate(String templateName, String experiment) throws Throwable {

		NotebookExperiment sampRegExp = null;
		NotebookExperimentManager notebookExperimentManager = dataMgmtServer.getNotebookExperimentManager(user);
		if (templateName != null) {
			TemplateExperiment tempExp = getLatestTemplateExperiment(templateName);
			if (tempExp == null) {
				clientCallback.displayError(templateName + " is not found in the system!");
				return null;
			}
			sampRegExp = notebookExperimentManager.createNotebookExperiment(experiment, tempExp, null, user);
		}
		else {
			sampRegExp = notebookExperimentManager.createNotebookExperiment(experiment, null, user);
		}

		if (sampRegExp == null) {
			clientCallback.displayError("Unable to create " + experiment + " experiment!");
			return null;
		}
		nbExpMgr.storeNotebookExperimentAndRecords(sampRegExp, experiment + " Exp Launch", clientCallback.getClientCallbackRMI(), user);
		storeEln(sampRegExp, experiment + " Exp Launch");
		return sampRegExp;
	}

	/**
	 * Get Latest TemplateExperiment by the template name
	 *
	 * @param templateName
	 * @return
	 * @throws Exception
	 */
	public TemplateExperiment getLatestTemplateExperiment(String templateName) throws Exception {
		List<TemplateExperimentInfo> templateInfoList = nbExpMgr.getLatestTemplateExperimentInfoList(user);

		templateInfoList = templateInfoList.stream().filter(templateInfo -> {
			return templateName.equals(templateInfo.getTemplateName());
		}).collect(Collectors.toList());

		if (!ListUtils.isBlank(templateInfoList)) {
			return nbExpMgr.getTemplateExperiment(templateInfoList.get(0).getTemplateId(), user);
		}
		return null;
	}

	public void storeEln(NotebookExperiment notebookExperiment, String storeComment) throws Exception {
		storeComment = StringUtils.isBlank(storeComment) ? "" : storeComment;
		transMan.storeChanges();
		dataRecordManager.storeAndCommit(storeComment, this.clientCallback.getClientCallbackRMI(), user);
		if (notebookExperiment != null) {
			nbExpMgr.storeNotebookExperimentAndRecords(notebookExperiment, storeComment, clientCallback.getClientCallbackRMI(), user);
		}
	}
	public ExperimentEntry getExperimentEntryWithDatatype(NotebookExperiment experiment, String datatypeName) throws Exception{
		if (experiment == null) {
		return null;
		}
		List<ExperimentEntry> entries = experiment.getExperimentEntryList(datatypeName, user);
		if (ListUtils.isBlank(entries)) {
		return null;
		}
		return entries.iterator().next();
		}
}
