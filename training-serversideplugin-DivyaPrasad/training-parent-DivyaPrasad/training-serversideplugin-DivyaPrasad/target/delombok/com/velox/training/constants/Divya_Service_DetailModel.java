package com.velox.training.constants;

import com.velox.sapio.commons.exemplar.recordmodel.annotation.ExemplarDataTypeModel;
import com.velox.sapio.commons.exemplar.recordmodel.record.AbstractRecordModelWrapper;
import com.velox.sapio.commons.exemplar.recordmodel.record.RecordModel;
import com.velox.util.time.DateRange;
/**
 * Automatically generated class for: Divya Prasad Service Detail
 */
@ExemplarDataTypeModel(dataTypeName="Divya_Service_Detail")
public class Divya_Service_DetailModel extends AbstractRecordModelWrapper {

	/**
	 * The name of the Data Type this class represents
	 */
	public static final String DATA_TYPE_NAME = "Divya_Service_Detail";

	/**
	 * <b>Data Field Name</b>: CompleteService<br/>
	 * <br/>
	 * <b>Display Name</b>: Complete Service<br/>
	 * <br/>
	 * <b>Description</b>: No Description
	 */
	public static final String COMPLETE_SERVICE = "CompleteService";

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
	 * <b>Data Field Name</b>: IsServiceCompleted<br/>
	 * <br/>
	 * <b>Display Name</b>: Is Service Completed<br/>
	 * <br/>
	 * <b>Description</b>: No Description
	 */
	public static final String IS_SERVICE_COMPLETED = "IsServiceCompleted";

	/**
	 * <b>Data Field Name</b>: RecordId<br/>
	 * <br/>
	 * <b>Display Name</b>: Record ID<br/>
	 * <br/>
	 * <b>Description</b>: The system-wide unique ID of this data record
	 */
	public static final String RECORD_ID = "RecordId";

	/**
	 * <b>Data Field Name</b>: ServiceCompletedDate<br/>
	 * <br/>
	 * <b>Display Name</b>: Service Completed Date<br/>
	 * <br/>
	 * <b>Description</b>: Service completion date to be given
	 */
	public static final String SERVICE_COMPLETED_DATE = "ServiceCompletedDate";

	/**
	 * <b>Data Field Name</b>: ServiceRequestDate<br/>
	 * <br/>
	 * <b>Display Name</b>: Service Request Date<br/>
	 * <br/>
	 * <b>Description</b>: The service request date
	 */
	public static final String SERVICE_REQUEST_DATE = "ServiceRequestDate";

	/**
	 * <b>Data Field Name</b>: ServiceStartDate<br/>
	 * <br/>
	 * <b>Display Name</b>: Service Start Date<br/>
	 * <br/>
	 * <b>Description</b>: When the service started!
	 */
	public static final String SERVICE_START_DATE = "ServiceStartDate";

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

	/**
	 * <b>Data Field Name</b>: VendorName<br/>
	 * <br/>
	 * <b>Display Name</b>: Vendor Name<br/>
	 * <br/>
	 * <b>Description</b>: Vendor Name
	 */
	public static final String VENDOR_NAME = "VendorName";

