/**
 * Copyright (C) 2005 - 2019 Sapio Sciences <support@sapiosciences.com>
 *
 * ====================================================================
 * This software is the property of Sapio Sciences.
 * ====================================================================
 */
package com.velox.training;

import com.velox.accessionservice.AccessionServiceBasicManager;
import com.velox.api.eln.notebookmanager.NotebookExperimentManager;
import com.velox.api.report.CustomReportManager;
import com.velox.api.servermanager.DataFieldDefinitionManager;
import com.velox.api.servermanager.DataTypeManager;
import com.velox.api.util.ServerException;
import com.velox.sapio.commons.exemplar.context.ExemplarContext;
import com.velox.sapio.commons.exemplar.context.ManagerBase;
import com.velox.sapio.commons.exemplar.definition.field.ExtensionFieldDefinitionHelper;
import com.velox.sapio.commons.exemplar.recordmodel.ancestor.RecordModelExemplarAncestorManager;
import com.velox.sapio.commons.exemplar.recordmodel.main.RecordModelManager;
import com.velox.sapio.commons.exemplar.recordmodel.record.RecordModelInstanceManager;
import com.velox.sapio.commons.exemplar.recordmodel.relationship.RecordModelRelationshipManager;
import com.velox.sapio.commons.exemplar.recordmodel.transaction.RecordModelTransactionManager;
import com.velox.sapioutils.shared.utilities.StringUtil;
import com.velox.training.constants.TrainingConstants;

import java.rmi.RemoteException;

import org.apache.commons.lang3.StringUtils;

/**
 * This class acts as a wrapper over {@link com.velox.sapio.commons.exemplar.context.ManagerBase}
 * that exposes additional managers and other utilities. Classes extending this essentially
 * gain access to Exemplar context.
 *
 * @author Deepak Rajendran
 * @since 2019-01-22
 */
public abstract class TrainingManagerBase extends ManagerBase {

	protected RecordModelManager recMan;
	protected RecordModelInstanceManager instMan;
	protected RecordModelTransactionManager transMan;
	protected RecordModelRelationshipManager relationshipMan;
	protected RecordModelExemplarAncestorManager ancestorMan;

	protected DataFieldDefinitionManager defMan;
	protected CustomReportManager reportMngr;
	protected DataTypeManager dataTypeManager;
	protected NotebookExperimentManager nbExpMgr;
	protected AccessionServiceBasicManager accessionMan;
	protected ExtensionFieldDefinitionHelper extHelper;

	public TrainingManagerBase(ExemplarContext exemplarContext) {

		setExemplarContext(exemplarContext);
		this.recMan = getInstance(RecordModelManager.class);
		this.instMan = recMan.getInstanceManager();
		this.transMan = recMan.getTransactionManager();
		this.relationshipMan = recMan.getRelationshipManager();
		this.ancestorMan = recMan.getModelManager(RecordModelExemplarAncestorManager.class);

		this.defMan = getInstance(DataFieldDefinitionManager.class);
		this.reportMngr = getInstance(CustomReportManager.class);
		this.dataTypeManager = getInstance(DataTypeManager.class);
		this.nbExpMgr = getInstance(NotebookExperimentManager.class);
		this.accessionMan = getInstance(AccessionServiceBasicManager.class);
		this.extHelper = getInstance(ExtensionFieldDefinitionHelper.class);
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
}
