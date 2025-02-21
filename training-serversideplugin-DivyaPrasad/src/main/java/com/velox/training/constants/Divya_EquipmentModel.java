package com.velox.training.constants;

import com.velox.sapio.commons.exemplar.recordmodel.annotation.ExemplarDataTypeModel;
import com.velox.sapio.commons.exemplar.recordmodel.record.AbstractRecordModelWrapper;
import com.velox.sapio.commons.exemplar.recordmodel.record.RecordModel;
import com.velox.util.time.DateRange;
/**
 * Automatically generated class for: Divya Prasad Equipment
 */
@ExemplarDataTypeModel(dataTypeName="Divya_Equipment")
public class Divya_EquipmentModel extends AbstractRecordModelWrapper {

	/**
	 * The name of the Data Type this class represents
	 */
	public static final String DATA_TYPE_NAME = "Divya_Equipment";

	/**
	 * <b>Data Field Name</b>: Barcode<br/>
	 * <br/>
	 * <b>Display Name</b>: Barcode<br/>
	 * <br/>
	 * <b>Description</b>: No Description
	 */
	public static final String BARCODE = "Barcode";

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
	 * <b>Data Field Name</b>: EquipmentName<br/>
	 * <br/>
	 * <b>Display Name</b>: Equipment Name<br/>
	 * <br/>
	 * <b>Description</b>: The equipment name
	 */
	public static final String EQUIPMENT_NAME = "EquipmentName";

	/**
	 * <b>Data Field Name</b>: IsActive<br/>
	 * <br/>
	 * <b>Display Name</b>: Is Active<br/>
	 * <br/>
	 * <b>Description</b>: No Description
	 */
	public static final String IS_ACTIVE = "IsActive";

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

	/**
	 * <b>Data Field Name</b>: VendorName<br/>
	 * <br/>
	 * <b>Display Name</b>: Vendor Name<br/>
	 * <br/>
	 * <b>Description</b>: Name of the vendor from whom the equipment is being used.
	 */
	public static final String VENDOR_NAME = "VendorName";

	protected Divya_EquipmentModel(RecordModel backingModel) {
		super(backingModel);
	}
	
	/**
	 * Retrieves the value stored on the {@link #BARCODE} field via {@link #getField(String)}<br/>
	 * <br/>
	 * <b>Data Field Name</b>: Barcode<br/>
	 * <br/>
	 * <b>Display Name</b>: Barcode<br/>
	 * <br/>
	 * <b>Description</b>: No Description
	 * 
	 * @return the value stored on the "Barcode" field
	 */
	public String getBarcode() {
		return getField(BARCODE);
	}
	
	/**
	 * Sets the value stored on the {@link #BARCODE} field via {@link #setField(String, Object)}<br/>
	 * <br/>
	 * <b>Data Field Name</b>: Barcode<br/>
	 * <br/>
	 * <b>Display Name</b>: Barcode<br/>
	 * <br/>
	 * <b>Description</b>: No Description
	 * 
	 * @param value The value to set on the "Barcode" field
	 */
	public void setBarcode(String value) {
		setField(BARCODE, value);
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
	 * Retrieves the value stored on the {@link #EQUIPMENT_NAME} field via {@link #getField(String)}<br/>
	 * <br/>
	 * <b>Data Field Name</b>: EquipmentName<br/>
	 * <br/>
	 * <b>Display Name</b>: Equipment Name<br/>
	 * <br/>
	 * <b>Description</b>: The equipment name
	 * 
	 * @return the value stored on the "EquipmentName" field
	 */
	public String getEquipmentName() {
		return getField(EQUIPMENT_NAME);
	}
	
	/**
	 * Sets the value stored on the {@link #EQUIPMENT_NAME} field via {@link #setField(String, Object)}<br/>
	 * <br/>
	 * <b>Data Field Name</b>: EquipmentName<br/>
	 * <br/>
	 * <b>Display Name</b>: Equipment Name<br/>
	 * <br/>
	 * <b>Description</b>: The equipment name
	 * 
	 * @param value The value to set on the "EquipmentName" field
	 */
	public void setEquipmentName(String value) {
		setField(EQUIPMENT_NAME, value);
	}
	
	/**
	 * Retrieves the value stored on the {@link #IS_ACTIVE} field via {@link #getField(String)}<br/>
	 * <br/>
	 * <b>Data Field Name</b>: IsActive<br/>
	 * <br/>
	 * <b>Display Name</b>: Is Active<br/>
	 * <br/>
	 * <b>Description</b>: No Description
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
	 * <b>Display Name</b>: Is Active<br/>
	 * <br/>
	 * <b>Description</b>: No Description
	 * 
	 * @param value The value to set on the "IsActive" field
	 */
	public void setIsActive(Boolean value) {
		setField(IS_ACTIVE, value);
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
	 * <b>Description</b>: Name of the vendor from whom the equipment is being used.
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
	 * <b>Description</b>: Name of the vendor from whom the equipment is being used.
	 * 
	 * @param value The value to set on the "VendorName" field
	 */
	public void setVendorName(String value) {
		setField(VENDOR_NAME, value);
	}
}