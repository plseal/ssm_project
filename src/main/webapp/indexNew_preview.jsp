<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@include  file="sessiontimeout.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head >
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>安全隐患排查治理信息化平台</title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/default.css"/>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/jquery-easyui-1.4.3/themes/default/easyui.css" />
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/jquery-easyui-1.4.3/themes/icon.css" />
<script type="text/javascript" src="${pageContext.request.contextPath}/jquery-easyui-1.4.3/jquery.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/jquery-easyui-1.4.3/jquery.easyui.min.js"></script>
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
		<link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/ace-skins.min.css" />

	
		  <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/ace-ie.min.css" />
		

		<!-- inline styles related to this page -->

		<!-- ace settings handler -->

		<script src="${pageContext.request.contextPath}/assets/js/ace-extra.min.js"></script>

		<!-- HTML5 shim and Respond.js IE8 support of HTML5 elements and media queries -->

		<!--[if lt IE 9]>
		<script src="${pageContext.request.contextPath}/assets/js/html5shiv.js"></script>
		<script src="${pageContext.request.contextPath}/assets/js/respond.min.js"></script>
		<![endif]-->
	</head>

	<body onload="init_();">
		<div class="navbar navbar-default" id="navbar">
			<script type="text/javascript">
				try{ace.settings.check('navbar' , 'fixed')}catch(e){}
				function init_() {
					goto_('${pageContext.request.contextPath}/listTask.jsp');
				}
				function goto_(strJsp){
					
					document.getElementById("indexFrame").src=strJsp;
					document.getElementById("indexFrame").style.width=(document.documentElement.clientWidth-250)+'px';
					//alert(document.documentElement.clientHeight)
					document.getElementById("indexFrame").style.height=(document.documentElement.clientHeight-200)+'px';
				}
				
				
		
				 /*第一次读取最新通知*/
				  setTimeout(function() {
				             Push();
				           },200);
				  /*15秒轮询读取函数*/
				  setInterval(function() {
				            Push();
				    },
				        15000);

				/*请求函数的ajax*/

				function Push() {


				    $.ajax({

				        type: "POST",
				        url: "${pageContext.request.contextPath}/admin/getMsg.do",
				        data: {
				            t: 3
				        },
				        beforeSend: function() {},
				        success: function(data) {

				            
				            //alert(data.msgsize);
				            if (data.msgsize > 0) {
				            	//alert(data.msgsize);
				                //$(".tongzhi").html(obj.sixin).show();
				            	$.messager.show({
				            	    title:'消息',
				            	    msg:'<a href="javascript:goto_(\'${pageContext.request.contextPath}/listTask.jsp\');">您有一条新任务等待处理 </a>',
				            	    timeout:15000,
				            	    showType:'slide'
				            	});

				            } else {
				                //$(".tongzhi").html(0).hide();

				            }


				        }


				    });
				}
				
			</script>

			<div class="navbar-container" id="navbar-container">
				<div class="navbar-header pull-left">
					<a href="#" class="navbar-brand">
						<small>
							
							工作流审批系统
						</small>
					</a><!-- /.brand -->
				</div><!-- /.navbar-header -->

				<div class="navbar-header pull-right" role="navigation">
					<ul class="nav ace-nav">
						<li class="green">
							<a data-toggle="dropdown" class="dropdown-toggle" href="#">
								
								用户：<%=session.getAttribute("name") %>
							</a>
						</li>
    					<li class="light-blue">
							<a  href="javascript:window.location.href='${pageContext.request.contextPath}/login_preview.jsp';" >
								
								退出登录
							</a>
						</li>
					</ul><!-- /.ace-nav -->
				</div><!-- /.navbar-header -->
			</div><!-- /.container -->
		</div>

		<div class="main-container" id="main-container">
			<script type="text/javascript">
				try{ace.settings.check('main-container' , 'fixed')}catch(e){}
			</script>

			<div class="main-container-inner">
				<a class="menu-toggler" id="menu-toggler" href="#">
					<span class="menu-text"></span>
				</a>

				<div class="sidebar" id="sidebar">
					<script type="text/javascript">
						try{ace.settings.check('sidebar' , 'fixed')}catch(e){}
					</script>

					<div class="sidebar-shortcuts" id="sidebar-shortcuts">
						<div class="sidebar-shortcuts-large" id="sidebar-shortcuts-large">
							<button class="btn btn-success">
								<i class="icon-signal"></i>
							</button>

							<button class="btn btn-info">
								<i class="icon-pencil"></i>
							</button>

							<button class="btn btn-warning">
								<i class="icon-group"></i>
							</button>

							<button class="btn btn-danger">
								<i class="icon-cogs"></i>
							</button>
						</div>

						<div class="sidebar-shortcuts-mini" id="sidebar-shortcuts-mini">
							<span class="btn btn-success"></span>

							<span class="btn btn-info"></span>

							<span class="btn btn-warning"></span>

							<span class="btn btn-danger"></span>
						</div>
					</div><!-- #sidebar-shortcuts -->

					<ul class="nav nav-list">
						
						<li>
							<a href="#" class="dropdown-toggle">
								
								<span class="menu-text"> 隐患排查</span>

								<b class="arrow icon-angle-down"></b>
							</a>

							<ul class="submenu">

								<li>
									<a href="#" class="dropdown-toggle">
										<i class="icon-double-angle-right"></i>

										隐患录入
										<b class="arrow icon-angle-down"></b>
									</a>

									<ul class="submenu">
										<li>
											<a href="javascript:goto_('${pageContext.request.contextPath}/rectiNew/initRectiNew.do');" style="font-size:12px;">
												
												自查隐患信息
											</a>
										</li>
										<li>
										
											<a href="#" class="dropdown-toggle" style="font-size:12px;">
												安全检查隐患信息
												<b class="arrow icon-angle-down"></b>
											</a>
	                                        <ul class="submenu">
	                                                <li>
	                                                    <a href="javascript:goto_('${pageContext.request.contextPath}/rectiCheck/initRectiCheck.do');"style="font-size:12px;">
	                                                                                                                                                       市局安全检查
	                                                    </a>
	                                                </li>
	
	                                                <li>
	                                                    <a href="javascript:goto_('${pageContext.request.contextPath}/rectiCheckXiashu/initRectiCheckXiashu.do');"style="font-size:12px;">
	                                                                                                                                                       直属单位安全检查
	                                                    </a>
	                                                </li>
	                                        </ul>
										</li>
										
									</ul>
								</li>
								<li>
								  <a href="javascript:goto_('${pageContext.request.contextPath}/rectiNew/toListRectiNew.do?listRectiAc=this_month');">
								  		<span > 隐患信息管理 </span>
								  </a>
								</li>
								<li>
								  <a href="javascript:goto_('${pageContext.request.contextPath}/analysis/getdata.do');">
   								  		<span > 隐患信息统计分析 </span>
								  </a>
								</li>

								<li>
									<a href="javascript:goto_('${pageContext.request.contextPath}/standardDatabase.jsp');">
										<span > 隐患管理标准数据库 </span>
									</a>
								</li>
								<li>
									<a href="javascript:goto_('${pageContext.request.contextPath}/standardpost/02.shixianju.htm');">
										
										<span > 岗位达标标准 </span>
									</a>
								</li>
								<li>
									<a href="javascript:goto_('${pageContext.request.contextPath}/error-jianshezhong.jsp');">
										
										<span > 安全管理考核 </span>
									</a>
								</li>
								
							</ul>
						</li>
						
						
						<li>
							<a href="javascript:goto_('${pageContext.request.contextPath}/rectiDanger/initRectiDanger.do');">
								
								<span class="menu-text"> 危险作业审批 </span>
							</a>
						</li>
                        <li>
                            <a href="javascript:goto_('${pageContext.request.contextPath}/rectiDanger/toListRectiDanger.do?listRectiAc=this_month');">
                                
                                <span class="menu-text"> 危险作业审批管理 </span>
                            </a>
                        </li>
						
						<li>
							<a href="javascript:goto_('${pageContext.request.contextPath}/error-jianshezhong.jsp');">
								
								<span class="menu-text"> 员工管理 </span>
							</a>
						</li>
												
						<li>
							<a href="javascript:goto_('${pageContext.request.contextPath}/listTask.jsp');">
								
								<span class="menu-text"> 个人中心 </span>
							</a>
						</li>
						
						<li>
							<a href="javascript:goto_('${pageContext.request.contextPath}/news.jsp');">
								
								<span class="menu-text"> 内部资讯 </span>
							</a>
						</li>