	protected Divya_Service_DetailModel(RecordModel backingModel) {
		super(backingModel);
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
	 * Retrieves the value stored on the {@link #IS_SERVICE_COMPLETED} field via {@link #getField(String)}<br/>
	 * <br/>
	 * <b>Data Field Name</b>: IsServiceCompleted<br/>
	 * <br/>
	 * <b>Display Name</b>: Is Service Completed<br/>
	 * <br/>
	 * <b>Description</b>: No Description
	 * 
	 * @return the value stored on the "IsServiceCompleted" field
	 */
	public Boolean getIsServiceCompleted() {
		return getField(IS_SERVICE_COMPLETED);
	}
	
	/**
	 * Sets the value stored on the {@link #IS_SERVICE_COMPLETED} field via {@link #setField(String, Object)}<br/>
	 * <br/>
	 * <b>Data Field Name</b>: IsServiceCompleted<br/>
	 * <br/>
	 * <b>Display Name</b>: Is Service Completed<br/>
	 * <br/>
	 * <b>Description</b>: No Description
	 * 
	 * @param value The value to set on the "IsServiceCompleted" field
	 */
	public void setIsServiceCompleted(Boolean value) {
		setField(IS_SERVICE_COMPLETED, value);
	}
	
	/**
	 * Retrieves the value stored on the {@link #SERVICE_COMPLETED_DATE} field via {@link #getField(String)}<br/>
	 * <br/>
	 * <b>Data Field Name</b>: ServiceCompletedDate<br/>
	 * <br/>
	 * <b>Display Name</b>: Service Completed Date<br/>
	 * <br/>
	 * <b>Description</b>: Service completion date to be given
	 * 
	 * @return the value stored on the "ServiceCompletedDate" field
	 */
	public Long getServiceCompletedDate() {
		return getField(SERVICE_COMPLETED_DATE);
	}
	
	/**
	 * Sets the value stored on the {@link #SERVICE_COMPLETED_DATE} field via {@link #setField(String, Object)}<br/>
	 * <br/>
	 * <b>Data Field Name</b>: ServiceCompletedDate<br/>
	 * <br/>
	 * <b>Display Name</b>: Service Completed Date<br/>
	 * <br/>
	 * <b>Description</b>: Service completion date to be given
	 * 
	 * @param value The value to set on the "ServiceCompletedDate" field
	 */
	public void setServiceCompletedDate(Long value) {
		setField(SERVICE_COMPLETED_DATE, value);
	}
	
	/**
	 * Retrieves the value stored on the {@link #SERVICE_REQUEST_DATE} field via {@link #getField(String)}<br/>
	 * <br/>
	 * <b>Data Field Name</b>: ServiceRequestDate<br/>
	 * <br/>
	 * <b>Display Name</b>: Service Request Date<br/>
	 * <br/>
	 * <b>Description</b>: The service request date
	 * 
	 * @return the value stored on the "ServiceRequestDate" field
	 */
	public Long getServiceRequestDate() {
		return getField(SERVICE_REQUEST_DATE);
	}
	
	/**
	 * Sets the value stored on the {@link #SERVICE_REQUEST_DATE} field via {@link #setField(String, Object)}<br/>
	 * <br/>
	 * <b>Data Field Name</b>: ServiceRequestDate<br/>
	 * <br/>
	 * <b>Display Name</b>: Service Request Date<br/>
	 * <br/>
	 * <b>Description</b>: The service request date
	 * 
	 * @param value The value to set on the "ServiceRequestDate" field
	 */
	public void setServiceRequestDate(Long value) {
		setField(SERVICE_REQUEST_DATE, value);
	}
	
	/**
	 * Retrieves the value stored on the {@link #SERVICE_START_DATE} field via {@link #getField(String)}<br/>
	 * <br/>
	 * <b>Data Field Name</b>: ServiceStartDate<br/>
	 * <br/>
	 * <b>Display Name</b>: Service Start Date<br/>
	 * <br/>
	 * <b>Description</b>: When the service started!
	 * 
	 * @return the value stored on the "ServiceStartDate" field
	 */
	public Long getServiceStartDate() {
		return getField(SERVICE_START_DATE);
	}
	
	/**
	 * Sets the value stored on the {@link #SERVICE_START_DATE} field via {@link #setField(String, Object)}<br/>
	 * <br/>
	 * <b>Data Field Name</b>: ServiceStartDate<br/>
	 * <br/>
	 * <b>Display Name</b>: Service Start Date<br/>
	 * <br/>
	 * <b>Description</b>: When the service started!
	 * 
	 * @param value The value to set on the "ServiceStartDate" field
	 */
	public void setServiceStartDate(Long value) {
		setField(SERVICE_START_DATE, value);
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
	
	/**
	 * Retrieves the value stored on the {@link #VENDOR_NAME} field via {@link #getField(String)}<br/>
	 * <br/>
	 * <b>Data Field Name</b>: VendorName<br/>
	 * <br/>
	 * <b>Display Name</b>: Vendor Name<br/>
	 * <br/>
	 * <b>Description</b>: Vendor Name
	 * 
	 * @return the value stored on the "VendorName" field
	 */
	public String getVendorName() {
		return getField(VENDOR_NAME);
	}
	
	/**
	 * Sets the value stored on the {@link #VENDOR_NAME} field via {@link #setField(String, Object)}<br/>
	 * <br/>
	 * <b>Data Field Name</b>: VendorName<br/>
	 * <br/>
	 * <b>Display Name</b>: Vendor Name<br/>
	 * <br/>
	 * <b>Description</b>: Vendor Name
	 * 
	 * @param value The value to set on the "VendorName" field
	 */
	public void setVendorName(String value) {
		setField(VENDOR_NAME, value);
	}
}