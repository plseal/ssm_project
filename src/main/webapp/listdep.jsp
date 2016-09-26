<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@include  file="sessiontimeout.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>

<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/jquery-easyui-1.4.3/themes/default/easyui.css" />
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/jquery-easyui-1.4.3/themes/icon.css" />
<script type="text/javascript" src="${pageContext.request.contextPath}/jquery-easyui-1.4.3/jquery.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/jquery-easyui-1.4.3/jquery.easyui.min.js"></script>
<script type="text/javascript">
	$(function(){
		$('#tt').datagrid({
		    url:'${pageContext.request.contextPath}/admin/listProcessDefinition.do',
		    rownumbers:true,
		    columns:[[
		        {field:'id',title:'流程ID',width:150,align:'center'},
		        {field:'key',title:'流程KEY',width:150,align:'center'},
		        {field:'name',title:'流程名称',width:150,align:'center'},
		        {field:'version',title:'流程版本',width:100,align:'center'},
		        {field:'opt',title:'操作',width:200,align:'center',  
		            formatter:function(value,rec){  
		                var btn = "<a class='editcls' onclick='#'"  
		                + "color=blue href='${pageContext.request.contextPath}/admin/viewForAdmin_.do?id="+rec.id+"&fromPage=listdep.jsp'>查看</a>";
		                var delbtn = "<a class='editcls' onclick='#'"  
			                + "color=blue href='${pageContext.request.contextPath}/admin/deleteProcessDefinition.do?PDID="+rec.version+"'>删除</a>";  
		                return "";   
		            }  
		        }
		    ]]
		});
	});
</script>
</head>
<body>
	<table id="tt"></table>
</body>
</html>