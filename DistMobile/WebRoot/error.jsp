<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>

<!DOCTYPE html>
<html>
   <head>
      <title>Login</title>
      <meta name="viewport" content="width=device-width, initial-scale=1.0">
	  
      <!-- 引入 Bootstrap -->
	  <link href="template/css/bootstrap.min.css" rel="stylesheet" />
	  
	  <!-- 引入自己的样式表 -->
	  <link href="template/css/main.css" rel="stylesheet" />
	  <link href="template/css/login.css" rel="stylesheet" />
      <!--<link href="http://apps.bdimg.com/libs/bootstrap/3.3.0/template/css/bootstrap.min.css" rel="stylesheet">-->
	  
      
	  <!-- 回到顶部 -->
	  
	  <!--<link href="template/css/nav.css" rel="stylesheet"/>-->
	  <!-- jQuery (Bootstrap 的 JavaScript 插件需要引入 jQuery) -->
	  <script src="template/js/jquery-2.1.1.js"></script>
	  <!--
      <script src="https://code.jquery.com/jquery.js"></script>
	  -->
      <!-- 包括所有已编译的插件 -->
      <script src="template/js/bootstrap.min.js"></script>
   </head>
 <body  onLoad="init()">
 
 
 
 
 
 <script type="text/javascript"> 
<!--
function init() { 

	 alert("用户名或密码错误！");
}
-->
</script>
 
 
 
 
 
   
   		<!-- 导航栏  begin-->		

		<!-- 导航栏 end-->


		<div class="container loginPage">
			<div class="loginBlock">
				<div class="loginTitle">
					<h1>Login</h1>
				</div>
				<div class="loginContent">
				<form action="user!loginsys.action" method="post">
						<input type="text" class="form-control" name="user.loginName"  placeholder="username">
      					<div style="height:30px;"></div>
         				<input type="password" class="form-control" name="user.password"  placeholder="password">
						<div style="height:30px;"></div>
						<div style="text-align:center;">
						<input type="checkbox" class="" value="1" style="magin-left:20px;">&nbsp;&nbsp;&nbsp;(&nbsp;一周内免登陆&nbsp;)
						</div>
						<div style="height:30px;"></div>
						<input class="btn btn-primary" value="登录" type="submit" style="width:100%">
				</form>	
				</div>
			</div>
		</div>

	  
	  
   </body>
</html>