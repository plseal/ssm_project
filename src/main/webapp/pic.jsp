<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
<script type="text/javascript">
function return_() {
	document.all.myform.action='${pageContext.request.contextPath}/${prepagejsp}';
	document.all.myform.submit();
}
</script>
</head>
<body bgcolor="#ffffff">
	
	<form method="post" id="myform"  >
	  <a href="javascript:return_();" class="easyui-linkbutton" style="padding-left: 0px;padding:10px 0px;width:500pt">
  &nbsp;返&nbsp;回&nbsp;</a>
	<img
		src="${pageContext.request.contextPath}/admin/pic_.do?processInstanceId=${id }"
		style="position:absolute;left:0px;top:100px;" />
	<c:if test="${activityCoordinates!=null }">
		<div
			style="position:absolute;border:3px solid red;left:${activityCoordinates.x }px;
			top:${activityCoordinates.y+100 }px;width:${activityCoordinates.width }px;
			height:${activityCoordinates.height}px;">
		</div>
	</c:if>
	<c:if test="${ac!=null }">
		<c:forEach items="${ac }" var="a">
			<div
				style="position:absolute;border:3px solid blue;
				left:${a.x }px;top:${a.y +100}px;width:${a.width }px;
				height:${a.height}px;">
			</div>

		</c:forEach>
	</c:if>
	</form>
</body>

</html>