<%@ page language="java" contentType="text/html; charset=UTF-8" %>

<%@page import="java.util.Date"%>
<%@page import="java.text.SimpleDateFormat"%>
<html>  
<body onload="keepsession()" >
 <head>   
  
   
</head>   


<p></p>  
<pre></pre>  
<p></p>  

 <script type="text/javascript">   
function keepsession(){   
	
//每隔300秒刷新页面
window.setTimeout("reload()",300000);
}   
function reload(){   
	//这里的RandStr=Math.random只是为了让每次back.src的值不同，防止同一地址刷新无效的情况   
	window.location="${pageContext.request.contextPath}/session_keep.jsp?RandStr="+Math.random();
	} 
//keepsession();   
</script> <br>  
<p></p>  
<span style="font-family:Helvetica,Tahoma,Arial,sans-serif; font-size:14px; line-height:25px; background-color:rgb(250,250,250)">
session_keep.jsp
<%
Date date = new Date();
SimpleDateFormat df = new SimpleDateFormat("YYYY-MM-dd HH:mm:ss");
String strTime = df.format(date);
%>
<%=strTime %>
</span>
</body>
</html> 
 
