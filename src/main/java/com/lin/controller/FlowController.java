package com.lin.controller;

import java.io.InputStream;
import java.util.Set;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.jbpm.api.ExecutionService;
import org.jbpm.api.HistoryService;
import org.jbpm.api.ProcessDefinition;
import org.jbpm.api.ProcessEngine;
import org.jbpm.api.RepositoryService;
import org.jbpm.api.TaskService;
import org.jbpm.api.cmd.Environment;
import org.jbpm.pvm.internal.env.EnvironmentFactory;
import org.jbpm.pvm.internal.env.EnvironmentImpl;
import org.jbpm.pvm.internal.session.RepositorySession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
/**
 * controller
 * @author Administrator
 *
 */
@Controller
@RequestMapping("flow")
public class FlowController {

	@Resource(name="repositoryService")
	private RepositoryService repositoryService;
	
	@Resource(name="taskService")
	private TaskService taskService;
	
	@Resource(name="executionService")
	private ExecutionService executionService;
	
	
	
	@Resource(name="historyService")
	private HistoryService historyService;
	

	
	@Resource(name="processEngine")
	private ProcessEngine processEngine;
	@RequestMapping("toFlow")
	public String toFlow(HttpServletRequest request){
		return "projectFlowAdd";
	}





	public InputStream getProcessDefCfg(String flowKey) {
		EnvironmentFactory environmentFactory = (EnvironmentFactory) processEngine;
		Environment environment = environmentFactory.openEnvironment();
				
			try{
					RepositorySession repositorySession = environment.get(RepositorySession.class);   
					ProcessDefinition pd = repositorySession.findProcessDefinitionByKey(flowKey);
					Set<String> names = repositoryService.getResourceNames(pd.getDeploymentId());
//					Set<String> names = new GetDeploymentResourceNamesCmd(pd.getDeploymentId()).execute(environment);
					for (String n :names) {
						if (n.indexOf(".jpdl.xml") != -1) {
							InputStream in = repositoryService.getResourceAsStream(pd.getDeploymentId(), n);
							return in;
						}
					}
				}catch(Exception e){
					e.printStackTrace();
				}finally{
					closeEnvironment(environment);
				}
		return null;
	}
	
	private void closeEnvironment(Environment environment){
		if(environment!=null){
			((EnvironmentImpl)environment).close();
		}
	}
	
	public ProcessDefinition getProcessDefinition(String flowKey) {
		EnvironmentFactory environmentFactory = (EnvironmentFactory) processEngine;
		Environment environment = environmentFactory.openEnvironment();
		ProcessDefinition pd = null;		
			try{
					RepositorySession repositorySession = environment.get(RepositorySession.class);   
					 pd = repositorySession.findProcessDefinitionByKey(flowKey);
			}catch(Exception e){
				e.printStackTrace();
			}
		return pd;
	}
	
}
