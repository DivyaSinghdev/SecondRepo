package com.velox.constantsforplugin;

public enum EnumClassofConstants {
	SAMPLE_TESTING_ENTRY_NAME("Sample Testing"),
	SAMPLE_TEMPERATURE_CONSTANT(20),
	SAMPLE_VOLUME_CONSTANT(15),
	TEMPLATE_NAME("Divya Prasad Singhdev Sample Testing Template"),
	SAMPLE_TESTING_ENTRY_OPTION_KEY("Blood Sample"),
	EXPERIMENT_NAME("Sample Experiment [Divya Prasad Singhdev]"),
	CURATED_SAMPLE_ENTRY_OPTION_KEY("Sample Filtered by Temperature and Volume"),
	CURATED_SAMPLE_ENTRY_NAME("Curated Sample"),
	PROCESS_NAME("DNA Extraction Process");
	
	
	private String stringValue;
	private int intValue;
	private boolean booleanValue;
	
	private EnumClassofConstants(String stringValue) {
		this.stringValue = stringValue;
	}
	
	private EnumClassofConstants(int intValue) {
		this.intValue = intValue;
	}
	
	private EnumClassofConstants(boolean booleanValue) {
		this.booleanValue = booleanValue;
	}
	
	public String getStringValue () {
		return stringValue;
	}
	
	public int getIntValue() {
		return intValue;
	}
	
	public boolean getBooleanValue () {
		return booleanValue;
	}

}
