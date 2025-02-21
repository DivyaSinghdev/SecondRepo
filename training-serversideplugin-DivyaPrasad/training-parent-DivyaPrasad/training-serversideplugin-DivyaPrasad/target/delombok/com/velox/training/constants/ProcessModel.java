package com.velox.training.constants;

import com.velox.sapio.commons.exemplar.recordmodel.annotation.ExemplarDataTypeModel;
import com.velox.sapio.commons.exemplar.recordmodel.record.AbstractRecordModelWrapper;
import com.velox.sapio.commons.exemplar.recordmodel.record.RecordModel;
import com.velox.util.time.DateRange;
/**
 * Automatically generated class for: Process
 */
@ExemplarDataTypeModel(dataTypeName="Process")
public class ProcessModel extends AbstractRecordModelWrapper {

	/**
	 * The name of the Data Type this class represents
	 */
	public static final String DATA_TYPE_NAME = "Process";

	/**
	 * <b>Data Field Name</b>: AssignedGroups<br/>
	 * <br/>
	 * <b>Display Name</b>: Assigned Groups<br/>
	 * <br/>
	 * <b>Description</b>: No Description
	 */
	public static final String ASSIGNED_GROUPS = "AssignedGroups";

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
	 * <b>Data Field Name</b>: DisplayName<br/>
	 * <br/>
	 * <b>Display Name</b>: Display Name<br/>
	 * <br/>
	 * <b>Description</b>: An additional display name for the Process for use in displaying in other interfaces such as a client-facing portal.
	 */
	public static final String DISPLAY_NAME = "DisplayName";

	/**
	 * <b>Data Field Name</b>: IsActive<br/>
	 * <br/>
	 * <b>Display Name</b>: Is Active?<br/>
	 * <br/>
	 * <b>Description</b>: Processes that are not marked as active will get ignored by process tracking features.
	 */
	public static final String IS_ACTIVE = "IsActive";

	/**
	 * <b>Data Field Name</b>: IsCustom<br/>
	 * <br/>
	 * <b>Display Name</b>: Is Custom<br/>
	 * <br/>
	 * <b>Description</b>: If true, this process was built for process queue items.
	 */
	public static final String IS_CUSTOM = "IsCustom";

	/**
	 * <b>Data Field Name</b>: ProcessDescription<br/>
	 * <br/>
	 * <b>Display Name</b>: Process Description<br/>
	 * <br/>
	 * <b>Description</b>: Description of the process as a whole.
	 */
	public static final String PROCESS_DESCRIPTION = "ProcessDescription";

	/**
	 * <b>Data Field Name</b>: ProcessName<br/>
	 * <br/>
	 * <b>Display Name</b>: Process Name<br/>
	 * <br/>
	 * <b>Description</b>: The primary identifier for the Process. This must be unique across the system.
	 */
	public static final String PROCESS_NAME = "ProcessName";

	/**
	 * <b>Data Field Name</b>: ProcessType<br/>
	 * <br/>
	 * <b>Display Name</b>: Process Type<br/>
	 * <br/>
	 * <b>Description</b>: <p>This is meant to be used by request creation features. During request creation, additional request-level information may need to be specified that is only applicable for certain types of processes.</p>
<p>For example in Illumina sequencing, there are built in features of Exemplar that leverage Multiplex Instructions or Run Type. These are fields on the Illumina Next-Gen Config data type (system name is IlluminaNextGenConfig).</p>
<p>Config records like this are to be added as children of the Request. They are created based on the process type. Typically, the value specified for the process type will have it's hyphens and spaces removed and then are affixed with "Config" to identify the corresponding config data type. For example, a process type of "Illumina Next-Gen" corresponds to the "IlluminaNextGenConfig" data type.</p>
	 */
	public static final String PROCESS_TYPE = "ProcessType";

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

	protected ProcessModel(RecordModel backingModel) {
		super(backingModel);
	}
	
	/**
	 * Retrieves the value stored on the {@link #ASSIGNED_GROUPS} field via {@link #getField(String)}<br/>
	 * <br/>
	 * <b>Data Field Name</b>: AssignedGroups<br/>
	 * <br/>
	 * <b>Display Name</b>: Assigned Groups<br/>
	 * <br/>
	 * <b>Description</b>: No Description
	 * 
	 * @return the value stored on the "AssignedGroups" field
	 */
	public String getAssignedGroups() {
		return getField(ASSIGNED_GROUPS);
	}
	
