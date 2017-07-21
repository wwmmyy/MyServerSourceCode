<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<html>
<head>
    <title>Sign On</title>
	<META NAME="Author" CONTENT="">
	<META NAME="Keywords" CONTENT="">
	<META NAME="Description" CONTENT="">
	<script language="javascript" type="text/javascript" src="template/common/js/jquery-1.5.2.min.js"></script>

</head>

<body>
<h1>admin user page</h1>
<h2>hi, ${user.name }</h2>
<div align="center" >
	<s:form action="user!reg.action">
	    <s:textfield key="user.loginName"/>
	    <s:textfield key="user.name"/>
	    <s:password key="user.password" />
	    <s:submit value="add"/>
	</s:form>
	
	<button onclick="javascript:window.location.href='user!list.action'">all uesr</button>
	<button onclick="javascript:window.location.href='../freeMarker.action'">freeMarker demo</button>
</div>	
</html>
