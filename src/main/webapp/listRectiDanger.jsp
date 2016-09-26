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
<script type="text/javascript" src="${pageContext.request.contextPath}/jquery-easyui-1.4.3/locale/easyui-lang-zh_CN.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/common.js"></script>

<script type="text/javascript">

$(function(){
	$('#listTable').datagrid({
	    url:'${pageContext.request.contextPath}/rectiDanger/listRectiGet.do?listRectiAc=' + document.all.myform.hid_listRectiAc.value 
	    ,rownumbers:true
	    ,pagination:true
	    ,columns:[[
			{field:'javaid',            title:'javaID',width:0,align:'center',hidden:'true'},
			{field:'taskid',            title:'任务ID',width:0,align:'center',hidden:'true'},
			{field:'processid',            title:'流程ID',width:0,align:'center',hidden:'true'},
			{field:'no',                title:'编号',width:0,align:'center'},
			{field:'danwei',            title:'单位(部门)',width:0,align:'center'},
			{field:'startdate',         title:'开始时间',width:0,align:'center'},
			{field:'enddate',         title:'结束时间',width:0,align:'center'},
			{field:'location',          title:'危险作业部位',width:0,align:'center'},
			
	        {field:'opt',               title:'操作',width:0,align:'center',  
	            formatter:function(value,rec){  
	                var btn = "<a class='editcls' onclick='#'"  
	                + "color=blue href='${pageContext.request.contextPath}/rectiDanger/"
	                														+"get_.do?taskid="+rec.taskid
	                														+"&taskname="+rec.taskstatus
	                														+"&javaid="+rec.javaid
	                														+"&fromPage=listRectiDanger.jsp"
	                														+"&listRectiAc="+document.all.myform.hid_listRectiAc.value
	                														+"'>查看</a>";
	                var pic = " | <a class='editcls' onclick='#'"  
		            + "color=blue href='${pageContext.request.contextPath}/admin/"
		            														+"view_.do?id="+rec.processid
		            														+"&taskid="+rec.taskid
		            														+"&fromPage=listRectiDanger.jsp"
		            														+"&listRectiAc="+document.all.myform.hid_listRectiAc.value
															                +"'>审批流程图</a>";
															 
	            
					if 	(rec.taskid == null ) {
		            	return btn  ;
		            } else {
		            	return btn + pic ;
		            } 
		            	
		             
	            }  
	        } ,
	        {field:'delete_',               title:'删除操作',width:0,align:'center',  
	            formatter:function(value,rec){  
															 
	                var del = "  <a class='editcls' onclick='#'"  
			            + "color=blue href='${pageContext.request.contextPath}/rectiDanger/"
			            														+"deleteListRectiDanger.do?javaid="+rec.javaid
			            														+"&taskid="+rec.taskid
			            														+"&taskstatus="+rec.taskstatus
			            														+"&processid="+rec.processid
			            														+"&listRectiAc="+document.all.myform.hid_listRectiAc.value
                             													+"'>删除</a>";  		            
		            	return del ;

	            }  
	        } ,
			{field:'taskname',        title:'任务状态',width:0,align:'center',
				formatter:function(value,rec){
	            if (rec.taskname.indexOf("安全管理员验收结束流程") !=-1 || rec.taskname.indexOf("发起人验收结束流程") != -1) {
	            	
	            	return "已办结" ;
	            } else {
	            	if (rec.taskname != null) {
	            		return ""+rec.taskname ;
	            	} else {
	            		return "未发起申请" ;
	            	}
	            }	
				}
			}
			
	    
	    ]]
	});
    //设置分页控件 
    var p = $('#listTable').datagrid('getPager'); 
    $(p).pagination({ 
        pageSize: 10,//每页显示的记录条数，默认为10 
        pageList: [5,10,15],//可以设置每页记录条数的列表 
        beforePageText: '第',//页数文本框前显示的汉字 
        afterPageText: '页    共 {pages} 页', 
        displayMsg: '当前显示 {from} - {to} 条记录   共 {total} 条记录', 
        /*onBeforeRefresh:function(){
            $(this).pagination('loading');
            alert('before refresh');
            $(this).pagination('loaded');
        }*/ 
    });
});


