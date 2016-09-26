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
<%

String hiddenFlg ="false";
String role = (String)session.getAttribute("role");

%>
<script type="text/javascript">

$(function(){
	$('#listTaskTable').datagrid({
	    url:'${pageContext.request.contextPath}/admin/userAdminGet.do',
	    rownumbers:true,
	    columns:[[
	        {field:'id',          title:'用户ID',width:0,align:'left'},
	        {field:'pas',         title:'密码',width:0,align:'left'},
	        {field:'name',        title:'名字',width:0,align:'left'},
	        {field:'position',title:'职务',width:0,align:'left'},
	        {field:'bumen'       ,title:'所属部门',width:0,align:'left'},
	        {field:'ngnameflg'   ,title:'重名标志',width:0,align:'left',
               formatter:function(value,rec){  
            	    if (rec.ngnameflg == "OK") {
            	    	return "";	
            	    } else {
            	    	return "重名用户";	
            	    } 
                } 
	        },
	        {field:'role',  title:'隐患审批身份',width:0,align:'left'},
	        {field:'user_danwei',  title:'单位',width:0,align:'left'},
	        {field:'opt',         title:'操作',width:0,align:'center',  
	            formatter:function(value,rec){  
	                var btn = "<a class='editcls' onclick='#'"  
	                + "color=blue href='${pageContext.request.contextPath}/admin/userAdminUpdate.do?id="+rec.id+"'>修正</a>";
	                var delbtn = "<a class='editcls' onclick='#'"  
		            + "color=blue href='${pageContext.request.contextPath}/admin/userAdminDelete.do?id="+rec.id+"'>删除</a>";  
	                return btn+"|"+delbtn;   
	            }  
	            
	        }  
	    ]]
	});
});

function toUserAdminInsertJsp(){
	document.all.myform.action="${pageContext.request.contextPath}/admin/gotoJSPuserAdminInsert.do";
	document.all.myform.submit();
}

//检索
function search_(){
    document.all.myform.action="${pageContext.request.contextPath}/admin/to_userAdminJSP.do";
    document.all.myform.submit(); 
}
function change_s_danwei() {
    document.all.myform.s_danwei.value =  $("#s_danwei_view").val();
}
function change_s_zhiwei() {
    document.all.myform.s_zhiwei.value =  $("#s_zhiwei_view").val();
}
function onload_(){
    

    var s_danwei = document.all.myform.s_danwei.value;
    
    if (s_danwei != "") {
        document.all.myform.s_danwei_view.value = s_danwei;
    } else {
        document.all.myform.s_danwei.value = "";
    }

    var s_zhiwei = document.all.myform.s_zhiwei.value;
    
    if (s_zhiwei != "") {
        document.all.myform.s_zhiwei_view.value = s_zhiwei;
    } else {
        document.all.myform.s_zhiwei.value = "";
    }
    
}
</script>
</head>
<body onload="javascript:onload_();">用户管理 主页<br/><br/>
<form method="post" id="myform">
<br/>

    <div id="searchPanel" class="easyui-panel" style="padding:2px 5px;">
        单位: 
        <select id = "s_danwei_view" name = "s_danwei_view" onchange="javascript:change_s_danwei();">
        <option value="" ></option>
        <c:forEach items="${danwei_For_Search_List }" var="danwei_For_Search_ListItem">
         <option value="${danwei_For_Search_ListItem.name }" >${danwei_For_Search_ListItem.name }</option>
        </c:forEach>
        </select> 
        <input type="hidden" name="s_danwei" value="${s_danwei}" class="rectimyinput" style="width:110px;" maxlength=12>
     职位: 
        <select id = "s_zhiwei_view" name = "s_zhiwei_view" onchange="javascript:change_s_zhiwei();">
        <option value="" ></option>
        <option value="市局局长" >市局局长</option>
        <option value="县局局长" >县局局长</option>
        <option value="分管领导" >分管领导</option>
        <option value="办公室主任" >办公室主任</option>
        <option value="部门负责人" >部门负责人</option>
        <option value="安全管理员" >安全管理员</option>
        <option value="安全管理部门负责人" >安全管理部门负责人</option>
        <option value="分管安全领导" >分管安全领导</option>
        <option value="员工" >员工</option>
        
        </select> 
        <input type="hidden" name="s_zhiwei" value="${s_zhiwei}" class="rectimyinput" style="width:110px;" maxlength=12>
        
        <a href="javascript:search_();" class="easyui-linkbutton" iconCls="icon-search">检索</a>
    </div>
    
    <div style="margin:10px 0;"></div>
<%
	
	if (role.equals("admin")) {
%>

<a href="javascript:toUserAdminInsertJsp();" class="easyui-linkbutton" >追加用户</a>
<br/>

    
	<table id="listTaskTable"></table>
	</form>
<%
	}
%>
		

</body>
</html>
	