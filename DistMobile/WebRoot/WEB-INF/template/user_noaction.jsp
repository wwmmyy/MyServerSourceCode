<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<html>
<head>
    <title>noaction</title>
</head>

<body>
<h1>noaction</h1>
<s:form action="user!reg.action">
登录名
        <s:textfield key="user.loginname"/>
        用户名
	    <s:textfield key="user.name"/>
	    密码
	    <s:password key="user.password" />
    
    <s:submit/>
</s:form>

<form id="user" name="user" action="/MyDemo/user!save.action" method="post">
        用户名
    <input type="text" name="user.name" value="" id="user_user_username"/>
      登录名
    <input type="text" name="user.loginName" value="" id="user_user_username"/>
      密码
    <input type="password" name="user.password" id="user_user_password"/>
    <input type="submit" id="user_0" value="Submit"/>

</form>
</body>
</html>
