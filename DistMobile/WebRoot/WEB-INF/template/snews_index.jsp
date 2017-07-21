
<%@ page language="java" import="java.util.*,java.lang.*"
	pageEncoding="utf-8" isELIgnored="false"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib uri="/disttags" prefix="dist"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>user list</title>
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

<style type="text/css">
#columnTable tr td {
	text-align: left;
}
;
</style>
<script type="text/javascript">
	$(document).ready(function() {
		$('#dataTables').DataTable({
			responsive : true
		});
	});

	function update(obj) {
		var tds = $(obj).parent().parent().find('td');
		var id = tds.eq(0).text();
		window.location = "snews!detail.action?news.id=" + id;

	}

	function deleteData(id) {
		var url="snews!delete.action?news.id="+id;
		$.post(url,null,function(data){
		if(data.success){
			alert("good job!删除成功");
			window.location="snews!index.action";
		}
		},"json");
	}

	function editeSub(id) {
		var url="subScriSource!detail.action?entity.id="+id;
		$.post(url,null,function(data){
			if(data.success){
			var re=data.obj;
				$("#columnName").val(re.name);
				$("#columnDescription").val(re.description);
				$("#columnID").val(re.id);
			}
		},"json");
	}
	function deleteColumn(id) {
		var url = "subScriSource!delete.action?entity.id=" + id;
		$.post(url, null, function(data) {
			if (data.success)
				alert("删除成功");
				window.location="snews!index.action";
		}, "json");
	}
	function addData(){
		var name=$("#entityName").val();
		var description=$("#entityDescription").val();
		var url="subScriSource!save.action";
		var args={"entity.name":name,"entity.description":description};
		$.post(url,args,function(data){
			if(data.success)
				window.location="snews!index.action";
		},"json");
	}
	
	function updateSubData(){
		var name=$("#columnName").val();
		var description=$("#columnDescription").val();
		var id=$("#columnID").val();
		var url="subScriSource!update.action";
		var args={"entity.id":id,"entity.name":name,"entity.description":description};
		$.post(url,args,function(data){
			if(data.success)
				window.location="snews!index.action";
		},"json");
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
							<li class="active">发布订阅</li>
							<li data-toggle="modal" style="cursor:pointer;"><a
								href="snews!add.action">发布新闻</a></li>
						</ol>
					</div>
				</div>
			</div>

			<div class="row" style="padding:0px; margin:0px;padding-top: 10px;">
				<div class="col-lg-3">
					<div class="panel panel-green">
						<div class="panel-heading">栏目</div>

						<table id="columnTable" class="table">
							<s:iterator value="subSourceList" id="ssl">
								<tr>
									<td>${ssl.name }</td>
									<td style="text-align: right;">
									<a data-toggle="modal" data-target="#updateColumnModal" style="cursor: pointer;"
										onclick="editeSub('${ssl.id }');"><i class="fa fa-pencil"></i></a>
									<a role="button" onclick="deleteColumn('${ssl.id }')"
										style="cursor: pointer;color:Red;margin-left: 10px;"><i class="fa fa-trash-o"></i></td>
								</tr>
							</s:iterator>
						</table>

						<div class="panel-footer">
							<button type="button" class="btn btn-primary btn-lg btn-block" data-toggle="modal" 
							data-target="#addColumnModal">添加栏目</button>
						</div>
					</div>
				</div>
				<div class="col-lg-9">
					<table id="dataTables" role="grid"
						class="table table-striped table-bordered table-hover dataTable no-footer"
						aria-describedby="dataTables-example_info">
						<thead>
							<tr role="row">
								<th style="display:none;">id</th>
								<th aria-controls="dataTables">标题</th>
								<th>所属栏目</th>
								<th aria-controls="dataTables">添加时间</th>
								<th>管理</th>
							</tr>
						</thead>
						<tbody>
							<s:iterator value="listnews" id="u">
								<tr href="" role="row" class="gradeA">
									<td style='display:none'>${u.id }</td>
									<td>${u.title}</td>
									<td>${u.PSubscriptionsource.name}</td>
									<td><s:date name="time" format="yyyy-MM-dd" /></td>
									<td><a href="javascript:void(0)" title="编辑" 
										onclick="update(this);"><i class="fa fa-pencil"></i></a> <a
										role="button" data-toggle="modal" onclick="deleteData('${u.id}')"
										style="margin-left:10px;cursor: pointer;color:Red;" title="删除">
											<i class="fa fa-trash-o"></i></td>
								</tr>
							</s:iterator>
						</tbody>
					</table>
				</div>
			</div>
		</div>
		<div class="modal fade" id="addColumnModal" tabindex="-1"
			role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
			<div class="modal-dialog">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal"
							aria-hidden="true">&times;</button>
						<h4 class="modal-title" id="myModalLabel">
							<li class="fa fa-fw fa-plus"></li>添加栏目
						</h4>
					</div>

					<div class="modal-body">
						<div class="form-group">
							<label for="name">栏目名称</label> <input type="text"
								id="entityName" class="form-control">
						</div>
						<div class="form-group">
							<label for="name">描述</label> <textarea 
								id="entityDescription" class="form-control"></textarea>
						</div>
					</div>
					<div class="modal-footer">
						<button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
						<button type="button" class="btn btn-primary" onclick="addData()">添加</button>
					</div>

				</div>
				<!-- /.modal-content -->
			</div>
			<!-- /.modal -->
		</div>
		
		<div class="modal fade" id="updateColumnModal" tabindex="-1"
			role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
			<div class="modal-dialog">
				<div class="modal-content">
					<div class="modal-header">
						当前栏目【<span id="nowColumn"></span>】
						
					</div>

					<div class="modal-body">
						<div class="form-group" style="display:none;">
							<label for="name">ID</label> <input type="text"
								id="columnID" class="form-control">
						</div>
						<div class="form-group">
							<label for="name">栏目名称</label> <input type="text"
								id="columnName" class="form-control">
						</div>
						<div class="form-group">
							<label for="name">描述</label> <textarea 
								id="columnDescription" class="form-control"></textarea>
						</div>
					</div>
					<div class="modal-footer">
						<button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
						<button type="button" class="btn btn-primary" onclick="updateSubData()">编辑</button>
					</div>

				</div>
				<!-- /.modal-content -->
			</div>
			<!-- /.modal -->
		</div>
		
		<dist:footerinclude />
</body>
</html>