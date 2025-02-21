/**
 * Copyright (C) 2005 - 2019 Sapio Sciences <support@sapiosciences.com>
 *
 * ====================================================================
 * This software is the property of Sapio Sciences.
 * ====================================================================
 */
package com.velox.training.managers;

import com.velox.api.datafielddefinition.DataFieldDefinition;
import com.velox.api.datafielddefinition.DataFieldDefinitions;
import com.velox.api.datatype.DataTypeDefinition;
import com.velox.api.datatype.TemporaryDataType;
import com.velox.api.datatype.fielddefinition.VeloxFieldDefinition;
import com.velox.api.eln.notebookexperiment.TemplateExperiment;
import com.velox.api.eln.notebookexperiment.TemplateExperimentInfo;
import com.velox.api.servermanager.DataTypeManager;
import com.velox.api.util.ServerException;
import com.velox.sapio.commons.collection.list.ListUtil;
import com.velox.sapio.commons.exemplar.context.ExemplarContext;
import com.velox.sapio.commons.exemplar.recordmodel.property.RecordModelPropertySetter;
import com.velox.sapio.commons.exemplar.recordmodel.record.RecordModel;
import com.velox.sapio.commons.exemplar.recordmodel.util.RecordModelUtil;
import com.velox.sapioutils.shared.utilities.FieldDefinitions;
import com.velox.sapioutils.shared.utilities.ListUtils;
import com.velox.training.TrainingManagerBase;
import org.apache.commons.collections4.MapUtils;
import org.apache.commons.lang3.StringUtils;

import java.rmi.RemoteException;
import java.util.*;
import java.util.stream.Collectors;

/**
 * This class contains a number of general purpose utility methods.
 *
 * @author Devasundaram.R
 * @author Gifan Thadathil
 */
public class TrainingManager extends TrainingManagerBase {

	private Map<String, DataTypeDefinition> fieldDefsMap = new HashMap<>();
	private Map<String, FieldDefinitions> fieldDefMapping = new HashMap<>();

	/**
	 * Sorts the TemplateExperimentInfo by version number descending order. Latest TemplateExperimentInfo will be in the 0th element after sort.
	 */
	private final Comparator<TemplateExperimentInfo> templateExperimentInfoComparator = (tempExpInfo1, tempExpInfo2) -> {
		if (tempExpInfo1 == null || tempExpInfo1.getTemplateVersion() == null) {
			return 1;//Push null to the end //PR-30229
		}
		if (tempExpInfo2 == null || tempExpInfo2.getTemplateVersion() == null) {
			return -1;//Push null to the end
		}
		return Integer.compare(tempExpInfo2.getTemplateVersion(), tempExpInfo1.getTemplateVersion());
	};


	public TrainingManager(ExemplarContext exemplarContext) {
		super(exemplarContext);
	}

	/**
	 * Removes leading and trailing whitespaces and then replaces spaces within the string with "_"
	 * @param str
	 * @return
	 */
	public String trimAndReplaceSpacesWithUnderscore(String str) {
		if (str == null) {
			return null;
		}
		return str.trim().replace(' ', '_');
	}

	/**
	 * Returns the records that was created the first from a set of records.</br>
	 * This is done by sorting the orders based on the RecordID. So only the record models that are stored will be considered
	 *
	 * @param relatedRecords
	 * @return
	 */
	public <T extends RecordModel> T getOldestRecord(Collection<T> relatedRecords) {
		if (ListUtils.isBlank(relatedRecords)) {
			return null;
		}
		if (relatedRecords.size() == 1) {
			return relatedRecords.iterator().next();
		}
		Map<Long, T> recIdToRecs = RecordModelUtil.mapRecordsByFieldValue(relatedRecords, "RecordId");
		recIdToRecs.remove(null);
		if (MapUtils.isEmpty(recIdToRecs)) {
			return null;
		}
		return recIdToRecs.get(Collections.min(ListUtils.removeNullValues(recIdToRecs.keySet())));
	}

	/**
	 * Returns the records that was created last from a set of records.</br>
	 * This is done by sorting the orders based on the RecordID. So only the record models that are stored will be considered
	 *
	 * @param relatedRecords
	 * @return
	 */
	public <T extends RecordModel> T getNewestRecord(Collection<T> relatedRecords) {
		if (ListUtils.isBlank(relatedRecords)) {
			return null;
		}
		if (relatedRecords.size() == 1) {
			return relatedRecords.iterator().next();
		}
		Map<Long, T> recIdToRecs = RecordModelUtil.mapRecordsByFieldValue(relatedRecords, "RecordId");
		recIdToRecs.remove(null);
		if (MapUtils.isEmpty(recIdToRecs)) {
			return null;
		}
		return recIdToRecs.get(Collections.max(ListUtils.removeNullValues(recIdToRecs.keySet())));
	}

