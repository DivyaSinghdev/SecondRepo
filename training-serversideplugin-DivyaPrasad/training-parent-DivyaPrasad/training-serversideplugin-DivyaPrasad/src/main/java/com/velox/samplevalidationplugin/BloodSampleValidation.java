/**
 * 
 */
package com.velox.samplevalidationplugin;

import java.rmi.RemoteException;
import java.util.List;
import java.util.stream.Collectors;

import com.velox.api.eln.experimententry.ExperimentEntry;
import com.velox.api.eln.experimententry.ExperimentTableEntry;
import com.velox.api.eln.notebookexperiment.NotebookExperiment;
import com.velox.api.plugin.EnbPluginResult;
import com.velox.api.plugin.PluginResult;
import com.velox.api.plugin.invocation.NotebookExperimentEntryValidationPlugin;
import com.velox.api.plugin.invocation.context.NotebookExperimentEntryValidationContext;
import com.velox.api.util.PopupType;
import com.velox.api.util.ServerException;
import com.velox.constantsforplugin.EnumClassofConstants;
import com.velox.sapio.commons.collection.list.ListUtil;
import com.velox.sapio.commons.exemplar.plugin.veloxplugin.ExemplarVeloxServerPlugin;
import com.velox.sapio.commons.exemplar.recordmodel.util.RecordModelUtil;
import com.velox.training.constants.SampleModel;

/**
 * This class is defined to validate the blood sample based on the temperature and volume
 * 
 * @author Divya Prasad Singhdev
 */
public class BloodSampleValidation  extends ExemplarVeloxServerPlugin<NotebookExperimentEntryValidationContext> implements NotebookExperimentEntryValidationPlugin {
	
	@Override
	protected boolean shouldRun(NotebookExperimentEntryValidationContext ctx) throws Throwable {
		//Only when the entry option has the same value as it is given in Sample_Testing_Entry_option_key of Enum, this validation plugin can run!
		return (ctx.getNotebookExperiment().getEntryOptions(ctx.getExperimentEntry(), user).containsKey(EnumClassofConstants.SAMPLE_TESTING_ENTRY_OPTION_KEY.getStringValue()) );
	}
	
	@Override
	protected PluginResult run(NotebookExperimentEntryValidationContext context) throws Throwable {
		
		//fetching the entry list available in the current experiment context!		
				List<SampleModel> entrySampleTestingDetails = instMan.addExistingRecordsOfType(
						context.getNotebookExperiment().getDataRecordsForExperimentEntry(EnumClassofConstants.SAMPLE_TESTING_ENTRY_NAME.getStringValue(), user),
						SampleModel.class);
				
				//Error Handling
				if(ListUtil.isBlank(entrySampleTestingDetails)) {
					clientCallback.displayPopup("Sample Experiment", "Unable to fetch any entry value from " + EnumClassofConstants.SAMPLE_TESTING_ENTRY_NAME.getStringValue(), PopupType.Error);
					return new PluginResult(false);
				}
				
				//getting entries which area valid based on Temperature and Volume!
				List<SampleModel> validSamples = entrySampleTestingDetails.stream()
				.filter(record -> {
					boolean status = false;
					if (record.getVialTemperature() < EnumClassofConstants.SAMPLE_TEMPERATURE_CONSTANT.getIntValue() && record.getVolume() < EnumClassofConstants.SAMPLE_VOLUME_CONSTANT.getIntValue()) {
						return status = true;
					}
					return status; 
				})
				.collect(Collectors.toList());
				
				//Error Handling
				if (ListUtil.isBlank(validSamples)) {
					clientCallback.displayPopup("Sample Experiment", "No sample were found to be less than temperature(" + EnumClassofConstants.SAMPLE_TEMPERATURE_CONSTANT.getIntValue() +
							" and volume(" + EnumClassofConstants.SAMPLE_VOLUME_CONSTANT.getIntValue(), PopupType.Error);
					return new PluginResult(false);
				}
				
				//getting experiment information
				NotebookExperiment notebookExperiment = context.getNotebookExperiment();
				List<ExperimentEntry> experimentEntryList = notebookExperiment.getExperimentEntryList(user);
				
				if (ListUtil.isBlank(experimentEntryList)) {
					clientCallback.displayPopup("Experiment Entry", "Unable to fetch entry information", PopupType.Error);
				}
				
				ExperimentEntry experimentEntry = experimentEntryList.stream()
				.filter(entryOption -> {
					try {
						return ( notebookExperiment.getEntryOptions(entryOption, user).containsKey(EnumClassofConstants.CURATED_SAMPLE_ENTRY_OPTION_KEY.getStringValue() ) );
					}catch (RemoteException | ServerException e) {
						e.printStackTrace();
					}
					return false;
				}).findFirst().orElse(null);
				
				if (experimentEntry == null) {
					clientCallback.displayPopup("Experiment Entry", "Unable to get Curated Sample Entry!", PopupType.Error);
					return new PluginResult(false);
				}
				
				notebookExperiment.addRecordsToTableEntry((ExperimentTableEntry) experimentEntry, RecordModelUtil.getDataRecordList(validSamples), user);
				
				clientCallback.displayPopup("Experiment Entry", "Curated sample entry created!", PopupType.Success);
				
				transMan.storeChanges();
				experimentManager.storeNotebookExperimentAndRecords(notebookExperiment, "Curated Sample Entry Successfully Created!", clientCallback.getClientCallbackRMI(), user);
				
				//return new PluginResult(true);
				return new EnbPluginResult(true, experimentEntry);
	}

}
