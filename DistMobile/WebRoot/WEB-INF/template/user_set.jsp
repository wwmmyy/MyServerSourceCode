<%@ page language="java" import="java.util.*,java.lang.*"
	pageEncoding="utf-8" isELIgnored="false"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib uri="/disttags" prefix="dist"%>
<!DOCTYPE html>
<html>
<head>
<title>User</title>
<dist:headinclude />
<!-- DataTables CSS -->
<link
	href="resources/bower_components/datatables-plugins/integration/bootstrap/3/dataTables.bootstrap.css"
	rel="stylesheet">

<!-- DataTables JavaScript -->
<script
	src="resources/bower_components/datatables/media/js/jquery.dataTables.js"></script>
<script
	src="resources/bower_components/datatables-plugins/integration/bootstrap/3/dataTables.bootstrap.js"></script>
<script type="text/javascript">
	//allUsers

	$(document).ready(function() {
		$('#dataTables').DataTable({
			responsive : true
		});
	});
	var selectId = null;
	function updateUser() {

		var name = $('#name').val();
		var loginName = $('#loginName').val();
		var password = $('#password').val();
		var sex = $('#p_man').is(':checked');
		var email = $('#email').val();
		var phoneNumber = $('#phoneNumber').val();
		var url = "user!updateUser.action";
		var arg = {
			"user.id" : selectId,
			"user.name" : name,
			"user.loginName" : loginName,
			"user.password" : password,
			"user.sex" : sex,
			"user.email" : email,
			"user.phoneNumber" : phoneNumber
		};
		$.post(url, arg, function(data) {
			if (data.success)
				window.location = "user!userlist.action";
		}, "json");
	}
</script>

</head>
<body>


	<div id="wrapper">
		<dist:navigator selectedIndex="${selectedIndex}" />
		<div id="page-wrapper">
			<div class="col-lg-12">
				<div class="dist-breadcrumb-container"></div>
			</div>
			<div class="col-lg-4"></div>
			<div class="col-lg-4" >
				<div class="panel panel-success">
						<div class="panel-heading">修改管理员密码</div>
						
						<div style="margin:20px;">
					<form role="form">
					<input type="hidden" value=${user.id}
								name="user.id" >
						<div class="form-group">
							<label for="name">原始密码*</label> <input  type="password"
							name="user.password" 	class="form-control" id="name" placeholder="请输入原始密码">
						</div>
	
						<div class="form-group">
							<label for="name">新密码*</label> <input type="password" id=password
								name="user.handPassword" class="form-control" placeholder="PassWord">
						</div>
				
						<div class="modal-footer">
							<button type="button" onclick="window.location.reload();"
								class="btn btn-default" data-dismiss="modal">取消</button>
							<button onclick="submit" class="btn btn-primary">确认修改</button>
						</div>
	
				</form>
				</div>
			</div>






		</div>
	</div>
	</div>

	<dist:footerinclude />
</body>
</html>