//检索
function search_(){
	document.all.myform.action="${pageContext.request.contextPath}/rectiDanger/toListRectiDanger.do?listRectiAc="+document.all.myform.hid_listRectiAc.value;
	document.all.myform.submit(); 
}
function change_() {
	document.all.myform.hid_listRectiAc.value =  $("#listRectiAc_view").val();
	document.all.myform.action="${pageContext.request.contextPath}/rectiDanger/toListRectiDanger.do?listRectiAc="+document.all.myform.hid_listRectiAc.value;
	document.all.myform.submit(); 
}

function change_s_searchForAllFlg() {
    document.all.myform.s_searchForAllFlg.value =  $("#s_searchForAllFlg_view").val();
}

function onload_(){
	
	var hid_listRectiAc = document.all.myform.hid_listRectiAc.value;
	
	if (hid_listRectiAc != "") {
		document.all.myform.listRectiAc_view.value = hid_listRectiAc;
	} else {
		document.all.myform.hid_listRectiAc.value = "this_month";
	}

	
    var s_searchForAllFlg = document.all.myform.s_searchForAllFlg.value;
    if (s_searchForAllFlg != "") {
        document.all.myform.s_searchForAllFlg_view.value = s_searchForAllFlg;
    } else {
        document.all.myform.s_searchForAllFlg.value = "本人相关隐患信息";
    }


	
}

</script>
<style>
.rectimyinput {
font-family:方正小标宋简体;
font-size: 10pt;
line-height:15px;
COLOR: #000000;
border-top-style: none;
border-right-style: none;
border-bottom-style: dotted;
border-left-style: none;
border-bottom-color: #000000;
border-top-width: 0px;
border-right-width: 0px;
border-bottom-width: 1px;
border-left-width: 0px;
}

</style>
</head>
<body onload="javascript:onload_();">
<form method="post" id="myform">
<input name="hid_listRectiAc" id="hid_listRectiAc" value="${listRectiAc}" type="hidden">
危险作业审批管理 <BR>
<table  border=0 width = 100% >
  <tr >
   <td width = 33%>

  </td>
  <td width = 33%>

   </td>
   <td style="text-align:right;valign:bottom;font-size:30px;">
  	  <select id = "listRectiAc_view" name = "listRectiAc_view" onchange="javascript:change_();"style="font-size:18px;">
	  <option value="this_month" >本月</option>
	  <option value="last_month" >上个月</option>
	  <option value="this_year" >今年</option>
     </select> 
  </td>
 </tr>

</table>


	<div id="searchPanel" class="easyui-panel" style="padding:2px 5px;">
		单位(部门): <input name="s_danwei" value="${s_danwei}" class="rectimyinput" style="width:110px" maxlength=20>
		地点: <input name="s_location" value="${s_location}" class="rectimyinput" style="width:110px" maxlength=20>
		
		
        <%
         
        String    role = (String)session.getAttribute("role");
        if (role.contains("superofficer")) {
        %>
                    查看对象:
        <select id = "s_searchForAllFlg_view" name = "s_searchForAllFlg_view" onchange="javascript:change_s_searchForAllFlg();">
        <option value="本人相关隐患信息" >本人相关隐患信息</option>
        <option value="所有人隐患信息" >所有人隐患信息</option>
        </select> 
        <input type="hidden" name="s_searchForAllFlg" value="${s_searchForAllFlg}" class="rectimyinput" style="width:110px;" maxlength=12>
        <%
        } else {
        
        %>
        <select id = "s_searchForAllFlg_view" name = "s_searchForAllFlg_view" onchange="javascript:change_s_searchForAllFlg();" style="display:none;">
        <option value="本人相关隐患信息" selected>本人相关隐患信息</option>
        <option value="所有人隐患信息" >所有人隐患信息</option>
        </select> 
        <input type="hidden" name="s_searchForAllFlg" value="${s_searchForAllFlg}" class="rectimyinput" style="width:110px;" maxlength=12>
        <%
        }
        %>
		
		<a href="javascript:search_();" class="easyui-linkbutton" iconCls="icon-search">检索</a>
	</div>
	
	<div style="margin:10px 0;"></div>

	<table id="listTable" class="easyui-datagrid"  ></table>
	
	</form>
	
		

</body>
</html>
	