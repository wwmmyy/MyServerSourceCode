<%@ page language="java" import="java.util.*,java.lang.*"
	pageEncoding="utf-8" isELIgnored="false"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib uri="/disttags" prefix="dist"%>
<!DOCTYPE html>
<html>
<head>
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
	function update(obj) {

		var tds = $(obj).parent().parent().find('td');
		//姓名
		$('#name').val(tds.eq(1).text());
		//标题中的用户
		$('#nowUser').text(tds.eq(1).text());

		//性别
		if (tds.eq(2).text().trim() == '男') {
			$('#p_man').attr("checked", "checked");
		} else {
			$('#p_woman').attr("checked", "checked");
		}
		//用户名
		$('#loginName').val(tds.eq(3).text());
		//phoneNumber
		$('#phoneNumber').val(tds.eq(4).text());
		//email
		$('#email').val(tds.eq(5).text());
		//password
		$('#password').val(tds.eq(6).text());
		selectId = tds.eq(0).text();
		//alert(tds.eq(0).text());
		$("#deleteBtn").attr("href",
				"user!remove.action?userid=" + tds.eq(0).text());
		$('#ManagerUserModal').modal('show');
	}
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
	function deleteData(obj){
		var lastname = $(obj).next(":hidden").val();
		var id = $(obj).next(":hidden").next(":hidden").val();
		var flag = confirm("确定要删除该用户【" + lastname + "】吗？");
		if (flag) {
			var url = "user!remove.action?userid=" + id;
			$.post(url, null, function(data) {
				if (data.success) {
					window.location = "user!userlist.action";
				} else {
					alert("删除失败");
				}

			}, "json");

		}
	}
</script>

