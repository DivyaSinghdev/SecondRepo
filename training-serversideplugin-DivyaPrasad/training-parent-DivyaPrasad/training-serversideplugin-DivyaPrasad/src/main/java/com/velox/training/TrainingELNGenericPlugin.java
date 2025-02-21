/**
 * Copyright (C) 2005 - 2019 Sapio Sciences <support@sapiosciences.com>
 *
 * ====================================================================
 * This software is the property of Sapio Sciences.
 * ====================================================================
 */
package com.velox.training;

import com.velox.accessionservice.AccessionServiceBasicManager;
import com.velox.api.eln.experimententry.ExperimentEntry;
import com.velox.api.eln.notebookmanager.NotebookExperimentManager;
import com.velox.api.plugin.PluginResult;
import com.velox.api.report.CustomReportManager;
import com.velox.api.servermanager.DataTypeManager;
import com.velox.api.util.ServerException;
import com.velox.training.constants.TrainingConstants;
import com.velox.sapio.commons.exemplar.definition.field.ExtensionFieldDefinitionHelper;
import com.velox.sapio.commons.exemplar.exception.ExemplarCancelException;
import com.velox.sapio.commons.exemplar.recordmodel.record.RecordModel;
import com.velox.sapioelnutils.plugin.DefaultELNGenericPlugin;
import com.velox.sapioutils.shared.utilities.StringUtil;
import com.velox.training.managers.TrainingExperimentManager;
import com.velox.training.managers.TrainingManager;
import org.apache.commons.lang3.StringUtils;

import java.rmi.RemoteException;

/**
 * A wrapper over {@link DefaultELNGenericPlugin} that exposes additional context
 * variables to child classes and other utilities.
 *
 * @author Gifan Thadathil
 */
public abstract class TrainingELNGenericPlugin extends DefaultELNGenericPlugin {

	protected CustomReportManager reportMngr;
	protected DataTypeManager dataTypeManager;
	protected NotebookExperimentManager nbExpMgr;
	protected AccessionServiceBasicManager accessionMan;
	protected ExtensionFieldDefinitionHelper extHelper;
	protected TrainingManager trainManager;
	protected TrainingExperimentManager trainExpManager;
	protected RecordModel expModel;
	
	/**
	 * This is the position user had dragged onto.
	 */
	protected Integer userDraggedPosition;
	/**
	 * This is the position we should add the entry.
	 */
	protected Integer nextEntryPosition;

	public abstract String getFeatureName();

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
	 *     <li>Exposes a number of manager variables.</li>
	 *     <li>Handles {@link ExemplarCancelException} cleanly.</li>
	 * </ul>
	 */
	@Override protected PluginResult run() throws Throwable {
		this.reportMngr = getInstance(CustomReportManager.class);
		this.dataTypeManager = getInstance(DataTypeManager.class);
		this.nbExpMgr = getInstance(NotebookExperimentManager.class);
		this.accessionMan = getInstance(AccessionServiceBasicManager.class);
		this.extHelper = getInstance(ExtensionFieldDefinitionHelper.class);
		this.trainManager = new TrainingManager(exemplarContext);
		this.trainExpManager = new TrainingExperimentManager(exemplarContext);
		
		if (notebookExperiment != null) {
			expModel = instMan.addExistingRecord(notebookExperiment.getExperimentDataRecord(user));
			if (super.experimentEntryPosition != null) {
				userDraggedPosition = super.experimentEntryPosition.getOrder();
				nextEntryPosition = userDraggedPosition;
			}
		} else if (experimentEntry != null) {
			userDraggedPosition = super.experimentEntry.getOrder();
			nextEntryPosition = userDraggedPosition+1;
		}

		try {
			return execute();
		}
		catch (ExemplarCancelException e) {
			return new PluginResult(false);
		}
	}

	/**
	 * Implements plugin logic.
	 * @throws Throwable
	 */
	protected abstract PluginResult execute() throws Throwable;

	/**
	 * TODO Document this.
	 *
	 * @param entry
	 * @param key
	 * @return
	 * @throws ServerException
	 * @throws RemoteException
	 */
	public boolean entryContainsOptionKey(ExperimentEntry entry, String key) throws ServerException, RemoteException {
		return notebookExperiment.getEntryOptions(entry, user).containsKey(key);
	}

	/**
	 * TODO Document
	 *
	 * @param entry
	 * @param key
	 * @return
	 * @throws ServerException
	 * @throws RemoteException
	 */
	public String getEntryOptionValue(ExperimentEntry entry, String key) throws ServerException, RemoteException {
		return notebookExperiment.getEntryOptions(entry, user).getOrDefault(key, null);
	}


}
