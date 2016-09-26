<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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

function closeThis(){
	window.close();
	
}
function return_() {
	history.back(-1);
}

</script>
</head>
<body><BR>


    <span style="font-size:18px;color:#FF2400;">
    	请选择要上传的文件  
         <form id="myform"  method="post" enctype="multipart/form-data" >   
            
  
             <div style="margin-bottom:10px">  
			<a href="#" class="easyui-linkbutton"  style="padding-left: 0px;padding:10px 0px;width:300;" onclick="return_()">
				<span style="font-size:15px;">&nbsp;&nbsp;返回&nbsp;&nbsp;&nbsp;&nbsp;</span>
			</a>
			</div>

         </form>  
      	
    	
    </span> 
     		

</body>
</html>
	