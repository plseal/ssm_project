package com.lin.entity;
/**
 * @author Administrator
 *
 */
public class TaskEntity {



	private String taskid;
	
	private String taskname;
	
	private String assignee;
	
	private String processid;
	
	private String starter;
	
	private String javaid;
	
	
	
	public String getJavaid() {
		return javaid;
	}

	public void setJavaid(String javaid) {
		this.javaid = javaid;
	}

	public String getStarter() {
		return starter;
	}

	public void setStarter(String starter) {
		this.starter = starter;
	}

	public String getTaskid() {
		return taskid;
	}

	public void setTaskid(String taskid) {
		this.taskid = taskid;
	}

	public String getTaskname() {
		return taskname;
	}

	public void setTaskname(String taskname) {
		this.taskname = taskname;
	}

	public String getAssignee() {
		return assignee;
	}

	public void setAssignee(String assignee) {
		this.assignee = assignee;
	}

	public String getProcessid() {
		return processid;
	}

	public void setProcessid(String processid) {
		this.processid = processid;
	}
	
}
