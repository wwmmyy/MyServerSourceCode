<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/disttags" prefix="dist"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<dist:headinclude />
<script type="text/javascript">
	function addApp() {
		var name = $("#appName").val();
		var icon = $("#upAppIcon").val();
		var description = $("#upAppDescription").val();
		var url = "applications!save.action";
		var args = {
			"app.id" : selectAppID,
			"app.name" : name,
			"app.icon" : icon,
			"app.description" : description,
		};
		$.post(url, args, function(data) {
			if (data.success) {
				alert("修改成功");
				window.location.reload();
			} else {
				alert("修改失败");
			}

		}, "json");
	}
</script>
</head>

<body>
	<div id="wrapper">
		<dist:navigator />
		<div id="page-wrapper">
			<div class="row">
				<div class="col-lg-12">
					<div class="dist-breadcrumb-container">
						<ol class="breadcrumb">
							<li class="active"><a href="app_list!index.action">所有应用程序</a></li>
							<li>新应用</li>
						</ol>
					</div>
				</div>
			</div>
			<div class="row">
				<div class="col-lg-3"></div>
				<div class="col-lg-6">
					<div style="margin:0px auto">
						<div style="text-align:center; margin:10px 0px">
							<img alt="" src="../resources/images/appicon.png"><br /> <span>建议上传图片尺寸为640x640，大小不超过2M。</span>
						</div>
						<div class="form-group">
							<label for="name">图标名称*</label> <input type="text" id="upAppIcon"
								class="form-control" placeholder="AppIconName">
						</div>
						<div class="form-group">
							<label>应用程序名称</label> <input id="appName" class="form-control"
								placeholder="AppName">
						</div>
					
						<div class="form-group">
							<label>应用程序描述*</label>
							<textarea id="upAppDescription" class="form-control" rows="4"
								placeholder="Description"></textarea>
						</div>
						<div class="form-group">
							<button onclick="addApp();" type="button" class="btn btn-primary">提交</button>
						</div>
					</div>
				</div>
				<div class="col-lg-4"></div>
			</div>
		</div>

	</div>
	<dist:footerinclude />
</body>
</html>
