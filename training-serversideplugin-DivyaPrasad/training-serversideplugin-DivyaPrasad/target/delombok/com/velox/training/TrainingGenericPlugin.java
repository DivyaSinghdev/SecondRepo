/**
 * Copyright (C) 2005 - 2019 Sapio Sciences <support@sapiosciences.com>
 *
 * ====================================================================
 * This software is the property of Sapio Sciences.
 * ====================================================================
 */
package com.velox.training;

import com.velox.accessionservice.AccessionServiceBasicManager;
import com.velox.api.datafielddefinition.DataFieldDefinitions;
import com.velox.api.datarecord.DataRecord;
import com.velox.api.datarecord.IoError;
import com.velox.api.datarecord.NotFound;
import com.velox.api.eln.notebookmanager.NotebookExperimentManager;
import com.velox.api.plugin.PluginResult;
import com.velox.api.report.CustomReportManager;
import com.velox.api.servermanager.DataTypeManager;
import com.velox.api.util.ServerException;
import com.velox.sapio.commons.exemplar.definition.field.ExtensionFieldDefinitionHelper;
import com.velox.sapio.commons.exemplar.exception.ExemplarCancelException;
import com.velox.sapio.commons.exemplar.plugin.ExemplarGenericPlugin;
import com.velox.sapioutils.shared.exceptions.CancelException;
import com.velox.sapioutils.shared.utilities.ListUtils;
import com.velox.sapioutils.shared.utilities.StringUtil;
import com.velox.training.constants.TrainingConstants;
import com.velox.training.managers.TrainingManager;
import org.apache.commons.lang3.StringUtils;

import java.rmi.RemoteException;
import java.util.List;
import java.util.Map;

/**
 * A wrapper over {@link ExemplarGenericPlugin} that exposes additional context
 * variables to child classes and other utilities.
 *T
 * @author Deepak Rajendran
 * @author Gifan Thadathil
 */
public abstract class TrainingGenericPlugin extends ExemplarGenericPlugin {

	protected CustomReportManager reportMngr;
	protected DataTypeManager dataTypeManager;
	protected NotebookExperimentManager nbExpMgr;
	protected AccessionServiceBasicManager accessionMan;
	protected ExtensionFieldDefinitionHelper extHelper;
	protected TrainingManager okanaganManager;


	/**
	 * Displays a single selection dialog for a list of datarecords and datafield definitions, as well as message. Recursively ask for the selection until the user selected a single record. <br>
	 * Also format the message to include the error when the user made an error.
	 *
	 * @param records
	 *            List of data records to select.
	 * @param defs
	 *            definitions for these records.
	 * @param message
	 *            The selection dialog to prompt.
	 * @return The data record the user selected.
	 * @throws RemoteException
	 * @throws ServerException
	 * @throws CancelException
	 *             If the user clicks cancel, throw this exception.
	 * @throws NotFound
	 * @throws IoError
	 */
	protected DataRecord displaySingleSelectionDialog(List<DataRecord> records, DataFieldDefinitions defs, String message)
			throws RemoteException, ServerException, CancelException, NotFound, IoError {
		return displaySingleSelectionDialog(records, defs, message, "");
	}

	// TODO rewrite with the deprecated method alternative
	private DataRecord displaySingleSelectionDialog(List<DataRecord> records,
	                                                DataFieldDefinitions defs,
	                                                String message,
	                                                String modifiedMessage)
			throws ServerException, RemoteException, CancelException, NotFound, IoError {
		List<Map<String, Object>> fieldMaps = dataRecordManager.getFieldsForRecords(records, user);
		List<Map<String, Object>> selected = clientCallback.showDataRecordSelectionDialog(fieldMaps, defs, message + modifiedMessage, user);

		if (selected == null) {
			throw new CancelException();
		}
		if (selected.isEmpty()) {
			String modMessage = "<font color=\"red\">You must select one record to continue.</font>";
			return displaySingleSelectionDialog(records, defs, message, modMessage);
		}
		if (selected.size() > 1) {
			String modMessage = "<font color=\"red\">Only one record may be selected.</font>";
			return displaySingleSelectionDialog(records, defs, message, modMessage);
		}

		List<Object> recordIds = ListUtils.getValueList(selected, "RecordId");
		List<DataRecord> ret = dataRecordManager.queryDataRecords(defs.getDataTypeName(), "RecordId", recordIds, user);
		return ret.iterator().next();
	}

	@Override
	public void displayError(String error) throws RemoteException {
		try {
			logError(error);
			if (clientCallback != null) {
				clientCallback.displayError(StringUtil.formatDisplayMessage(error, TrainingConstants.MESSAGE_LINE_LIMIT));
			}
		}
		catch (ServerException e) {
			logError(e, e.getMessage());
		}
	}

	@Override
	public void displayError(Throwable e) throws RemoteException {
		try {
			logError(e, e.getMessage());
			if (clientCallback != null) {
				clientCallback.displayError(StringUtils.isBlank(e.getMessage()) ? "Error is undefined" :
				                            StringUtil.formatDisplayMessage(e.getMessage(), TrainingConstants.MESSAGE_LINE_LIMIT));
			}
		}
		catch (ServerException e2) {
			logError(e2, e2.getMessage());
		}
	}

	@Override
	public void displayError(String error, Throwable e) throws RemoteException {
		try {
			logError(error, e);
			if (clientCallback != null) {
				clientCallback.displayError(error + (StringUtils.isBlank(e.getMessage()) ? "" :
				                                     ((StringUtils.isNotBlank(error) ? error.endsWith(".") ? " " : ". " : "")) +
				                                     StringUtil.formatDisplayMessage(e.getMessage(),
				                                                                     TrainingConstants.MESSAGE_LINE_LIMIT)));
			}
		}
		catch (ServerException e2) {
			logError(e2, e2.getMessage());
		}
	}

	@Override
	public void displayWarning(String warning) throws RemoteException {
		try {
			logInfo(warning);
			if (clientCallback != null) {
				clientCallback.displayInfo(StringUtil.formatDisplayMessage(warning, TrainingConstants.MESSAGE_LINE_LIMIT));
			}
		}
		catch (ServerException e2) {
			logError(e2, e2.getMessage());
		}
	}

	@Override
	public void displayInfo(String message) throws RemoteException {
		try {
			logInfo(message);
			if (clientCallback != null) {
				clientCallback.displayInfo(StringUtil.formatDisplayMessage(message, TrainingConstants.MESSAGE_LINE_LIMIT));
			}
		}
		catch (ServerException e2) {
			logError(e2, e2.getMessage());
		}
	}

	/**
	 * Performs the following utilities:
	 * <ul>
	 * <li>Exposes a number of manager variables.</li>
	 * <li>Handles {@link ExemplarCancelException} cleanly.</li>
	 * </ul>
	 */
	@Override
	protected PluginResult run() throws Throwable {
		this.reportMngr = getInstance(CustomReportManager.class);
		this.dataTypeManager = getInstance(DataTypeManager.class);
		this.nbExpMgr = getInstance(NotebookExperimentManager.class);
		this.accessionMan = getInstance(AccessionServiceBasicManager.class);
		this.extHelper = getInstance(ExtensionFieldDefinitionHelper.class);
		this.okanaganManager = new TrainingManager(exemplarContext);


		try {
			return execute();
		}
		catch (ExemplarCancelException e) {
			return new PluginResult(false);
		}
	}

	/**
	 * Implements plugin logic.
	 */
	protected abstract PluginResult execute() throws Throwable;
}
