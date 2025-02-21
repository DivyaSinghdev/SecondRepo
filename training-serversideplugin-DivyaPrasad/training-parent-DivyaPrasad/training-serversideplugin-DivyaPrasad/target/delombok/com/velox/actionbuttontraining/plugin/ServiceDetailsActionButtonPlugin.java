package com.velox.actionbuttontraining.plugin;

import com.velox.api.datarecord.DataRecord;
import com.velox.api.plugin.PluginResult;
import com.velox.api.plugin.invocation.ActionDataFieldPlugin;
import com.velox.api.plugin.invocation.context.ActionDataFieldContext;
import com.velox.api.util.PopupType;
import com.velox.sapio.commons.exemplar.plugin.veloxplugin.ExemplarVeloxServerPlugin;
import com.velox.sapio.commons.exemplar.recordmodel.relationship.Parents;
import com.velox.sapio.commons.exemplar.recordmodel.relationship.Relationships;
import com.velox.training.constants.Divya_EquipmentModel;
import com.velox.training.constants.Divya_Service_DetailModel;



public class ServiceDetailsActionButtonPlugin extends ExemplarVeloxServerPlugin<ActionDataFieldContext> implements ActionDataFieldPlugin{
	/*
	 * This class has been defined to check, whether for a defined class, a service has been started yet and if it is completed!
	 * Based on the conditions it will update, the Service end date, and the "is service completed" button status!
	 */

	@Override
	protected boolean shouldRun(ActionDataFieldContext ctx) throws Throwable {

		// Condition defined for the configuration to run!
		return ctx.getDataTypeName().equalsIgnoreCase(Divya_Service_DetailModel.DATA_TYPE_NAME)
				&& ctx.getDataFieldName().equalsIgnoreCase(Divya_Service_DetailModel.COMPLETE_SERVICE);
	}

	@Override
	protected PluginResult run(ActionDataFieldContext ctx) throws Throwable {

		//getting the data record for the current context
		DataRecord ctxDataRecord = ctx.getDataRecord();

		//to get the records of Divya_Service_DetailModel
		Divya_Service_DetailModel servicedetailModel = instMan.addExistingRecordOfType(ctxDataRecord, Divya_Service_DetailModel.class);

		//loading the parent, Divya_EquipmetModel before getting it!
		relationshipMan.loadParents(servicedetailModel, Divya_EquipmentModel.class);

		//getting the parent!
		Relationships<Divya_EquipmentModel> serviceParent = servicedetailModel.get(Parents.ofType(Divya_EquipmentModel.class));

		//Error handling
		if(serviceParent.isEmpty() || serviceParent == null) {
			clientCallback.displayPopup("Service Completed", "Unable to fetch any value from " + Divya_EquipmentModel.DATA_TYPE_NAME, PopupType.Error);
			return new PluginResult(false);
		}

		Divya_EquipmentModel parentModel = serviceParent.iterator().next();

		//Error handling
		if (servicedetailModel.getServiceStartDate() == null) {
			clientCallback.displayPopup("Warning", "The service has not been started yet!", PopupType.Warning);
			return new PluginResult(false);
		}else if (servicedetailModel.getServiceCompletedDate()!= null) {
			clientCallback.displayPopup("Error", "The service has been completed already!", PopupType.Error);
			return new PluginResult(false);
		}else {
			Boolean userSelectionOutput = clientCallback.showYesNoDialog("Service Completed", "Are you sure to mark the service as 'Completed'?", true);
			//showYesNoDialog("Service Completed", "Are you sure to mark the Service as Completed?");
			//Error handling
			if (userSelectionOutput == null ) {
				//when handling null, do it as a stand-alone statement and don't include it with other conditions!
				clientCallback.displayPopup("Service Completed", "Service Completion is not marked as 'Completed'! The session has closed", PopupType.Warning);
				return new PluginResult(false);
				
			}else if (userSelectionOutput.equals(false) ) {
				clientCallback.displayPopup("Service Completed", "Service Completion has been cancelled!", PopupType.Info);
				return new PluginResult(false);
				
			}else {
				//taking system time in long data type!
				long timeMillis = System.currentTimeMillis();


				servicedetailModel.setServiceCompletedDate(timeMillis);
				servicedetailModel.setIsServiceCompleted(true);
				
				//updating the equipment record
				parentModel.setIsActive(true);

				transMan.storeChanges();
				dataRecordManager.storeAndCommit("Updated Service Details", clientCallback.getClientCallbackRMI(), user);
				
			}
		}


		// TODO Auto-generated method stub
		return new PluginResult(true);
	}


}
