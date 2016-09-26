<%@page import="org.jbpm.api.history.HistoryTask"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@include  file="sessiontimeout.jsp"%>
<%@ page import="java.util.*,org.jbpm.api.model.*,org.jbpm.api.*,org.jbpm.api.task.Task,java.lang.String.*,org.jbpm.api.task.Task" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
 <link rel="stylesheet" href="jtip/jquery.cluetip.css" type="text/css" />
 <link rel="stylesheet" href="jtip/demo.css" type="text/css" />
 <script src="jtip/jquery-1.7.1.js"></script>
 <script src="jtip/jquery.cluetip.js"></script>
 <script type="text/javascript">
 	$(function(){
 		$('#flow').cluetip({
 							splitTitle: '|',
 							//hoverClass: 'highlight',
 							width: '150px',
							cursor: 'pointer',
							//tracking: true,
							cluetipClass: 'rounded'//jtip
 							});
 	});
 </script>
</head>
<body>
<%
	String id = request.getParameter("id");
	ProcessEngine pe = Configuration.getProcessEngine();
	RepositoryService rs = pe.getRepositoryService();
	ExecutionService es = pe.getExecutionService();
	ProcessInstance pi = es.findProcessInstanceById(id);
	Set<String> activies = pi.findActiveActivityNames();
	String acName = activies.iterator().next();
	ActivityCoordinates ac = rs.getActivityCoordinates(pi.getProcessDefinitionId(), acName);
	
	// 获取当前任务
	TaskService ts = pe.getTaskService();
	Task task = ts.createTaskQuery().executionId(pi.getId()).activityName(acName).uniqueResult();
	
    Object msgObj = ts.getVariable(task.getId(),"msg");
	String msg = "";
	if(msgObj != null){
		msg = msgObj.toString();
	}  
	
/*  	// 获取历史任务信息
	HistoryService hs = pe.getHistoryService();
	List<HistoryTask> lists = hs.createHistoryTaskQuery().executionId(pi.getId()).list();
	String msg = "";
	for (HistoryTask ht : lists) {
		System.out.println(ht);
		Object msgObj = hs.getVariable(ht.getId(), "msg");
		System.out.println(msgObj);
		if(msgObj != null){
			msg += msgObj.toString();
		}
	} */ 
	
%>
<img alt="" src="pic.jsp?id=<%=id %>" style="position:absolute;left:0px;top:0px;"><hr>
<div id="flow" style="position:absolute;border:1px solid red;left:<%=ac.getX() %>px;top:<%=ac.getY()%>px;width:<%=ac.getWidth()%>px;height:<%=ac.getHeight() %>px" title="<%=task.getActivityName() %>|受理人:<%=task.getAssignee()%>|<%=msg%>"></div>
</body>
</html>