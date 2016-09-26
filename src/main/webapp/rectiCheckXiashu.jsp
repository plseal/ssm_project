<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@include  file="../sessiontimeout.jsp"%>
<%@ page import="com.lin.entity.RectiCheckEntity" %>
<%@ page import="com.lin.entity.UserEntity" %>
<%@ page import="com.lin.util.common" %>
<html>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/jquery-easyui-1.4.3/themes/default/easyui.css" />
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/jquery-easyui-1.4.3/themes/icon.css" />
<script type="text/javascript" src="${pageContext.request.contextPath}/jquery-easyui-1.4.3/jquery.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/jquery-easyui-1.4.3/jquery.easyui.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/jquery-easyui-1.4.3/locale/easyui-lang-zh_CN.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/common.js"></script>

<script type="text/javascript">
function pass(strResult,strTrans,strOkmessage){
	document.all.myform.action="${pageContext.request.contextPath}/rectiCheckXiashu/complete.do?strResult="+strResult+"&strTrans="+strTrans+"&strOkmessage="+strOkmessage;
	document.all.myform.submit();
}
function pass_fenguanlingdao(strResult,strTrans,strOkmessage){
    if (document.all.myform.fenguanlingdao.value=="") {
        alert("请选择分管领导")
        document.all.myform.fenguanlingdao_view.focus();
        return;
    }
    document.all.myform.action="${pageContext.request.contextPath}/rectiCheckXiashu/complete.do?strResult="+strResult+"&strTrans="+strTrans+"&strOkmessage="+strOkmessage;
    document.all.myform.submit();
}
function printword(){
	
	document.all.myform.action="${pageContext.request.contextPath}/rectiCheckXiashu/printword.do";
	document.all.myform.submit();
	
}

function start_(){
	
	if (document.all.myform.becheckedbuleader.value=="") {
		alert("请选择受检部门负责人")
		document.all.myform.becheckedbuleader_view.focus();
		return;
	}
	document.all.myform.action="${pageContext.request.contextPath}/rectiCheckXiashu/start_.do";
	document.all.myform.submit();
}
function save_(){
	
	
	document.all.myform.action="${pageContext.request.contextPath}/rectiCheckXiashu/save_.do";
	document.all.myform.submit();
}
function onload_(){
	
	
	//隐患类型
	var rectitype = document.all.myform.rectitype.value;
	if (rectitype != "") {
		document.all.myform.rectitype_view.value = rectitype;
	} else {
		document.all.myform.rectitype.value = "车辆隐患";
	}
	
	//受检部门
	var danwei = document.all.myform.danwei.value;
	
	if (danwei != "") {
		document.all.myform.danwei_view.value = danwei;
	} else {
        
        document.all.myform.danwei.value = "营销科";
	}
	//检查地点1
	var location1 = document.all.myform.location1.value;
	if (location1 != "") {
		document.all.myform.location1_view.value = location1;
	} else {
		document.all.myform.location1.value = "";
	}
    //检查地点2
    var location2 = document.all.myform.location2.value;
    if (location2 != "") {
        document.all.myform.location2_view.value = location2;
    } else {
        document.all.myform.location2.value = "";
    }
    //检查地点3
    var location3 = document.all.myform.location3.value;
    if (location3 != "") {
        document.all.myform.location3_view.value = location3;
    } else {
        document.all.myform.location3.value = "";
    }
    //受检单位主要负责人
    var becheckedbuleader = document.all.myform.becheckedbuleader.value;
    if (becheckedbuleader != "") {
        document.all.myform.becheckedbuleader_view.value = becheckedbuleader;
    } else {
        document.all.myform.becheckedbuleader.value = "";
    }
	
	$("#checkdate_view").datebox("setValue",document.all.myform.checkdate.value);
	
	$("#deadlinedate_view").datebox("setValue",document.all.myform.deadlinedate.value);
	
		
}
function change_danwei() {
	document.all.myform.danwei.value =  $("#danwei_view").val();

}

function change_rectitype() {
    document.all.myform.rectitype.value =  $("#rectitype_view").val();
}
function change_fenguanlingdao() {
    document.all.myform.fenguanlingdao.value =  $("#fenguanlingdao_view").val();
}

function change_becheckedbuleader() {
    document.all.myform.becheckedbuleader.value =  $("#becheckedbuleader_view").val();
}
function change_location1() {
    document.all.myform.location1.value =  $("#location1_view").val();
}
function change_location2() {
    document.all.myform.location2.value =  $("#location2_view").val();
}
function change_location3() {
    document.all.myform.location3.value =  $("#location3_view").val();
}
function onSelect_checkdate(date){
	document.all.myform.checkdate.value = date.format("yyyy-MM-dd");
}
function onSelect_deadlinedate(date){
	document.all.myform.deadlinedate.value = date.format("yyyy-MM-dd");
}



function goto_(target){
	
	document.all.myform.action="${pageContext.request.contextPath}/rectiCheck/toListRectiCheck.do?listRectiAc=this_month";
	document.all.myform.submit(); 
}

</script>

