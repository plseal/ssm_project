<%@ page language="java" contentType="text/html; charset=UTF-8"   pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@include  file="sessiontimeout.jsp"%>
<%@ page import="com.lin.entity.RectiNewEntity" %>
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
	document.all.myform.action="${pageContext.request.contextPath}/rectiNew/complete.do?strResult="+strResult+"&strTrans="+strTrans+"&strOkmessage="+strOkmessage;
	document.all.myform.submit();
}
function pass_fenguanlingdao(strResult,strTrans,strOkmessage){
    if (document.all.myform.fenguanlingdao.value=="") {
        alert("请选择分管领导")
        document.all.myform.fenguanlingdao_view.focus();
        return;
    }
    document.all.myform.action="${pageContext.request.contextPath}/rectiNew/complete.do?strResult="+strResult+"&strTrans="+strTrans+"&strOkmessage="+strOkmessage;
    document.all.myform.submit();
}
function printword(){
	document.all.myform.rectitype.value =  $("#rectitype_view").val();
	
	document.all.myform.action="${pageContext.request.contextPath}/rectiNew/printword.do";
	document.all.myform.submit();
	
}
function addFile(){
	window.open("${pageContext.request.contextPath}/addFile.jsp?javaid="+document.all.javaid.value,"newwindow", "height=480, width=350"); 
}
function start_(){
	document.all.myform.rectitype.value =  $("#rectitype_view").val();
    if (document.all.myform.bumenfuzeren.value=="") {
        alert("请选择部门负责人")
        document.all.myform.bumenfuzeren_view.focus();
        return;
    }
	document.all.myform.action="${pageContext.request.contextPath}/rectiNew/start_.do";
	document.all.myform.submit();
}
function save_(){
	document.all.myform.rectitype.value =  $("#rectitype_view").val();
	
	document.all.myform.action="${pageContext.request.contextPath}/rectiNew/save_.do";
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
	//单位
	var danwei = document.all.myform.danwei.value;
	var user_danwei = document.all.myform.user_danwei.value;
	if (danwei != "") {
		document.all.myform.danwei_view.value = danwei;
	} else if(user_danwei != "") {
		document.all.myform.danwei_view.value = user_danwei;
		document.all.myform.danwei.value = user_danwei;
	} else {
		//登陆用户所在的单位
		document.all.myform.danwei.value = document.all.myform.user_danwei.value;
	}
	//地点
	var location = document.all.myform.location.value;
	if (location != "") {
		document.all.myform.location_view.value = location;
	} else {
		document.all.myform.location.value = "";
	}
	
	$("#checkdate_view").datebox("setValue",document.all.myform.checkdate.value);
			
}


function gotoListRectiNewjsp(){
	document.all.myform.action="${pageContext.request.contextPath}/rectiNew/toListRectiNew.do?listRectiAc=${listRectiAc }";
	document.all.myform.submit(); 
}
function change_danwei() {
	document.all.myform.danwei.value =  $("#danwei_view").val();
}
function change_bumenfuzeren() {
    document.all.myform.bumenfuzeren.value =  $("#bumenfuzeren_view").val();
}
function change_fenguanlingdao() {
    document.all.myform.fenguanlingdao.value =  $("#fenguanlingdao_view").val();
}


function change_rectitype() {
    document.all.myform.rectitype.value =  $("#rectitype_view").val();
}
function change_location() {
    document.all.myform.location.value =  $("#location_view").val();
}

