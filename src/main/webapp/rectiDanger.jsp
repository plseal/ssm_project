<%@ page language="java" contentType="text/html; charset=UTF-8"   pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@include  file="sessiontimeout.jsp"%>
<%@ page import="com.lin.entity.RectiDangerEntity" %>
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
	document.all.myform.action="${pageContext.request.contextPath}/rectiDanger/complete.do?strResult="+strResult+"&strTrans="+strTrans+"&strOkmessage="+strOkmessage;
	document.all.myform.submit();
}

function printword(){
	
	
	document.all.myform.action="${pageContext.request.contextPath}/rectiDanger/printword.do";
	document.all.myform.submit();
	
}
function addFile(){
	window.open("${pageContext.request.contextPath}/addFile.jsp?javaid="+document.all.javaid.value,"newwindow", "height=480, width=350"); 
}
function start_(){
	
	document.all.myform.action="${pageContext.request.contextPath}/rectiDanger/start_.do";
	document.all.myform.submit();
}
function save_(){
	
	document.all.myform.action="${pageContext.request.contextPath}/rectiDanger/save_.do";
	document.all.myform.submit();
}
function onload_(){
	
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

	
	$("#startdate_view").datebox("setValue",document.all.myform.startdate.value);
	$("#enddate_view").datebox("setValue",document.all.myform.enddate.value);
			
}


function gotoListRectiDangerjsp(){
	document.all.myform.action="${pageContext.request.contextPath}/rectiDanger/toListRectiDanger.do?listRectiAc=${listRectiAc }";
	document.all.myform.submit(); 
}
function change_danwei() {
	document.all.myform.danwei.value =  $("#danwei_view").val();
}





function change_location() {
    document.all.myform.location.value =  $("#location_view").val();
}

