/**
 * 
 */
package com.velox.processmanagerplugin;

import java.util.Collections;
import java.util.List;

import com.velox.api.datarecord.DataRecord;
import com.velox.api.plugin.PluginResult;
import com.velox.api.plugin.invocation.NotebookExperimentEntryValidationPlugin;
import com.velox.api.plugin.invocation.context.NotebookExperimentEntryValidationContext;
import com.velox.api.util.PopupType;
import com.velox.constantsforplugin.EnumClassofConstants;
import com.velox.sapio.commons.collection.list.ListUtil;
import com.velox.sapio.commons.exemplar.plugin.veloxplugin.ExemplarVeloxServerPlugin;
import com.velox.sapio.commons.exemplar.process.ExemplarProcess;
import com.velox.sapio.commons.exemplar.process.impl.ExemplarProcessImpl;
import com.velox.sapio.commons.exemplar.process.impl.ProcessTrackedSampleRecordImpl;
import com.velox.sapio.commons.recordmodels.ngs.ProcessModel;
import com.velox.sapio.commons.recordmodels.ngs.SampleModel;

/**
 * This class is defined to fetch the values form curated blood sample and update it in dna experiment!
 * 
 * @author Divya Prasad Singhdev
 */
public class BloodDnaSampleExperimentValidation extends ExemplarVeloxServerPlugin<NotebookExperimentEntryValidationContext> implements NotebookExperimentEntryValidationPlugin {
	
	@Override
	protected boolean shouldRun(NotebookExperimentEntryValidationContext ctx) throws Throwable {
		//should run this validation plugin for entry option: "Sample Filtered by Temperature and Volume"
		return ( ctx.getNotebookExperiment().getEntryOptions(ctx.getExperimentEntry(), user).containsKey(EnumClassofConstants.CURATED_SAMPLE_ENTRY_OPTION_KEY.getStringValue()) );
	}
	
	@Override
	protected PluginResult run(NotebookExperimentEntryValidationContext ctx) throws Throwable {
		
		List<SampleModel> curatedSamplesList = instMan.addExistingRecordsOfType(
				ctx.getNotebookExperiment().getDataRecordsForExperimentEntry(
						EnumClassofConstants.CURATED_SAMPLE_ENTRY_NAME.getStringValue(), user),
				SampleModel.class);
		
		//Error Handling
		if(ListUtil.isBlank(curatedSamplesList)) {
			clientCallback.displayPopup("DNA Sample Testing", "Unable to get records for instanting DNA Extraction Process", PopupType.Error);
			return new PluginResult(false);
		}
		
		//fetching the data records for the process model
		DataRecord processDataRecord = dataRecordManager.queryDataRecords(
				ProcessModel.DATA_TYPE_NAME, ProcessModel.PROCESS_NAME,
				Collections.singletonList(EnumClassofConstants.PROCESS_NAME.getStringValue()), user).iterator().next();
		
		ExemplarProcessImpl processModel = ExemplarProcess.PROCESS.get(instMan.addExistingRecord(processDataRecord), exemplarContext );
		
		//Error Handling
		if (processModel == null) {
			clientCallback.displayPopup("DNA Sample Testing", "Unable to fetch Process Model", PopupType.Error);
			return new PluginResult(false);
		}
		
		ProcessTrackedSampleRecordImpl.SAMPLE.get(curatedSamplesList, exemplarContext).stream().forEachOrdered(t -> {
			try {
				t.addToProcess(processModel, processModel.getFirstStep(), null);
			} catch (Exception e) {
				e.printStackTrace();
			}
		});
		
		transMan.storeChanges();
		dataRecordManager.storeAndCommit("Updating DNA Sample Testing Process flow", clientCallback.getClientCallbackRMI(), user);
		
		return new PluginResult(true);
	}
}
