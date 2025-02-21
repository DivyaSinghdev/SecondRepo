package com.velox.training.plugin;

import java.rmi.RemoteException;

/**
 *  **  Class Information  **
 * This class has been defined to add equipment records for Data type: Divya_Eqiupment
 * 
 * **  Properties of this plugin  **
 * The changes made through this Plugin are not saved in database but rather in cache. Only if the user saves using default Save button, then the created records will be saved!
 * The created records will be numeric until the required name for the equipments records are not provided!
 * 
 * **  Default methods information  **
 * This class extends ExemplarVeloxServerPlugin<ActionMenuContext> and implements implements ActionMenuPlugin
 * Hence the default overridden methods that we are using in this class are:
 * getLine1Text, getLine2Text: Name of the button we are creating
 * getIcon: The icon for that button. It needs to be in .svg format
 * 
 * 
 * **  Parameters this class deals with  **
 * @param veloxIntegerFieldDefinition: provides the default and maximum number of records that can be created.
 * @param inputDialogCriteria: For creating the dialog box
 * @param showInputDialog: executes the dialog to show it to the user
 * @param newRecords: used to add new equipment records based on the number of records selected by the user
 * @param dataRecordList: used for table directive
 * 
 */


import java.util.List;

import com.velox.api.clientcallback.InputDialogCriteria;
import com.velox.api.datarecord.DataRecord;
import com.velox.api.datatype.fielddefinition.VeloxFieldDefinition;
import com.velox.api.datatype.fielddefinition.VeloxIntegerFieldDefinition;
import com.velox.api.plugin.PluginResult;
import com.velox.api.plugin.directive.DataRecordTableDirective;
import com.velox.api.plugin.invocation.ActionMenuPlugin;
import com.velox.api.plugin.invocation.context.ActionMenuContext;

import com.velox.api.util.InputDialogResult;
import com.velox.api.util.PopupType;
import com.velox.api.util.ServerException;
import com.velox.sapio.commons.exemplar.plugin.veloxplugin.ExemplarVeloxServerPlugin;
import com.velox.sapio.commons.exemplar.recordmodel.record.RecordModel;

import com.velox.sapio.commons.exemplar.recordmodel.util.RecordModelUtil;
import com.velox.training.constants.Divya_EquipmentModel;



public class MainAddEquipmentPlugin extends ExemplarVeloxServerPlugin<ActionMenuContext> implements ActionMenuPlugin {

	@Override
	public String getLine1Text() {

		return "Add";
	}

	@Override
	public String getLine2Text() {
		// TODO Auto-generated method stub
		return " Equipment_Divya Prasad";
	}

	@Override
	public byte[] getIcon() {
		return getIcon("AddEquipmentIcon.svg");
	}

	protected InputDialogResult userInputDialogBox () throws RemoteException, ServerException {
		//  **Creating the inputDialogBox**

		//veloxIntegerFieldDefinition: to create a integer based input with default value set to 1 and maximum value to 100
		VeloxIntegerFieldDefinition veloxIntegerFieldDefinition = VeloxFieldDefinition.integerFieldBuilder().defaultValue(1).build();

		//adding the title and message the dialog box should have to inform the user about its purpose
		InputDialogCriteria inputDialogCriteria = InputDialogCriteria.builder().title("Add Equipment Dialog").message("Enter the number of equipments to be added")
				.fieldDefinition(veloxIntegerFieldDefinition).build();

		//to show the dialog box to the user
		InputDialogResult showInputDialog = clientCallback.showInputDialog(inputDialogCriteria);

		//  **inputDialogBox created!**

		return showInputDialog;
	}

	protected boolean userInputCheck (Object valueNumparam) throws RemoteException, ServerException {

		if (valueNumparam == null) {
			clientCallback.displayPopup("Equipment Record Creation", 
					"No value has been selected for equipment records creation", 
					PopupType.Warning);
			return false;

		} else if ((int) valueNumparam < 1) {
			clientCallback.displayPopup("Equipment Record Dialog",
					"The entered value is less than minimum value (1) ",
					PopupType.Warning);
			return false;


		} else if ((int) valueNumparam > 100 ) {
			clientCallback.displayPopup("Equment Record Dialog",
					"The entered value is more than maximum value (100) ",
					PopupType.Warning);
			return false;

		}

		return true;

	}

	@Override
	protected PluginResult run(ActionMenuContext ctx) throws Throwable {

		Object valueNum = null;

		for (int userInputCount = 1; userInputCount <= 3; userInputCount++) {
			InputDialogResult userInputDialogBoxctx = userInputDialogBox();
			
			//when the entered value by the user is not valid as per the given conditions, the valueNum will be set to null, to handle the next iteration.
			valueNum = null;
			
			//When the dialog box is closed or cancelled
			if(userInputDialogBoxctx == null) {
				
				//keep it as an info for the client like: user cancelled the process!
				clientCallback.displayPopup("Equipment Record Dialog", 
						"Add equipment dialog has been closed! No equipment records created!", 
						PopupType.Warning);
			}else {
				//when the dialog box is not closed abruptly and some value has been given!
				valueNum = userInputDialogBoxctx.getValue();
				boolean userInputCheckOutput = userInputCheck(valueNum);

					if (userInputCount == 3) {
						return new PluginResult (false);
					}else if (userInputCheckOutput == false) {
						continue;
					}else if (userInputCheckOutput == true ) {
						break;
					}
			}
		}
		
		//Error handling
		/*
		 * since the valueNum is being reassigned as null for every iteration! and then handled at last based on any value provided!
		 *  we are handling the null pointer exception here! 
		 */
		if (valueNum == null) {
			clientCallback.displayPopup("Add Equpiment", "The add equipment dialog was closed! No records were created", PopupType.Info);
			return new PluginResult (false);
		}

		int valueInt = Integer.parseInt(valueNum.toString());
		//need to update to the wrapper class!!
		List<RecordModel> newRecords = instMan.addNewRecords(Divya_EquipmentModel.DATA_TYPE_NAME, valueInt);
		//saving the made changes only when after there are no errors!
		transMan.storeChanges();

		List<DataRecord> dataRecordList = RecordModelUtil.getDataRecordList(newRecords);

		DataRecordTableDirective tableDirective = new DataRecordTableDirective(dataRecordList);
		return new PluginResult (true, tableDirective);
	} 

}