<%
if (session.getAttribute("role").equals("admin")) {

%>
						<li>
							<a href="javascript:goto_('${pageContext.request.contextPath}/deployZIP.jsp');">
								
								<span class="menu-text"> 系统管理 </span>
							</a>
						</li>

						<li>
							<a href="javascript:goto_('${pageContext.request.contextPath}/admin/to_userAdminJSP.do');">
								
								<span class="menu-text"> 用户管理 </span>
							</a>
						</li>
						
                        <li>
                            <a href="javascript:goto_('${pageContext.request.contextPath}/listNGnames.jsp');">
                                
                                <span class="menu-text"> 姓名重复人员一览 </span>
                            </a>
                        </li>
						

<%
}

%>
					</ul><!-- /.nav-list -->

					<div class="sidebar-collapse" id="sidebar-collapse">
						<i class="icon-double-angle-left" data-icon1="icon-double-angle-left" data-icon2="icon-double-angle-right"></i>
					</div>

					<script type="text/javascript">
						try{ace.settings.check('sidebar' , 'collapsed')}catch(e){}
					</script>
				</div>

				<div class="main-content" >
					<div class="breadcrumbs" id="breadcrumbs">
						<script type="text/javascript">
							try{ace.settings.check('breadcrumbs' , 'fixed')}catch(e){}
						</script>
					</div>

					<div class="page-content">
						<div class="row">
							<div class="col-xs-12">
								<!-- PAGE CONTENT BEGINS -->
