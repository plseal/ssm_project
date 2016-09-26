<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@include  file="sessiontimeout.jsp"%>
<%@ page import="com.lin.entity.UserEntity" %>
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

function insertThis(){
	document.all.myform.action="${pageContext.request.contextPath}/admin/userAdminInsert.do";
	document.all.myform.submit();
}
function return_() {
	history.back(-1);
}



</script>
</head>
<body>用户管理追加数据 <BR>

<%

UserEntity entity = new UserEntity();

entity = (UserEntity)request.getAttribute("entity");
%>

<a href="javascript:return_();" class="easyui-linkbutton" >返回</a>

    <form method="post" id="myform">
   
 

	<table >
		<tr>
			<td>ID</td>
			<td><input  name = "id" class="easyui-textbox" value = "" >注：人名拼音，不能重复，如果有重复人名，请加02（例如：zhangsan,zhangsan02）加以区分</td>
		</tr>
		<tr>
			<td>初始密码</td>
			<td><input  name = "pas" class="easyui-textbox" value = "" ></td>
		</tr>
		<tr>
			<td>名字</td>
			<td><input  name = "name" class="easyui-textbox" value = "" ></td>
		</tr>
		<tr>
			<td>职务(审批角色)</td>
			<td>
			<select id="role" name = "role" class="easyui-combobox" style="width:125px;">
				<option value="yuangong">员工</option>
				<option value="leader">部门负责人</option>
				<option value="manager" >分管副经理</option>
				<option value="geofficer">综合办</option>
				<option value="townofficer">县局主要负责人</option>
				<option value="cityofficer">市局</option>
				<option value="safetyofficer">安管员</option>
				<option value="admin">管理员</option>
			</select>
			</td>
		</tr>
		<tr>
			<td>所属部门</td>
			<td><input  name = "bumen" class="easyui-textbox" value = "" ></td>
		</tr>
		<tr>
			<td>手机号</td>
			<td><input  name = "tel" class="easyui-textbox" value = "" ></td>
		</tr>
		<tr>
			<td>审批负责人</td>
			<td><input  name = "leader" class="easyui-textbox" value = ""></td>
		</tr>

	</table>
	<!-- 
	userid:  <input type="text" name = "id"   value="<%=session.getAttribute("id") %>" ><br>
	userrole:<input type="text" name = "role" value="<%=session.getAttribute("role") %>" >
	 -->
	
	</form>
	
	<a href="javascript:insertThis();" class="easyui-linkbutton" >确认追加</a>	

</body>
</html>
	