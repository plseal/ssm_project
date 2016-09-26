<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@include  file="sessiontimeout.jsp"%>

<%@ page import="java.io.File" %>
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


function return_() {
	history.back(-1);
}
</script>
</head>
<body><BR>

<form name = "myform">

<table style='width:475.7pt;'>
    <TR >
   	  <TD>
   	  		<a href="javascript:return_();" class="easyui-linkbutton" style="padding-left: 0px;padding:10px 0px;width:100%" >
			&nbsp;返&nbsp;回&nbsp;</a>
   	 </TD>
   	</TR>

</table>
<BR/>
<table style='width:475.7pt;'border=1>

   	<TR style='border:solid windowtext 1pt;'>
   	  <TD>
   	  		<a href='${strRealHTTPPath}'class="easyui-linkbutton" style="padding-left: 0px;padding:10px 0px;width:100%">
    	  ${strFileNameForView} </a>
    	  
   	 </TD>
  	</TR>

</table>
</form>
		

</body>
</html>
	