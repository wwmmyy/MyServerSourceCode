<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>error</title>
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
    <h1>成功登录了</h1> <br>
    <br>
  设备名称  ：${device.id}  
    设备名称  ：${device.name}     设备编码 ：${device.deviceNumber}
          设备状态  ：${device.status}    设备位置  ：${device.lastLocation} 
           设备标记 ：${device.remark}  注册时间  ：${device.registerTime}
             注册说明  ：${device.registerMessage}   添加时间：${device.joinTime}
    <br>
   设备类型  ：  ${device.SDevicetype.name}
    
    
    <s:iterator value="device.SDeviceactivities" id="u" >
		<li>
		<a href="#"  > 活动id：  ${u.id}</a>
		<a href="#"  > 活动名称：  ${u.activity}</a>
		<a href="#"  > 活动时间：  ${u.eventTime}</a>
		</li>
	</s:iterator>
    
    <br>
    	设备应用程序：
    
	    	<s:iterator value="listdeviceapps" id="u" >
		<li>
		应用程序id  <a href="#"  >   ${u.id}</a>
		<a href="#" >    ${u.deviceId}</a>
		应用程序名称：<a href="#" >    ${u.SApplications.name}</a>
		</li>
	</s:iterator>
	
	
	
	
	
	
	
	
	
	
	
	
	
	
  </body>
</html>
