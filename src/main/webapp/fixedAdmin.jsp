<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@include  file="sessiontimeout.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link href="${pageContext.request.contextPath}/css/default.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/jquery-easyui-1.4.3/themes/default/easyui.css" />
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/jquery-easyui-1.4.3/themes/icon.css" />
<script type="text/javascript" src="${pageContext.request.contextPath}/jquery-easyui-1.4.3/jquery.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/jquery-easyui-1.4.3/jquery.easyui.min.js"></script>
<script type="text/javascript">

$(function(){
	$('#listTaskTable').datagrid({
	    url:'${pageContext.request.contextPath}/admin/fixedAdminGet.do',
	    rownumbers:true,
	    columns:[[
	        {field:'id',title:'ID',width:50,align:'center'},
	        {field:'name',title:'名称',width:200,align:'center'},
	        {field:'used',title:'用途',width:50,align:'center'},
	        {field:'opt',title:'操作',width:200,align:'center',  
	            formatter:function(value,rec){  
	                var btn = "<a class='editcls' onclick='#'"  
	                + "color=blue href='javascript:easyui_window_update_open();'>更新</a>";
	                return btn ;  
	            }  
	        }  
	    ]]
	});
});


function easyui_window_add_open() {
	$('#easyui_window_add').window('open');
}
function addData() {

	$('#addDataform').form('submit', {
	    url:"${pageContext.request.contextPath}/admin/fixedAdminAdd.do",
	    onSubmit: function(){
	        //进行表单验证
	        //如果返回false阻止提交
	    },
	    success:function(data){
	        if(data == "ok"){
	        	$.messager.alert("提示","操作成功");
	        	location.reload(); 
	        }else{
	        	$.messager.alert("提示","操作失败","error"+data);
	        }
	    }
	});

	//画面重新刷新（加载新数据）
	//history.go(0);
}

function easyui_window_update_open() {
	$('#easyui_window_update').window('open');
	alert($('#id')[0].val());
	
}

function updateData() {

	$('#updateDataform').form('submit', {
	    url:"${pageContext.request.contextPath}/admin/fixedAdminAdd.do",
	    onSubmit: function(){
	        //进行表单验证
	        //如果返回false阻止提交
	    },
	    success:function(data){
	        if(data == "ok"){
	        	$.messager.alert("提示","操作成功");
	        	location.reload(); 
	        }else{
	        	$.messager.alert("提示","操作失败","error"+data);
	        }
	    }
	});

	//画面重新刷新（加载新数据）
	//history.go(0);
}


</script>
</head>
<body>fixedAdmin.jsp建设中<BR>

<a href="javascript:easyui_window_add_open();"    class="easyui-linkbutton" >插入数据</a>

    <form method="post" id="listTaskForm">
   
 

	<table id="listTaskTable"></table>
	<!-- 
	userid:  <input type="text" name = "userid"   value="<%=session.getAttribute("userid") %>" ><br>
	userrole:<input type="text" name = "userrole" value="<%=session.getAttribute("userrole") %>" >
	 -->
	</form>
	
	<div id="easyui_window_add" class="easyui-window" title="数据登陆" data-options="modal:true,closed:true,iconCls:'icon-save'" style="width:430px;height:200px;padding:10px;">
	    <form method="post" id="addDataform">
		    <table id="addDataTable" width="80%"  class="easyui-table">
		        <tr>
		            <td align="right">id:</td>
		            <td><input type="text" class="easyui-textbox"  name="id" value=""></input></td>
		            <td>主键不能和原有数据重复</td>
		        </tr>
		        <tr>
		            <td align="right">名称:</td>
		            <td> <input type="text" class="easyui-textbox" name="name" value=""/></input></td>
		            <td></td>
		        </tr>
		        <tr>
		            <td align="right">用途:</td>
		            <td><input type="text" class="easyui-textbox" name="used" value=""/></input></td>
		            <td></td>
		        </tr>
		        <tr>
		            <td></td>
		            <td align="left">
		            <a class="easyui-linkbutton" data-options="iconCls:'icon-save'" href="javascript:void(0)" onclick="addData()" style="width:120px">插入数据</a></td>
		            <td></td>
		        </tr>
		
		    </table>
	    </form>
	 </div>   
	 
	<div id="easyui_window_update" class="easyui-window" title="更新数据" data-options="modal:true,closed:true,iconCls:'icon-save'" style="width:430px;height:200px;padding:10px;">
	    <form method="post" id="updateDataform">
		    <table id="updateDataTable" width="80%"  class="easyui-table">
		        <tr>
		            <td align="right">id:</td>
		            <td><input type="text" class="easyui-textbox"  name="updateid" value="" disabled="disabled" ></input></td>
		            <td>主键不能更新</td>
		        </tr>
		        <tr>
		            <td align="right">名称:</td>
		            <td> <input type="text" class="easyui-textbox" name="updatename" value=""/></input></td>
		            <td></td>
		        </tr>
		        <tr>
		            <td align="right">用途:</td>
		            <td><input type="text" class="easyui-textbox" name="updateused" value=""/></input></td>
		            <td></td>
		        </tr>
		        <tr>
		            <td></td>
		            <td align="left">
		            <a class="easyui-linkbutton" data-options="iconCls:'icon-save'" href="javascript:void(0)" onclick="updateData()" style="width:120px">更新数据</a></td>
		            <td></td>
		        </tr>
		
		    </table>
	    </form>
	 </div>  
	 
		

</body>
</html>
	