<iframe id="indexFrame" width=800px height=600px border=1 frameborder=”no” src='' ></iframe>
								<!-- PAGE CONTENT ENDS -->
							</div><!-- /.col -->
						</div><!-- /.row -->
					</div><!-- /.page-content -->
				</div><!-- /.main-content -->

				
			</div><!-- /.main-container-inner -->

		</div><!-- /.main-container -->


		<script type="text/javascript">
			window.jQuery || document.write("<script src='${pageContext.request.contextPath}/assets/js/jquery-2.0.3.min.js'>"+"<"+"/script>");
		</script>

		<!-- <![endif]-->

		<!--[if IE]>
<script type="text/javascript">
 window.jQuery || document.write("<script src='${pageContext.request.contextPath}/assets/js/jquery-1.10.2.min.js'>"+"<"+"/script>");
</script>
<![endif]-->

		<script type="text/javascript">
			if("ontouchend" in document) document.write("<script src='${pageContext.request.contextPath}/assets/js/jquery.mobile.custom.min.js'>"+"<"+"/script>");
		</script>
		<script src="${pageContext.request.contextPath}/assets/js/bootstrap.min.js"></script>
		<script src="${pageContext.request.contextPath}/assets/js/typeahead-bs2.min.js"></script>

		<!-- page specific plugin scripts -->

		<!-- ace scripts -->

		<script src="${pageContext.request.contextPath}/assets/js/ace-elements.min.js"></script>
		<script src="${pageContext.request.contextPath}/assets/js/ace.min.js"></script>

		<!-- inline scripts related to this page -->
	</body>
</html>
