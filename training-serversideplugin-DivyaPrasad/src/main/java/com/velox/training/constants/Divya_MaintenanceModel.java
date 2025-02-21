package com.velox.training.constants;

import com.velox.sapio.commons.exemplar.recordmodel.annotation.ExemplarDataTypeModel;
import com.velox.sapio.commons.exemplar.recordmodel.record.AbstractRecordModelWrapper;
import com.velox.sapio.commons.exemplar.recordmodel.record.RecordModel;
import com.velox.util.time.DateRange;
/**
 * Automatically generated class for: Divya Prasad Maintenance Detail
 */
@ExemplarDataTypeModel(dataTypeName="Divya_Maintenance")
public class Divya_MaintenanceModel extends AbstractRecordModelWrapper {

	/**
	 * The name of the Data Type this class represents
	 */
	public static final String DATA_TYPE_NAME = "Divya_Maintenance";

	/**
	 * <b>Data Field Name</b>: Assigned<br/>
	 * <br/>
	 * <b>Display Name</b>: Assigned to<br/>
	 * <br/>
	 * <b>Description</b>: No Description
	 */
	public static final String ASSIGNED = "Assigned";

	/**
	 * <b>Data Field Name</b>: CreatedBy<br/>
	 * <br/>
	 * <b>Display Name</b>: Created By<br/>
	 * <br/>
	 * <b>Description</b>: The name of the user who created this record
	 */
	public static final String CREATED_BY = "CreatedBy";

	/**
	 * <b>Data Field Name</b>: DataRecordName<br/>
	 * <br/>
	 * <b>Display Name</b>: ID<br/>
	 * <br/>
	 * <b>Description</b>: The textual identifier or name for this data record.
	 */
	public static final String DATA_RECORD_NAME = "DataRecordName";

	/**
	 * <b>Data Field Name</b>: DateCreated<br/>
	 * <br/>
	 * <b>Display Name</b>: Date Created<br/>
	 * <br/>
	 * <b>Description</b>: The date that this Data Record was created in or added to the system.
	 */
	public static final String DATE_CREATED = "DateCreated";

	/**
	 * <b>Data Field Name</b>: MaintenanceCompletedAt<br/>
	 * <br/>
	 * <b>Display Name</b>: Maintenance Completed At<br/>
	 * <br/>
	 * <b>Description</b>: Maintenance Completed at
	 */
	public static final String MAINTENANCE_COMPLETED_AT = "MaintenanceCompletedAt";

	/**
	 * <b>Data Field Name</b>: MaintenanceStartedAt<br/>
	 * <br/>
	 * <b>Display Name</b>: Maintenance Started At<br/>
	 * <br/>
	 * <b>Description</b>: Maintenance Started at
	 */
	public static final String MAINTENANCE_STARTED_AT = "MaintenanceStartedAt";

	/**
	 * <b>Data Field Name</b>: RecordId<br/>
	 * <br/>
	 * <b>Display Name</b>: Record ID<br/>
	 * <br/>
	 * <b>Description</b>: The system-wide unique ID of this data record
	 */
	public static final String RECORD_ID = "RecordId";

	/**
	 * <b>Data Field Name</b>: VeloxLastModifiedBy<br/>
	 * <br/>
	 * <b>Display Name</b>: Last Modified By<br/>
	 * <br/>
	 * <b>Description</b>: The name of the user who last modified this record
	 */
	public static final String VELOX_LAST_MODIFIED_BY = "VeloxLastModifiedBy";

	/**
	 * <b>Data Field Name</b>: VeloxLastModifiedDate<br/>
	 * <br/>
	 * <b>Display Name</b>: Last Modified Date<br/>
	 * <br/>
	 * <b>Description</b>: The date that this Data Record was last modified in the system.
	 */
	public static final String VELOX_LAST_MODIFIED_DATE = "VeloxLastModifiedDate";

	protected Divya_MaintenanceModel(RecordModel backingModel) {
		super(backingModel);
	}
	
	/**
	 * Retrieves the value stored on the {@link #ASSIGNED} field via {@link #getField(String)}<br/>
	 * <br/>
	 * <b>Data Field Name</b>: Assigned<br/>
	 * <br/>
	 * <b>Display Name</b>: Assigned to<br/>
	 * <br/>
	 * <b>Description</b>: No Description
	 * 
	 * @return the value stored on the "Assigned" field
	 */
	public String getAssigned() {
		return getField(ASSIGNED);
	}
	
	/**
	 * Sets the value stored on the {@link #ASSIGNED} field via {@link #setField(String, Object)}<br/>
	 * <br/>
	 * <b>Data Field Name</b>: Assigned<br/>
	 * <br/>
	 * <b>Display Name</b>: Assigned to<br/>
	 * <br/>
	 * <b>Description</b>: No Description
	 * 
	 * @param value The value to set on the "Assigned" field
	 */
	public void setAssigned(String value) {
		setField(ASSIGNED, value);
	}
	
