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



</script>
</head>
<body><BR>
<!-- 

<input type="button" value="返回" onclick="return_();">

listTask.jsp

 -->
 <form name = "myform">
<%
	//String userid = request.getParameter("userid");
	//String userrole = request.getParameter("userrole");
	//System.out.println("rectiSelfMakeSure.jsp:"+userrole);
	//if(userid != null && !"".equals(userid)){
	//	session.setAttribute("userid",userid);
	//}
	//if(userrole != null && !"".equals(userrole)){
	//	session.setAttribute("userrole",userrole);
	//}
	//RectiSelfService rectiSelfService = new RectiSelfService();
	//String userrole = rectiSelfService.getUserAndRole(userid).getRoleName();
	
	//session.setAttribute("userrole",userrole);
	String javaid = (String)session.getAttribute("javaid");
	
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
	
	String strRealHTTPPath = 
	basePath+"upload\\"+session.getAttribute("javaid")+"\\";
	
	System.out.println("strRealHTTPPath:"+strRealHTTPPath);
	String strRealServerPath1 = 
	request.getRealPath("/") + "upload\\";
	
	System.out.println("strRealServerPath:"+strRealServerPath1);
	
	
	File file =new File(strRealServerPath1);    
	//如果文件夹不存在则创建    
	if  (!file .exists()  && !file .isDirectory())      
	{       
	    System.out.println("//不存在(用户文件夹)--创建文件夹");  
	    file .mkdir();    
	} else   
	{  
	    System.out.println("//目录存在");  
	}  
	String strRealServerPath2 = strRealServerPath1+session.getAttribute("javaid")+"\\";
	
	file =new File(strRealServerPath2);    
	//如果文件夹不存在则创建    
	if  (!file .exists()  && !file .isDirectory())      
	{       
	    System.out.println("//不存在(javaid)--创建文件夹");  
	    file .mkdir();    
	} else   
	{  
	    System.out.println("//目录存在");  
	}  
	
	File f=new File(strRealServerPath2);
    File[] f1=f.listFiles();
    
    
    out.println("");
    out.println("<table border = 0 width = '100%' >");

    for(int i=0;i<f1.length;i++){
    	out.println("<TR>");
    	out.println("  <TD width='15%'>");
    	out.println("    文件"+(i+1)+":");
    	out.println("  </TD>");
    	out.println("  <TD>");
    	out.println("    <a href='"+strRealHTTPPath+f1[i].getName()+"' target='_blank'>  "+f1[i].getName()+"  </a>");
    	out.println("  </TD>");
    
    	out.println("</TR>");
    }
    out.println("</table>");
    

%>

</form>
		

</body>
</html>
	