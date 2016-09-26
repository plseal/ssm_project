<%@ page language="java" contentType="text/html; charset=UTF-8"   pageEncoding="UTF-8"%>
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
	    url:'${pageContext.request.contextPath}/rectiCheck/listRectiGet.do?listRectiAc=' + document.all.myform.hid_listRectiAc.value 
	    ,rownumbers:true
	    ,pagination:true
	    ,columns:[[
			{field:'javaid',            title:'javaID',width:0,align:'center',hidden:'true'},
			{field:'taskid',            title:'任务ID',width:0,align:'center',hidden:'true'},
			{field:'processid',         title:'流程ID',width:0,align:'center',hidden:'true'},
			{field:'no',                title:'隐患编号',width:0,align:'center'},
			{field:'danwei',            title:'受检单位(部门)',width:0,align:'center'},
			{field:'checkdate',         title:'检查时间',width:0,align:'center'},
			{field:'location1',          title:'检查地点1',width:0,align:'center'},
			{field:'location2',          title:'检查地点2',width:0,align:'center'},
			{field:'location3',          title:'检查地点3',width:0,align:'center'},
			{field:'content',           title:'检查内容',width:0,align:'center'},
			{field:'rectitype',         title:'隐患类型',width:0,align:'center'},
			{field:'rectilevel',        title:'隐患等级',width:0,align:'center'},
		        {field:'opt',               title:'操作',width:0,align:'center',  
	            formatter:function(value,rec){
	            	//alert(rec.taskid);
	            	var shiju_xiashu_div = "rectiCheck";
	            	if (rec.javaid.indexOf("xiashu") != -1) {
	            		shiju_xiashu_div = "rectiCheckXiashu";
	            	}
	                var btn = "<a class='editcls' "  
	                + "color=blue href='${pageContext.request.contextPath}/"+shiju_xiashu_div+"/get_.do"
	                						+"?taskid="+rec.taskid
	                						+"&taskname="+rec.taskstatus
	                						+"&javaid="+rec.javaid
	                						+"&fromPage=listRectiCheck.jsp" 
	                						+"&listRectiAc="+document.all.myform.hid_listRectiAc.value
	                				        +"'>查看</a>";
	                var pic = " | <a class='editcls' onclick='#'"  
		            + "color=blue href='${pageContext.request.contextPath}/admin/view_.do?id="+rec.processid
		            																	 +"&taskid="+rec.taskid
		            																	 +"&javaid="+rec.javaid
		            																	 +"&fromPage=listRectiCheck.jsp"
		            																	 +"&listRectiAc="+document.all.myform.hid_listRectiAc.value
															                             +"'>审批流程图</a>";
					if 	(rec.taskid == null) {
		            	return btn  ;
		            } else {
		            	return btn + pic ;
		            } 
	            }  
	        } ,
	        {field:'delete_',               title:'删除操作',width:0,align:'center',  
	            formatter:function(value,rec){  
															                             
                   var del = " <a class='editcls' onclick='#'"  
   	                + "color=blue href='${pageContext.request.contextPath}/rectiCheck/deleteListRectiCheck.do?javaid="+rec.javaid
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
	            if (rec.taskname.indexOf("安全管理员验收结束流程") != -1) {
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

//自查隐患信息
function toRectiNew(){
	document.all.myform.action="${pageContext.request.contextPath}/rectiNew/toListRectiNew.do?listRectiAc=this_month";
	document.all.myform.submit();
}
//上级检查组安全检查隐患信息
function toRectiCheck(){
	document.all.myform.action="${pageContext.request.contextPath}/rectiCheck/toListRectiCheck.do?listRectiAc=this_month";
	document.all.myform.submit(); 
}
//检索
function search_(){
	document.all.myform.action="${pageContext.request.contextPath}/rectiCheck/toListRectiCheck.do?listRectiAc="+document.all.myform.hid_listRectiAc.value;
	document.all.myform.submit(); 
}

function change_() {
	document.all.myform.hid_listRectiAc.value =  $("#listRectiAc_view").val();
	document.all.myform.action="${pageContext.request.contextPath}/rectiCheck/toListRectiCheck.do?listRectiAc="+document.all.myform.hid_listRectiAc.value;
	document.all.myform.submit(); 
}
function change_s_rectitype() {
	document.all.myform.s_rectitype.value =  $("#s_rectitype_view").val();
}
function change_s_rectilevel() {
    document.all.myform.s_rectilevel.value =  $("#s_rectilevel_view").val();
}
function change_s_searchForAllFlg() {
    document.all.myform.s_searchForAllFlg.value =  $("#s_searchForAllFlg_view").val();
}

function onSelect(date){
	document.all.myform.s_checkdate.value = date.format("yyyy-MM-dd");
}

function onload_(){
	
	var hid_listRectiAc = document.all.myform.hid_listRectiAc.value;
	
	if (hid_listRectiAc != "") {
		document.all.myform.listRectiAc_view.value = hid_listRectiAc;
	} else {
		document.all.myform.hid_listRectiAc.value = "this_month";
	}
	var s_rectitype = document.all.myform.s_rectitype.value;
	
	if (s_rectitype != "") {
		document.all.myform.s_rectitype_view.value = s_rectitype;
	} else {
		document.all.myform.s_rectitype.value = "";
	}

	var s_rectilevel = document.all.myform.s_rectilevel.value;
	if (s_rectilevel != "") {
		document.all.myform.s_rectilevel_view.value = s_rectilevel;
	} else {
		document.all.myform.s_rectilevel.value = "";
	}
	
    var s_searchForAllFlg = document.all.myform.s_searchForAllFlg.value;
    if (s_searchForAllFlg != "") {
        document.all.myform.s_searchForAllFlg_view.value = s_searchForAllFlg;
    } else {
        document.all.myform.s_searchForAllFlg.value = "本人相关隐患信息";
    }
	
	$("#s_checkdate_view").datebox("setValue",document.all.myform.s_checkdate.value);
	
	
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

隐患信息管理 (上级检查组安全检查隐患信息)主页<BR>
<table  border=0 width = 100% >
  <tr >
  <td width = 33%>
	
	<a href="javascript:toRectiNew();" class="easyui-linkbutton"  style="padding:10px 0px;width:100%;">
		<span style="font-size:11pt;font-family:宋体;mso-bidi-font-family:宋体;">
			自查隐患信息</span>
	</a>
	
  </td>
  <td width = 33%>
  	
	<a href="javascript:toRectiCheck();" class="easyui-linkbutton"  style="padding:10px 0px;width:100%;">
		<span style="font-size:11pt;font-family:宋体;mso-bidi-font-family:宋体;">
			上级检查组安全检查隐患信息</span>
		</a>
	
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
		受检单位(部门): <input name="s_danwei" value="${s_danwei}" class="rectimyinput" style="width:110px" maxlength=20>
		检查地点: <input name="s_location" value="${s_location}" class="rectimyinput" style="width:110px" maxlength=20>
		隐患类型: 
		<select id = "s_rectitype_view" name = "s_rectitype_view" onchange="javascript:change_s_rectitype();">
	    <option value="" ></option>
        <c:forEach items="${yinhuanTypeList }" var="yinhuanTypeListItem">
         <option value="${yinhuanTypeListItem.id }" >${yinhuanTypeListItem.id }</option>
        </c:forEach>
        </select> 
        <input type="hidden" name="s_rectitype" value="${s_rectitype}" class="rectimyinput" style="width:110px;" maxlength=12>
		
		隐患等级:
		<select id = "s_rectilevel_view" name = "s_rectilevel_view" onchange="javascript:change_s_rectilevel();">
	    <option value="" ></option>
	    <option value="重大隐患" >重大隐患</option>
	    <option value="非重大隐患" >非重大隐患</option>
        </select>  
		<input type="hidden" name="s_rectilevel" value="${s_rectilevel}" class="rectimyinput" style="width:110px" maxlength=20>
		检查时间: 
		<input name="s_checkdate_view" id ="s_checkdate_view"  class="easyui-datebox" data-options="onSelect:onSelect" style='width:110.0pt;'></input>
  		<input type="hidden" name="s_checkdate" value="${s_checkdate}" class="rectimyinput" style="width:110px;" maxlength=12>
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
	<table id="listTable" class="easyui-datagrid" ></table>

	</form>
	
		

</body>
</html>
	