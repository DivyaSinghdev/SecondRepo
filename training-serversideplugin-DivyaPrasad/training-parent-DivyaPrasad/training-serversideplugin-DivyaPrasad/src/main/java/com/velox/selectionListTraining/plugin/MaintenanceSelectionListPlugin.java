package com.velox.selectionListTraining.plugin;


import java.util.Arrays;
import java.util.Collection;


import java.util.List;
import java.util.stream.Collectors;



import com.velox.api.datarecord.DataRecord;
import com.velox.api.plugin.PluginResult;
import com.velox.api.plugin.invocation.SelectionListPlugin;
import com.velox.api.plugin.invocation.context.SelectionListContext;
import com.velox.sapio.commons.exemplar.plugin.veloxplugin.ExemplarVeloxServerPlugin;
import com.velox.sapio.commons.exemplar.recordmodel.record.RecordModel;
import com.velox.sapio.commons.exemplar.recordmodel.relationship.Children;
import com.velox.sapio.commons.exemplar.recordmodel.relationship.Relationships;
import com.velox.sapio.commons.exemplar.recordmodel.util.RecordModelUtil;
import com.velox.training.constants.Divya_LabDataTypeModel;
import com.velox.training.constants.Divya_MaintenanceModel;
import com.velox.training.constants.Divya_TechnicianModel;


public class MaintenanceSelectionListPlugin extends ExemplarVeloxServerPlugin<SelectionListContext> implements SelectionListPlugin {
	/**
	 * This class provides the Technician names in selection list for Maintenance Data type based!
	 * Wrapper classes used: Divya_MaintenanceModel, Divya_TechnicianModel, Divya_LabrecordModel.
	 */
	
	
	@Override
	protected boolean shouldRun(SelectionListContext arg0) throws Throwable {
		// to make sure the plugin runs when this condition satisfies.	
		return arg0.getDataTypeName().equalsIgnoreCase(Divya_MaintenanceModel.DATA_TYPE_NAME)
				&& arg0.getDataFieldName().equalsIgnoreCase(Divya_MaintenanceModel.ASSIGNED);
	}
	
	@Override
	protected PluginResult run(SelectionListContext arg0) throws Throwable {
		
		/*
		 * the ancestors of Maintenance data type are Lab records!
		 * all the records from the Technician table will be fetched which are child of lab records!
		 * then they will be linked together to fetch the values into Maintenance Data type.
		 */
		
		//user selected this
		//we will set the value
		DataRecord dataRecordMaintenance = arg0.getDataRecord();
		Divya_MaintenanceModel rmlMaintenance = instMan.addExistingRecordOfType(dataRecordMaintenance, Divya_MaintenanceModel.class);
		
		//loading the ancestors first
		ancestorMan.loadAncestors(Arrays.asList(rmlMaintenance), Divya_LabDataTypeModel.DATA_TYPE_NAME);
		
		
		Collection<RecordModel> ancestorMaintenance = ancestorMan.getKnownAncestors(rmlMaintenance, Divya_LabDataTypeModel.DATA_TYPE_NAME);
		
		//now load the child of this ancestor and then get it!
		relationshipMan.loadChildren(ancestorMaintenance, Divya_TechnicianModel.class);
		
		
		//List<Object> valueList = RecordModelUtil.getValueList(ancestorMaintenance, Divya_TechnicianModel.TECHNICIAN_NAME);
		List<Relationships<Divya_TechnicianModel>> relationshipChildTechnician = RecordModelUtil
				.getFromAll(ancestorMaintenance, Children.ofType(Divya_TechnicianModel.class));
		
		//relationshipChildTechnician variable filtered based on the Technician names and to have "Object" as Data type.
		List<Object> rmlChildTechnician = relationshipChildTechnician.stream()
				.flatMap(Collection::stream)
				.map(n -> n.getTechnicianName())
				.collect(Collectors.toList());
		

		return new PluginResult(true, rmlChildTechnician);
	}

}
