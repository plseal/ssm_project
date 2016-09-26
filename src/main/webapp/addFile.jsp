<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@include  file="sessiontimeout.jsp"%>

<%@ page import="java.io.File" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>上传文件</title>

<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/jquery-easyui-1.4.3/themes/default/easyui.css" />
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/jquery-easyui-1.4.3/themes/icon.css" />
<script type="text/javascript" src="${pageContext.request.contextPath}/jquery-easyui-1.4.3/jquery.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/jquery-easyui-1.4.3/jquery.easyui.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/jquery-easyui-1.4.3/locale/easyui-lang-zh_CN.js"></script>
<script type="text/javascript">

function uploadFile(){
	
	document.all.myform.action="${pageContext.request.contextPath}/uploadFile/saveuploadFile.do";
	document.all.myform.submit();
}

</script>
</head>
<body>
    	
         <form id="myform"  method="post" enctype="multipart/form-data" >   
         
             上传文件：<input class="easyui-filebox" style="width:200px" data-options="buttonText:'选择文件'"  name="fileObj"/>    
           
			<a href="javascript:uploadFile();" class="easyui-linkbutton"  dstyle="padding-left: 10px;padding:10px 0px;width:90" >
				<span style="font-size:15px;">保存文件</span>
			</a>
			
         </form>  
     
     <iframe width=100% height="200pt"  src="${pageContext.request.contextPath}/uploadFile/addFileList.do?flg=qita" ></iframe>

</body>
</html>
	