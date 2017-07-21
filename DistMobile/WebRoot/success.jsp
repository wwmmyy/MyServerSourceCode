<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>success</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">

<link href="template/css/bootstrap.min.css" rel="stylesheet" />
<!--<link href="template/css/bootstrap-theme.min.css" rel="stylesheet" type="text/css" />-->
<link type="text/css" rel="stylesheet"
	href="template/css/font-awesome.min.css">
<link type="text/css" rel="stylesheet" href="template/css/sys.css" />
<script src="template/js/jquery-2.1.1.js"></script>
<script src="template/js/bootstrap.min.js"></script>
<style type="text/css">
</style>

<script type="text/javascript">
var login_username_info = '<%=request.getSession().getAttribute("tip") == null ? "" : request.getSession().getAttribute("tip")%>';
if(login_message_info != null && login_message_info != ''){
	alert(login_username_info);
}else{
	
	alert("没有登录！！！！！");
}

</script>
</head>
<body  onLoad="init()">
<script type="text/javascript"> 
<!--
function init() { 
if(<%=request.getSession().getAttribute("userid")%>==0||<%=request.getSession().getAttribute("userid")%>==null){
	// alert("没有登录！"+ <%=request.getSession().getAttribute("loginName")%>);
   window.close();
   
 
}else{
	//  alert("登录！"+<%=request.getSession().getAttribute("password")%>);
}

}
-->
</script>
  </head>
  
  <body>
    <h1>success !</h1> <br>
  </body>
</html>
