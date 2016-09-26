<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%

if(session.getAttribute("id")==null) {
	
	response.sendRedirect("../error-sessiontimeout.jsp");
}
%>

<iframe width=100 height=100  src="${pageContext.request.contextPath}/session_keep.jsp" style= "display :none "></iframe> 

  