	/**
	 * Retrieves the value stored on the {@link #CREATED_BY} field via {@link #getField(String)}<br/>
	 * <br/>
	 * <b>Data Field Name</b>: CreatedBy<br/>
	 * <br/>
	 * <b>Display Name</b>: Created By<br/>
	 * <br/>
	 * <b>Description</b>: The name of the user who created this record
	 * 
	 * @return the value stored on the "CreatedBy" field
	 */
	public String getCreatedBy() {
		return getField(CREATED_BY);
	}
	
	/**
	 * Retrieves the value stored on the {@link #DATA_RECORD_NAME} field via {@link #getField(String)}<br/>
	 * <br/>
	 * <b>Data Field Name</b>: DataRecordName<br/>
	 * <br/>
	 * <b>Display Name</b>: ID<br/>
	 * <br/>
	 * <b>Description</b>: The textual identifier or name for this data record.
	 * 
	 * @return the value stored on the "DataRecordName" field
	 */
	public String getDataRecordName() {
		return getField(DATA_RECORD_NAME);
	}
	
	/**
	 * Retrieves the value stored on the {@link #DATE_CREATED} field via {@link #getField(String)}<br/>
	 * <br/>
	 * <b>Data Field Name</b>: DateCreated<br/>
	 * <br/>
	 * <b>Display Name</b>: Date Created<br/>
	 * <br/>
	 * <b>Description</b>: The date that this Data Record was created in or added to the system.
	 * 
	 * @return the value stored on the "DateCreated" field
	 */
	public Long getDateCreated() {
		return getField(DATE_CREATED);
	}
	
	/**
	 * Retrieves the value stored on the {@link #MAINTENANCE_COMPLETED_AT} field via {@link #getField(String)}<br/>
	 * <br/>
	 * <b>Data Field Name</b>: MaintenanceCompletedAt<br/>
	 * <br/>
	 * <b>Display Name</b>: Maintenance Completed At<br/>
	 * <br/>
	 * <b>Description</b>: Maintenance Completed at
	 * 
	 * @return the value stored on the "MaintenanceCompletedAt" field
	 */
	public Long getMaintenanceCompletedAt() {
		return getField(MAINTENANCE_COMPLETED_AT);
	}
	
	/**
	 * Sets the value stored on the {@link #MAINTENANCE_COMPLETED_AT} field via {@link #setField(String, Object)}<br/>
	 * <br/>
	 * <b>Data Field Name</b>: MaintenanceCompletedAt<br/>
	 * <br/>
	 * <b>Display Name</b>: Maintenance Completed At<br/>
	 * <br/>
	 * <b>Description</b>: Maintenance Completed at
	 * 
	 * @param value The value to set on the "MaintenanceCompletedAt" field
	 */
	public void setMaintenanceCompletedAt(Long value) {
		setField(MAINTENANCE_COMPLETED_AT, value);
	}
	
	/**
	 * Retrieves the value stored on the {@link #MAINTENANCE_STARTED_AT} field via {@link #getField(String)}<br/>
	 * <br/>
	 * <b>Data Field Name</b>: MaintenanceStartedAt<br/>
	 * <br/>
	 * <b>Display Name</b>: Maintenance Started At<br/>
	 * <br/>
	 * <b>Description</b>: Maintenance Started at
	 * 
	 * @return the value stored on the "MaintenanceStartedAt" field
	 */
	public Long getMaintenanceStartedAt() {
		return getField(MAINTENANCE_STARTED_AT);
	}
	
	/**
	 * Sets the value stored on the {@link #MAINTENANCE_STARTED_AT} field via {@link #setField(String, Object)}<br/>
	 * <br/>
	 * <b>Data Field Name</b>: MaintenanceStartedAt<br/>
	 * <br/>
	 * <b>Display Name</b>: Maintenance Started At<br/>
	 * <br/>
	 * <b>Description</b>: Maintenance Started at
	 * 
	 * @param value The value to set on the "MaintenanceStartedAt" field
	 */
	public void setMaintenanceStartedAt(Long value) {
		setField(MAINTENANCE_STARTED_AT, value);
	}
	
	/**
	 * Retrieves the value stored on the {@link #VELOX_LAST_MODIFIED_BY} field via {@link #getField(String)}<br/>
	 * <br/>
	 * <b>Data Field Name</b>: VeloxLastModifiedBy<br/>
	 * <br/>
	 * <b>Display Name</b>: Last Modified By<br/>
	 * <br/>
	 * <b>Description</b>: The name of the user who last modified this record
	 * 
	 * @return the value stored on the "VeloxLastModifiedBy" field
	 */
	public String getVeloxLastModifiedBy() {
		return getField(VELOX_LAST_MODIFIED_BY);
	}
	
	/**
	 * Retrieves the value stored on the {@link #VELOX_LAST_MODIFIED_DATE} field via {@link #getField(String)}<br/>
	 * <br/>
	 * <b>Data Field Name</b>: VeloxLastModifiedDate<br/>
	 * <br/>
	 * <b>Display Name</b>: Last Modified Date<br/>
	 * <br/>
	 * <b>Description</b>: The date that this Data Record was last modified in the system.
	 * 
	 * @return the value stored on the "VeloxLastModifiedDate" field
	 */
	public Long getVeloxLastModifiedDate() {
		return getField(VELOX_LAST_MODIFIED_DATE);
	}
}