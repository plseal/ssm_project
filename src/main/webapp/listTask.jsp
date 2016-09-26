<%@ page language="java" contentType="text/html; charset=UTF-8"   pageEncoding="UTF-8"%>
<%@include  file="sessiontimeout.jsp"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title></title>

<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/jquery-easyui-1.4.3/themes/default/easyui.css" />
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/jquery-easyui-1.4.3/themes/icon.css" />
<script type="text/javascript" src="${pageContext.request.contextPath}/jquery-easyui-1.4.3/jquery.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/jquery-easyui-1.4.3/jquery.easyui.min.js"></script>
<script type="text/javascript">

$(function(){
	$('#listTaskTable').datagrid({
	    url:'${pageContext.request.contextPath}/admin/listTask.do',
	    rownumbers:true,
	    
	    
	    columns:[[
	        {field:'taskid',title:'任务ID',width:0,align:'center'},
	        {field:'taskname',title:'任务节点名称',width:200,align:'center'},
	        {field:'assignee',title:'任务接收人',width:100,align:'center',hidden:'true'},
	        {field:'processid',title:'流程ID',width:0,align:'center',hidden:'true'},
	        {field:'starter',title:'发起人',width:0,align:'center'},
	        {field:'javaid',title:'javaid',width:0,align:'center',hidden:'true'},
	        
	        {field:'opt',title:'操作',width:200,align:'center',  
	            formatter:function(value,rec){  
	            	var recticheck_rectiNew_recticheckXiashu_Div = "rectiNew";
	            	
	            	if (rec.javaid.indexOf("xiashu") != -1) {
	            		recticheck_rectiNew_recticheckXiashu_Div = "rectiCheckXiashu";
	            	} else if (rec.processid.indexOf("rectification6") != -1) {
	            		recticheck_rectiNew_recticheckXiashu_Div = "rectiCheck";
                    } else if (rec.processid.indexOf("rectidanger3") != -1) {
                        recticheck_rectiNew_recticheckXiashu_Div = "rectiDanger";
	            	} else {
	            		//什么都不干
	            	}
	            	
                    var btn = "<a class='easyui-linkbutton'  onclick='#'"  
		                + "color=blue href='${pageContext.request.contextPath}/"+recticheck_rectiNew_recticheckXiashu_Div+"/get_.do"
		                +"?taskid="+rec.taskid
		                +"&taskname="+rec.taskname
		                +"&javaid="+rec.javaid
		                +"&fromPage=listTask.jsp'>查看</a>";
	                
	                	
	               var pic = " | <a class='editcls' onclick='#'"  
		                + "color=blue href='${pageContext.request.contextPath}/admin/view_.do"
		                	+"?id="+rec.processid
		                	+"&taskid="+rec.taskid
		                	+"&fromPage=listTask.jsp'>审批流程图</a>";
	                var del = " | <a class='editcls' onclick='#'"  
		                + "color=blue href='${pageContext.request.contextPath}/rectiNew/deleteTask.do?executionId="+rec.processid+"'>删除</a>";  
	                return btn +pic;  
	            }  
	        }  
	    ]]
	});
});
function return_() {
	history.back(-1);
}

</script>
</head>
<body>

    <form method="post" id="listTaskForm">
 	<table id="listTaskTable">
 	</table>

	</form>
</body>
</html>
	