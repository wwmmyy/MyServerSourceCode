<%@ page language="java" import="java.util.*,java.lang.*" pageEncoding="utf-8"  isELIgnored="false"%>
<%@ taglib prefix="s" uri="/struts-tags" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>user list</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	
  </head>
  
  <body>
    <h1>admin user list !</h1> <br>
	<a href="#" onclick="window.history.back(); return false;">返回</a>
	<%--userList --%>
	<s:iterator value="listdevice" id="u" >
		<li>
		<a href="#" >用户名 ：   ${u.name}</a>
		<a href="#" >密码 ：   ${u.deviceNumber}</a>
		</li>
		
		<!--  
		<li>
		<a href="#" ><s:property value="#u.username" /></a>
		</li>
		 -->					
	</s:iterator>

<br>

<s:select list="listdevice"
 listKey="id" listValue="name" name="u.id"
 label="device"
></s:select>
	


  </body>
  
  
</html>
