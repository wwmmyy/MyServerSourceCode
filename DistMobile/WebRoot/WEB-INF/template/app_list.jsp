<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/disttags" prefix="dist"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE>
<html>
<head>
<dist:headinclude />
<style type="text/css">
.app_listitem {
	width: 90px;
	height: 100px;
	margin: 15px;
	float: left;
	cursor: pointer;
	display: block
}

.app_listitem i {
	display: block;
	width: 100px;
	height: 80px;
	background-position: center;
	background-repeat: no-repeat;
}

.app_listitem span {
	display: block;
	text-align: center;
	height: 20px;
	line-height: 20px;
	font-size: 14px;
}

.app_create {
	background: url(../resources/images/newapplication.png) center no-repeat;
}
</style>
<script type="text/javascript">
	var selectAppID = null;
	function update(id) {
		selectAppID = id;
		var url = "applications!detail.action?app.id=" + id;
		$.post(url, null, function(data) {
			if (data.state) {
				var result = JSON.parse(data.result);
				$("#nowDevice").text(result.name);
				$("#upAppName").val(result.name);
				$("#upAppIcon").val(result.icon);
				$("#upAppDescription").val(result.description);
				$("#ManagerApplicationsModal").modal("show");
			}
		}, "json");

	}
	function updateApp(obj) {
		var name = $("#upAppName").val();
		var icon = $("#upAppIcon").val();
		var description = $("#upAppDescription").val();
		var url = "applications!update.action";
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
	$(function() {
		$("#btnDelete").click(function() {
			var flag = confirm("确定要删除该应用吗？");
			if (flag) {
				var $tr = $(this).parent().parent();
				var url = "applications!delete.action?app.id=" + selectAppID;
				$.post(url, null, function(data) {
					if (data.success) {
						window.location = "app_list!index.action";
						alert("删除成功");
					} else {
						alert("删除失败");
					}
				},"json");
			}
		})
	});
	
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
							<li class="active">所有应用程序</li>
							<li><a href="app_create!createApp.action">新应用</a></li>
						</ol>
					</div>
				</div>
			</div>
			<s:iterator value="listapplications" id="u">
				<div class="app_listitem" onclick="update('${u.id}')">
					<i style="background-image:url(../appIcon/${u.icon})"></i> <span
						class="">${u.name}</span>
				</div>
			</s:iterator>
			<a class="app_create app_listitem" href="app_create!createApp.action">

			</a>
		</div>
	</div>
	<div class="modal fade" id="ManagerApplicationsModal" tabindex="-1"
		role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-hidden="true">&times;</button>
					<h4 class="modal-title" id="myModalLabel">
						修改应用【<span id="nowDevice"></span>】
					</h4>
				</div>
				<div>
					<div class="modal-body">
						<div class="form-group">
							<label for="name">应用程序名称*</label> <input type="text"
								id="upAppName" name="app.name" class="form-control"
								placeholder="AppName">
						</div>
						<div class="form-group">
							<label for="name">应用程序图标*</label> <input type="text"
								id="upAppIcon" name="app.icon" class="form-control"
								placeholder="AppIcon">
						</div>
						<div class="form-group">
							<label for="name">应用程序描述*</label> <input type="text"
								id="upAppDescription" name="app.description"
								class="form-control" placeholder="Description">
						</div>

					</div>
					<div class="modal-footer">
						<button type="button" class="btn btn-default" data-dismiss="modal" onclick="appList();">
							应用实例列表</button>
						<button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
						<button type="button" class="btn btn-primary" data-dismiss="modal"
							onclick="updateApp();">确认修改</button>
						<button id="btnDelete" type="button" class="btn btn-danger"
							data-dismiss="modal">删除</button>
					</div>
				</div>
			</div>
		</div>
	</div>
	<dist:footerinclude />
</body>
</html>
