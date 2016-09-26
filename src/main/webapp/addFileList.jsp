<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@include  file="sessiontimeout.jsp"%>
<%@ page import="com.lin.entity.RectiNewEntity" %>
<%@ page import="java.io.File" %>
<%@ page import="com.lin.util.common" %>
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



</script>




</head>
<body>

 <form name = "myform">
<%

	
RectiNewEntity entity = new RectiNewEntity();

entity = (RectiNewEntity)request.getAttribute("entity");

String javaid = entity.getJavaid();

if (common.isEmpty(javaid)) {
	javaid = (String)session.getAttribute("javaid");
}

String path = request.getContextPath();
String basePath = request.getScheme() + "://"
		+ request.getServerName() + ":" + request.getServerPort()
		+ path + "/";

String strRealHTTPPath = basePath+"upload\\"+javaid+"\\";

String strRealServerPath1 = request.getRealPath("/") + "upload\\";
File file =new File(strRealServerPath1);    
//如果文件夹不存在则创建    
if  (!file .exists()  && !file .isDirectory())  {       
    System.out.println("//不存在(用户文件夹)--创建文件夹"+strRealServerPath1);  
    file .mkdir();    
} else {  
    System.out.println("//目录存在"+strRealServerPath1);  
}  
String strRealServerPath2 = strRealServerPath1+javaid+"\\";
file =new File(strRealServerPath2);    
//如果文件夹不存在则创建    
if  (!file .exists()  && !file .isDirectory())  {       
    System.out.println("//不存在(javaid)--创建文件夹"+strRealServerPath2);  
    file .mkdir();    
} else   
{  
    System.out.println("//目录存在"+strRealServerPath2);  
}  

File f=new File(strRealServerPath2);

	

    File[] f1=f.listFiles();
    
    if (f1.length >=1) {

	    out.println("<table border = 1>");
	
	    for(int i=0;i<f1.length;i++){
	    	String filename = f1[i].getName();
	    	String prefix=filename.substring(filename.lastIndexOf(".")+1);
	    	
	    	out.println("<TR>");
	    	out.println("  <TD>");
	    	
	    	if ("png".equals(prefix) || "jpg".equals(prefix) || "bmp".equals(prefix)) {
	    		out.println("    <img src='"+strRealHTTPPath + filename + "' width='500' height='200'>");
	    	}else {
	    		out.println("    <a href='"+strRealHTTPPath + filename +"' target='_blank'>" + filename + "</a>");
	    	}
	    	out.println("  </TD>");
	      	out.println("  <TD width=30>");
	    	out.println("    <a href='"+request.getContextPath()+"/" 
	    			+"uploadFile/deleteUploadFile.do?strFile=" + filename + "' class='easyui-linkbutton' >删除</a>");
	    	
	    	out.println("  </TD>");
	    	out.println("</TR>");
	    	
	    	
	    }
	    out.println("</table>");
    }

%>

</form>
		

</body>
</html>
	