	/**
	 * TODO Write documentation
	 * @return
	 */
	public RecordModelPropertySetter<Object> fieldsFromBackingDataRecord(){
		return rec -> {
			try {
				rec.setFields(rec.getDataRecord().getFields(user));
				return null;
			}
			catch (RemoteException e) {
				try {
					displayError(e);
				} catch (RemoteException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				return null;
			}
		};
	}

	public List<Long> getRecordIds(List<? extends RecordModel> recordModels) {
		return recordModels.stream().map(recordModel -> recordModel.getRecordId()).collect(Collectors.toList());
	}

	/**
	 *  Get the DataTypeDefinition by the extension Display Name for the given parentType
	 * @param parentType
	 * @param extnDisplayName
	 * @return
	 * @throws RemoteException
	 * @throws ServerException
	 */
	public DataTypeDefinition getExtensionDataTypeDefinition(String parentType, String extnDisplayName)
			throws RemoteException, ServerException {
		DataTypeDefinition dataTypeDefinition = null;
		DataTypeDefinition sampleDataTypeDef = this.dataTypeManager.getDataTypeDefinition(parentType);
		List<DataTypeDefinition> possibleExtensionTypes = sampleDataTypeDef.getExtensionChildTypes(this.user);
		if (possibleExtensionTypes.size() > 0) {
			for (DataTypeDefinition extDef : possibleExtensionTypes) {
				if (StringUtils.equals(extnDisplayName, extDef.getDisplayName(user))) {
					dataTypeDefinition = extDef;
					break;
				}
			}
		}

		return dataTypeDefinition;
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

	/**
	 * Given a list of record models, will return only the record models that are new.</br>
	 * This method uses dataRecordManager.isNew so the record models are expected to be stored already so that they have a corresponding datarecord.
	 * Returns empty list if the passed list is null.
	 * Ignores if any one of the recordmodel in the passed collection is null.
	 * @param recordModelsList
	 * @return List of new record models.
	 */
	public final <T extends RecordModel> List<T> getNewRecordModels(List<T> recordModelsList) {
		List<T> newModels = new ArrayList<>();
		if (!ListUtils.isBlank(recordModelsList)) {
			newModels = ListUtils.removeNullValues(recordModelsList.stream().map(recModel -> {
				try {
					if (recModel.isNew() || (recModel.getDataRecord() != null && recModel.getDataRecord().isNew())) {
						return recModel;
					}
				}
				catch (RemoteException e) {
					logError("", e);
					return null;
				}
				return null;
			}).collect(Collectors.toList()));
		}
		return newModels;
	}

	/**
	 * By default, this method is used to return the field definitions for a data type containing only the <b>non-system, editable and visible</b> fields.
	 *
	 *
	 * @param datatypeName
	 * @param fieldsToExclude List of field names to be excluded from display. Could be {@code null}
	 * @param fieldsToInclude List of system/non-editable/invisible fields that should be displayed and editable. Could be {@code null}.
	 * @return {@link DataFieldDefinitions} with relevant fields that could be used for form/table entry dialog
	 * @throws Throwable
	 */
	public TemporaryDataType getDefinitonToAddRecord(String datatypeName, List<String> fieldsToExclude, List<String> fieldsToInclude)
			throws Throwable {

		TemporaryDataType dataTypeDef = dataTypeManager.getDataTypeDefinition(datatypeName).getTemporaryDataType(user);
		if (dataTypeDef == null) {
			return null;
		}

		for (VeloxFieldDefinition<?> field : dataTypeDef.getVeloxFieldDefinitionList()) {

			if (fieldsToExclude != null && fieldsToExclude.contains(field.getDataFieldName())) {
				dataTypeDef.removeVeloxFieldDefinition(field.getDataFieldName());
				continue;
			}

			// Remove non-editable, invisible and system fields fields
			if (!ListUtil.isBlank(fieldsToInclude) && fieldsToInclude.contains(field.getDataFieldName())) {
				field.setVisible(true);
				continue;
			}

			dataTypeDef.removeVeloxFieldDefinition(field.getDataFieldName());
		}

		return dataTypeDef;
	}

	public VeloxFieldDefinition<?> getVeloxFieldDefinition(String dataType, String fieldName)
			throws RemoteException, ServerException {
		if (!fieldDefsMap.containsKey(dataType)) {
			if (!getInstance(DataTypeManager.class).getDataTypeNameList().contains(dataType)) {
				// Unfortunately this is the best we can do, since throwing any type of
				// exception will potentially
				// break all backwards compatibility
				throw new ServerException("Data type '" + dataType + "' is not defined in the system.");
			}

			fieldDefsMap.put(dataType, getInstance(DataTypeManager.class).getDataTypeDefinition(dataType));
		}
		return fieldDefsMap.get(dataType).getVeloxFieldDefinition(fieldName, user);
	}

	public DataFieldDefinition getDataFieldDefinition(String dataType, String fieldName) throws RemoteException, ServerException {
		FieldDefinitions fieldDefs = getDefinitions(dataType);
		return (fieldDefs != null && fieldDefs.isFieldExist(fieldName)) ? fieldDefs.getDefinition(fieldName) : null;
	}

	public FieldDefinitions getDefinitions(String dataType) throws RemoteException, ServerException {
		if (!fieldDefMapping.containsKey(dataType)) {
			fieldDefMapping.put(dataType, new FieldDefinitions(dataType, exemplarContext));
		}
		return fieldDefMapping.get(dataType);
	}
	

}