<style>
.rectimyinput {
font-family:方正小标宋简体;
font-size: 12pt;
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

<body lang=ZH-CN style='text-justify-trim:punctuation' onload="javascript:onload_();">
  
<%
RectiCheckEntity entity = new RectiCheckEntity();
entity = (RectiCheckEntity)request.getAttribute("entity");
String taskname = entity.getTaskname();

String id      = (String)session.getAttribute("id");
String name    = (String)session.getAttribute("name");
String role = (String)session.getAttribute("role");

%>
<form method="post" id="myform"  >
<input name="javaid" id="javaid" value="${javaid }" type="hidden">
<input name="ac" id="ac" value="${ac }" type="hidden">
<input name="taskid" id="taskid" value="${taskid }" type="hidden">
<input name="taskname" id="taskname" value="${taskname }" type="hidden">
<input name="processid" id="processid" value="${processid }" type="hidden">
<input name="starter" id="starter" value="${starter}" type="hidden">
<input name="rectilevel" id="rectilevel" value="${rectilevel}" type="hidden">
<input name="pageid" id="pageid" value="rectiCheckXiashu.jsp" type="hidden">
<input name="role" id="role" value="<%=role %>" type="hidden">
<input name="user_danwei" value="${user_danwei}" type="hidden">

<table class=MsoNormalTable border=0 cellspacing=0 cellpadding=0 width=634
 style='width:475.7pt;border-collapse:collapse'>
 
  
 <tr >
  <td width=28 style='width:21.0pt;height:30.0pt;border:solid windowtext 0.0pt;border-top:
  none;padding:0cm 5.4pt 0cm 5.4pt;'>
  <p class=MsoNormal align=center style='text-align:center;text-autospace:none'><span
  style='font-family:宋体'></span></p>
  </td>
  <td width=459 colspan=6 valign=bottom style='width:344.4pt;border-top:none;
  border-left:none;border-bottom:solid windowtext 0.0pt;border-right:solid windowtext 0.0pt;
  padding:0cm 5.4pt 0cm 5.4pt;'>
 <p class=MsoNormal  style='text-align:center;text-autospace:none'><span
  style='font-size:22.0pt;font-family:方正小标宋简体'>安全检查记录表</span></p>
  </td>
  <td width=117 valign=top style='width:88.0pt;border-top:none;border-left:
  none;border-bottom:solid windowtext 0.0pt;border-right:solid windowtext 0.0pt;
  padding:5.4pt 5.4pt 5.4pt 5.4pt'>
  <a href="javascript:printword();" class="easyui-linkbutton" style="padding:10px 0px;height:40px;width:110px">输出Word</a>
  </td>
 </tr>
 
 <tr >
  <td  colspan=7 valign=middle style='border:solid windowtext 0pt;
  padding:0cm 5.4pt 0cm 5.4pt'nowrap="nowrap">
  <p class=MsoNormal  style='text-align:left;text-autospace:none'><span
  style='font-size:14.0pt;font-family:黑体'>JZYC-AQBZH-CX-18-JL-01</span></p>
  </td>
  <td colspan=1  style='text-align:right;width:72.0pt;border:solid windowtext 0pt;
  border-left:none;padding:0cm 5.4pt 0cm 5.4pt'nowrap="nowrap">
  
  <%
  //发起人可以修改
  if (id.equals(entity.getStarter())&& common.isEmpty((entity.getLeaderokdate()))) {
  %>
  <input type="text" name="no" value="${no}" class="rectimyinput" style="width:110px;" maxlength=12>
  <%
  } else{
	  
  %>
  ${no}
  <input type="hidden" name="no" value="${no}" class="rectimyinput" style="width:110px;" maxlength=12>
  <%  
  }
  %>
  </td>
 </tr>
 
 
 
 <tr >
  <td  colspan=2 valign=middle style='height:25pt;border:solid windowtext 1.0pt;padding:0cm 5.4pt 0cm 5.4pt'nowrap="nowrap">
  <p class=MsoNormal align=center style='text-align:center;text-autospace:none'><span
  style='font-family:宋体'>受检部门</span></p>
  </td>
  <td width=145 colspan=4 valign=bottom style='width:110.0pt;border:solid windowtext 1.0pt;
  border-left:none;padding:0cm 5.4pt 2.4pt 5.4pt'>
  <p class=MsoNormal align=left style='text-align:left;text-autospace:none'><span
  style='font-family:仿宋_GB2312;'>
  
  <%
  //发起人可以修改
  if (id.equals(entity.getStarter())&& common.isEmpty((entity.getLeaderokdate()))) {
  %>
  <select id = "danwei_view" name = "danwei_view" onchange="javascript:change_danwei();">
  
    <c:forEach items="${beCheckedBuList }" var="beCheckedBuListItem">
      <option value="${beCheckedBuListItem.id }" >${beCheckedBuListItem.id }</option>
    </c:forEach>
  
  <option value="其他" >其他</option> 
  </select> 
  <input type="text" name="danwei" value="${danwei}" class="rectimyinput" style="width:180px;" maxlength=12>
  <%
  } else{
  %>
  ${danwei}
  <input type="hidden" name="danwei" value="${danwei}" class="rectimyinput" style="width:210px;" maxlength=12>
  <%  
  }
  %>
  </span></p>
  
  </td>
  <td width=96 valign=middle style='width:72.0pt;border:solid windowtext 1.0pt;
  border-left:none;padding:0cm 5.4pt 0cm 5.4pt'nowrap="nowrap">
  <p class=MsoNormal align=center style='text-align:center;text-autospace:none'><span
  style='font-family:宋体'>检查时间</span></p>
  </td>
  <td width=117 valign=middle style='width:88.0pt;border:solid windowtext 1.0pt;
  border-left:none;padding:0cm 5.4pt 0cm 5.4pt'>
  <p class=MsoNormal align=center style='text-align:center;text-autospace:none'><span
  style='font-family:仿宋_GB2312'>

  <%
  //发起人可以修改
  if (id.equals(entity.getStarter())&& common.isEmpty((entity.getLeaderokdate()))) {
  %>
  <input name="checkdate_view" id ="checkdate_view" class="easyui-datebox" data-options="onSelect:onSelect_checkdate" style='width:110.0pt;'></input>
  <input type="hidden" name="checkdate" value="${checkdate}" class="rectimyinput" style="width:110px;" maxlength=12>
  <%
  } else{
  %>
  ${checkdate}
  <input type="hidden" name="checkdate" value="${checkdate}" class="rectimyinput" style="width:110px;" maxlength=12>
  <%  
  }
  %>
  </span></p>
  </td>
  <td width=30 rowspan=22 style='width:22.0pt;border-top:solid windowtext 1.0pt;border-left:none;border-bottom:
  solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;padding:0cm 5.4pt 0cm 5.4pt'>
  <p class=MsoNormal align=center style='text-align:center;text-autospace:none'><span
  style='font-family:宋体'>第</span></p>
    <p class=MsoNormal align=center style='text-align:center;text-autospace:none'><span
  style='font-family:宋体'>一</span></p>
  <p class=MsoNormal align=center style='text-align:center;text-autospace:none'><span
  style='font-family:宋体'>联</span><span style='font-family:宋体'></span></p>
  <p class=MsoNormal align=center style='text-align:center;text-autospace:none'><span
  style='font-family:宋体'>︰</span><span style='font-family:宋体'></span></p>
  <p class=MsoNormal align=center style='text-align:center;text-autospace:none'><span
  style='font-family:宋体'>存</span></p>
  <p class=MsoNormal align=center style='text-align:center;text-autospace:none'><span
  style='font-family:宋体'>根</span></p>
  <p class=MsoNormal align=center style='text-align:center;text-autospace:none'><span
  style='font-family:宋体'>联</span></p>
  </td>
 </tr>
 
  <tr>
  <td width=146 colspan=2 valign=middle style='width:109.4pt;height:25pt;border:solid windowtext 1.0pt;
  border-top:none;padding:0cm 5.4pt 0cm 5.4pt'>
  <p class=MsoNormal align=center style='text-align:center;text-autospace:none'><span
  style='font-family:宋体'>检查地点</span></p>
  </td>
  <td width=459 colspan=6 valign=middle style='width:344.0pt;border-top:none;
  border-left:none;border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
  padding:0cm 5.4pt 0cm 5.4pt'>
  
  <%
  //发起人可以修改
  if (id.equals(entity.getStarter())&& common.isEmpty((entity.getLeaderokdate()))) {
  %>
    <select id = "location1_view" name = "location1_view" onchange="javascript:change_location1();">
        <c:forEach items="${locationList }" var="locationListItem">
         <option value="${locationListItem.id }" >${locationListItem.id }</option>
        </c:forEach>
    </select> 
    <select id = "location2_view" name = "location2_view" onchange="javascript:change_location2();">
        <c:forEach items="${locationList }" var="locationListItem">
         <option value="${locationListItem.id }" >${locationListItem.id }</option>
        </c:forEach>
    </select> 
    <select id = "location3_view" name = "location3_view" onchange="javascript:change_location3();">
        <c:forEach items="${locationList }" var="locationListItem">
         <option value="${locationListItem.id }" >${locationListItem.id }</option>
        </c:forEach>
      <option value="其他地点" >其他地点</option>
    </select> 
     
     <input type="hidden" name="location1" value="${location1}" class="rectimyinput" style="width:210px;" maxlength=12>
     <input type="hidden" name="location2" value="${location2}" class="rectimyinput" style="width:210px;" maxlength=12>
     <input type="text" name="location3" value="${location3}" class="rectimyinput" style="width:80px;" maxlength=12>
  <%
  } else{
  %>
  ${location1}&nbsp;&nbsp;${location2}&nbsp;&nbsp;${location3}
  <input type="hidden" name="location1" value="${location1}" class="rectimyinput" style="width:110px;" maxlength=12>
  <input type="hidden" name="location2" value="${location2}" class="rectimyinput" style="width:110px;" maxlength=12>
  <input type="hidden" name="location3" value="${location3}" class="rectimyinput" style="width:110px;" maxlength=12>
  <%  
  }
  %>
  </td>
 </tr>
 
 <tr>
  <td width=146 colspan=2 valign=middle style='width:109.4pt;height:20pt;border:solid windowtext 1.0pt;
  border-top:none;padding:0cm 5.4pt 0cm 5.4pt'>
  <p class=MsoNormal align=center style='text-align:center;text-autospace:none'><span
  style='font-family:宋体'>整改期限</span></p>
  </td>
  <td width=245 colspan=4 valign=middle style='width:184.0pt;border-top:none;
  border-left:none;border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
  padding:0cm 5.4pt 0cm 5.4pt'>
  <p class=MsoNormal align=left style='text-align:left;text-autospace:none'><span
  lang=EN-US>

  
  
  <%
  //发起人可以修改
  if (id.equals(entity.getStarter())&& common.isEmpty((entity.getLeaderokdate()))) {
  %>
  <input name="deadlinedate_view" id ="deadlinedate_view" class="easyui-datebox" data-options="onSelect:onSelect_deadlinedate" style='width:110.0pt;'></input>
  <input type="hidden" name="deadlinedate" value="${deadlinedate}" class="rectimyinput" style="width:110px;" maxlength=12>
  <%
  } else{
  %>
  ${deadlinedate}
  <input type="hidden" name="deadlinedate" value="${deadlinedate}" class="rectimyinput" style="width:110px;" maxlength=12>
  <%  
  }
  %>
  </span></p>
  </td>
  <td width=96 valign=middle style='width:72.0pt;border-top:none;border-left:none;
  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
  padding:0cm 5.4pt 0cm 5.4pt'>
  <p class=MsoNormal align=center style='text-align:center;text-autospace:none'><span
  style='font-family:宋体'>记录人员</span></p>
  </td>
  <td width=117 valign=middle style='width:88.0pt;border-top:none;border-left:
  none;border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
  padding:0cm 5.4pt 0cm 5.4pt'>
  <p class=MsoNormal align=center style='text-align:center;text-autospace:none'><span
  lang=EN-US>
  
  <%
  //发起人可以修改
  if (id.equals(entity.getStarter())&& common.isEmpty((entity.getLeaderokdate()))) {
  %>
  <input type="text" name="name" value="${name}" class="rectimyinput" style="width:110px;" maxlength=12>
  <%
  } else{
  %>
  ${name}
  
  <input type="hidden" name="name" value="${name}" class="rectimyinput" style="width:110px;" maxlength=12>
  <%  
  }
  %>
  </span></p>
  </td>
 </tr>
 <tr>
  <td width=146 colspan=2 valign=middle style='width:109.4pt;height:20pt;border:solid windowtext 1.0pt;
  border-top:none;padding:0cm 5.4pt 0cm 5.4pt'>
  <p class=MsoNormal align=center style='text-align:center;text-autospace:none'><span
  style='font-family:宋体'>检查人员</span></p>
  </td>
  <td width=459 colspan=6 valign=middle style='width:344.0pt;border-top:none;
  border-left:none;border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
  padding:0cm 5.4pt 0cm 5.4pt'>
  
  <%
  //发起人可以修改
  if (id.equals(entity.getStarter())&& common.isEmpty((entity.getLeaderokdate()))) {
  %>
  <input type="text" name="checkname" value="${checkname}" class="rectimyinput" style="width:110px;" maxlength=12>
  <%
  } else{
  %>
  ${checkname}
  
  <input type="hidden" name="checkname" value="${checkname}" class="rectimyinput" style="width:110px;" maxlength=12>
  <%  
  }
  %>
  </td>
 </tr>
 <tr style='height:45pt'>
  <td width=28 style='width:21.0pt;border:solid windowtext 1.0pt;border-top:
  none;padding:0cm 5.4pt 0cm 5.4pt;'>
  <p class=MsoNormal align=center style='text-align:center;text-autospace:none'><span
  style='font-family:宋体'>检查内容</span></p>
  </td>
  <td width=459 colspan=6 valign=bottom style='width:344.4pt;border-top:none;
  border-left:none;border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
  padding:0cm 5.4pt 2.4pt 5.4pt;'>
  
  <%
  //发起人可以修改
  if (id.equals(entity.getStarter())&& common.isEmpty((entity.getLeaderokdate()))) {
  %>
  <input type="hidden" name="content2" value=""  style="width:560px;" maxlength=40>
  <textarea id="content" rows=3 name="content" style="width:560px;"maxlength="250">${content}</textarea>
  <%
  } else{
  %>
  ${content}
  <input type="hidden" name="content" value="${content}" class="rectimyinput" style="width:560px;" maxlength=40>
  <%  
  }
  %>
  </td>
  <td width=117 valign=top style='width:88.0pt;border-top:none;border-left:
  none;border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
  padding:0cm 5.4pt 0cm 5.4pt;'>
  <p class=MsoNormal align=center style='text-align:center;text-autospace:none'><span
  lang=EN-US>&nbsp;</span></p>
  </td>
 </tr>
 <tr style='height:25pt'>
  <td width=28 style='width:21.0pt;border:solid windowtext 1.0pt;border-top:
  none;padding:0cm 5.4pt 0cm 5.4pt;'>
  <p class=MsoNormal align=center style='text-align:center;text-autospace:none'><span
  style='font-family:宋体'>隐患类型</span></p>
  </td>
  <td width=459 colspan=6 valign=bottom style='width:344.4pt;border-top:none;
  border-left:none;border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
  padding:0cm 5.4pt 3pt 5.4pt;'>
    
  <%
  //发起人可以修改
  if (id.equals(entity.getStarter())&& common.isEmpty((entity.getLeaderokdate()))) {
  %>
      <select id = "rectitype_view" name = "rectitype_view" onchange="javascript:change_rectitype();">
        <c:forEach items="${yinhuanTypeList }" var="yinhuanTypeListItem">
         <option value="${yinhuanTypeListItem.id }" >${yinhuanTypeListItem.id }</option>
        </c:forEach>
      </select>
      <input type="hidden" name="rectitype" value="${rectitype}" class="rectimyinput" style="width:560px;" maxlength=40> 
  <%
  } else{
  %>
  ${rectitype}
  <input type="hidden" name="rectitype" value="${rectitype}" class="rectimyinput" style="width:560px;" maxlength=40>
  <%  
  }
  %>
    
    
    
  </td>
  <td width=117 valign=top style='width:88.0pt;border-top:none;border-left:
  none;border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
  padding:0cm 5.4pt 0cm 5.4pt;'>
  <p class=MsoNormal align=center style='text-align:center;text-autospace:none'><span
  lang=EN-US>&nbsp;</span></p>
  </td>
 </tr>
 <tr style='height:19.5pt'>
  <td width=28 rowspan=14 style='width:21.0pt;border:solid windowtext 1.0pt;
  border-top:none;padding:0cm 5.4pt 0cm 5.4pt;height:19.5pt'>
  <p class=MsoNormal align=center style='text-align:center;text-autospace:none'><span
  style='font-family:宋体'>整改事项</span></p>
  </td>
  <td width=459 colspan=6 valign=middle style='width:344.4pt;border-top:none;
  border-left:none;border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
  padding:0cm 5.4pt 0cm 5.4pt;height:30.5pt'>
  <p class=MsoNormal align=center style='text-align:center;text-autospace:none'><span
  style='font-family:宋体'>隐患整改事项</span></p>
  </td>
  <td width=117 valign=top style='width:88.0pt;border-top:none;border-left:
  none;border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
  padding:0cm 5.4pt 0cm 5.4pt;height:19.5pt'>
  <p class=MsoNormal align=center style='text-align:center;text-autospace:none'><span
  lang=EN-US>&nbsp;</span></p>
  </td>
 </tr>
 <tr style='height:18.75pt'>
  <td width=459 colspan=6 valign=top style='width:344.4pt;border-top:none;
  border-left:none;border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
  padding:0cm 5.4pt 0cm 5.4pt;height:18.75pt'>
  <p class=MsoNormal align=center style='text-align:left;text-autospace:none'><span
  lang=EN-US>
  
  
  <%
  //发起人可以修改
  if (id.equals(entity.getStarter())&& common.isEmpty((entity.getLeaderokdate()))) {
  %>
  <input type="text" name="recti1" value="${recti1}" class="rectimyinput" style="width:560px;" maxlength=40>
  <%
  } else{
  %>
  ${recti1}
  
  <input type="hidden" name="recti1" value="${recti1}" class="rectimyinput" style="width:560px;" maxlength=40>
  <%  
  }
  %>
  </span></p>
  </td>
  <td width=117 valign=top style='width:88.0pt;border-top:none;border-left:
  none;border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
  padding:0cm 5.4pt 0cm 5.4pt;height:18.75pt'>
  <p class=MsoNormal align=center style='text-align:center;text-autospace:none'><span
  lang=EN-US>&nbsp;</span></p>
  </td>
 </tr>
 <tr style='height:18.0pt'>
  <td width=459 colspan=6 valign=top style='width:344.4pt;border-top:none;
  border-left:none;border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
  padding:0cm 5.4pt 0cm 5.4pt;height:18.0pt'>
  <p class=MsoNormal align=center style='text-align:left;text-autospace:none'><span
  lang=EN-US>
  <%
  //发起人可以修改
  if (id.equals(entity.getStarter())&& common.isEmpty((entity.getLeaderokdate()))) {
  %>
  <input type="text" name="recti2" value="${recti2}" class="rectimyinput" style="width:560px;" maxlength=40>
  <%
  } else{
  %>
  ${recti2}
  
  <input type="hidden" name="recti2" value="${recti2}" class="rectimyinput" style="width:560px;" maxlength=40>
  <%  
  }
  %>
  
  </span></p>
  </td>
  <td width=117 valign=top style='width:88.0pt;border-top:none;border-left:
  none;border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
  padding:0cm 5.4pt 0cm 5.4pt;height:18.0pt'>
  <p class=MsoNormal align=center style='text-align:center;text-autospace:none'><span
  lang=EN-US>&nbsp;</span></p>
  </td>
 </tr>
 <tr style='height:18.0pt'>
  <td width=459 colspan=6 valign=top style='width:344.4pt;border-top:none;
  border-left:none;border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
  padding:0cm 5.4pt 0cm 5.4pt;height:18.0pt'>
  <p class=MsoNormal align=center style='text-align:left;text-autospace:none'><span
  lang=EN-US>
  
  <%
  //发起人可以修改
  if (id.equals(entity.getStarter())&& common.isEmpty((entity.getLeaderokdate()))) {
  %>
  <input type="text" name="recti3" value="${recti3}" class="rectimyinput" style="width:560px;" maxlength=40>
  <%
  } else{
  %>
  ${recti3}
  
  <input type="hidden" name="recti3" value="${recti3}" class="rectimyinput" style="width:560px;" maxlength=40>
  <%  
  }
  %>
  </span></p>
  </td>
  <td width=117 valign=top style='width:88.0pt;border-top:none;border-left:
  none;border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
  padding:0cm 5.4pt 0cm 5.4pt;height:18.0pt'>
  <p class=MsoNormal align=center style='text-align:center;text-autospace:none'><span
  lang=EN-US>&nbsp;</span></p>
  </td>
 </tr>
 <tr style='height:18.0pt'>
  <td width=459 colspan=6 valign=top style='width:344.4pt;border-top:none;
  border-left:none;border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
  padding:0cm 5.4pt 0cm 5.4pt;height:18.0pt'>
  <p class=MsoNormal align=center style='text-align:left;text-autospace:none'><span
  lang=EN-US>
 <%
  //发起人可以修改
  if (id.equals(entity.getStarter())&& common.isEmpty((entity.getLeaderokdate()))) {
  %>
  <input type="text" name="recti4" value="${recti4}" class="rectimyinput" style="width:560px;" maxlength=40>
  <%
  } else{
  %>
  ${recti4}
  
  <input type="hidden" name="recti4" value="${recti4}" class="rectimyinput" style="width:560px;" maxlength=40>
  <%  
  }
  %>
  
  </span></p>
  </td>
  <td width=117 valign=top style='width:88.0pt;border-top:none;border-left:
  none;border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
  padding:0cm 5.4pt 0cm 5.4pt;height:18.0pt'>
  <p class=MsoNormal align=center style='text-align:center;text-autospace:none'><span
  lang=EN-US>&nbsp;</span></p>
  </td>
 </tr>
 <tr style='height:18.0pt'>
  <td width=459 colspan=6 valign=middle style='width:344.4pt;border-top:none;
  border-left:none;border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
  padding:0cm 5.4pt 0cm 5.4pt;height:30.0pt'>
  <p class=MsoNormal align=center style='text-align:center;text-autospace:none'><span
  style='font-family:宋体'>一般问题整改事项</span></p>
  </td>
  <td width=117 valign=top style='width:88.0pt;border-top:none;border-left:
  none;border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
  padding:0cm 5.4pt 0cm 5.4pt;height:18.0pt'>
  <p class=MsoNormal align=center style='text-align:center;text-autospace:none'><span
  lang=EN-US>&nbsp;</span></p>
  </td>
 </tr>
 <tr style='height:18.0pt'>
  <td width=459 colspan=6 valign=top style='width:344.4pt;border-top:none;
  border-left:none;border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
  padding:0cm 5.4pt 2.4pt 5.4pt;height:18.0pt'>
  <p class=MsoNormal align=center style='text-align:left;text-autospace:none'><span
  lang=EN-US>

  
   <%
  //发起人可以修改
  if (id.equals(entity.getStarter())&& common.isEmpty((entity.getLeaderokdate()))) {
  %>
  <input type="text" name="nrecti1" value="${nrecti1}" class="rectimyinput" style="width:560px;" maxlength=40>
  <%
  } else{
  %>
  ${nrecti1}
  
  <input type="hidden" name="nrecti1" value="${nrecti1}" class="rectimyinput" style="width:560px;" maxlength=40>
  <%  
  }
  %>
  </span></p>
  </td>
  <td width=117 valign=top style='width:88.0pt;border-top:none;border-left:
  none;border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
  padding:0cm 5.4pt 2.4pt 5.4pt;height:18.0pt'>
  <p class=MsoNormal align=center style='text-align:center;text-autospace:none'><span
  lang=EN-US>&nbsp;</span></p>
  </td>
 </tr>
 <tr style='height:18.0pt'>
  <td width=459 colspan=6 valign=top style='width:344.4pt;border-top:none;
  border-left:none;border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
  padding:0cm 5.4pt 2.4pt 5.4pt;height:18.0pt'>
  <p class=MsoNormal align=center style='text-align:left;text-autospace:none'><span
  lang=EN-US>
  
     <%
  //发起人可以修改
  if (id.equals(entity.getStarter())&& common.isEmpty((entity.getLeaderokdate()))) {
  %>
  <input type="text" name="nrecti2" value="${nrecti2}" class="rectimyinput" style="width:560px;" maxlength=40>
  <%
  } else{
  %>
  ${nrecti2}
  
  <input type="hidden" name="nrecti2" value="${nrecti2}" class="rectimyinput" style="width:560px;" maxlength=40>
  <%  
  }
  %>
  </span></p>
  </td>
  <td width=117 valign=top style='width:88.0pt;border-top:none;border-left:
  none;border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
  padding:0cm 5.4pt 2.4pt 5.4pt;height:18.0pt'>
  <p class=MsoNormal align=center style='text-align:center;text-autospace:none'><span
  lang=EN-US>&nbsp;</span></p>
  </td>
 </tr>
 <tr style='height:18.0pt'>
  <td width=459 colspan=6 valign=top style='width:344.4pt;border-top:none;
  border-left:none;border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
  padding:0cm 5.4pt 2.4pt 5.4pt;height:18.0pt'>
  <p class=MsoNormal align=center style='text-align:left;text-autospace:none'><span
  lang=EN-US>
  
  <%
  //发起人可以修改
  if (id.equals(entity.getStarter())&& common.isEmpty((entity.getLeaderokdate()))) {
  %>
  <input type="text" name="nrecti3" value="${nrecti3}" class="rectimyinput" style="width:560px;" maxlength=40>
  <%
  } else{
  %>
  ${nrecti3}
  
  <input type="hidden" name="nrecti3" value="${nrecti3}" class="rectimyinput" style="width:560px;" maxlength=40>
  <%  
  }
  %>
  </span></p>
  </td>
  <td width=117 valign=top style='width:88.0pt;border-top:none;border-left:
  none;border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
  padding:0cm 5.4pt 2.4pt 5.4pt;height:18.0pt'>
  <p class=MsoNormal align=center style='text-align:center;text-autospace:none'><span
  lang=EN-US>&nbsp;</span></p>
  </td>
 </tr>
 <tr style='height:18.0pt'>
  <td width=459 colspan=6 valign=top style='width:344.4pt;border-top:none;
  border-left:none;border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
  padding:0cm 5.4pt 2.4pt 5.4pt;height:18.0pt'>
  <p class=MsoNormal align=center style='text-align:left;text-autospace:none'><span
  lang=EN-US>
  
     <%
  //发起人可以修改
  if (id.equals(entity.getStarter())&& common.isEmpty((entity.getLeaderokdate()))) {
  %>
  <input type="text" name="nrecti4" value="${nrecti4}" class="rectimyinput" style="width:560px;" maxlength=40>
  <%
  } else{
  %>
  ${nrecti4}
  
  <input type="hidden" name="nrecti4" value="${nrecti4}" class="rectimyinput" style="width:560px;" maxlength=40>
  <%  
  }
  %>
  </span></p>
  </td>
  <td width=117 valign=top style='width:88.0pt;border-top:none;border-left:
  none;border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
  padding:0cm 5.4pt 2.4pt 5.4pt;height:18.0pt'>
  <p class=MsoNormal align=center style='text-align:center;text-autospace:none'><span
  lang=EN-US>&nbsp;</span></p>
  </td>
 </tr>
 <tr style='height:18.0pt'>
  <td width=459 colspan=6 valign=top style='width:344.4pt;border-top:none;
  border-left:none;border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
  padding:0cm 5.4pt 2.4pt 5.4pt;height:18.0pt'>
  <p class=MsoNormal align=center style='text-align:left;text-autospace:none'><span
  lang=EN-US>
  
  <%
  //发起人可以修改
  if (id.equals(entity.getStarter())&& common.isEmpty((entity.getLeaderokdate()))) {
  %>
  <input type="text" name="nrecti5" value="${nrecti5}" class="rectimyinput" style="width:560px;" maxlength=40>
  <%
  } else{
  %>
  ${nrecti5}
  
  <input type="hidden" name="nrecti5" value="${nrecti5}" class="rectimyinput" style="width:560px;" maxlength=40>
  <%  
  }
  %>
  </span></p>
  </td>
  <td width=117 valign=top style='width:88.0pt;border-top:none;border-left:
  none;border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
  padding:0cm 5.4pt 2.4pt 5.4pt;height:18.0pt'>
  <p class=MsoNormal align=center style='text-align:center;text-autospace:none'><span
  lang=EN-US>&nbsp;</span></p>
  </td>
 </tr>
 <tr style='height:18.0pt'>
  <td width=459 colspan=6 valign=top style='width:344.4pt;border-top:none;
  border-left:none;border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
  padding:0cm 5.4pt 2.4pt 5.4pt;height:18.0pt'>
  <p class=MsoNormal align=center style='text-align:left;text-autospace:none'><span
  lang=EN-US>
  
     <%
  //发起人可以修改
  if (id.equals(entity.getStarter())&& common.isEmpty((entity.getLeaderokdate()))) {
  %>
  <input type="text" name="nrecti6" value="${nrecti6}" class="rectimyinput" style="width:560px;" maxlength=40>
  <%
  } else{
  %>
  ${nrecti6}
  
  <input type="hidden" name="nrecti6" value="${nrecti6}" class="rectimyinput" style="width:560px;" maxlength=40>
  <%  
  }
  %>
  </span></p>
  </td>
  <td width=117 valign=top style='width:88.0pt;border-top:none;border-left:
  none;border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
  padding:0cm 5.4pt 2.4pt 5.4pt;height:18.0pt'>
  <p class=MsoNormal align=center style='text-align:center;text-autospace:none'><span
  lang=EN-US>&nbsp;</span></p>
  </td>
 </tr>
 <tr style='height:13.5pt'>
  <td width=459 colspan=6 valign=top style='width:344.4pt;border:none;
  border-right:solid windowtext 1.0pt;padding:0cm 5.4pt 2.4pt 5.4pt;height:13.5pt'>
  <p class=MsoNormal align=center style='text-align:left;text-autospace:none'><span
  lang=EN-US>
  
  <%
  //发起人可以修改
  if (id.equals(entity.getStarter())&& common.isEmpty((entity.getLeaderokdate()))) {
  %>
  <input type="text" name="nrecti7" value="${nrecti7}" class="rectimyinput" style="width:560px;" maxlength=40>
  <%
  } else{
  %>
  ${nrecti7}
  
  <input type="hidden" name="nrecti7" value="${nrecti7}" class="rectimyinput" style="width:560px;" maxlength=40>
  <%  
  }
  %>
  </span></p>
  </td>
  <td width=117 valign=top style='width:88.0pt;border:none;border-right:solid windowtext 1.0pt;
  padding:0cm 5.4pt 2.4pt 5.4pt;height:13.5pt'>
  <p class=MsoNormal align=center style='text-align:center;text-autospace:none'><span
  lang=EN-US>&nbsp;</span></p>
  </td>
 </tr>
 <tr style='height:6.9pt'>
  <td width=459 colspan=6 valign=top style='width:344.4pt;border-top:solid windowtext 1.0pt;
  border-left:none;border-bottom:none;border-right:solid windowtext 1.0pt;
  padding:0cm 5.4pt 2.4pt 5.4pt;height:6.9pt'>
  <p class=MsoNormal align=center style='text-align:left;text-autospace:none'><span
  lang=EN-US>
  
  <%
  //发起人可以修改
  if (id.equals(entity.getStarter())&& common.isEmpty((entity.getLeaderokdate()))) {
  %>
  <input type="text" name="nrecti8" value="${nrecti8}" class="rectimyinput" style="width:560px;" maxlength=40>
  <%
  } else{
  %>
  ${nrecti8}
  
  <input type="hidden" name="nrecti8" value="${nrecti8}" class="rectimyinput" style="width:560px;" maxlength=40>
  <%  
  }
  %>
  </span></p>
  </td>
  <td width=117 valign=top style='width:88.0pt;border-top:solid windowtext 1.0pt;
  border-left:none;border-bottom:none;border-right:solid windowtext 1.0pt;
  padding:0cm 5.4pt 0cm 5.4pt;height:6.9pt'>
  <p class=MsoNormal align=center style='text-align:center;text-autospace:none'><span
  lang=EN-US>&nbsp;</span></p>
  </td>
 </tr>
 <tr style='height:62.9pt'>
  <td width=28 style='width:21.0pt;border:solid windowtext 1.0pt;border-top:
  none;padding:0cm 5.4pt 0cm 5.4pt;height:62.9pt'>
  <p class=MsoNormal align=center style='text-align:center;text-autospace:none'><span
  style='font-family:宋体'>整改建议</span></p>
  </td>
  <td width=459 colspan=6 valign=bottom style='width:344.4pt;border:solid windowtext 1.0pt;
  border-left:none;padding:0cm 5.4pt 2.4pt 5.4pt;height:62.9pt'>
  <p class=MsoNormal align=center style='text-align:left;text-autospace:none'><span
  lang=EN-US>
  
  
  
  <%
  //发起人可以修改
  if (id.equals(entity.getStarter())&& common.isEmpty((entity.getLeaderokdate()))) {
  %>
  <input type="text" name="rectisuggest" value="${rectisuggest}" class="rectimyinput" style="width:560px;" maxlength=40>
  <%
  } else{
  %>
  ${rectisuggest}
  
  <input type="hidden" name="rectisuggest" value="${rectisuggest}" class="rectimyinput" style="width:560px;" maxlength=40>
  <%  
  }
  %>
  
  
  
  </span></p>
  </td>
  <td width=117 valign=top style='width:88.0pt;border:solid windowtext 1.0pt;
  border-left:none;padding:0cm 5.4pt 0cm 5.4pt;height:62.9pt'>
  <p class=MsoNormal align=center style='text-align:center;text-autospace:none'><span
  lang=EN-US>&nbsp;</span></p>
  </td>
 </tr>
 <tr style='height:64.35pt'>
  <td width=28 style='width:21.0pt;border:solid windowtext 1.0pt;border-top:
  none;padding:0cm 5.4pt 0cm 5.4pt;height:64.35pt'>
  <p class=MsoNormal align=center style='text-align:center;text-autospace:none'><span
  style='font-family:宋体'>整改情况</span></p>
  </td>
  <td width=459 colspan=6 valign=bottom style='width:344.4pt;border-top:none;
  border-left:none;border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
  padding:0cm 5.4pt 2.4pt 5.4pt;height:64.35pt'>
  <p class=MsoNormal align=center style='text-align:left;text-autospace:none'><span
  lang=EN-US>

  
  <%
  //发起人可以修改
  if (id.equals(entity.getStarter())&& common.isEmpty((entity.getLeaderokdate()))) {
  %>
  <input type="text" name="rectistatus" value="${rectistatus}" class="rectimyinput" style="width:560px;" maxlength=40>
  <%
  } else{
  %>
  ${rectistatus}
  
  <input type="hidden" name="rectistatus" value="${rectistatus}" class="rectimyinput" style="width:560px;" maxlength=40>
  <%  
  }
  %>
  
  </span></p>
  </td>
  <td width=117 style='width:88.0pt;border-top:none;border-left:none;
  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
  padding:0cm 5.4pt 0cm 5.4pt;height:64.35pt'>
  <p class=MsoNormal align=center style='text-align:center;text-autospace:none'><span
  style='font-family:宋体'>整改完成后由追踪验证人验收填写未整改事项或整改不到位事项</span></p>
  </td>
 </tr>
 <tr >
  <td width=180 colspan=3 valign=top style='width:150.4pt;border:solid windowtext 1.0pt;
  border-top:none;padding:0cm 5.4pt 0cm 5.4pt'nowrap="nowrap">
  <p class=MsoNormal align=center style='text-align:center;text-autospace:none'><span
  style='font-family:宋体' >
     受检部门负责人<br/>
     整改通知书确认接收人</span></p>
  
  </td>
  <td  valign=middle style='border-top:none;border-left:
  none;border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
  padding:0cm 5.4pt 0cm 5.4pt' nowrap>


  <%
  //发起人可以修改
  if (id.equals(entity.getStarter())&& common.isEmpty((entity.getLeaderokdate()))) {
  %>
  
  <select id ="becheckedbuleader_view" name = "becheckedbuleader_view" onchange="javascript:change_becheckedbuleader();">
      <option value="" ></option>
    <c:forEach items="${leaderList }" var="leaderListItem">
      <option value="${leaderListItem.id }" >${leaderListItem.id }</option>
    </c:forEach>
  </select>
          
  <input type="hidden" name="becheckedbuleader" value="${becheckedbuleader}" class="rectimyinput" style="width:110px;" maxlength=40>
  <input type="hidden" name="becheckedbuleadername" value="${becheckedbuleadername}" class="rectimyinput" style="width:560px;" maxlength=40>
  <%
  } else{
  %>
  ${becheckedbuleadername}
  
  <input type="hidden" name="becheckedbuleader" value="${becheckedbuleader}" class="rectimyinput" style="width:560px;" maxlength=40>
  <input type="hidden" name="becheckedbuleadername" value="${becheckedbuleadername}" class="rectimyinput" style="width:560px;" maxlength=40>
 
  <%  
  }
  %>



  </td>
  <td width=64 valign=middle style='width:48.0pt;border-top:none;border-left:none;
  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
  padding:0cm 5.4pt 0cm 5.4pt'nowrap="nowrap">
  <p class=MsoNormal align=center style='text-align:left;text-autospace:none'><span
  style='font-family:宋体'>检查组<br/>负责人</span></p>
 
  </td>
  <td width=54 valign=bottom style='width:40.5pt;border-top:none;border-left:none;
  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
  padding:0cm 5.4pt 2.4pt 5.4pt;' nowrap>
  
  
  
  <%
  //发起人可以修改
  if (id.equals(entity.getStarter())&& common.isEmpty((entity.getLeaderokdate()))) {
  %>
  <input type="text" name="recticheckteamleadername" value="${recticheckteamleadername}" class="rectimyinput" style="width:110px;" maxlength=12>
  <%
  } else{
  %>
  ${recticheckteamleadername}
  
  <input type="hidden" name="recticheckteamleadername" value="${recticheckteamleadername}" class="rectimyinput" style="width:110px;" maxlength=12>
  <%  
  }
  %>
  
  </td>
  <td width=26 style='width:42.0pt;border-top:none;border-left:none;border-bottom:
  solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;padding:0cm 5.4pt 0cm 5.4pt'>
  <p class=MsoNormal align=center style='text-align:left;text-autospace:none'><span
  style='font-family:宋体'>追踪&nbsp;<br/>验证人</span></p>
  </td>
  <td width=117 colspan=3  valign=bottom style='width:88.0pt;border-top:none;border-left:
  none;border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
  padding:0cm 5.4pt 2.4pt 5.4pt'>
  
  
    
  <%
  //发起人可以修改
  if (id.equals(entity.getStarter())&& common.isEmpty((entity.getLeaderokdate()))) {
  %>
  <input type="text" name="rectimakesurename" value="${rectimakesurename}" class="rectimyinput" style="width:110px;" maxlength=12>
  <%
  } else{
  %>
  ${rectimakesurename}
  
  <input type="hidden" name="rectimakesurename" value="${rectimakesurename}" class="rectimyinput" style="width:110px;" maxlength=12>
  <%  
  }
  %>
   
    </td>

 </tr>
 
<tr style='mso-yfti-irow:6;'>
  <td width=186 style='width:108.0pt;border:solid windowtext 1.0pt;border-top:
  none;mso-border-left-alt:solid windowtext .5pt;mso-border-bottom-alt:solid windowtext .5pt;
  mso-border-right-alt:solid windowtext .5pt;padding:0cm 5.4pt 0cm 5.4pt;
  height:40pt'>
  <p class=MsoNormal align=center style='text-align:center;mso-pagination:widow-orphan'><span
  style='font-size:12.0pt;font-family:宋体;mso-bidi-font-family:宋体;mso-font-kerning:
  0pt'>部门负责人意见：<span lang=EN-US><o:p></o:p></span></span></p>
  </td>

  <td width=600 colspan=8 valign=bottom style='width:400.0pt;border-top:none;
  border-left:none;border-bottom:solid windowtext 1.0pt;border-right:solid black 1.0pt;'>
  
  <div style="margin-left:15px;margin-top:15px">
  	审批意见：
  	<%
  	//审批人可以修改
  	if ((id.equals(entity.getBecheckedbuleader()) || role.contains("leader")) & "".equals(entity.getLeaderokdate()) ){ 
  	%>
    <input type="text" name="leaderokmessage" value="${leaderokmessage}" class="rectimyinput" style="width:200px;"maxlength=40>
    <%
    }else {
    %>
    ${leaderokmessage}
    <input type="hidden" name="leaderokmessage" value="${leaderokmessage}" class="rectimyinput" style="width:200px;"maxlength=40>
    <%
    }%>
  
  <p class=MsoNormal align=left style='text-align:left;mso-pagination:widow-orphan'>

  <span  style='font-size:12.0pt;font-family:宋体;mso-bidi-font-family:宋体;mso-font-kerning:
  0pt'>签&nbsp;&nbsp;字：<b><span
  lang=EN-US style="color:#0000CD;text-align:right;"><span
  style='mso-spacerun:yes'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
  
  ${leader}
  <input name="leader" id="leader" value="${leader}" type="hidden">
  <input name="leaderokdate" id="leaderokdate" value="${leaderokdate}" type="hidden">
  &nbsp;&nbsp;&nbsp;&nbsp;
  
  </span>${leaderokdate}</span></b></span></p>
  </div>
  </td>
 </tr>
 
 <tr style='mso-yfti-irow:6;'>
  <td width=186 style='width:108.0pt;border:solid windowtext 1.0pt;border-top:
  none;mso-border-left-alt:solid windowtext .5pt;mso-border-bottom-alt:solid windowtext .5pt;
  mso-border-right-alt:solid windowtext .5pt;padding:0cm 5.4pt 0cm 5.4pt;
  height:40pt'>
  <p class=MsoNormal align=center style='text-align:center;mso-pagination:widow-orphan'><span
  style='font-size:12.0pt;font-family:宋体;mso-bidi-font-family:宋体;mso-font-kerning:
  0pt'>分管领导意见：<span lang=EN-US><o:p></o:p></span></span></p>
  </td>

  <td width=600 colspan=8 valign=bottom style='width:400.0pt;border-top:none;
  border-left:none;border-bottom:solid windowtext 1.0pt;border-right:solid black 1.0pt;'>
  
  <div style="margin-left:15px;margin-top:15px">
    审批意见：
    <%
    //审批人可以修改
    if ((id.equals(entity.getManager()) || role.contains("manager")) & "".equals(entity.getManagerokdate()) ){ 
    %>
    <input type="text" name="managerokmessage" value="${managerokmessage}" class="rectimyinput" style="width:200px;"maxlength=40>
    <%
    }else {
    %>
    ${managerokmessage}
    <input type="hidden" name="managerokmessage" value="${managerokmessage}" class="rectimyinput" style="width:200px;"maxlength=40>
    <%
    }%>
  
  <p class=MsoNormal align=left style='text-align:left;mso-pagination:widow-orphan'>

  <span  style='font-size:12.0pt;font-family:宋体;mso-bidi-font-family:宋体;mso-font-kerning:
  0pt'>签&nbsp;&nbsp;字：<b><span
  lang=EN-US style="color:#0000CD;text-align:right;"><span
  style='mso-spacerun:yes'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
  
  ${manager}
  <input name="manager" id="manager" value="${manager}" type="hidden">
  <input name="managerokdate" id="managerokdate" value="${managerokdate}" type="hidden">
  &nbsp;&nbsp;&nbsp;&nbsp;
  
  </span>${managerokdate}</span></b></span></p>
  </div>
  </td>
 </tr>
 <tr style='mso-yfti-irow:8;'>
  <td width=143 style='width:107.0pt;border:solid windowtext 1.0pt;border-top:
  none;mso-border-left-alt:solid windowtext .5pt;mso-border-bottom-alt:solid windowtext .5pt;
  mso-border-right-alt:solid windowtext .5pt;padding:0cm 5.4pt 0cm 5.4pt;
  height:40pt'>
  <p class=MsoNormal align=center style='text-align:center;mso-pagination:widow-orphan'><span
  style='font-size:12.0pt;font-family:宋体;mso-bidi-font-family:宋体;mso-font-kerning:
  0pt'>综合办公室意见：<span lang=EN-US><o:p></o:p></span></span></p>
  </td>
  <td width=600 colspan=8 valign=bottom style='width:400.0pt;border-top:none;
  border-left:none;border-bottom:solid windowtext 1.0pt;border-right:solid black 1.0pt;'>
    <div style="margin-left:15px;margin-top:15px">
  	审批意见：
  	<%
    //审批人可以修改
  	if (id.equals(entity.getGeofficer())|| role.contains("geofficer") ){ %>
    <input type="text" name="geofficerokmessage" value="${geofficerokmessage}" class="rectimyinput" style="width:200px;"maxlength=40>
    <%}else {
    %>
    ${geofficerokmessage}
    <input type="hidden" name="geofficerokmessage" value="${geofficerokmessage}" class="rectimyinput" style="width:200px;"maxlength=40>
    <%}%>
  
  <p class=MsoNormal align=left style='text-align:left;mso-pagination:widow-orphan'><span 
  style='font-size:12.0pt;font-family:宋体;mso-bidi-font-family:宋体;mso-font-kerning:
  0pt'>签&nbsp;&nbsp;字：<b><span
  lang=EN-US style="color:#0000CD;text-align:right;"><span
  style='mso-spacerun:yes'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
  
  
  ${geofficername}
  &nbsp;&nbsp;&nbsp;&nbsp;
  <input name="geofficername" id="geofficername" value="${geofficername}" type="hidden">
  <input name="geofficer" id="geofficer" value="${geofficer}" type="hidden">
  <input name="geofficerokdate" id="geofficerokdate" value="${geofficerokdate}" type="hidden">
  </span>${geofficerokdate}</span></b></span></p>
  </div>
  </td>
 </tr>
 
  <tr style='mso-yfti-irow:9;'>
  <td width=143 style='width:107.0pt;border:solid windowtext 1.0pt;border-top:
  none;mso-border-left-alt:solid windowtext .5pt;mso-border-bottom-alt:solid windowtext .5pt;
  mso-border-right-alt:solid windowtext .5pt;padding:0cm 5.4pt 0cm 5.4pt;
  height:40pt'>
  <p class=MsoNormal align=center style='text-align:center;mso-pagination:widow-orphan'><span
  style='font-size:12.0pt;font-family:宋体;mso-bidi-font-family:宋体;mso-font-kerning:
  0pt'>市局（办公室）意见：<span lang=EN-US><o:p></o:p></span></span></p>
  </td>
  <td width=600 colspan=8 valign=bottom style='width:400.0pt;border-top:none;
  border-left:none;border-bottom:solid windowtext 1.0pt;border-right:solid black 1.0pt;'>
    <div style="margin-left:15px;margin-top:15px">
  	审批意见：
  	<%
    //审批人可以修改
  	if (id.equals(entity.getCityofficer())|| (role.contains("geofficer") && "市局公司".equals(request.getSession().getAttribute("user_danwei")))){ %>
    <input type="text" name="cityofficerokmessage" value="${cityofficerokmessage}" class="rectimyinput" style="width:200px;"maxlength=40>
    <%}else {
    %>
    ${cityofficerokmessage}
    <input type="hidden" name="cityofficerokmessage" value="${cityofficerokmessage}" class="rectimyinput" style="width:200px;"maxlength=40>
    <%}%>
  
  <p class=MsoNormal align=left style='text-align:left;mso-pagination:widow-orphan'><span 
  style='font-size:12.0pt;font-family:宋体;mso-bidi-font-family:宋体;mso-font-kerning:
  0pt'>签&nbsp;&nbsp;字：<b><span
  lang=EN-US style="color:#0000CD;text-align:right;"><span
  style='mso-spacerun:yes'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
  
  
  ${cityofficername}
  &nbsp;&nbsp;&nbsp;&nbsp;
  <input name="cityofficername" id="cityofficername" value="${cityofficername}" type="hidden">
  <input name="cityofficer" id="cityofficer" value="${cityofficer}" type="hidden">
  <input name="cityofficerokdate" id="cityofficerokdate" value="${cityofficerokdate}" type="hidden">
  </span>${cityofficerokdate}</span></b></span></p>
  </div>
  </td>
 </tr>
  <tr style='mso-yfti-irow:10;'>
  <td width=143 style='width:107.0pt;border:solid windowtext 1.0pt;border-top:
  none;mso-border-left-alt:solid windowtext .5pt;mso-border-bottom-alt:solid windowtext .5pt;
  mso-border-right-alt:solid windowtext .5pt;padding:0cm 5.4pt 0cm 5.4pt;
  height:50pt'>
  <p class=MsoNormal align=center style='text-align:center;mso-pagination:widow-orphan'><span
  style='font-size:12.0pt;font-family:宋体;mso-bidi-font-family:宋体;mso-font-kerning:
  0pt'>相关方整改：<span lang=EN-US><o:p></o:p></span></span></p>
  </td>
  <td width=600 colspan=8 valign=bottom style='width:400.0pt;border-top:none;
  border-left:none;border-bottom:solid windowtext 1.0pt;border-right:solid black 1.0pt;'>
    <div style="margin-left:15px;margin-top:15px">
  
  </div>
  </td>
 </tr>
 
 
 <tr style='mso-yfti-irow:10;'>
  <td width=143 style='width:107.0pt;border:solid windowtext 1.0pt;border-top:
  none;mso-border-left-alt:solid windowtext .5pt;mso-border-bottom-alt:solid windowtext .5pt;
  mso-border-right-alt:solid windowtext .5pt;padding:0cm 5.4pt 0cm 5.4pt;
  height:40pt'>
  <p class=MsoNormal align=center style='text-align:center;mso-pagination:widow-orphan'><span
  style='font-size:12.0pt;font-family:宋体;mso-bidi-font-family:宋体;mso-font-kerning:
  0pt'>安全管理员验收：<span lang=EN-US><o:p></o:p></span></span></p>
  </td>
  <td width=600 colspan=8 valign=bottom style='width:400.0pt;border-top:none;
  border-left:none;border-bottom:solid windowtext 1.0pt;border-right:solid black 1.0pt;'>
    <div style="margin-left:15px;margin-top:15px">
  	审批意见：
  	<%
    //审批人可以修改
  	if ((id.equals(entity.getSafetymanager())|| role.contains("safetyofficer") ) &&"".equals(entity.getSafetymanagerokdate()) ){
  	%>
    <input type="text" name="safetymanagerokmessage" value="${safetymanagerokmessage}" class="rectimyinput" style="width:200px;"maxlength=40>
    <%}else {
    %>
    ${safetymanagerokmessage}
    <input type="hidden" name="safetymanagerokmessage" value="${safetymanagerokmessage}" class="rectimyinput" style="width:200px;"maxlength=40>
    <%}%>
  
  <p class=MsoNormal align=left style='text-align:left;mso-pagination:widow-orphan'><span 
  style='font-size:12.0pt;font-family:宋体;mso-bidi-font-family:宋体;mso-font-kerning:
  0pt'>签&nbsp;&nbsp;字：<b><span lang=EN-US style="color:#0000CD;text-align:right;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
  
  
  ${safetymanagername}
  &nbsp;&nbsp;&nbsp;&nbsp;
  <input name="safetymanagername" id="safetymanagername" value="${safetymanagername}" type="hidden">
  <input name="safetymanager" id="safetymanager" value="${safetymanager}" type="hidden">
  <input name="safetymanagerokdate" id="safetymanagerokdate" value="${safetymanagerokdate}" type="hidden">
  ${safetymanagerokdate}</span></b></span></p>
  </div>
  </td>
 </tr>
    
    <!-- 
    
    ********************
           按钮显示区域
    ********************
        
     -->
    
    
    
   <%
	if (taskname.contains("开始节点") & role.contains("safetyofficer")) {
	%>
	 <TR>
	  <td colspan=5>
	  <br/>
	  <a href="javascript:save_();" id ="save_" class="easyui-linkbutton"  style="padding-left: 0px;padding:10px 0px;width:95%" >
		<span style="font-size:15px;">&nbsp;&nbsp;保存清单&nbsp;&nbsp;</span>
	  </a>
	  </td>
	  <td colspan=4>
		  <%
		//已经发起流程
		//不可再次发起申请
		if (common.isEmpty((String)request.getAttribute("processid"))) {
		%>
  
		  <br/>
		  <a href="javascript:start_();" id ="start_" class="easyui-linkbutton"  style="padding-left: 0px;padding:10px 0px;width:100%" >
			<span style="font-size:15px;">&nbsp;&nbsp;发起申请&nbsp;&nbsp;</span>
		  </a>
 
  
		  <%
		}
		 %>
		 </td>
		</TR> 
   
	<%
	 
	} else if (taskname.contains("部门负责人") && role.contains("leader")& "".equals(entity.getLeaderokdate())) {
		  
	%>
	<TR>
	 <td colspan=9>
          <br/>
                         请选择分管领导
		  <select id ="fenguanlingdao_view" name = "fenguanlingdao_view" onchange="javascript:change_fenguanlingdao();">
		      <option value="" ></option>
		    <c:forEach items="${managerList }" var="managerListItem">
		      <option value="${managerListItem.id }" >${managerListItem.name }</option>
		    </c:forEach>
		  </select>
		  <input type="hidden" name="fenguanlingdao" value="${fenguanlingdao}" class="rectimyinput" style="width:560px;" maxlength=40>
		          
          <br/>
	
	 <a href="javascript:pass_fenguanlingdao('','审批','');" class="easyui-linkbutton"  style="padding-left: 0px;padding:10px 0px;width:100%;" >
		<span style="font-size:15px;">审批</span>
	 </a>
	
	<!-- 
	<div style="margin-bottom:10px">
	<a href="javascript:pass('','驳回',document.all.strOkmessage.value);" class="easyui-linkbutton"  style="padding-left: 0px;padding:10px 0px;width:100%;" >
		<span style="font-size:15px;">驳回</span>
	</a>
	</div>
	 -->
	 </td>
	 <tr>
    <%
    
    } else if (taskname.contains("分管领导") && role.contains("manager")&& "".equals(entity.getManagerokdate())) {
          
    %>
    <TR>
     <td colspan=9>
          <br/>
    
     <a href="javascript:pass('','审批','');" class="easyui-linkbutton"  style="padding-left: 0px;padding:10px 0px;width:100%;" >
        <span style="font-size:15px;">审批</span>
     </a>
    
    <!-- 
    <div style="margin-bottom:10px">
    <a href="javascript:pass('','驳回',document.all.strOkmessage.value);" class="easyui-linkbutton"  style="padding-left: 0px;padding:10px 0px;width:100%;" >
        <span style="font-size:15px;">驳回</span>
    </a>
    </div>
     -->
     </td>
     <tr>
	 <%
    //再次审批放在前面的原因，为了不进先入“办公室”那个逻辑
    } else if (taskname.contains("综合办公室再次审批")& role.contains("geofficer") ) {
    %>
    <TR>
     <td colspan=9>
            <BR/>
            <div style="margin-bottom:10px">
            <a href="javascript:pass('rectiLevelYes','审批','');" class="easyui-linkbutton"  style="padding-left: 0px;padding:10px 0px;width:100%;" >
                <span style="font-size:15px;">再审批（重大隐患）</span>
            </a>

            </div>
            
            <!-- 
            <div style="margin-bottom:10px">
            <a href="javascript:pass('','驳回',document.all.strOkmessage.value);" class="easyui-linkbutton"  style="padding-left: 0px;padding:10px 0px;width:100%;" >
                <span style="font-size:15px;">驳回</span>
            </a>
            </div>
            --> 
        </td>
    </tr>
	<%
	} else if (taskname.contains("综合办公室")& role.contains("geofficer")& "".equals(entity.getGeofficerokdate())) {
	%>
	<TR>
	    <td colspan=5>

			<BR/>
			<div style="margin-bottom:10px">
			<a href="javascript:pass('rectiLevelYes','审批','');" class="easyui-linkbutton"  style="padding-left: 0px;padding:10px 0px;width:95%;" >
				<span style="font-size:15px;">审批（是重大隐患）提交至市局办公室</span>
			</a>

			</div>

			<!-- 
			<div style="margin-bottom:10px">
			<a href="javascript:pass('','驳回',document.all.strOkmessage.value);" class="easyui-linkbutton"  style="padding-left: 0px;padding:10px 0px;width:100%;" >
				<span style="font-size:15px;">驳回</span>
			</a>
			</div>
			--> 
		</td>
		<td colspan=4>
			<br/>
			<div style="margin-bottom:10px">
			<a href="javascript:pass('rectiLevelNo','审批','');" class="easyui-linkbutton"  style="padding-left: 0px;padding:10px 0px;width:100%;" >
				<span style="font-size:15px;">审批（非重大隐患）提交至安全管理员</span>
			</a>
			</div>
		</td>
	</tr>
	
	<%	
	} else if (taskname.contains("市局办公室") && role.contains("geofficer") && "市局公司".equals(request.getSession().getAttribute("user_danwei"))& "".equals(entity.getCityofficerokdate())) {
		
	%>
	<TR>
	 <td colspan=9>
		<BR/>
		<div style="margin-bottom:10px">
		<a href="javascript:pass('','审批','');" class="easyui-linkbutton"  style="padding-left: 0px;padding:10px 0px;width:100%;" >
			<span style="font-size:15px;">审批</span>
		</a>
		</div>	
		<!-- 
		<div style="margin-bottom:10px">
		<a href="javascript:pass('','驳回',document.all.strOkmessage.value);" class="easyui-linkbutton"  style="padding-left: 0px;padding:10px 0px;width:100%;" >
			<span style="font-size:15px;">驳回</span>
		</a>
		</div>
		-->
				</td>
	</tr>
	<%
	} else if (taskname.contains("安全管理员验收")& role.contains("safetyofficer")& "".equals(entity.getSafetymanagerokdate())) {
	%>
	<TR>
	 <td colspan=9>
		<BR/>
		<div style="margin-bottom:10px">
		<a href="javascript:pass('','验收','');" class="easyui-linkbutton"  style="padding-left: 0px;padding:10px 0px;width:100%;" >
			<span style="font-size:15px;">验收</span>
		</a>
		</div>
	</td>
	</tr>

	<%
	}
	%>
 </td>
 </tr>

   <tr >
    <td colspan=5 style="color:black;text-align:left;font-size:12.0pt;">
      <br/>
      <b>${messeageForSave }</b>
      <%
      if (!common.isEmpty((String)request.getAttribute("messeageForSave"))) {
      %>
	  <a href="javascript:goto_('listRectiCheck.jsp');" class="easyui-linkbutton"  style="padding-left: 0px;padding:10px 0px;width:90%" >
		<span style="font-size:15px;">已保存清单</span>
	  </a>
	  <%
      }
	  %>
    </td>
    <td colspan=4 style="color:black;text-align:left;font-size:12.0pt;">
      <br/>
      <b>${messeageForSubmit }</b>
    </td>
  </tr> 
  <tr height=0>
  <td  style='border:none'></td>
  <td width=118 style='border:none'></td>
  <td width=21 style='border:none'></td>
  <td width=106 style='border:none'></td>
  <td width=64 style='border:none'></td>
  <td width=54 style='border:none'></td>
  <td width=96 style='border:none'></td>
  <td width=117 style='border:none'></td>
  <td width=30 style='border:none'></td>
 </tr>
 
 
 
 
 
 
 
 
</table>

</form>




</body>

</html>
