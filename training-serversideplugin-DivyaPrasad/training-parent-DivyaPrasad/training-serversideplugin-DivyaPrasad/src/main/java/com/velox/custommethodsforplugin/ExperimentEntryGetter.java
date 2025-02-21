package com.velox.custommethodsforplugin;

import java.rmi.RemoteException;
import java.util.List;
import java.util.stream.Collectors;

import com.velox.api.eln.experimententry.ExperimentEntry;
import com.velox.api.eln.notebookexperiment.NotebookExperiment;
import com.velox.api.eln.notebookmanager.NotebookExperimentManager;
import com.velox.api.user.User;
import com.velox.api.util.ServerException;
import com.velox.sapio.commons.collection.list.ListUtil;

/**
 * A Singleton class created to get the required NotebookManager or NotebookExperiment based on the requirement!
 * 
 */
public class ExperimentEntryGetter <T> {


	private static ExperimentEntryGetter instance;
//	private ExperimentEntry experimentEntry;
//	private NotebookExperiment notebookExperiment;
//	private NotebookExperimentManager notebookExperimentManager;

	private ExperimentEntryGetter () {}

	public static <T> ExperimentEntryGetter <T> getInstance (){
		if (instance == null) {
			instance = new ExperimentEntryGetter<T>();
		}
		return instance;
	}
	
	/**
	 * This method used to fetch the TemplateID accessible by the user
	 * @param notebookExperimentManager, user, templateName
	 * @return TemplateID
	 * @throws ServerException 
	 * @throws RemoteException 
	 */
	public void userExperimentEntry(NotebookExperimentManager notebookManagerParam, User userParam, String templateNameParam) throws RemoteException, ServerException {
		List<Long> TemplateID = notebookManagerParam.getActiveTemplateExperimentInfo(userParam).stream()
		.filter(record -> templateNameParam.equalsIgnoreCase(record.getTemplateName()))
		.map(record -> record.getTemplateId())
		.sorted()
		.collect(Collectors.toList());
		
		if(ListUtil.isBlank(TemplateID)) {
			
		}
	}
}