<%@ page language="java" import="java.util.*,java.lang.*"
	pageEncoding="utf-8" isELIgnored="false"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib uri="/disttags" prefix="dist"%>

<!DOCTYPE html>
<html>
<head>
<title>组织机构</title>
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
		$("#dataTables").DataTable({
			responsive : true
		});
	});
	
	var selectId=null;
	
	function update(obj) {
		
		var tds = $(obj).parent().parent().find('td');
		//组织机构名称
		$('#name').val(tds.eq(1).text());
		//上级机构
		$("#upper").val(tds.eq(3).text())
		$('#nowOrganization').text(tds.eq(1).text());
		selectId=tds.eq(0).text();
		//alert(tds.eq(0).text());
		//$("#deleteBtn").attr("href","user!remove.action?deviceid="+tds.eq(0).text());
		$('#ManagerModal').modal('show');
	}
	
	function deleteData(obj){
		var lastname = $(obj).next(":hidden").val();
		var id = $(obj).next(":hidden").next(":hidden").val();
		var flag = confirm("确定要删除该组织机构【" + lastname + "】吗？");
		if (flag) {

			var $tr = $(this).parent().parent();
			var url = "organization!delete.action?entity.id=" + id;
			$.post(url, null, function(data) {
				if (data.success) {
					$tr.remove();
					alert("删除成功");
				} else {

					alert("删除失败");
				}

			},"json");

		}

	}
	function updateOrganization(){
		
		var name=$('#name').val();
		var upper=$('#upper').val();
		var url = "organization!updateOrganization.action";
		var arg={"entity.id":selectId,"entity.name":name,"entity.sorganization.id":upper};
		$.post(url, arg,function(data){
			if(data.success)
				window.location="organization!list.action";
		},"json");
	}
</script>
</head>
<body>
	<div id="wrapper">
		<dist:navigator selectedIndex="${selectedIndex}" />
		<div id="page-wrapper">
			<div class="col-lg-12">
				<div class="col-lg-12">
					<div class="dist-breadcrumb-container">
						<ol class="breadcrumb">
							<li class="active">组织机构列表</li>
							<li data-toggle="modal" data-target="#addModal"
								style="cursor: pointer;"><a href="#">添加组织机构</a></li>
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
								<th aria-controls="dataTables">id</th>
								<th aria-controls="dataTables">机构名称</th>
								<th aria-controls="dataTables">上级机构</th>
								<th aria-controls="dataTables" style="display:none;">上级机构</th>
								<th>操作</th>
							</tr>
						</thead>
						<tbody>
							<s:iterator value="listEntity" id="u">
								<tr role="row">
									<td>${u.id }</td>
									<td>${u.name }</td>
									<td>${u.sorganization.name}</td>
									<td style="display:none;">${u.sorganization.id}</td>
									<td><a href="javascript:void(0)" title="编辑"
										onclick="update(this);"><i class="fa fa-pencil"></i></a> <a
										role="button" data-toggle="modal" onclick="deleteData(this)"
										style="margin-left:10px;cursor: pointer;color:Red;" title="删除">
											<i class="fa fa-trash-o"></i>
									</a> <input type="hidden" value="${u.name}" /> <input
										type="hidden" value="${u.id}" /></td>
								</tr>
							</s:iterator>
						</tbody>
					</table>
				</div>
			</div>
		</div>
	</div>

	<!-- model field begin -->
	<div class="modal fade" id="addModal" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-hidden="true">&times;</button>
					<h4 class="modal-title" id="myModalLabel">
						<li class="fa fa-fw fa-plus"></li>添加组织机构
					</h4>
				</div>
				<form role="form" action="organization!save.action" method="post">
					<div class="modal-body">

						<!-- 添加内容时候需要输入的信息 -->
						<div class="form-group">
							<label for="name">组织机构名称</label> <input type="text"
								name="entity.name" class="form-control"
								placeholder="organization">
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




	<!-- 管理模态框（Modal） -->
	<div class="modal fade" id="ManagerModal" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-hidden="true">&times;</button>
					<h4 class="modal-title" id="myModalLabel">
						当前机构（<span id="nowOrganization"></span>）
					</h4>
				</div>
				<div class="modal-body">
					<div class="form-group">
						<label for="name">组织机构名称</label> <input type="text" id="name"
							class="form-control" placeholder="organization"> <label
							for="name">上级机构</label> 
							<select id="upper"class="form-control">
								<option value="">无</option>
								<s:iterator value="allListEntity" id="ale">
									<option value="${ale.id}">${ale.name }</option>
								</s:iterator>
							</select>
					</div>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">取消
					</button>
					<button onclick="updateOrganization()" class="btn btn-primary">确认修改</button>
				</div>
			</div>
			<!-- /.modal-content -->
		</div>
		<!-- /.modal -->
	</div>
	<dist:footerinclude />
</body>
</html>