package com.velox.servicedetailtraining.plugin;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import com.velox.api.datarecord.DataRecord;
import com.velox.api.plugin.PluginResult;
import com.velox.api.plugin.invocation.TableToolbarPlugin;
import com.velox.api.plugin.invocation.context.OnTableToolbarContext;
import com.velox.api.plugin.invocation.context.TableToolbarContext;
import com.velox.api.util.PopupType;
import com.velox.sapio.commons.exemplar.plugin.veloxplugin.ExemplarVeloxServerPlugin;
import com.velox.sapio.commons.exemplar.recordmodel.relationship.Parents;
import com.velox.sapio.commons.exemplar.recordmodel.relationship.Relationships;
import com.velox.sapioutils.shared.utilities.ListUtils;
import com.velox.training.constants.Divya_EquipmentModel;
import com.velox.training.constants.Divya_Service_DetailModel;

public class ServiceDetailToolbarButton extends ExemplarVeloxServerPlugin<TableToolbarContext> implements TableToolbarPlugin{
	/**
	 * This class provides a Table toolbar button for Service Detail Data type based on the condition given in "onTableToolbar" method!
	 * Its purpose is to get the service detail information from the context and then check whether the service has been started for the selected record!
	 * If yes, then it will throw a message/warning and if not then it will start the service and give start date as the system's current date!
	 * 
	 * Wrapper classes used: Divya_Service_DetailModel, Divya_EquipmentModel
	 */

	@Override
	public String getLine1Text() {
		// TODO Auto-generated method stub
		return "Start";
	}

	@Override
	public String getLine2Text() {
		// TODO Auto-generated method stub
		return "Service_DivyaPrasad";
	}

	@Override
	public byte[] getIcon() {
		// TODO Auto-generated method stub
		return getIcon("ServiceDetails.svg");
	}

	@Override
	public String getDescription() {
		// TODO Auto-generated method stub
		return "Service detail of equipments";
	}


	@Override
	public boolean onTableToolbar(OnTableToolbarContext ctx) throws Throwable {
		// TODO Auto-generated method stub
		return Divya_Service_DetailModel.DATA_TYPE_NAME.equals(ctx.getDataTypeName()) ;
	}


	@Override
	protected PluginResult run(TableToolbarContext arg0) throws Throwable {
		
		Divya_EquipmentModel parentEquipmentModel = null;
		
		//to get the selected DataRecord from the context!
		
		List<DataRecord> dataRecordList = arg0.getDataRecordList();
		if(ListUtils.isBlank(dataRecordList)) {
			clientCallback.displayPopup("Error message", "Error occured", PopupType.Error);
			return new PluginResult (false);
		}
		//converting the data type from DataRecord to RecordModel
		List<Divya_Service_DetailModel> rmlServiceDetailModel = instMan.addExistingRecordsOfType
				(dataRecordList, Divya_Service_DetailModel.class);

		//fetching the parent data types
		relationshipMan.loadParents(rmlServiceDetailModel, Divya_EquipmentModel.class);
		
		
		for (Divya_Service_DetailModel service: rmlServiceDetailModel) {
			
			Relationships<Divya_EquipmentModel> relationships = service.get(Parents.ofType(Divya_EquipmentModel.class));
			
			Iterator<Divya_EquipmentModel> iterator = relationships.iterator();
			if (iterator.hasNext()) {
				parentEquipmentModel = iterator.next();
			}
			
			if(service.getServiceStartDate() != null) {
				displayWarning("Selected Service has already been started! Kindly check and select again");
				return new PluginResult(false);
			} else {
				//setting the vendor name from parent class (EquipmentModel)
				service.setVendorName(parentEquipmentModel.getVendorName());
				
				LocalDate currentSystemDate = LocalDate.now();
				DateTimeFormatter formatterDate = DateTimeFormatter.ofPattern("MMM dd,yyyy");
				
				String formattedDate = currentSystemDate.format(formatterDate);
				
				LocalDate parsedLocalDate = LocalDate.parse(formattedDate, formatterDate);				
				Date date = Date.from(parsedLocalDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
				
				long timeinMillis = date.getTime();
				
				service.setServiceStartDate(timeinMillis);
				parentEquipmentModel.setIsActive(false);
				
			}
			
		}
		
		transMan.storeChanges();
		dataRecordManager.storeAndCommit("Service Record update", clientCallback.getClientCallbackRMI(), rmlServiceDetailModel, user);
		
		
		return new PluginResult(true);
	}

}