</head>
<body>

	<div>
		<!-- model field begin -->
		<div class="modal fade" id="myModal" tabindex="-1" role="dialog"
			aria-labelledby="myModalLabel" aria-hidden="true">
			<div class="modal-dialog">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal"
							aria-hidden="true">&times;</button>
						<h4 class="modal-title" id="myModalLabel">
							<li class="fa fa-fw fa-plus"></li>添加用户
						</h4>
					</div>
					<form role="form" action="user!save.action" method="post">
						<div class="modal-body">

							<!-- 添加内容时候需要输入的信息 -->
							<div class="form-group">
								<label for="name">姓名*</label> <input type="text"
									name="user.name" class="form-control" placeholder="UserName">
							</div>
							<div class="form-group">
								<label for="name">用户名*</label> <input type="text"
									name="user.loginName" class="form-control"
									placeholder="UserName">
							</div>
							<div class="form-group">
								<label for="name">密码*</label> <input type="password"
									name="user.password" class="form-control"
									placeholder="PassWord">
							</div>
							<div class="form-group">
								<label for="name">选择性别</label>
								<div class="radio">
									<label> <input type="radio" name="user.sex"
										id="optionsRadios1" value="1" checked>男
									</label>
								
									<label> <input type="radio" name="user.sex"
										id="optionsRadios2" value="0"> 女
									</label>
								</div>
							</div>
							<div class="form-group">
								<label for="name">组织机构</label>
								<s:select list="organizationtype" listKey="id" listValue="name"
									name="user.SOrganization.id" label="organization"></s:select>
							</div>
							<div class="form-group">
								<label for="name">Email</label> <input type="text"
									name="user.email" class="form-control"
									placeholder="abc@dist.com.cn">
							</div>
							<div class="form-group">
								<label for="name">Phone</label> <input type="text"
									name="user.phoneNumber" class="form-control"
									placeholder="Phone number">
							</div>
						</div>
						<div class="modal-footer">

							<button type="button" class="btn btn-default"
								data-dismiss="modal">取消</button>
							<button type="submit" class="btn btn-primary">添加</button>
						</div>
					</form>
				</div>
				<!-- /.modal-content -->
			</div>
			<!-- /.modal -->
		</div>
		<!-- model field end -->
	</div>
	<div id="wrapper">
		<dist:navigator selectedIndex="${selectedIndex}" />
		<div id="page-wrapper">
			<div class="col-lg-12">
				<div class="dist-breadcrumb-container">
					<ol class="breadcrumb">
						<li class="active"><a href="user!userlist.action">用户列表</a></li>
						<li style="display:none;" ><a href="user!userlist.action?user.status=false">不可用</a></li>
						<li style="display:none;"><a href="user!userlist.action?user.status=true">可用</a></li>
						<li data-toggle="modal" data-target="#myModal"><a href="#">添加用户</a></li>
					</ol>
				</div>
			</div>
			<div class="col-lg-12">
				<table
					class="table table-striped table-bordered table-hover dataTable no-footer"
					id="dataTables" role="grid"
					aria-describedby="dataTables-example_info">
					<thead>
						<tr role="row">
							<th style='display:none'>id</th>
							<th aria-controls="dataTables">姓名</th>
							<th aria-controls="dataTables" style="display:none;">性别</th>
							<th aria-controls="dataTables">用户名</th>
							<th aria-controls="dataTables" style="display:none;">电话</th>
							<th aria-controls="dataTables">Email</th>
							<th style="display:none;">密码</th>
							<th aria-controls="dataTables">状态</th>
							<th aria-controls="dataTables" style="display:none;">组织机构</th>
							<th aria-controls="dataTables" style="display:none;">所属角色</th>
							<th aria-controls="dataTables" style="display:none;">创建时间</th>
							<th width="40px">操作</th>
						</tr>
					</thead>
					<tbody>
						<s:iterator value="listUser" id="u">
							<tr role="row">
								<td style='display:none'>${u.id }</td>
								<td>${u.name}</td>
								<td style="display:none;"><s:if test="#u.sex==null">男</s:if><s:elseif test="#u.sex==true">男</s:elseif>
								<s:else>女</s:else></td>
								<td>${u.loginName }</td>
								<td style="display:none;">${u.phoneNumber}</td>
								<td>${u.email }</td>
								<td style="display:none;" >${u.password }</td>
								<td><s:if test="#u.status==true"><i class="fa fa-check fa-fw green"></s:if>
								<s:elseif test="#u.status==false"><i class="fa fa-remove fa-fw Red"></s:elseif>
								<s:else></s:else></td>
								<td style="display:none;">${u.SOrganization.name }</td>
								<td style="display:none;"><s:iterator value="#u.SUserroleses" id="r">
										<label>${r.SRole.name}</label>
									</s:iterator></td>
								<td style="display:none;"><s:date name="createTime" format="yyyy-MM-dd" /></td>
								<td><a href="javascript:void(0)" title="编辑"
									onclick="update(this);"><i class="fa fa-pencil"></i></a> <a
									role="button" data-toggle="modal" onclick="deleteData(this)"
									style="margin-left:10px;cursor: pointer;color:Red;" title="删除">
										<i class="fa fa-trash-o"></i>
								</a> <input type="hidden" value="${u.name}" /> <input type="hidden"
									value="${u.id}" /></td>

							</tr>
						</s:iterator>
					</tbody>
				</table>
			</div>
		</div>
	</div>
	<!-- 管理模态框（Modal） -->
	<div class="modal fade" id="ManagerUserModal" tabindex="-1"
		role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-hidden="true">&times;</button>
					<h4 class="modal-title" id="myModalLabel">
						当前用户【<span id="nowUser"></span>】
					</h4>
				</div>
				<div>
					<div class="modal-body">
						<div class="form-group">
							<label for="name">姓名*</label> <input type="text" id="name"
								name="user.name" class="form-control" placeholder="UserName">
						</div>
						<div class="form-group">
							<label for="name">用户名*</label> <input type="text" id="loginName"
								name="user.loginName" class="form-control"
								placeholder="UserName">
						</div>
						<div class="form-group">
							<label for="name">密码*</label> <input type="password" id=password
								name="user.password" class="form-control" placeholder="PassWord">
						</div>

						<div class="form-group">
							<label for="name">选择性别</label>

							<div class="radio">
						
								<label> <input id="p_man" type="radio" name="user.sex"
									value="#user.sex" >									
								男
								</label>
									<label> <input id="p_woman" type="radio" name="user.sex"
									value="#user.sex"> 女
								</label>
								
							</div>
						</div>
						<div class="form-group">
							<label for="name">Email</label> <input type="text" id="email"
								name="user.email" class="form-control"
								placeholder="abc@dist.com.cn">
						</div>
						<div class="form-group">
							<label for="name">Phone</label> <input type="text"
								id="phoneNumber" name="user.phoneNumber" class="form-control"
								placeholder="Phone number">
						</div>
					</div>
					<div class="modal-footer">
						<button type="button" onclick="window.location.reload();"
							class="btn btn-default" data-dismiss="modal">取消</button>
						<button onclick="updateUser()" class="btn btn-primary">确认修改</button>
					</div>
				</div>
			</div>
			<!-- /.modal-content -->
		</div>
		<!-- /.modal -->
	</div>
	<dist:footerinclude />
</body>
</html>