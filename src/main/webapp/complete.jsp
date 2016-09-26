<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@include  file="sessiontimeout.jsp"%>
<%@ page import="com.lin.entity.RectiNewEntity" %>
<%@include  file="sessiontimeout.jsp"%>
<html>
<head>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/jquery-easyui-1.4.3/themes/default/easyui.css" />
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/jquery-easyui-1.4.3/themes/icon.css" />
<script type="text/javascript" src="${pageContext.request.contextPath}/jquery-easyui-1.4.3/jquery.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/jquery-easyui-1.4.3/jquery.easyui.min.js"></script>

<meta http-equiv=Content-Type content="text/html; charset=UTF-8">
<script type="text/javascript">

</script>
</head>

<%

String strStatus = "";
String leaderName = (String)session.getAttribute("leadername");
String role       = (String)session.getAttribute("role");

String strTrans = (String)request.getParameter("strTrans");
System.out.println("complete.jsp---strTrans:"+strTrans);
RectiNewEntity entity = new RectiNewEntity();

entity = (RectiNewEntity)request.getAttribute("entity");

if(role.equals("yuangong") ){
	strStatus = "等待部门负责人("+leaderName+")审批";
	
}else if(role.equals("leader")& strTrans.equals("批准")){
	strStatus = "等待县局分管副经理("+leaderName+")审批";
}else if(role.equals("leader")& strTrans.equals("驳回")){
	strStatus = "已驳回";	
	
}else if(role.equals("manager")& strTrans.equals("批准")){
	strStatus = "等待综合办公室("+leaderName+")审批";
}else if(role.equals("manager")& strTrans.equals("驳回")){
	strStatus = "已驳回";	

}else if(role.equals("geofficer")& strTrans.equals("批准")){
	if (entity.getRectilevel().equals("重大隐患")) {
		strStatus = "重大隐患：等待市局("+leaderName+")审批";
	} else {
		strStatus = "非重大隐患：等待安全管理员（例：safetymanager1）确认验收";
	}
}else if(role.equals("geofficer")& strTrans.equals("驳回")){
	strStatus = "已驳回";	
	
}else if(role.equals("cityofficer")& strTrans.equals("批准")){
	strStatus = "等待安全管理员（例：safetymanager1）确认验收";
}else if(role.equals("cityofficer")& strTrans.equals("驳回")){
	strStatus = "已驳回";	
	
}else if(role.equals("safetymanager")& strTrans.equals("验收")){
	strStatus = "结束";
}

%>

<body lang=ZH-CN style='tab-interval:21.0pt;text-justify-trim:punctuation'>

发起成功<br/>
流程状态为
<%=strStatus %>


</body>
</html>