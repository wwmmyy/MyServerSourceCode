
<%@ page language="java" import="java.util.*,java.lang.*"
	pageEncoding="utf-8" isELIgnored="false"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib uri="/disttags" prefix="dist"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
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
	$(document).ready(function() {
		$('#dataTables').DataTable({
			responsive : true
		});
	});
	
	function update(obj, status) {
		
		var tds = $(obj).parent().parent().find('td');
		//姓名
		$('#upDeviceName').val(tds.eq(1).text());
		//标题中的设备
		$('#nowDevice').text(tds.eq(0).text());
		//用户名
		$('#updeviceNumber').val(tds.eq(2).text());
		//phoneNumber
		$('#updevicenum').val(tds.eq(3).text());
		//email
		$('#updeviceremark').val(tds.eq(5).text());
		//upregisterMessage
		$('#upregisterMessage').val(tds.eq(6).text());
		
		$("#btnAccept").hide();
		$("#btnUnable").hide();
		$("#btnAble").hide();
		$("#btnLost").hide();
		switch (status) {
		case "0":
			$("#btnAccept").show();
			break;
		case "1":
			$("#btnLost").show();
			$("#btnUnable").show();
			break;
		case "2":
			$("#btnUnable").show();
			$("#btnAble").show();
			break;
		case "3":
			$("#btnAble").show();
			break;
		}
		$('#ManagerUserModal').modal('show');
	}

	function changeStatus(type) {
	    var id = $("#nowDevice").text();
		var url = "device!changeDeviceStatus.action?device.status=" + type+"&device.id="+id;
		$.post(url, null,function(data){
			if(data.success)
				window.location="device!index.action?type=${pageShow.type}&pageShow.hardwaretype=${pageShow.hardwaretype}&pageShow.systemtype=${pageShow.systemtype}";
		},"json");
	}
	function deleteData(obj){
		var lastname = $(obj).next(":hidden").val();
		var id = $(obj).next(":hidden").next(":hidden").val();
		var flag = confirm("确定要删除该设备【" + lastname + "】吗？");
		if (flag) {
			var url = "device!deleteDevice.action?deviceid=" + id;
			var args = {
				"time" : new Date()
			};
			$.post(url, args, function(data) {
				if (data.success) {
					alert("删除成功");
					window.location="device!index.action?type=${pageShow.type}&pageShow.hardwaretype=${pageShow.hardwaretype}&pageShow.systemtype=${pageShow.systemtype}";
				} else {
					alert("删除失败");
				}

			}, 'json');
		}
	}
	function changenav(type, obj) {
		if (type == "Phone" || type == "PAD")
			window.location = "device!index.action?type=${pageShow.type}&pageShow.hardwaretype="+ type;
		else
			window.location = "device!index.action?type=${pageShow.type}&pageShow.systemtype="+ type;
		
	}
	function updateDevice(){
		var id = $("#nowDevice").text();
		var name=$('#upDeviceName').val();
		var deviceNumber=$('#updeviceNumber').val();
		var deviceremark=$('#updeviceremark').val();
		var registerMessage=$('#upregisterMessage').val();
		var url = "device!updateDevice.action";
		var args={"device.id":id,"device.name":name,"device.deviceNumber":deviceNumber,"device.remark":deviceremark,"device.registerMessage":registerMessage};
		$.post(url, args,function(data){
			window.location="device!index.action?type=${pageShow.type}&pageShow.hardwaretype=${pageShow.hardwaretype}&pageShow.systemtype=${pageShow.systemtype}";
		});
	}
</script>

