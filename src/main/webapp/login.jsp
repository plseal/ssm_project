<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html >
	<head>
		<meta charset="utf-8" />
		<title>安全隐患排查治理信息化平台</title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/jquery-easyui-1.4.3/themes/default/easyui.css" />
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/jquery-easyui-1.4.3/themes/icon.css" />
<script type="text/javascript" src="${pageContext.request.contextPath}/jquery-easyui-1.4.3/jquery.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/jquery-easyui-1.4.3/jquery.easyui.min.js"></script>

		<meta name="description" content="User login page" />
		<meta name="viewport" content="width=device-width, initial-scale=1.0" />

		<!-- basic styles -->

		<link href="${pageContext.request.contextPath}/assets/css/bootstrap.min.css" rel="stylesheet" />
		<link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/font-awesome.min.css" />

		<!--[if IE 7]>
		  <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/font-awesome-ie7.min.css" />
		<![endif]-->

		<!-- page specific plugin styles -->

		<!-- fonts -->

		

		<!-- ace styles -->

		<link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/ace.min.css" />
		<link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/ace-rtl.min.css" />

		<!--[if lte IE 8]>
		  <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/ace-ie.min.css" />
		<![endif]-->

		<!-- inline styles related to this page -->

		<!-- HTML5 shim and Respond.js IE8 support of HTML5 elements and media queries -->

		<!--[if lt IE 9]>
		<script src="${pageContext.request.contextPath}/assets/js/html5shiv.js"></script>
		<script src="${pageContext.request.contextPath}/assets/js/respond.min.js"></script>
		<![endif]-->
		<script type="text/javascript">
		    document.onkeydown=function(event){
		        var e = event || window.event || arguments.callee.caller.arguments[0];
		         if(e && e.keyCode==13){ // enter 键
		        	 toIndexJSP();
		        }
		    }; 
			function toIndexJSP(){
				document.all.myform.action="${pageContext.request.contextPath}/admin/loginnew.do";
				document.all.myform.submit();
			}
            function toRegisterJSP(){
                document.all.myform.action="${pageContext.request.contextPath}/register.jsp";
                document.all.myform.submit();
            }
			function init(id){
				document.all.myform.id.value=id;
				document.all.myform.pas.value='111';
			}
		</script>
	</head>
<style type="text/css">
body{ background:url(${pageContext.request.contextPath}/images/login_1.jpg);
background-size:100% 100%;
background-repeat:no-repeat;}
html,body{
width:100%; 

height:100%;

}  

<!--以下设置全屏--> 

body {
margin-left: 0px;
margin-top: 0px;
margin-right: 0px;
margin-bottom: 0px;
}
</style>
	<body >
	
<table width="100%" height="100%" border="1" cellspacing="0" cellpadding="0">
<tr>
<td align="center" valign="middle"> 

                                            <form method="post" id="myform">
	<table width="20%" border="0" align="center" cellpadding="0" cellspacing="0">
	<tr>
	<td>用户名:<input type="text" name="id" class="form-control" value="" placeholder="输入用户名" /></td>
	</tr>
    <tr>
    <td>&nbsp;</td>
    </tr>
    <tr>
    <td>密码:<input type="password" name="pas" class="form-control" placeholder="输入密码" />
                                                        <%
    
                                                        String errorMSG = (String)request.getAttribute("errorMSG");
                                                        if (errorMSG == null || errorMSG.length() == 0 || "null".equals(errorMSG) ){
                                                            errorMSG ="";
                                                        }
                                                        %>
                                                        <p style='color:red'><b><%=errorMSG%></b>   </p>
                                                        <div class="space"></div>
                                                        
    </td>
    </tr>
    <tr>
    <td>
		<a href="#" class="btn btn-lg btn-success"  style="width:100%;" onclick="toIndexJSP()">
		<span style="font-size:18pt;">
		    登录系统</span>
		</a>
		<div class="space-4"></div>
		
    </td>
    </tr>
	</table> 
        </form>

</td>
</tr>
</table>
		


	</body>
</html>
