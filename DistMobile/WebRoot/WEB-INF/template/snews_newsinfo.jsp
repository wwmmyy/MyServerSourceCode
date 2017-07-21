<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<html>
  <head>
  <meta name="viewport" content="initial-scale=1.0, minimum-scale=0.1, maximum-scale=2.0, user-scalable=yes\">
    <base href="<%=basePath%>">
    
    <title>My JSP 'newsinfo.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

  </head>
  
  <body>
    <h2>${news.title}</h2>
    <div>
    	<span style="color:gray; font-size:12px;">2015/05/01 10:10:11</span>&nbsp;&nbsp;&nbsp;&nbsp;${news.browse}阅读
    </div>
    <p>
    	${news.content}
    </p>
  </body>
</html>
