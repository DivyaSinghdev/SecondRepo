package com.velox.SampleExperimentation.plugin;


import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.velox.api.datarecord.DataRecord;
import com.velox.api.eln.experimententry.ExperimentEntry;
import com.velox.api.eln.experimententry.ExperimentTableEntry;
import com.velox.api.eln.notebookexperiment.NotebookExperiment;
import com.velox.api.eln.notebookexperiment.TemplateExperiment;
import com.velox.api.eln.notebookmanager.NotebookExperimentManager;
import com.velox.api.plugin.PluginResult;
import com.velox.api.plugin.directive.NotebookExperimentDirective;
import com.velox.api.plugin.invocation.TableToolbarPlugin;
import com.velox.api.plugin.invocation.context.OnTableToolbarContext;
import com.velox.api.plugin.invocation.context.TableToolbarContext;
import com.velox.api.util.PopupType;
import com.velox.api.util.ServerException;
import com.velox.constantsforplugin.EnumClassofConstants;
import com.velox.sapio.commons.exemplar.plugin.veloxplugin.ExemplarVeloxServerPlugin;
import com.velox.sapio.commons.exemplar.recordmodel.util.RecordModelUtil;
import com.velox.sapioutils.shared.utilities.ListUtils;
import com.velox.training.constants.SampleModel;

/**
 * This class has been defined to launch an experiment only for Blood and DNA samples!
 * 
 * @author Divya Prasad Singhdev
 */

public class SampleExperimentPlugin extends ExemplarVeloxServerPlugin<TableToolbarContext> implements TableToolbarPlugin {

	@Override
	public String getLine1Text() {
		return "Start";
	}

	@Override
	public String getLine2Text() {
		return "Experiment";
	}

	@Override
	public byte[] getIcon() {
		return getIcon("Experiment.svg");
	}

	@Override
	public String getDescription() {
		return "To Launch an Experiment for selected samples!";
	}
	@Override
	public boolean onTableToolbar(OnTableToolbarContext ctx) throws Throwable {
		return SampleModel.DATA_TYPE_NAME.equalsIgnoreCase(ctx.getDataTypeName());
	}
//	@Override
//	protected boolean shouldRun(TableToolbarContext ctx) throws Throwable {
//		//use the constants first!
//		return SampleModel.DATA_TYPE_NAME.equalsIgnoreCase(ctx.getDataTypeName());
//	}

	@Override
	protected PluginResult run(TableToolbarContext var1) throws Throwable {

		CharSequence bloodSmplType = "Blood";

		//Getting the sample records!
		//to get the DataRecord from the context
		List<DataRecord> dataRecordList = var1.getDataRecordList();

		//Error handling!
		if(ListUtils.isBlank(dataRecordList)) {
			clientCallback.displayPopup("Error message", "No records found!", PopupType.Error);
			return new PluginResult (false);
		}

		//Converting dataRecordList from DataRecord data type to RecordModel!
		List<SampleModel> rmlSample = instMan.addExistingRecordsOfType(dataRecordList, SampleModel.class);

		//filtering to check the type of the sample! and to select only blood samples!
		List <SampleModel> filteredSamples = new ArrayList<>();
		List <SampleModel> filteredWrongsamples = new ArrayList <>();

		for (SampleModel sample : rmlSample) {

			if (sample.getExemplarSampleType().contains(bloodSmplType)) {
				filteredSamples.add(sample);
			}else {
				filteredWrongsamples.add(sample);
			}
		}

		//error handling!
		if (ListUtils.isBlank(filteredSamples)) {
			clientCallback.displayPopup("Sample Experimentation Dialog", "No valid samples were found!", PopupType.Error);
			return new PluginResult(false);
		}else if (!ListUtils.isBlank(filteredWrongsamples)) {
			clientCallback.displayPopup("Sample Experimentation Dialog", "Invalid Samples" + filteredWrongsamples + "were excluded!", PopupType.Warning);
		}

		//getting experiment informations!
		NotebookExperimentManager notebookManager = dataMgmtServer.getNotebookExperimentManager(user);

		//sorting using stream to get the templateID
		List<Long> collectedTemplateID = notebookManager.getActiveTemplateExperimentInfo(user)
				.stream()
				.filter(record -> EnumClassofConstants.TEMPLATE_NAME.getStringValue().equalsIgnoreCase(record.getTemplateName()))
				.map(record -> record.getTemplateId())
				.sorted()
				.collect(Collectors.toList());

		//Error handling!
		if(ListUtils.isBlank(collectedTemplateID)) {
			clientCallback.displayPopup("Sample Experiment Creation", "No Template ID found for the user!", PopupType.Error);
			return new PluginResult(false);
		}

		//getting the templateExperiment!
		Long templateIDtoUse = collectedTemplateID.get(collectedTemplateID.size() - 1);

		TemplateExperiment templateExperiment = notebookManager.getTemplateExperiment(templateIDtoUse, user);

		//Error handling!
		if (templateExperiment == null) {
			clientCallback.displayPopup("Sample Experiment Creation!", "Unable to fetch any template for the user!", PopupType.Error);
			return new PluginResult(false);
		}

		//asking the user to select the location for the experiment!
		DataRecord locationExperiment = clientCallback.showElnHierarchyDialog("Select a location to create the Experiment", null, user);

		//Error handling!
		if (locationExperiment == null ) {
			clientCallback.displayPopup("Sample Experiment Creation", "User did not select any location. So the experiment will be created in 'Default' location!", PopupType.Warning);
			return new PluginResult(false);
		}

		//creating the notebook!
		NotebookExperiment notebookExperiment = notebookManager.createNotebookExperiment(EnumClassofConstants.EXPERIMENT_NAME.getStringValue(),templateExperiment, locationExperiment, user);

		//getting the entryOption key
		List<ExperimentEntry> experimentEntryList = notebookExperiment.getExperimentEntryList(user);

		//Error handling
		if(ListUtils.isBlank(experimentEntryList)) {
			clientCallback.displayPopup("Sample Experiment", "Unable to find any entry option created for the user!", PopupType.Error);
			return new PluginResult(false);
		}

		ExperimentEntry experimentEntry = experimentEntryList.stream()
				.filter(entryOption -> {
					try {
						return (notebookExperiment.getEntryOptions(entryOption, user).containsKey(EnumClassofConstants.SAMPLE_TESTING_ENTRY_OPTION_KEY.getStringValue()));
					}catch (RemoteException | ServerException e) {
						e.getLocalizedMessage();
					}
					return false;
				}).findFirst().orElse(null);

		//Error handling!
		if (experimentEntry == null) {
			clientCallback.displayError("no experiment entryOptions found!");
			return new PluginResult(false);
		}

		notebookExperiment.addRecordsToTableEntry((ExperimentTableEntry) experimentEntry, RecordModelUtil.getDataRecordList(filteredSamples), user);

		//to update the user with a popUP!
		clientCallback.displayPopup("Sample Experiment", "Experiment created successfully for sample " , PopupType.Success);

		//storing the made changes!
		transMan.storeChanges();
		experimentManager.storeNotebookExperimentAndRecords(notebookExperiment, "Experiment got created!", clientCallback.getClientCallbackRMI(), user);
		return new PluginResult(true, new NotebookExperimentDirective(notebookExperiment));
	}
}