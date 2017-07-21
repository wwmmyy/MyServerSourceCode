<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<html>
<head>
    <title>noaction</title>
</head>

<body>
<h1>noaction</h1>
<s:form action="device!save.action">
登录名
        <s:textfield key="device.deviceNumber"/>
        用户名
	    <s:textfield key="device.name"/>
	    密码
	    <s:password key="device.SDevicetype.id" />
    
    <s:submit/>
</s:form>

<form id="device" name="device" action="/MyDemo/device!save.action" method="post">
        用户名
    <input type="text" name="device.name" value="" id="user_user_username"/>
    登录名
    <input type="text" name="device.deviceNumber" value="qwe" id="user_user_username"/>
      密码
    <input type="password" name="device.SDevicetype.id" id="user_user_devicetype"/>
    <input type="submit" id="user_0" value="Submit"/>
</form>

</body>
</html>
