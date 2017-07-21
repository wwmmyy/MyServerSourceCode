<%@ page language="java" import="java.util.*,java.lang.*"
	pageEncoding="utf-8" isELIgnored="false"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib uri="/disttags" prefix="dist"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title></title>
<dist:headinclude />
<script type="text/javascript">
	$(document).ready(
			function() {
				var selectAppID = window.location.search.split('&')[0]
						.split('=')[1];
				var selectAppName = window.location.search.split('&')[1]
						.split('=')[1];
				$("#platId").val(selectAppID);
				$("#nowApp").text(unescape(selectAppName));
				$("#goBack").attr("href",
						"applications!list.action?app.id=" + selectAppID);
			});
	function platformChange() {
		var t = $("#platType").val();
		if (t == "Web")
			$("#webUrl").show();
		else
			$("#webUrl").hide();
	}
</script>
</head>

<body>
	<div id="wrapper">
		<dist:navigator selectedIndex="${selectedIndex}" />
		<div id="page-wrapper">
			<div class="row">
				<div class="col-lg-12">
					<div class="dist-breadcrumb-container">
						<ol class="breadcrumb">
							<li id="nowApp" class="active">${ap.name}</li>
							<li><a id="goBack" href="applications!list.action">返回</a></li>
						</ol>
					</div>
				</div>
			</div>
			<div class="col-lg-3"></div>
			<div class="col-lg-6" style="padding-top:30px;">
				<form action=" applicationPlatform!save.action" method="post"
					enctype="multipart/form-data">
					<div class="form-group" style="display:none;">
						<label for="name">名称：</label> <input id="platId" type="text"
							name="app.SApplications.id" class="form-control" value="${ap.id}">
					</div>
					<div class="form-group">
						<label for="name">应用包名(唯一标识号)：</label> <input type="text"
							name="app.applicationIdentity" class="form-control" />
					</div>
					<div class="form-group">
						<label for="name">所属类别： </label> <select name="app.platform"
							id="platType" class="form-control" onchange="platformChange();">
							<option value="IOS" selected="selected">IOS</option>
							<option value="Android">android</option>
							<option value="Web">Web</option>
						</select>
					</div>
					<div id="webUrl" class="form-group" style="display:none;">
						<label for="name">webURL：</label> <input type="text"
							name="app.url" class="form-control" />
					</div>
					<div class="form-group">
						<label for="name">版本号：</label> <input type="text"
							name="app.version" class="form-control" />
					</div>
					<div class="form-group">
						<label for="name">上传APP：</label> <input name="file" type="file">
					</div>
					<div class="form-group">
						<button type="submit" class="btn btn-primary">提交</button>
					</div>
				</form>
			</div>
		</div>
	</div>
	<dist:footerinclude />
</body>
</html>