</head>
<body>

	<!-- model field begin -->
	<div class="modal fade" id="myModal" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-hidden="true">&times;</button>
					<h4 class="modal-title" id="myModalLabel">
						<li class="fa fa-fw fa-plus"></li>添加设备
					</h4>
				</div>
				<form role="form" action="device!save.action" method="post">
					<div class="modal-body">

						<!-- 添加内容时候需要输入的信息 -->
						<div class="form-group">
							<label for="name">设备名称*</label> <input type="text"
								name="device.name" class="form-control" placeholder="DeviceName">
						</div>
						<div class="form-group">
							<label for="name">设备编号*</label> <input type="text"
								name="device.deviceNumber" class="form-control"
								placeholder="DeviceNumber">
						</div>
						<div class="form-group">
							<label for="name">备注说明*</label> <input type="text"
								name="device.registerMessage" class="form-control"
								placeholder="Message">
						</div>

						<div class="form-group">
							<label for="name">设备类型</label>
							<s:select list="listdevicetype" listKey="id" listValue="name"
								name="device.SDevicetype.id" label="devicetype"></s:select>
						</div>


						<div class="form-group">
							<label for="name">设备简介</label> <input type="textfield"
								name="device.remark" class="form-control" placeholder="设备简介">
						</div>


					</div>
					<div class="modal-footer">
						<button type="button" class="btn btn-default" data-dismiss="modal">取消
						</button>
						<button type="submit" class="btn btn-primary">添加</button>
					</div>
				</form>
			</div>
			<!-- /.modal-content -->
		</div>
		<!-- /.modal -->
	</div>
	<!-- model field end -->

	<div id="wrapper">
		<dist:navigator selectedIndex="${selectedIndex}"/>
		<div id="page-wrapper">

			<div class="row">
				<div class="col-lg-12">
					<div class="dist-breadcrumb-container">
						<ol class="breadcrumb">
							<li class="${allClass}"><a  href="#" onclick="changenav('',this)"><s:if
									test="pageShow.type==1">可用设备</s:if> <s:elseif
									test="pageShow.type==2">已挂失设备</s:elseif> <s:elseif
									test="pageShow.type==3">已禁用设备</s:elseif> <s:elseif
									test="pageShow.type==0">新设备</s:elseif> <s:else>所有设备</s:else> </a></li>
									
									
							<li class="${iosClass}"><a href="#" onclick="changenav('IOS',this)">IOS</a></li>							
							<li class="${androidClass}"><a href="#" onclick="changenav('Android',this)">Android</a></li>
							<li class="${phoneClass}"><a href="#" onclick="changenav('Phone',this)">手机</a></li>
							<li class="${padClass}"><a href="#" onclick="changenav('PAD',this)">平板</a></li>
							<li data-toggle="modal" data-target="#myModal"
								style="cursor:pointer;"><a href="#">添加设备</a></li>
						</ol>
					</div>
				</div>
			</div>

			<div class="row" style="padding:0px; margin:0px;padding-top: 10px;">
				<div class="col-lg-8">
					<table id="dataTables" role="grid"
						class="table table-striped table-bordered table-hover dataTable no-footer"
						aria-describedby="dataTables-example_info">
						<thead>
							<tr role="row">
								<th style="display:none;">id</th>
								<th aria-controls="dataTables">名称</th>
								<th style="display:none;">编号</th>
								<th aria-controls="dataTables">类型</th>
								<th aria-controls="dataTables">操作系统</th>
								<th style="display:none;">备注</th>
								<th style="display:none;">简介</th>
								<th aria-controls="dataTables">状态</th>
								<th>管理</th>
							</tr>
						</thead>
						<tbody>
							<s:iterator value="listdevice" id="u">
								<tr href="" role="row" class="gradeA">
									<td style='display:none'>${u.id }</td>
									<td>${u.name}</td>
									<td style="display:none;">${u.deviceNumber}</td>
									<td>${u.SDevicetype.name}</td>
									<td>${u.SDevicetype.systemtype}</td>
									<td style="display:none;">${u.registerMessage}</td>
									<td style="display:none;">${u.remark}</td>
									<td><s:if test="#u.status==1">正常</s:if> <s:elseif
											test="#u.status==2">挂失</s:elseif> <s:elseif
											test="#u.status==3">禁用</s:elseif> <s:else>待审核</s:else></td>
									<td><a href="javascript:void(0)" title="编辑"
										onclick="update(this,'${u.status}');"><i
											class="fa fa-pencil"></i></a> <a role="button"
										data-toggle="modal" onclick="deleteData(this)"
										style="margin-left:10px;cursor: pointer;color:Red;" title="删除">
											<i class="fa fa-trash-o"></i>
									</a> <input type="hidden" value="${u.name}" /> <input
										type="hidden" value="${u.id}" /></td>
								</tr>
							</s:iterator>
						</tbody>
					</table>

					<!-- 管理模态框（Modal） -->
					<div class="modal fade" id="ManagerUserModal" tabindex="-1"
						role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
						<div class="modal-dialog">
							<div class="modal-content">
								<div class="modal-header">
									<button type="button" class="close" data-dismiss="modal"
										aria-hidden="true">&times;</button>
									<h4 class="modal-title" id="myModalLabel">
										当前设备[<span id="nowDevice"></span>]
									</h4>
								</div>
								<div>
									<div class="modal-body">

										<div class="form-group">
											<label for="name">设备名称*</label> <input type="text"
												id="upDeviceName" name="device.name" class="form-control"
												placeholder="DeviceName">
										</div>
										<div class="form-group">
											<label for="name">设备编号*</label> <input type="text"
												id="updeviceNumber" name="device.deviceNumber"
												class="form-control" placeholder="deviceNumber">
										</div>
										<div class="form-group">
											<label for="name">备注说明*</label> <input type="text"
												id="upregisterMessage" name="device.registerMessage"
												class="form-control" placeholder="upregisterMessage">
										</div>

										<div class="form-group">
											<label for="name">设备类型*</label> <input disabled="disabled"
												type="text" id="updevicenum" name="device.SDevicetype.id"
												class="form-control" placeholder="SDevicetype">
										</div>
										<div class="form-group">
											<label for="name">设备简介*</label> <input type="text"
												id="updeviceremark" name="device.remark"
												class="form-control" placeholder="remark">
										</div>

									</div>
									<div class="modal-footer">
										<button type="button" class="btn btn-default"
											data-dismiss="modal">取消</button>
										<button onclick="updateDevice()" class="btn btn-primary">确认修改</button>
										<button id="btnAccept" onclick="changeStatus('1')"
											class="btn btn-primary">受理</button>
										<button id="btnAble" onclick="changeStatus('1')"
											class="btn btn-primary">启用</button>
										<button id="btnUnable" onclick="changeStatus('3')"
											class="btn btn-danger">禁用</button>
										<button id="btnLost" onclick="changeStatus('2')"
											class="btn btn-danger">挂失</button>
									</div>
								</div>
							</div>
							<!-- /.modal-content -->
						</div>
						<!-- /.modal -->
					</div>

				</div>
				<div class="col-lg-4">
					<s:iterator value="listdevicenew" id="ld">
						<div class="alert alert-warning alert-dismissible" role="alert">
							<button type="button" class="close" data-dismiss="alert">
								<span aria-hidden="true">&times;</span><span class="sr-only">Close</span>
							</button>
							<i class="fa fa-clock-o"></i>
							<s:date name="registerTime" format="yyyy-MM-dd" />
							有新设备申请，请及时受理
						</div>
					</s:iterator>
				</div>
			</div>
		</div>
		<dist:footerinclude />
</body>
</html>