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

function updateThis(){
	document.all.myform.action="${pageContext.request.contextPath}/admin/userAdminUpdateSave.do";
	document.all.myform.submit();
}
function return_() {
	history.back(-1);
}


</script>
</head>
<body>用户管理 更新数据 <BR>

<%

UserEntity entity = new UserEntity();

entity = (UserEntity)request.getAttribute("entity");
String role = entity.getRole();
String strselected[]=new String[8];

	if(role.equals("yuangong") ){
		strselected[0] = "selected";
	}else if(role.equals("leader")){
		strselected[1] = "selected";
	}else if(role.equals("manager")){
		strselected[2] = "selected";
	}else if(role.equals("geofficer")){
		strselected[3] = "selected";
	}else if(role.equals("townofficer")){
		strselected[4] = "selected";
	}else if(role.equals("cityofficer")){
		strselected[5] = "selected";
	}else if(role.equals("safetyofficer")){
		strselected[6] = "selected";
	}else if(role.equals("admin")){
		strselected[7] = "selected";
	}

%>
<script type="text/javascript">



</script>
<a href="javascript:return_();" class="easyui-linkbutton" >返回</a>

    <form method="post" id="myform">
    
 

	<table >
		<tr>
			<td>ID</td>
			<td><input  name = "id" class="easyui-textbox"  value = "<%=entity.getId() %>" readonly="readonly">注：主键不能更改,要更改的话可删除该数据再追加</td>
		</tr>
		<tr>
			<td>密码</td>
			<td><input  name = "pas" class="easyui-textbox"  value = "<%=entity.getPas() %>" ></td>
		</tr>
		<tr>
			<td>名字</td>
			<td><input  name = "name" class="easyui-textbox" value = "<%=entity.getName() %>" ></td>
		</tr>


		<tr>
			<td>所在部门</td>
			<td><input  name = "bumen" class="easyui-textbox" value = "<%=entity.getBumen() %>" ></td>
		</tr>
		<tr>
			<td>重名标志</td>
			<td><input  name = "ngnameflg" class="easyui-textbox" value = "<%=entity.getNgnameflg() %>" >OK：没有重名，NG：该ID有重名</td>
		</tr>
        <tr>
            <td>职务（审批角色）</td>
            <td>
		        &nbsp;&nbsp;员工:<input type="checkbox">&nbsp;&nbsp;部门负责人:<input type="checkbox">&nbsp;&nbsp;分管副经理:<input type="checkbox">
		        &nbsp;&nbsp;综合办:<input type="checkbox">&nbsp;&nbsp;县局主要负责人:<input type="checkbox">&nbsp;&nbsp;市局:<input type="checkbox">
		        &nbsp;&nbsp;安全管理员:<input type="checkbox">
            
            </td>
        </tr>
		<tr>
			<td>隐患审批身份</td>
			<td><input  name = "role" class="easyui-textbox" value = "<%=entity.getRole() %>"></td>
		</tr>
        <tr>
            <td>单位</td>
            <td><input  name = "user_danwei" class="easyui-textbox" value = "<%=entity.getUser_danwei() %>" style="width:200px"></td>
        </tr>
	</table>

	
	</form>
	
	<a href="javascript:updateThis();" class="easyui-linkbutton" >确认修改</a>	

</body>
</html>
	