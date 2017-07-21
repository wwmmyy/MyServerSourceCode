<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<%@ taglib uri="/struts-tags" prefix="s"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<base href="<%=basePath%>">

		<title>本页面为添加注册上传APP应用</title>

		<style type="text/css">
<!--
.STYLE1 {
	font-size: 24px;
	color: #2A00AA;
	font-weight: bold;
}
.STYLE2 {
	color: #2A9FAA
}

.STYLE3 {
	color: 0
}
-->
</style>
	</head>

	<body onLoad="init()" class="main_body">
		<table width="100%" border="0">
			<tr>
				<td height="28" valign="middle">
										   	<p align="center" class="STYLE1">
			   APP注册上传页面
		</p>
					<form action="mobile/regApp.action" name="form1" method="POST"
						enctype="multipart/form-data">
						  
	
	                    <table width="640" border="0" align="center" cellspacing="0"
							>
							
							<input type="hidden"  name=" " value=<%=session.getAttribute("userid")%>  />
							
							<tr>
								<td height="341" scope="col">

									<div align="center">
										<table width="845" height="308" align="center">
																				<tr valign="baseline">
												<td height="25" align="right" valign="top" nowrap="nowrap"
													>
													<span class="STYLE3">名称：</span>
												</td>
												<td height="25" colspan="3" valign="middle">
													<label></label>
													<input type="text" name="app.name" value="" size="40" />
												</td>
											</tr>
											
											<tr valign="baseline">
												<td height="25" align="right" valign="top" nowrap="nowrap"
													>
													<span class="STYLE3"> 简介：</span>
												</td>
												<td height="25" colspan="3" valign="middle">
													<textarea name="app.description" cols="60" rows="7">请对APP做概述（字数不大于500字）</textarea>
												</td>
											</tr>
									
							
										
											<tr valign="baseline">
												<td align="right" valign="middle" nowrap="nowrap"
													>
													所属类别：
													<span class="STYLE1"></span>
												</td>
												<td width="167" valign="middle">
													<select name="app.SApplicationcategory.id">
														<option value="3" selected="selected">
															IOS平板
														</option>
														<option value="4">
															IOS手机
														</option>
														<option value="1">
															android平板
														</option>
														<option value="2">
															android手机
														</option>
														<option value="5">
															IOS设备
														</option>
														<option value="7">
															android设备
														</option>
														<option value="7">
															全部
														</option>
													</select>
											  </td>
											</tr>

										
											<tr valign="baseline">
												<td align="right" valign="middle" nowrap="nowrap"
													>
													APP ICON：
												</td>
												<td colspan="3" valign="middle">
													<label>
														<input name="picfile" type="file" size="50">
													</label>
													<br>
													<a><font color="#0066FF">(允许格式：.bmp,.png,.gif,.jpeg,.jpg,其他上传不显示)
													</font>
													</a>
												</td>
											</tr>
																						
											<tr valign="baseline">
												<td height="28" colspan="4" align="right" valign="middle"
													nowrap="nowrap" >
													<div align="center">
														<input type="submit" name="Submit" value="确认上传" />
													</div>
												</td>
											</tr>
										</table>
									</div>
							  </td>
							</tr>
					  </table>

					</form>
				</td>
			</tr>
		</table>
	</body>
</html>
