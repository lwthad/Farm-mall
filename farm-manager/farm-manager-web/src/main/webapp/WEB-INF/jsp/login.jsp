<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>管理员登录</title>
</head>
<body style="background-color: #F3F3F3">

    <div class="easyui-dialog" title="管理员登录" data-options="closable:false,draggable:false" style="width:400px;height:300px;padding:10px;">
       	<div style="margin-left: 50px;margin-top: 50px;">
       		<div style="margin-bottom:20px;">
	            <div>
	            	用户名: <input name="username" class="easyui-textbox" data-options="required:true" style="width:200px;height:32px" value="admin"/>
	            </div>
	        </div>
	        <div style="margin-bottom:20px">
	            <div>
	            	密&nbsp;&nbsp;码: <input name="password" class="easyui-textbox" type="password" style="width:200px;height:32px" data-options="" value="admin"/>
	            </div>
	        </div>
	        <div>
	            <a id="login" class="easyui-linkbutton" iconCls="icon-ok" style="width:100px;height:32px;margin-left: 50px">登录</a>
	        </div>
       	</div>
    </div>
    
    <script type="text/javascript">
    	$("#login").click(function(){
    		var username = $("[name=username]").val();
    		var password = $("[name=password]").val();
    		
    		if(username!="admin" || password!="admin"){
    			$.messager.alert('错误',"用户名密码不正确！");
    			return ;
    		}
    		window.location.href="/";
    	});
    </script>
    <script type="text/javascript">
		var redirectUrl = "${redirect}";
		var LOGIN = {
				checkInput:function() {
					if ($("#loginname").val() == "") {
						alert("用户名不能为空");
						$("#loginname").focus();
						return false;
					}
					if ($("#nloginpwd").val() == "") {
						alert("密码不能为空");
						$("#nloginpwd").focus();
						return false;
					}
					return true;
				},
				doLogin:function() {
					$.post("/user/login", $("#formlogin").serialize(),function(data){
						if (data.status == 200) {
							alert("登录成功！");
							if (redirectUrl == "") {
									location.href = "http://www.farm-mall.com"; 
								   /* location.href = "http://localhost:8082/";  */
								   /* location.href = "http://192.168.29.129:8081/";   */
							} else {
								location.href = redirectUrl;
							}
						} else {
							alert("登录失败，原因是：" + data.msg);
							$("#loginname").select();
						}
					});
				},
				login:function() {
					if (this.checkInput()) {
						this.doLogin();
					}
				}
			
		};
		$(function(){
			$("#loginsubmit").click(function(){
				LOGIN.login();
			});
		});
	</script>
</body>
</html>