function onSelectStart(date){
	document.all.myform.startdate.value = date.format("yyyy-MM-dd");
}
function onSelectEnd(date){
    document.all.myform.enddate.value = date.format("yyyy-MM-dd");
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
RectiDangerEntity entity = new RectiDangerEntity();

entity = (RectiDangerEntity)request.getAttribute("entity");
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
<input name="pageid" id="pageid" value="rectiDanger.jsp" type="hidden">
<input name="role" id="role" value="<%=role %>" type="hidden">
<input name="user_danwei" value="${user_danwei}" type="hidden">

 
<table class=MsoNormalTable   style='width:542.0pt;margin-left:4.65pt;border-collapse:collapse;mso-padding-alt:
 0cm 5.4pt 0cm 5.4pt'>



 <tr>
  <td width=723 colspan=6 style='width:542.0pt;height:20pt;padding:0cm 5.4pt 0cm 5.4pt;'>
<p class=MsoNormal align=center style='text-align:center'><b><span
style='font-size:22.0pt;font-family:方正小标宋简体'>危险作业审批单</span></b></p>
  </td>
 </tr>

 <tr style='mso-yfti-irow:1;height:35.25pt'>
  <td  valign='middle' style='width:107.0pt;padding:0cm 5.4pt 0cm 5.4pt;'nowrap>
       记录编号：
  </td>
  <td style='width:174.0pt;padding:0cm 5.4pt 0cm 5.4pt;' nowrap>

  JZYC-AQBZH-CX-11-JL-01
  <input type="hidden" name="no" value="${no}" class="rectimyinput" style="width:110px;" maxlength=12>

  </td>

  <td width=116 style='width:87.0pt;padding:0cm 5.4pt 0cm 5.4pt;'>
  <p class=MsoNormal align=center style='text-align:center;mso-pagination:widow-orphan'><span
  lang=EN-US style='font-size:12.0pt;font-family:宋体;mso-bidi-font-family:宋体;
  mso-font-kerning:0pt'></span></p>
  </td>

  <td width=116 style='width:87.0pt;padding:0cm 5.4pt 0cm 5.4pt;'>
  <p class=MsoNormal align=right style='text-align:center;mso-pagination:widow-orphan'><span
  lang=EN-US style='font-size:12.0pt;font-family:宋体;mso-bidi-font-family:宋体;
  mso-font-kerning:0pt'>
  <a href="javascript:printword();" class="easyui-linkbutton" style="padding:10px 0px;height:35px;width:75px">输出Word</a>
  </span></p>
  </td>
 </tr>
 
 
 <tr style='height:45pt'>
  <td width=115 style='width:86.35pt;border:solid windowtext 1.0pt;padding:
  0cm 5.4pt 0cm 5.4pt;'>
  <p class=MsoNormal align=center style='text-align:center'><span
  style='font-size:12.0pt;font-family:宋体'>申请单位</span></p>
  </td>
  <td width=156 style='width:117.05pt;border:solid windowtext 1.0pt;border-left:
  none;padding:0cm 5.4pt 0cm 5.4pt;'>
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
  <td width=120 style='width:90.0pt;border:solid windowtext 1.0pt;border-left:
  none;padding:0cm 5.4pt 0cm 5.4pt;' nowrap>
  <p class=MsoNormal align=center style='text-align:center'><span
  style='font-size:12.0pt;font-family:宋体'>申请负责人</span></p>
  </td>
  <td width=181 style='width:135.75pt;border:solid windowtext 1.0pt;border-left:
  none;padding:0cm 5.4pt 0cm 5.4pt;'>
  <%
  //发起人可以修改
  if (id.equals(entity.getStarter()) && common.isEmpty((entity.getLeaderokdate()))) {
  %>
  <input type="text" name="starter" value="${starter}" class="rectimyinput" style="width:110px;" maxlength=12>
  <%
  } else{
  %>
  ${starter}
  
  <input type="hidden" name="starter" value="${starter}" class="rectimyinput" style="width:110px;" maxlength=12>
  <%  
  }
  %>
  </td>
 </tr>
 <tr style='height:45pt'>
  <td width=115 style='width:86.35pt;border:solid windowtext 1.0pt;border-top:
  none;padding:0cm 5.4pt 0cm 5.4pt;'>
  <p class=MsoNormal align=center style='text-align:center'><span
  style='font-size:12.0pt;font-family:宋体'>危险作业性质</span></p>
  </td>
  <td width=156 style='width:117.05pt;border-top:none;border-left:none;
  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
  padding:0cm 5.4pt 0cm 5.4pt;height:33.3pt'>
  <%
  //发起人可以修改
  if (id.equals(entity.getStarter()) && common.isEmpty((entity.getLeaderokdate()))) {
  %>
  <input type="text" name="dangertype" value="${dangertype}" class="rectimyinput" style="width:210px;" maxlength=12>
  <%
  } else{
  %>
  ${dangertype}
  
  <input type="hidden" name="dangertype" value="${dangertype}" class="rectimyinput" style="width:210px;" maxlength=12>
  <%  
  }
  %>
  </td>
  <td width=120 style='width:90.0pt;border-top:none;border-left:none;
  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
  padding:0cm 5.4pt 0cm 5.4pt;'>
  <p class=MsoNormal align=center style='text-align:center'><span
  style='font-size:12.0pt;font-family:宋体'>起止时间</span></p>
  </td>
  <td width=281 style='border-top:none;border-left:none;
  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
  padding:0cm 5.4pt 0cm 5.4pt; font-size:10px;' nowrap>
  <%
  //发起人可以修改
  if (id.equals(entity.getStarter()) && common.isEmpty((entity.getLeaderokdate()))) {
  %>
  开始：<input name="startdate_view" id ="startdate_view" class="easyui-datebox" data-options="onSelect:onSelectStart"style='width:110.0pt;'></input>
  <div style="margin:5px 0;"></div>
  结束：<input name="enddate_view" id ="enddate_view" class="easyui-datebox" data-options="onSelect:onSelectEnd"style='width:110.0pt;'></input>
  <input type="hidden" name="startdate" value="${startdate}" class="rectimyinput" style="width:110px;" maxlength=12>
  <input type="hidden" name="enddate" value="${enddate}" class="rectimyinput" style="width:110px;" maxlength=12>
  <%
  } else{
  %>
  ${startdate} 至  ${enddate}
  <input type="hidden" name="startdate" value="${startdate}" class="rectimyinput" style="width:110px;" maxlength=12>
  <input type="hidden" name="enddate" value="${enddate}" class="rectimyinput" style="width:110px;" maxlength=12>
  <%  
  }
  %>
  </td>
 </tr>
 <tr style='height:45pt'>
  <td width=115 style='width:86.35pt;border:solid windowtext 1.0pt;border-top:
  none;padding:0cm 5.4pt 0cm 5.4pt;'>
  <p class=MsoNormal align=center style='text-align:center'><span
  style='font-size:12.0pt;font-family:宋体'>危险作业部位</span></p>
  </td>
  <td width=156 style='width:117.05pt;border-top:none;border-left:none;
  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
  padding:0cm 5.4pt 0cm 5.4pt;height:30.95pt'>
   <%
  //发起人可以修改
  if (id.equals(entity.getStarter()) && common.isEmpty((entity.getLeaderokdate()))) {
  %>
      
    <input type="text" name="location" value="${location}" class="rectimyinput" style="width:210px;" maxlength=12>
  
  <%
  } else{
  %>
  ${location}
  <input type="hidden" name="location" value="${location}" class="rectimyinput" style="width:110px;" maxlength=12>
  <%  
  }
  %>
  </td>
  <td width=120 style='width:90.0pt;border-top:none;border-left:none;
  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
  padding:0cm 5.4pt 0cm 5.4pt;'>
  <p class=MsoNormal align=center style='text-align:center'><span
  style='font-size:12.0pt;font-family:宋体'>作业人员</span></p>
  </td>
  <td width=181 style='width:135.75pt;border-top:none;border-left:none;
  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
  padding:0cm 5.4pt 0cm 5.4pt;'>
   <%
  //发起人可以修改
  if (id.equals(entity.getStarter()) && common.isEmpty((entity.getLeaderokdate()))) {
  %>
      
    <input type="text" name="operator" value="${operator}" class="rectimyinput" style="width:110px;" maxlength=12>
  
  <%
  } else{
  %>
  ${operator}
  <input type="hidden" name="operator" value="${operator}" class="rectimyinput" style="width:110px;" maxlength=12>
  <%  
  }
  %>
  </td>
 </tr>
 <tr style='height:140pt;'>
  <td width=572 colspan=4 valign=top style='width:429.15pt;border:solid windowtext 1.0pt;
  border-top:none;padding:0cm 5.4pt 0cm 5.4pt;'>
  <div style="margin:10px 0;"></div>
    安全措施：
  <div style="margin-top:10px">
  
   <%
  //发起人可以修改
  if (id.equals(entity.getStarter()) && common.isEmpty((entity.getLeaderokdate()))) {
  %>
      
    <textarea id="precaution" rows=5 name="precaution" style="width:100%;"maxlength="250">${precaution}</textarea>
  
  <%
  } else{
  %>
  ${precaution}
  <input type="hidden" name="precaution" value="${precaution}" class="rectimyinput" style="width:110px;" maxlength=12>
  <%  
  }
  %>  
  
  
  </div>
  <div style="margin-top:10px; text-align:right;">
  监护人员：
  <%
  //发起人可以修改
  if (id.equals(entity.getStarter()) && common.isEmpty((entity.getLeaderokdate()))) {
  %>
      
    <input type="text" name="precautionchecker" value="${precautionchecker}" class="rectimyinput" style="width:110px;" maxlength=12>
  
  <%
  } else{
  %>
  ${precautionchecker}&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
  <input type="hidden" name="precautionchecker" value="${precautionchecker}" class="rectimyinput" style="width:110px;" maxlength=12>
  <%  
  }
  %>
  </div>
  
  
  </td>
 </tr>
 <tr style='height:140pt'>
  <td width=572 colspan=4 valign=top style='width:429.15pt;border:solid windowtext 1.0pt;
  border-top:none;padding:0cm 5.4pt 0cm 5.4pt;'>
  
   <div style="margin:10px 0;"></div>
    安全告知：
  <div style="margin-top:10px">
  
   <%
  //发起人可以修改
  if (id.equals(entity.getStarter()) && common.isEmpty((entity.getLeaderokdate()))) {
  %>
      
    <textarea id="inform" rows=5 name="inform" style="width:100%;"maxlength="250">${inform}</textarea>
  
  <%
  } else{
  %>
  ${inform}
  <input type="hidden" name="inform" value="${inform}" class="rectimyinput" style="width:110px;" maxlength=12>
  <%  
  }
  %>  
  
  
  </div>
  <div style="margin-top:10px; text-align:right;">
 告知人员：
  <%
  //发起人可以修改
  if (id.equals(entity.getStarter()) && common.isEmpty((entity.getLeaderokdate()))) {
  %>
      
    <input type="text" name="informer" value="${informer}" class="rectimyinput" style="width:110px;" maxlength=12>
  
  <%
  } else{
  %>
  ${informer}&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
  <input type="hidden" name="informer" value="${informer}" class="rectimyinput" style="width:110px;" maxlength=12>
  <%  
  }
  %>
  </div>
  


  </td>
 </tr>
 <tr style='height:81.75pt'>
  <td width=572 colspan=4 valign=top style='width:429.15pt;border:solid windowtext 1.0pt;
  border-top:none;padding:0cm 5.4pt 0cm 5.4pt;height:81.75pt' nowrap>
  <div style="margin:5px 0;"></div>
  安全管理部门负责人意见：
  <%
  //发起人可以修改
  if (role.contains("leader") && common.isEmpty((entity.getLeaderokdate()))) {
  %>
      
    <input type="text" name="leaderokmessage" value="${leaderokmessage}" class="rectimyinput" style="width:210px;" maxlength=12>
  
  <%
  } else{
  %>
  ${leaderokmessage}
  <input type="hidden" name="leaderokmessage" value="${leaderokmessage}" class="rectimyinput" style="width:110px;" maxlength=12>
  <%  
  }
  %>
  <br><br>
  <span style='font-size:12.0pt;font-family:宋体;margin-top:100px; text-align:left;'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
  &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
  负责人（签字）：
  <b><span lang=EN-US style="color:#0000CD;text-align:right;"><span
  style='mso-spacerun:yes'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
  ${leader}
  <input name="leader" id="leader" value="${leader}" type="hidden">
  <input name="leaderokdate" id="leaderokdate" value="${leaderokdate}" type="hidden">
  &nbsp;&nbsp;&nbsp;&nbsp;
  </span>${leaderokdate}</span></b>
  </span>
  </td>
 </tr>
 <tr style='height:86.55pt'>
  <td width=572 colspan=4 valign=top style='width:429.15pt;border:solid windowtext 1.0pt;
  border-top:none;padding:0cm 5.4pt 0cm 5.4pt;height:86.55pt' nowrap>
  <div style="margin:5px 0;"></div>
  分管安全领导意见：
    <%
  //发起人可以修改
  if (role.contains("manager") && common.isEmpty((entity.getManagerokdate()))) {
  %>
      
    <input type="text" name="managerokmessage" value="${managerokmessage}" class="rectimyinput" style="width:210px;" maxlength=12>
  
  <%
  } else{
  %>
  ${managerokmessage}
  <input type="hidden" name="managerokmessage" value="${managerokmessage}" class="rectimyinput" style="width:110px;" maxlength=12>
  <%  
  }
  %>
  <br>
  <p class=MsoNormal><span lang=EN-US style='font-size:12.0pt;margin-top:10px; text-align:left;'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
  &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
  &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
&nbsp;&nbsp;
  </span><span style='font-size:12.0pt;font-family:宋体;'>分管领导（签字）：
  
    <b><span lang=EN-US style="color:#0000CD;text-align:right;"><span
  style='mso-spacerun:yes'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
  ${manager}
  <input name="manager" id="manager" value="${manager}" type="hidden">
  <input name="managerokdate" id="managerokdate" value="${managerokdate}" type="hidden">
  &nbsp;&nbsp;&nbsp;&nbsp;
  </span>${managerokdate}</span></b>
  
  
  </span></p>

  </td>


    <%
	if (taskname.contains("开始节点") & role.contains("yuangong")) {
	%>
	 <TR>
	  <td colspan=2>
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
	} else if (taskname.contains("发起人验收导出审批单") & id.equals(entity.getStarter())& "".equals(entity.getStarterokdate())) {
	%>	
	<TR>
	  <td colspan=6>
	  <br/>
		<BR/>
		
		<a href="javascript:pass('','结束','');" class="easyui-linkbutton"  style="padding-left: 0px;padding:10px 0px;width:100%;" >
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
	}
	%>
  
 
   <tr >
    <td colspan=3 style="color:black;text-align:left;font-size:12.0pt;width:50%;border:solid windowtext 0.0pt;">
      
      <b>${messeageForSave }</b>
      
    </td>
    <td colspan=3 style="color:black;text-align:left;font-size:12.0pt;width:50%">
      
      <b>${messeageForSubmit }</b>
      
    </td>
  </tr> 

   <tr >
    <td colspan=2 style="color:black;text-align:left;font-size:12.0pt;width:50%;border:solid windowtext 0.0pt;">
      
      <%
      if (!common.isEmpty((String)request.getAttribute("messeageForSave"))) {
      %>
	  <a href="javascript:gotoListRectiDangerjsp();" class="easyui-linkbutton"  style="padding-left: 0px;padding:10px 0px;width:100%" >
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
