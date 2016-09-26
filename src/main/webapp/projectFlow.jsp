<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@include  file="sessiontimeout.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<html>
<head>
<base href="<%=basePath%>">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<link type="text/css" href="js/myflow/jquery.ui.all.css" rel="stylesheet" /> <%--控制工具集样式,核心 --%>
<link type="text/css" href="js/myflow/flow.css" rel="stylesheet" /> <%--显示网格,核心 --%>


<script type="text/javascript" src="js/myflow/jquery-1.7.1.js"></script> <%--核心 --%>
<script type="text/javascript" src="js/myflow/jquery-ui-1.8.17.custom.min.js"></script> <%--核心 --%>
<link type="text/css" href="js/myflow/jquery-ui-1.8.17.custom.css" rel="stylesheet" />
<script type="text/javascript" src="js/myflow/raphael.js"></script> <%--核心 ,矢量图像库--%>


<script type="text/javascript" src="js/myflow/myflow.js"></script> <%--核心 --%>
<script type="text/javascript" src="js/myflow/myflow.jpdl4.js"></script> <%--核心 --%>
<script type="text/javascript" src="js/myflow/myflow.editors.js"></script> <%--核心 --%>
<script type="text/javascript" src="js/myflow/workJob.js"></script>  <%--核心 --%>
<script type="text/javascript" src="js/myflow/userlib.js"></script>  <%--核心 --%>


<script type="text/javascript">



$.extend(true,$.myflow.config.props.props,{
	name : {name:'name', label:'名称', value:'${flowName}', editor:function(){return new $.myflow.editors.inputEditor();}},
	key : {name:'key', label:'Key', value:'${flowKey}', editor:function(){return new $.myflow.editors.inputEditor();}}
});
//设置表单属性,在流程的每个节点,选择已经定义好的表单
$.extend(true,$.myflow.config.tools.states,{
	'task' : {
		props : {
			form: {name:'form', label : '表单', value:'', editor: function(){
					return new $.myflow.editors.selectEditor(
						{name:'a',value:'1'},
						{name:'b',value:'2'}
					);
				}}}
		}
});




$(function() {
	var basePath = $("#basePath").attr("value");
	var flowJsonResult = ${flowJsonResult};
	$('#myflow').myflow({
		basePath : basePath + "/js",
		restore : flowJsonResult,
		tools : {
			save : {
				onclick : function(data) {
					var workflowId = $("#workflowId").attr("value");
					$.ajax({
						"url":"flow/deploy.do",                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                        
						type: "POST",
						data:{"flowJson":data,"workflowId":workflowId},
						success:function(data){
							alert("成功");
						} 
					});
				}
			}
		}
	});
	
	$("#pageloading").hide();
});
</script>

</head>
<body>

<div class="l-loading" style="display:block" id="pageloading"></div>
<form id="flowEditForm" name="flowEditForm" method="post";>
<basePath value = "<%=basePath%>" id = "basePath"></basePath>
<input type="hidden" id="flowKey" name="flowKey" value="${flowKey}"/>
<input type="hidden" id="flowName" name="flowName" value="${flowName}"/>
</form>
<div id="myflow_tools" class="ui-widget-content">
	<div id="myflow_tools_handle" class="ui-widget-header">工具集</div>

	<div class="node" id="myflow_save">
		<img src="js/myflow/images/save.gif" />保&nbsp;存
	</div>
	<div class="node" id="myflow_back" onclick="javascript:backToModel();">
		<img src="js/myflow/images/delete.png" />返&nbsp;回
	</div>
	<div class="node" id="myflow_props_def">
		<img src="js/myflow/images/props.png" />属&nbsp;性
	</div>
	<div>
		<hr />
	</div>
	<div class="node selectable" id="pointer">
		<img src="js/myflow/images/select16.gif" />选&nbsp;择
	</div>
	<div class="node selectable" id="path">
		<img src="js/myflow/images/16/flow_sequence.png" />转&nbsp;换
	</div>
	<div>
		<hr />
	</div>
	<div class="node state" id="start" type="start">
		<img src="js/myflow/images/16/start_event_empty.png" />开&nbsp;始
	</div>
	<div class="node state" id="task" type="task">
		<img src="js/myflow/images/16/task_empty.png" />任&nbsp;务
	</div>
	<%--<div class="node state" id="state" type="state"><img
	src="js/myflow/images/16/task_empty.png" />状&nbsp;态</div>
	--%>
	<div class="node state" id="join" type="join">
		<img src="js/myflow/images/16/gateway_parallel.png" />合&nbsp;并
	</div>
	<div class="node state" id="decision" type="decision">
		<img src="js/myflow/images/16/gateway_parallel.png" />分&nbsp;支
	</div>
	<div class="node state" id="end" type="end">
		<img src="js/myflow/images/16/end_event_terminate.png" />结&nbsp;束
	</div>
	<div class="node state" id="end-cancel" type="end-cancel"><img
		src="js/myflow/images/16/end_event_cancel.png" />取&nbsp;消</div>
	<div class="node state" id="end-error" type="end-error"><img
		src="js/myflow/images/16/end_event_error.png" />错&nbsp;误</div>
	</div>

<div id="myflow_props" class="ui-widget-content">
<div id="myflow_props_handle" class="ui-widget-header">属性</div>
	<table border="0" cellpadding="0" cellspacing="0">
	</table>
	<div>&nbsp;</div>
</div>

<div id="myflow"></div>
</body>
</html>