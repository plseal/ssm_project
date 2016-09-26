<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="com.lin.entity.RectiNewEntity" %>
<%@include  file="sessiontimeout.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/default.css"/>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/jquery-easyui-1.4.3/themes/default/easyui.css" />
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/jquery-easyui-1.4.3/themes/icon.css" />
<script type="text/javascript" src="${pageContext.request.contextPath}/jquery-easyui-1.4.3/jquery.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/jquery-easyui-1.4.3/jquery.easyui.min.js"></script><script type="text/javascript">



function pass(strResult){
	$('#myform').form('submit', {
	    url:"${pageContext.request.contextPath}/rectiNew/complete.do?strResult="+strResult,
	    onSubmit: function(){
	        //进行表单验证
	        //如果返回false阻止提交
	    },
	    success:function(data){
	        if(data == "ok"){
	        	$.messager.alert("提示","操作成功");
	        }else{
	        	$.messager.alert("提示","操作失败","error");
	        }
	    }
	});
}





function return_() {
	history.back(-1);
}
</script>
</head>
<body>
<%
String role = (String)session.getAttribute("role");
System.out.println("role:"+role);
String strStatus = "该页面建设中,稍后开放";

%>
<!-- jianshezhong.jsp  -->
<%=strStatus %>


</body>
</html>