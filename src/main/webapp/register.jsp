<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<%@ page import="com.lin.util.common" %>
<!DOCTYPE html>
<html >
	<head>
		<meta charset="utf-8" />
		<title>安全隐患排查治理信息化平台</title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/jquery-easyui-1.4.3/themes/default/easyui.css" />
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/jquery-easyui-1.4.3/themes/icon.css" />
<script type="text/javascript" src="${pageContext.request.contextPath}/jquery-easyui-1.4.3/jquery.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/jquery-easyui-1.4.3/jquery.easyui.min.js"></script>
		<!-- basic styles -->

		<link href="${pageContext.request.contextPath}/assets/css/bootstrap.min.css" rel="stylesheet" />
		<link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/font-awesome.min.css" />
		<link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/ace.min.css" />
		<link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/ace-rtl.min.css" />

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
            function onload_(){
            	show_box('signup-box');
            	return false;
            }
            function toLoginJSP(){
                document.all.myform.action="${pageContext.request.contextPath}/login.jsp";
                document.all.myform.submit();
            }
            function submitForm(){
                $('#myform').form('submit',{
                    onSubmit:function(){
                        return $(this).form('enableValidation').form('validate');
                    }
                });
            }
            $.extend($.fn.validatebox.defaults.rules, {
                /*必须和某个字段相等*/
                equalTo: { validator: function (value, param) { return $(param[0]).val() == value; }, message: '字段不匹配' }
            });
		</script>
	</head>

	<body class="login-layout" onload="onload_();">
	  <form method="post" id="myform">
		<div class="main-container">
			<div class="main-content">
				<div class="row">
					<div class="col-sm-10 col-sm-offset-1">
						<div class="login-container">
							<div class="center">
								<h1>
									
									<span class="red"></span>
									
								</h1>
								<h4 class="blue" style="font-size:20.0pt;">
								山东济宁烟草有限公司<br/>
								安全隐患排查治理信息化平台</h4>
							</div>

							<div class="space-6"></div>

							<div class="position-relative">
							    <div id="signup-box" class="signup-box widget-box no-border">
                                    <div class="widget-body">
                                        <div class="widget-main">
                                            <h4 class="header green lighter bigger">
                                                <i class="icon-group blue"></i>
                                                                                                                               注册页面                                                 
                                            </h4>

                                            <div class="space-6"></div>
                                            <p> 输入注册信息: </p>

                                            <form>
                                                <fieldset>
                                                    <label class="block clearfix">
                                                        <span class="block input-icon input-icon-right">
                                                            <input type="email" class="form-control" placeholder="电子邮箱（E-Mail）" />
                                                            <i class="icon-envelope"></i>
                                                        </span>
                                                    </label>

                                                    <label class="block clearfix">
                                                        <span class="block input-icon input-icon-right">
                                                            <input type="text" class="form-control" placeholder="用户名" />
                                                            <i class="icon-user"></i>
                                                        </span>
                                                    </label>

                                                    <label class="block clearfix">
                                                        <span class="block input-icon input-icon-right">
                                                            <input type="password" class="form-control" placeholder="密码" />
                                                            <i class="icon-lock"></i>
                                                        </span>
                                                    </label>

                                                    <label class="block clearfix">
                                                        <span class="block input-icon input-icon-right">
                                                            <input type="password" class="form-control" placeholder="重复密码" />
                                                            <i class="icon-retweet"></i>
                                                        </span>
                                                    </label>


                                                     <select class="form-control" id="form-field-select-1">
                                                         <option value="请选择单位（部门）">请选择单位（部门）</option>
														  <option value="市局" >市局</option> 
														  <option value="嘉祥县局" >嘉祥县局</option> 
														  <option value="金乡县局" >金乡县局</option> 
														  <option value="梁山县局" >梁山县局</option>
														  <option value="曲阜市局" >曲阜市局</option> 
														  <option value="任城区局" >任城区局</option> 
														  <option value="泗水县局" >泗水县局</option> 
														  <option value="微山县局" >微山县局</option> 
														  <option value="汶上县局" >汶上县局</option>
														  <option value="兖州区局" >兖州区局</option>
														  <option value="鱼台县局" >鱼台县局</option> 
														  <option value="邹城市局" >邹城市局</option> 
														  <option value="物流分公司" >物流分公司</option>
														  <option value="济宁泰山1532公司" >济宁泰山1532公司</option>
														  <option value="海川物流" >海川物流</option>
                  
                                                     </select>
 密码：          <input type="password" id="password" name="password" validType="length[4,32]" class="easyui-validatebox" required="true"  value=""/><br/>
 确认密码：<input type="password" name="repassword" id="repassword" required="true" class="easyui-validatebox"  validType="equalTo['#password']" invalidMessage="两次输入密码不匹配"/>

                                                    <div class="space-24"></div>

                                                    <div class="clearfix">
                                                        <button type="reset" class="width-30 pull-left btn btn-sm">
                                                            <i class="icon-refresh"></i>清空
                                                        </button>

                                                        <button type="button" onclick="submitForm()" class="width-65 pull-right btn btn-sm btn-success">
                                                                                                                                                                
                                                            <i class="icon-arrow-right icon-on-right"></i>
                                                                                                                                                                    注册
                                                        </button>
                                                    </div>
                                                </fieldset>
                                            </form>
                                        </div>

                                        <div class="toolbar center">
                                            <a href="#" onclick="toLoginJSP();" class="back-to-login-link">
                                                <i class="icon-arrow-left"></i>
                                                                                                                                返回登陆页面
                                            </a>
                                        </div>
                                    </div><!-- /widget-body -->
                                </div><!-- /signup-box -->

								
								
							</div><!-- /position-relative -->
						</div>
					</div><!-- /.col -->
				</div><!-- /.row -->
			</div>
		</div><!-- /.main-container -->



		<script type="text/javascript">
			function show_box(id) {
			 jQuery('.widget-box.visible').removeClass('visible');
			 jQuery('#'+id).addClass('visible');
			}
		</script>
      </form>
	</body>
</html>