function onSelect(date){
	document.all.myform.checkdate.value = date.format("yyyy-MM-dd");
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

<body lang=ZH-CN style='tab-interval:21.0pt;text-justify-trim:punctuation' onload="onload_()">
<%
RectiNewEntity entity = new RectiNewEntity();

entity = (RectiNewEntity)request.getAttribute("entity");
String taskname = entity.getTaskname();
String id      = (String)session.getAttribute("id");
String name    = (String)session.getAttribute("name");
String role = (String)session.getAttribute("role");

%>

<div align=left>
<form method="post" id="myform"  >
<input name="javaid" id="javaid" value="${javaid }" type="hidden">
<input name="ac" id="ac" value="${ac }" type="hidden">
<input name="taskid" id="taskid" value="${taskid }" type="hidden">
<input name="taskname" id="taskname" value="${taskname }" type="hidden">
<input name="processid" id="processid" value="${processid }" type="hidden">
<input name="starter" id="starter" value="${starter}" type="hidden">
<input name="rectilevel" id="rectilevel" value="${rectilevel}" type="hidden">
<input name="pageid" id="pageid" value="rectiNew.jsp" type="hidden">
<input name="role" id="role" value="<%=role %>" type="hidden">
<input name="user_danwei" value="${user_danwei}" type="hidden">


<table class=MsoNormalTable border=0 cellspacing=0 cellpadding=0 width=723
 style='width:542.0pt;margin-left:4.65pt;border-collapse:collapse;mso-padding-alt:
 0cm 5.4pt 0cm 5.4pt'>



 <tr>
  <td width=723 colspan=6 style='width:542.0pt;height:20pt;padding:0cm 5.4pt 0cm 5.4pt;'>
  <p class=MsoNormal align=middle style='text-align:center;mso-pagination:widow-orphan'>
  <span
  style='font-size:20.0pt;font-family:方正小标宋简体;mso-hansi-font-family:宋体;
  mso-bidi-font-family:宋体;mso-font-kerning:0pt'>安全隐患整改落实清单</span></p>
  </td>
 </tr>

 <tr style='mso-yfti-irow:1;height:35.25pt'>
  <td  valign='middle' style='width:107.0pt;padding:0cm 5.4pt 0cm 5.4pt;'nowrap>
       记录编号：
  </td>
  <td width=116 style='width:87.0pt;padding:0cm 5.4pt 0cm 5.4pt;'>

  <%
  //发起人可以修改
  if (id.equals(entity.getStarter()) && common.isEmpty((entity.getLeaderokdate()))) {
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
  <td width=116 style='width:87.0pt;padding:0cm 5.4pt 0cm 5.4pt;'>
  <p class=MsoNormal align=center style='text-align:center;mso-pagination:widow-orphan'><span
  lang=EN-US style='font-size:12.0pt;font-family:宋体;mso-bidi-font-family:宋体;
  mso-font-kerning:0pt'><o:p>&nbsp;</o:p></span></p>
  </td>
  <td width=116 style='width:87.0pt;padding:0cm 5.4pt 0cm 5.4pt;'>
  <p class=MsoNormal align=center style='text-align:center;mso-pagination:widow-orphan'><span
  lang=EN-US style='font-size:12.0pt;font-family:宋体;mso-bidi-font-family:宋体;
  mso-font-kerning:0pt'><o:p>&nbsp;</o:p></span></p>
  </td>
  <td width=116 style='width:87.0pt;padding:0cm 5.4pt 0cm 5.4pt;'>
  <p class=MsoNormal align=center style='text-align:center;mso-pagination:widow-orphan'><span
  lang=EN-US style='font-size:12.0pt;font-family:宋体;mso-bidi-font-family:宋体;
  mso-font-kerning:0pt'><o:p>&nbsp;</o:p></span></p>
  </td>
  <td width=116 style='width:87.0pt;padding:0cm 5.4pt 0cm 5.4pt;'>
  <p class=MsoNormal align=center style='text-align:center;mso-pagination:widow-orphan'><span
  lang=EN-US style='font-size:12.0pt;font-family:宋体;mso-bidi-font-family:宋体;
  mso-font-kerning:0pt'>
  <a href="javascript:printword();" class="easyui-linkbutton" style="padding:10px 0px;height:35px;width:75px">输出Word</a>
  </span></p>
  </td>
 </tr>
  <tr>
  <td width=143 style='width:107.0pt;border:solid windowtext 1.0pt;border-top:solid windowtext 1.0pt;
  mso-border-left-alt:solid windowtext .5pt;mso-border-bottom-alt:solid windowtext .5pt;
  mso-border-right-alt:solid windowtext .5pt;padding:0cm 5.4pt 0cm 5.4pt;
  height:30pt'>
  <p class=MsoNormal align=center style='text-align:center;mso-pagination:widow-orphan'><span
  style='font-size:12.0pt;font-family:宋体;mso-bidi-font-family:宋体;mso-font-kerning:
  0pt'>单位：<span lang=EN-US><o:p></o:p></span></span></p>
  </td>
  <td width=580 colspan=5 style='width:435.0pt;
  border-left:none;border-bottom:solid windowtext 1.0pt;border-top:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
  padding:0cm 5.4pt 0cm 5.4pt;'>
  

  <%
  //发起人可以修改
  if (id.equals(entity.getStarter()) && common.isEmpty((entity.getLeaderokdate()))) {
	  
  %>
    <select id = "danwei_view" name = "danwei_view" onchange="javascript:change_danwei();">
  
    <c:forEach items="${danweiList }" var="danweiListItem">
      <option value="${danweiListItem.id }" >${danweiListItem.id }</option>
    </c:forEach>

	<option value="部门（手动录入）" >部门（手动录入）</option> 
	</select> 
  


<input type="text" name="danwei" value="${danwei}" class="rectimyinput" style="width:210px;" maxlength=12>
  <%
  } else{
	  
  %>
  ${danwei}
  <input type="hidden" name="danwei" value="${danwei}" class="rectimyinput" style="width:110px;" maxlength=12>
  <%  
  }
  %>
  </td>
 </tr>
 <tr style='mso-yfti-irow:2;height:40.0pt'>
  <td width=143 style='width:107.0pt;border:solid windowtext 1.0pt;mso-border-alt:
  solid windowtext .5pt;padding:0cm 5.4pt 0cm 5.4pt;height:24.0pt'>
  <p class=MsoNormal align=center style='text-align:center;mso-pagination:widow-orphan'><span
  style='font-size:12.0pt;font-family:宋体;mso-bidi-font-family:宋体;mso-font-kerning:
  0pt'>检查人：<span lang=EN-US><o:p></o:p></span></span></p>
  </td>
  <td width=116 style='width:87.0pt;border-top:solid windowtext 1.0pt;
  border-left:none;border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
  padding:0cm 5.4pt 0cm 5.4pt;height:24.0pt'>
  
  <%
  //发起人可以修改
  if (id.equals(entity.getStarter()) && common.isEmpty((entity.getLeaderokdate()))) {
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
  
  </td>
  <td  style='border-top:solid windowtext 1.0pt;
  border-left:none;border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
  mso-border-top-alt:solid windowtext .5pt;mso-border-right-alt:solid windowtext .5pt;
  padding:0cm 5.4pt 0cm 5.4pt;height:24.0pt'nowrap>
      自查地点：
  </td>
  <td width=116 style='width:87.0pt;border-top:solid windowtext 1.0pt;
  
  border-left:none;border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
  mso-border-top-alt:solid windowtext .5pt;mso-border-right-alt:solid windowtext .5pt;
  padding:0cm 5.4pt 0cm 5.4pt;height:24.0pt'>
   <%
  //发起人可以修改
  if (id.equals(entity.getStarter()) && common.isEmpty((entity.getLeaderokdate()))) {
  %>
	  
	<select id = "location_view" name = "location_view" onchange="javascript:change_location();">
        <c:forEach items="${locationList }" var="locationListItem">
         <option value="${locationListItem.id }" >${locationListItem.id }</option>
        </c:forEach>
	  <option value="其他自查地点" >其他自查地点</option>
	</select> 

     <input type="text" name="location" value="${location}" class="rectimyinput" style="width:110px;" maxlength=12>
    
  
  <%
  } else{
  %>
  ${location}
  <input type="hidden" name="location" value="${location}" class="rectimyinput" style="width:110px;" maxlength=12>
  <%  
  }
  %>
  </td>
  <td  style='border-top:solid windowtext 1.0pt;
  border-left:solid windowtext 1.0pt;border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
  height:24.0pt'nowrap>
 自查时间：
  </td>
  <td width=100 style='width:87.0pt;border:solid windowtext 1.0pt;border-left:
  none;mso-border-top-alt:solid windowtext .5pt;mso-border-bottom-alt:solid windowtext .5pt;
  mso-border-right-alt:solid windowtext .5pt;padding:0cm 5.4pt 0cm 5.4pt;
  height:24.0pt'>
  
  <%
  //发起人可以修改
  if (id.equals(entity.getStarter()) && common.isEmpty((entity.getLeaderokdate()))) {
  %>
  <input name="checkdate_view" id ="checkdate_view" class="easyui-datebox" data-options="onSelect:onSelect"style='width:110.0pt;'></input>
  <input type="hidden" name="checkdate" value="${checkdate}" class="rectimyinput" style="width:110px;" maxlength=12>
  <%
  } else{
  %>
  ${checkdate}
  <input type="hidden" name="checkdate" value="${checkdate}" class="rectimyinput" style="width:110px;" maxlength=12>
  <%  
  }
  %>
  
 
  </td>
 </tr>
 <tr>
  <td width=143 style='width:107.0pt;border:solid windowtext 1.0pt;border-top:
  none;mso-border-left-alt:solid windowtext .5pt;mso-border-bottom-alt:solid windowtext .5pt;
  mso-border-right-alt:solid windowtext .5pt;padding:0cm 5.4pt 0cm 5.4pt;
  height:30pt'>
  <p class=MsoNormal align=center style='text-align:center;mso-pagination:widow-orphan'><span
  style='font-size:12.0pt;font-family:宋体;mso-bidi-font-family:宋体;mso-font-kerning:
  0pt'>隐患内容：<span lang=EN-US><o:p></o:p></span></span></p>
  </td>
  <td width=580 colspan=5 style='width:435.0pt;
  border-left:none;border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
  padding:0cm 5.4pt 0cm 5.4pt;'>
  
  <%
  //发起人可以修改
  if (id.equals(entity.getStarter()) && common.isEmpty((entity.getLeaderokdate()))) {
  %>
  <input type="text" name="content" value="${content}" class="rectimyinput" style="width:560px;" maxlength=40>
  <%
  } else{
  %>
  ${content}
  <input type="hidden" name="content" value="${content}" class="rectimyinput" style="width:560px;" maxlength=40>
  <%  
  }
  %>
  </td>
 </tr>
 <tr>
  <td width=143 style='width:107.0pt;border:solid windowtext 1.0pt;border-top:
  none;mso-border-left-alt:solid windowtext .5pt;mso-border-bottom-alt:solid windowtext .5pt;
  mso-border-right-alt:solid windowtext .5pt;padding:0cm 5.4pt 0cm 5.4pt;
  height:30pt'>
  <p class=MsoNormal align=center style='text-align:center;mso-pagination:widow-orphan'><span
  style='font-size:12.0pt;font-family:宋体;mso-bidi-font-family:宋体;mso-font-kerning:
  0pt'>隐患类型：<span lang=EN-US><o:p></o:p></span></span></p>
  </td>
  <td width=580 colspan=5 style='width:435.0pt;
  border-left:none;border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
  padding:0cm 5.4pt 0cm 5.4pt;'>
  

    
  <%
  //发起人可以修改
  if (id.equals(entity.getStarter()) && common.isEmpty((entity.getLeaderokdate()))) {
  %>
      <select id = "rectitype_view" name = "rectitype_view" onchange="javascript:change_rectitype();">
        <c:forEach items="${yinhuanTypeList }" var="yinhuanTypeListItem">
         <option value="${yinhuanTypeListItem.id }" >${yinhuanTypeListItem.id }</option>
        </c:forEach>
      </select> 
      <input type="hidden" name="rectitype" value="${rectitype}" class="rectimyinput" style="width:110px;" maxlength=12>
  <%
  } else{
  %>
  ${rectitype}
  
  <input type="hidden" name="rectitype" value="${rectitype}" class="rectimyinput" style="width:110px;" maxlength=12>
  <%  
  }
  %>  
    
    
  </td>
 </tr>

 <tr style='mso-yfti-irow:5;'>
  <td width=143 style='width:107.0pt;border:solid windowtext 1.0pt;border-top:
  none;mso-border-left-alt:solid windowtext .5pt;mso-border-bottom-alt:solid windowtext .5pt;
  mso-border-right-alt:solid windowtext .5pt;padding:0cm 5.4pt 0cm 5.4pt;
  height:30pt'>
  <p class=MsoNormal align=center style='text-align:center;mso-pagination:widow-orphan'><span
  style='font-size:12.0pt;font-family:宋体;mso-bidi-font-family:宋体;mso-font-kerning:
  0pt'>整改措施：<span lang=EN-US><o:p></o:p></span></span></p>
  </td>
  <td width=580 colspan=5 style='width:435.0pt;border-top:none;border-left:
  none;border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
  mso-border-top-alt:solid windowtext .5pt;mso-border-top-alt:solid windowtext .5pt;
  mso-border-bottom-alt:solid windowtext .5pt;mso-border-right-alt:solid windowtext .5pt;
  padding:0cm 5.4pt 0cm 5.4pt;'>
  
  
  <%
  //发起人可以修改
  if (id.equals(entity.getStarter()) && common.isEmpty((entity.getLeaderokdate()))) {
  %>
  <input type="text" name="okmeasure" value="${okmeasure}" class="rectimyinput" style="width:560px;" maxlength=40>
  <%
  } else{
  %>
  ${okmeasure}
  <input type="hidden" name="okmeasure" value="${okmeasure}" class="rectimyinput" style="width:560px;" maxlength=40>
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
  0pt'>所在单位(部门)负责人意见：<span lang=EN-US><o:p></o:p></span></span></p>
  </td>

  <td width=600 colspan=5 valign=bottom style='width:400.0pt;border-top:none;
  border-left:none;border-bottom:solid windowtext 1.0pt;border-right:solid black 1.0pt;'>
  
  <div style="margin-left:15px;margin-top:15px">
  	审批意见：
  	<%
  	//审批人可以修改
  	if ((id.equals(entity.getLeader()) || role.contains("leader") ) & "".equals(entity.getLeaderokdate())){
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
  
  ${leadername}
  <input name="leadername" id="leadername" value="${leadername}" type="hidden">
  <input name="leader" id="leader" value="${leader}" type="hidden">
  <input name="leaderokdate" id="leaderokdate" value="${leaderokdate}" type="hidden">
  &nbsp;&nbsp;&nbsp;&nbsp;
  
  </span>${leaderokdate}</span></b></span></p>
  </div>
  </td>
 </tr>
 <tr style='mso-yfti-irow:7;'>
  <td width=143 style='width:107.0pt;border:solid windowtext 1.0pt;border-top:
  none;mso-border-left-alt:solid windowtext .5pt;mso-border-bottom-alt:solid windowtext .5pt;
  mso-border-right-alt:solid windowtext .5pt;padding:0cm 5.4pt 0cm 5.4pt;
  height:40pt'>
  <p class=MsoNormal align=center style='text-align:center;mso-pagination:widow-orphan'><span
  style='font-size:12.0pt;font-family:宋体;mso-bidi-font-family:宋体;mso-font-kerning:
  0pt'>分管领导审批：<span lang=EN-US><o:p></o:p></span></span></p>
  </td>
  <td width=600 colspan=5 valign=bottom style='width:400.0pt;border-top:none;
  border-left:none;border-bottom:solid windowtext 1.0pt;border-right:solid black 1.0pt;'>
    <div style="margin-left:15px;margin-top:15px">
  	审批意见：
  	<%
    //审批人可以修改
  	if (id.equals(entity.getManager()) || role.contains("manager")){ %>
    <input type="text" name="managerokmessage" value="${managerokmessage}" class="rectimyinput" style="width:200px;"maxlength=40>
    <%}else {
    %>
    ${managerokmessage}
    <input type="hidden" name="managerokmessage" value="${managerokmessage}" class="rectimyinput" style="width:200px;"maxlength=40>
    <%}%>
  
  <p class=MsoNormal align=left style='text-align:left;mso-pagination:widow-orphan'><span 
  style='font-size:12.0pt;font-family:宋体;mso-bidi-font-family:宋体;mso-font-kerning:
  0pt'>签&nbsp;&nbsp;字：<b><span
  lang=EN-US style="color:#0000CD;text-align:right;"><span
  style='mso-spacerun:yes'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
  
  
  ${managername}
  &nbsp;&nbsp;&nbsp;&nbsp;
  <input name="managername" id="managername" value="${managername}" type="hidden">
  <input name="manager" id="manager" value="${manager}" type="hidden">
  <input name="managerokdate" id="managerokdate" value="${managerokdate}" type="hidden">
  </span>${managerokdate}</span></b></span></p>
  </div>
  </td>
 <tr style='mso-yfti-irow:8;'>
  <td width=143 style='width:107.0pt;border:solid windowtext 1.0pt;border-top:
  none;mso-border-left-alt:solid windowtext .5pt;mso-border-bottom-alt:solid windowtext .5pt;
  mso-border-right-alt:solid windowtext .5pt;padding:0cm 5.4pt 0cm 5.4pt;
  height:40pt'>
  <p class=MsoNormal align=center style='text-align:center;mso-pagination:widow-orphan'><span
  style='font-size:12.0pt;font-family:宋体;mso-bidi-font-family:宋体;mso-font-kerning:
  0pt'>办公室意见：<span lang=EN-US><o:p></o:p></span></span></p>
  </td>
  <td width=600 colspan=5 valign=bottom style='width:400.0pt;border-top:none;
  border-left:none;border-bottom:solid windowtext 1.0pt;border-right:solid black 1.0pt;'>
    <div style="margin-left:15px;margin-top:15px">
  	审批意见：
  	<%
    //审批人可以修改
  	if ((id.equals(entity.getGeofficer())|| role.contains("geofficer")) & "".equals(entity.getGeofficerokdate())){ 
  	%>
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
  0pt'>主要负责人意见：<span lang=EN-US><o:p></o:p></span></span></p>
  </td>
  <td width=600 colspan=5 valign=bottom style='width:400.0pt;border-top:none;
  border-left:none;border-bottom:solid windowtext 1.0pt;border-right:solid black 1.0pt;'>
    <div style="margin-left:15px;margin-top:15px">
  	审批意见：
  	<%
    //审批人可以修改
  	if (id.equals(entity.getTownofficer())|| role.contains("townofficer") ){ %>
    <input type="text" name="townofficerokmessage" value="${townofficerokmessage}" class="rectimyinput" style="width:200px;"maxlength=40>
    <%}else {
    %>
    ${townofficerokmessage}
    <input type="hidden" name="townofficerokmessage" value="${townofficerokmessage}" class="rectimyinput" style="width:200px;"maxlength=40>
    <%}%>
  
  <p class=MsoNormal align=left style='text-align:left;mso-pagination:widow-orphan'><span 
  style='font-size:12.0pt;font-family:宋体;mso-bidi-font-family:宋体;mso-font-kerning:
  0pt'>签&nbsp;&nbsp;字：<b><span
  lang=EN-US style="color:#0000CD;text-align:right;"><span
  style='mso-spacerun:yes'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
  
  
  ${townofficername}
  &nbsp;&nbsp;&nbsp;&nbsp;
  <input name="townofficername" id="townofficername" value="${townofficername}" type="hidden">
  <input name="townofficer" id="townofficer" value="${townofficer}" type="hidden">
  <input name="townofficerokdate" id="townofficerokdate" value="${townofficerokdate}" type="hidden">
  </span>${townofficerokdate}</span></b></span></p>
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
  0pt'>市局办公室意见：<span lang=EN-US><o:p></o:p></span></span></p>
  </td>
  <td width=600 colspan=5 valign=bottom style='width:400.0pt;border-top:none;
  border-left:none;border-bottom:solid windowtext 1.0pt;border-right:solid black 1.0pt;'>
    <div style="margin-left:15px;margin-top:15px">
  	审批意见：
  	<%
    //审批人可以修改
  	if (id.equals(entity.getCityofficer())|| role.contains("cityofficer") ){ %>
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
  <td width=600 colspan=5 valign=bottom style='width:400.0pt;border-top:none;
  border-left:none;border-bottom:solid windowtext 1.0pt;border-right:solid black 1.0pt;'>
    <div style="margin-left:10px;margin-top:15px">
    
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
  0pt'>安全管理员/发起人验收：<span lang=EN-US><o:p></o:p></span></span></p>
  </td>
  <td width=600 colspan=5 valign=bottom style='width:400.0pt;border-top:none;
  border-left:none;border-bottom:solid windowtext 1.0pt;border-right:solid black 1.0pt;'>


  
  <%
  if (entity.getTaskname().contains("安全管理员验收")) {
  %>
    <div style="margin-left:10px;margin-top:15px">
    审批意见：
    <%
    //审批人可以修改
    if ((id.equals(entity.getSafetymanager())|| role.contains("safetyofficer")) & "".equals(entity.getSafetymanagerokdate())){
    %>
    <input type="text" name="safetymanagerokmessage" value="${safetymanagerokmessage}" class="rectimyinput" style="width:200px;"maxlength=40>
    <%}else {
    %>
    ${safetymanagerokmessage}
    <input type="hidden" name="safetymanagerokmessage" value="${safetymanagerokmessage}" class="rectimyinput" style="width:200px;"maxlength=40>
    <%}%>
  
   <p class=MsoNormal align=left style='text-align:left;mso-pagination:widow-orphan'><span 
  style='font-size:12.0pt;font-family:宋体;mso-bidi-font-family:宋体;mso-font-kerning:
  0pt'>签&nbsp;&nbsp;字：<b><span
  lang=EN-US style="color:#0000CD;text-align:right;"><span
  style='mso-spacerun:yes'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
  ${safetymanagername}
    &nbsp;&nbsp;&nbsp;&nbsp;
  <input name="safetymanagername" id="safetymanagername" value="${safetymanagername}" type="hidden">
  <input name="safetymanager" id="safetymanager" value="${safetymanager}" type="hidden">
  <input name="safetymanagerokdate" id="safetymanagerokdate" value="${safetymanagerokdate}" type="hidden">
  </span>${safetymanagerokdate}</span></b></span></p>
  </div>
  <%
  //发起人结束流程
  }else if(entity.getTaskname().contains("隐患发起人验收")) {
  %>
      <div style="margin-left:10px;margin-top:15px">
                 审批意见：
    <%
    //验收人可以修改
    if ((id.equals(entity.getStarter())|| role.contains("yuangong")) & "".equals(entity.getStarterokdate())){
    %>
    <input type="text" name="starterokmessage" value="${starterokmessage}" class="rectimyinput" style="width:200px;"maxlength=40>
    <%}else {
    %>
    ${starterokmessage}
    <input type="hidden" name="starterokmessage" value="${starterokmessage}" class="rectimyinput" style="width:200px;"maxlength=40>
    <%}%>

    <p class=MsoNormal align=left style='text-align:left;mso-pagination:widow-orphan'><span 
  style='font-size:12.0pt;font-family:宋体;mso-bidi-font-family:宋体;mso-font-kerning:
  0pt'>签&nbsp;&nbsp;字：<b><span
  lang=EN-US style="color:#0000CD;text-align:right;"><span
  style='mso-spacerun:yes'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		<%
		//发起人验收过
		if(!"".equals(entity.getStarterokdate())) {
		%>
		${starter}
		<%
		}
		%>
    &nbsp;&nbsp;&nbsp;&nbsp;
  <input name="starterokdate" id="starterokdate" value="${starterokdate}" type="hidden">
  </span>${starterokdate}</span></b></span></p>
    </div>
  <% }
  %>

  </td>
 </tr>
 	<%
	 if ( role.contains("geofficer") || id.equals(entity.getStarter())  && common.isEmpty((entity.getLeaderokdate()))) {
	%>
	
	 <tr style='height:200pt'>
	  <td width=143 style='border:solid windowtext 1.0pt;border-top:
	  none;mso-border-left-alt:solid windowtext .5pt;mso-border-bottom-alt:solid windowtext .5pt;
	  mso-border-right-alt:solid windowtext .5pt;padding:0cm 5.4pt 0cm 5.4pt'>
	    附件：
	  </td>
	  <td  colspan=5 valign=top style='border-top:none;
	  border-left:none;border-bottom:solid windowtext 1.0pt;border-right:solid black 1.0pt;'>
		<iframe width=99% height="280pt" frameborder="no" src='../addFile.jsp' ></iframe>
	  </td>
	 </tr>
	
	<%
	
	 } else {
	%>
	<tr style='mso-yfti-irow:11;mso-yfti-lastrow:yes;height:200pt'>
	  <td width=143 style='width:107.0pt;border:solid windowtext 1.0pt;border-top:
	  none;mso-border-left-alt:solid windowtext .5pt;mso-border-bottom-alt:solid windowtext .5pt;
	  mso-border-right-alt:solid windowtext .5pt;padding:0cm 5.4pt 0cm 5.4pt'>
	  <p class=MsoNormal align=center style='text-align:center;mso-pagination:widow-orphan'><span
	  style='font-size:12.0pt;font-family:宋体;mso-bidi-font-family:宋体;mso-font-kerning:
	  0pt'>附件：<span lang=EN-US><o:p></o:p></span></span></p>
	  </td>
	  <td  colspan=5 valign=top style='border-top:none;
	  border-left:10pt;border-bottom:solid windowtext 1.0pt;border-right:solid black 1.0pt;'>
	    
		<iframe width=99% height="250pt" frameborder="no" src="${pageContext.request.contextPath}/uploadFile/addFileListReadOnly.do?javaid=${javaid}" ></iframe>
		
	  </td>
	 </tr>
 
 <%
	 }
 %>

    <%
	if (taskname.contains("开始节点") & role.contains("yuangong")) {
	%>
	 <TR>
	  <td colspan=3>
	  <br/>
	  <br/>
	  <a href="javascript:save_();" id ="save_" class="easyui-linkbutton"  style="padding-left: 0px;padding:10px 0px;width:95%" >
		<span style="font-size:15px;">&nbsp;&nbsp;保存清单&nbsp;&nbsp;</span>
	  </a>
	  </td>
	  <td colspan=3>
		  <%
		//已经发起流程
		//不可再次发起申请
		if (common.isEmpty((String)request.getAttribute("processid"))) {
		%>
          <br/>
                         请选择部门负责人
  <select id ="bumenfuzeren_view" name = "bumenfuzeren_view" onchange="javascript:change_bumenfuzeren();">
      <option value="" ></option>
    <c:forEach items="${leaderList }" var="leaderListItem">
      <option value="${leaderListItem.id }" >${leaderListItem.name }</option>
    </c:forEach>
  </select>
  <input type="hidden" name="bumenfuzeren" value="${bumenfuzeren}" class="rectimyinput" style="width:560px;" maxlength=40>
          
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
    //
	} else if (taskname.contains("隐患发起人验收") & role.contains("yuangong")& "".equals(entity.getStarterokdate())) {
	%>	
	<TR>
	  <td colspan=6>
	  <br/>
		<BR/>
		
		<a href="javascript:pass('','验收','');" class="easyui-linkbutton"  style="padding-left: 0px;padding:10px 0px;width:100%;" >
			<span style="font-size:15px;">验收</span>
		</a>
		</td>
	</tr>
		
	<%
		
	} else if (taskname.contains("部门负责人") & role.contains("leader")) {
	%>
	<TR>
	 <td colspan=6>
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
	} else if (taskname.contains("分管领导") & role.contains("manager")) {
	%>
	<TR>
	 <td colspan=6>
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
	//再次审批放在前面的原因，为了不进先入“办公室”那个逻辑
    } else if (taskname.contains("办公室再次审批")& role.contains("geofficer")) {
    %>
    <TR>
     <td colspan=6>
            <BR/>
            <div style="margin-bottom:10px">
            <a href="javascript:pass('rectiLevelYes','再审批','');" class="easyui-linkbutton"  style="padding-left: 0px;padding:10px 0px;width:100%;" >
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
	} else if (taskname.contains("办公室")& role.contains("geofficer")) {
	%>
	<TR>
	    <td colspan=3>

			<BR/>
			<div style="margin-bottom:10px">
			<a href="javascript:pass('rectiLevelYes','审批','');" class="easyui-linkbutton"  style="padding-left: 0px;padding:10px 0px;width:100%;" >
				<span style="font-size:15px;">审批（是重大隐患）</span>
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
		<td colspan=3>
			<br/>
			<div style="margin-bottom:10px">
			<a href="javascript:pass('rectiLevelNo','审批','');" class="easyui-linkbutton"  style="padding-left: 0px;padding:10px 0px;width:100%;" >
				<span style="font-size:15px;">审批（非重大隐患）</span>
			</a>
			</div>
		</td>
	</tr>

	<%	
		
	} else if (taskname.contains("主要负责人")& role.contains("townofficer")) {
	%>
	<TR>
	 <td colspan=6>
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
	} else if (taskname.contains("市局")& role.contains("cityofficer")) {
	%>
	<TR>
	 <td colspan=6>
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
	} else if (taskname.contains("安全管理员验收")& role.contains("safetyofficer") & "".equals(entity.getSafetymanagerokdate())) {
	%>
	<TR>
	 <td colspan=6>
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
    <td colspan=3 style="color:black;text-align:left;font-size:12.0pt;width:50%;border:solid windowtext 0.0pt;">
      
      <b>${messeageForSave }</b>
      
    </td>
    <td colspan=3 style="color:black;text-align:left;font-size:12.0pt;width:50%">
      
      <b>${messeageForSubmit }</b>
      
    </td>
  </tr> 

   <tr >
    <td colspan=3 style="color:black;text-align:left;font-size:12.0pt;width:50%;border:solid windowtext 0.0pt;">
      
      <%
      if (!common.isEmpty((String)request.getAttribute("messeageForSave"))) {
      %>
	  <a href="javascript:gotoListRectiNewjsp();" class="easyui-linkbutton"  style="padding-left: 0px;padding:10px 0px;width:100%" >
		<span style="font-size:15px;">已保存清单</span>
	  </a>
	  <%
      }
	  %>
    </td>
    <td colspan=3 style="color:black;text-align:left;font-size:12.0pt;width:50%">

    </td>
  </tr> 

 

</table>

</form>
</div>
</body>

</html>