	/**
	 * Sets the value stored on the {@link #ASSIGNED_GROUPS} field via {@link #setField(String, Object)}<br/>
	 * <br/>
	 * <b>Data Field Name</b>: AssignedGroups<br/>
	 * <br/>
	 * <b>Display Name</b>: Assigned Groups<br/>
	 * <br/>
	 * <b>Description</b>: No Description
	 * 
	 * @param value The value to set on the "AssignedGroups" field
	 */
	public void setAssignedGroups(String value) {
		setField(ASSIGNED_GROUPS, value);
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
	 * Retrieves the value stored on the {@link #DISPLAY_NAME} field via {@link #getField(String)}<br/>
	 * <br/>
	 * <b>Data Field Name</b>: DisplayName<br/>
	 * <br/>
	 * <b>Display Name</b>: Display Name<br/>
	 * <br/>
	 * <b>Description</b>: An additional display name for the Process for use in displaying in other interfaces such as a client-facing portal.
	 * 
	 * @return the value stored on the "DisplayName" field
	 */
	public String getDisplayName() {
		return getField(DISPLAY_NAME);
	}
	
	/**
	 * Sets the value stored on the {@link #DISPLAY_NAME} field via {@link #setField(String, Object)}<br/>
	 * <br/>
	 * <b>Data Field Name</b>: DisplayName<br/>
	 * <br/>
	 * <b>Display Name</b>: Display Name<br/>
	 * <br/>
	 * <b>Description</b>: An additional display name for the Process for use in displaying in other interfaces such as a client-facing portal.
	 * 
	 * @param value The value to set on the "DisplayName" field
	 */
	public void setDisplayName(String value) {
		setField(DISPLAY_NAME, value);
	}
	
	/**
	 * Retrieves the value stored on the {@link #IS_ACTIVE} field via {@link #getField(String)}<br/>
	 * <br/>
	 * <b>Data Field Name</b>: IsActive<br/>
	 * <br/>
	 * <b>Display Name</b>: Is Active?<br/>
	 * <br/>
	 * <b>Description</b>: Processes that are not marked as active will get ignored by process tracking features.
	 * 
	 * @return the value stored on the "IsActive" field
	 */
	public Boolean getIsActive() {
		return getField(IS_ACTIVE);
	}
	
	/**
	 * Sets the value stored on the {@link #IS_ACTIVE} field via {@link #setField(String, Object)}<br/>
	 * <br/>
	 * <b>Data Field Name</b>: IsActive<br/>
	 * <br/>
	 * <b>Display Name</b>: Is Active?<br/>
	 * <br/>
	 * <b>Description</b>: Processes that are not marked as active will get ignored by process tracking features.
	 * 
	 * @param value The value to set on the "IsActive" field
	 */
	public void setIsActive(Boolean value) {
		setField(IS_ACTIVE, value);
	}
	
	/**
	 * Retrieves the value stored on the {@link #IS_CUSTOM} field via {@link #getField(String)}<br/>
	 * <br/>
	 * <b>Data Field Name</b>: IsCustom<br/>
	 * <br/>
	 * <b>Display Name</b>: Is Custom<br/>
	 * <br/>
	 * <b>Description</b>: If true, this process was built for process queue items.
	 * 
	 * @return the value stored on the "IsCustom" field
	 */
	public Boolean getIsCustom() {
		return getField(IS_CUSTOM);
	}
	
	/**
	 * Sets the value stored on the {@link #IS_CUSTOM} field via {@link #setField(String, Object)}<br/>
	 * <br/>
	 * <b>Data Field Name</b>: IsCustom<br/>
	 * <br/>
	 * <b>Display Name</b>: Is Custom<br/>
	 * <br/>
	 * <b>Description</b>: If true, this process was built for process queue items.
	 * 
	 * @param value The value to set on the "IsCustom" field
	 */
	public void setIsCustom(Boolean value) {
		setField(IS_CUSTOM, value);
	}
	
	/**
	 * Retrieves the value stored on the {@link #PROCESS_DESCRIPTION} field via {@link #getField(String)}<br/>
	 * <br/>
	 * <b>Data Field Name</b>: ProcessDescription<br/>
	 * <br/>
	 * <b>Display Name</b>: Process Description<br/>
	 * <br/>
	 * <b>Description</b>: Description of the process as a whole.
	 * 
	 * @return the value stored on the "ProcessDescription" field
	 */
	public String getProcessDescription() {
		return getField(PROCESS_DESCRIPTION);
	}
	
	/**
	 * Sets the value stored on the {@link #PROCESS_DESCRIPTION} field via {@link #setField(String, Object)}<br/>
	 * <br/>
	 * <b>Data Field Name</b>: ProcessDescription<br/>
	 * <br/>
	 * <b>Display Name</b>: Process Description<br/>
	 * <br/>
	 * <b>Description</b>: Description of the process as a whole.
	 * 
	 * @param value The value to set on the "ProcessDescription" field
	 */
	public void setProcessDescription(String value) {
		setField(PROCESS_DESCRIPTION, value);
	}
	
	/**
	 * Retrieves the value stored on the {@link #PROCESS_NAME} field via {@link #getField(String)}<br/>
	 * <br/>
	 * <b>Data Field Name</b>: ProcessName<br/>
	 * <br/>
	 * <b>Display Name</b>: Process Name<br/>
	 * <br/>
	 * <b>Description</b>: The primary identifier for the Process. This must be unique across the system.
	 * 
	 * @return the value stored on the "ProcessName" field
	 */
	public String getProcessName() {
		return getField(PROCESS_NAME);
	}
	
	/**
	 * Sets the value stored on the {@link #PROCESS_NAME} field via {@link #setField(String, Object)}<br/>
	 * <br/>
	 * <b>Data Field Name</b>: ProcessName<br/>
	 * <br/>
	 * <b>Display Name</b>: Process Name<br/>
	 * <br/>
	 * <b>Description</b>: The primary identifier for the Process. This must be unique across the system.
	 * 
	 * @param value The value to set on the "ProcessName" field
	 */
	public void setProcessName(String value) {
		setField(PROCESS_NAME, value);
	}
	
	/**
	 * Retrieves the value stored on the {@link #PROCESS_TYPE} field via {@link #getField(String)}<br/>
	 * <br/>
	 * <b>Data Field Name</b>: ProcessType<br/>
	 * <br/>
	 * <b>Display Name</b>: Process Type<br/>
	 * <br/>
	 * <b>Description</b>: <p>This is meant to be used by request creation features. During request creation, additional request-level information may need to be specified that is only applicable for certain types of processes.</p>
<p>For example in Illumina sequencing, there are built in features of Exemplar that leverage Multiplex Instructions or Run Type. These are fields on the Illumina Next-Gen Config data type (system name is IlluminaNextGenConfig).</p>
<p>Config records like this are to be added as children of the Request. They are created based on the process type. Typically, the value specified for the process type will have it's hyphens and spaces removed and then are affixed with "Config" to identify the corresponding config data type. For example, a process type of "Illumina Next-Gen" corresponds to the "IlluminaNextGenConfig" data type.</p>
	 * 
	 * @return the value stored on the "ProcessType" field
	 */
	public String getProcessType() {
		return getField(PROCESS_TYPE);
	}
	
	/**
	 * Sets the value stored on the {@link #PROCESS_TYPE} field via {@link #setField(String, Object)}<br/>
	 * <br/>
	 * <b>Data Field Name</b>: ProcessType<br/>
	 * <br/>
	 * <b>Display Name</b>: Process Type<br/>
	 * <br/>
	 * <b>Description</b>: <p>This is meant to be used by request creation features. During request creation, additional request-level information may need to be specified that is only applicable for certain types of processes.</p>
<p>For example in Illumina sequencing, there are built in features of Exemplar that leverage Multiplex Instructions or Run Type. These are fields on the Illumina Next-Gen Config data type (system name is IlluminaNextGenConfig).</p>
<p>Config records like this are to be added as children of the Request. They are created based on the process type. Typically, the value specified for the process type will have it's hyphens and spaces removed and then are affixed with "Config" to identify the corresponding config data type. For example, a process type of "Illumina Next-Gen" corresponds to the "IlluminaNextGenConfig" data type.</p>
	 * 
	 * @param value The value to set on the "ProcessType" field
	 */
	public void setProcessType(String value) {
		setField(PROCESS_TYPE, value);
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