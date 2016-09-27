package com.lin.service;

import org.jbpm.api.jpdl.DecisionHandler;
import org.jbpm.api.model.OpenExecution;
/**
 * Decision节点处理类
 * @author Administrator
 *
 */
public class MyHandler implements DecisionHandler {

	private static final long serialVersionUID = 4761048525213708431L;

	@Override
	public String decide(OpenExecution execution) {
		
		int day = Integer.parseInt(execution.getVariable("day").toString());
		
		if(day <=3){
			return "to task1";
		}else{
			return "to task2";
		}
		
	}

}
