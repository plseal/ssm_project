<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@include  file="sessiontimeout.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="./jquery-easyui-1.4.3/themes/default/easyui.css" />
<link rel="stylesheet" type="text/css" href="./jquery-easyui-1.4.3/themes/icon.css" />
<script type="text/javascript" src="./jquery-easyui-1.4.3/jquery.min.js"></script>
<script type="text/javascript" src="./jquery-easyui-1.4.3/jquery.easyui.min.js"></script>
<script type="text/javascript">

$(function(){
	$('#listTable').datagrid({
	    url:'./admin/listNGnames.do',
	    rownumbers:true,
	    
	    columns:[[
			{field:'id',            title:'ID',width:0,align:'left'},
			{field:'position',           title:'职务',width:0,align:'left'},
			{field:'bumen',            title:'详细内容 ',width:0,align:'left'},
			{field:'role',            title:'role',width:0,align:'left'},
			{field:'user_danwei',            title:'单位',width:0,align:'left'}
			
	        /*
			,{field:'opt',            title:'操作',width:0,align:'center',  
	            formatter:function(value,rec){  
	                var update = "  <a class='editcls' onclick='#'"  
		            + "color=blue href='./standardDatabase/update.do?id="+rec.id
															 +"'>修正</a>";
	                var del = " | <a class='editcls' onclick='#'"  
			            + "color=blue href='./standardDatabase/delete.do?id="+rec.id
															 +"'>删除</a>";  		            
	            	return update + del ;
	            }  
	        }
			*/
	    ]]
	});
});


</script>
</head>
<body><BR>


    <form method="post" id="myform">
   
 

	<table id="listTable" ></table>
	</form>
	
		

</body>
</html>
	