<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
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
				<p align="center" class="STYLE1">APP注册上传页面</p>
				<form action="mobile/regAppPlatform.action" name="form1"
					method="POST" enctype="multipart/form-data">


					<table width="640" border="0" align="center" cellspacing="0">

						<input type="hidden" name=" "
							value=<%=session.getAttribute("userid")%> />

						<tr>
							<td height="341" scope="col">

								<div align="center">
									<table width="845" height="308" align="center">
										<tr valign="baseline">
											<td height="25" align="right" valign="top" nowrap="nowrap">
												<span class="STYLE3">名称：</span>
											</td>
											<td height="25" colspan="3" valign="middle"><label></label>
												<input type="text" name="app.SApplications.id"
												value="4028b8814cdaa04c014cdadbc998002b" size="40" /></td>
										</tr>

										<tr valign="baseline">
											<td align="right" valign="middle" nowrap="nowrap">
												应用包名(唯一标识号)：</td>
								
											<td colspan="3" valign="middle"><input type="text"
												name="app.applicationIdentity" value="" size="30" /> 
														</tr>
										<tr valign="baseline">web
												URL： <input type="text" name="app.url" value="" size="45" />

											</td>
										</tr>

										<tr valign="baseline">
											<td align="right" valign="middle" nowrap="nowrap">
												所属类别： <span class="STYLE1"></span>
											</td>
											<td width="167" valign="middle"><select
												name="app.platform">
													<option value="IOS" selected="selected">IOS</option>
													<option value="Android">android</option>
													<option value="Web">Web</option>
											</select></td>
											<td width="117" align="right" valign="middle" nowrap="nowrap">
												版本号：</td>
											<td width="465" align="left"><input type="text"
												name="app.version" size="14" /></td>
										</tr>
										<tr valign="baseline">
											<td align="right" valign="middle" nowrap="nowrap">
												上传APP：</td>
											<td colspan="3" valign="middle"><span class="STYLE9">
													<input name="file" type="file" size="50" />
											</span></td>

										</tr>
										<tr valign="baseline">
											<td height="28" colspan="4" align="right" valign="middle"
												nowrap="nowrap">
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
