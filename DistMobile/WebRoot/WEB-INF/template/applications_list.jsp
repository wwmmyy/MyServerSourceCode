
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
<link href="resources/dist/css/zTreeStyle.css" rel="stylesheet">
<script src="resources/dist/js/jquery.ztree.core-3.5.js"></script>
<script src="resources/dist/js/jquery.ztree.excheck-3.5.js"></script>
<script type="text/javascript">
	var selectAppID = null;
	var selectAppName = null;
	$(document).ready(function() {
		$('#dataTables').DataTable({
			responsive : true
		});
		$("#dataTables_filter").hide();
		$("#dataTables_length").hide();
		selectAppID = window.location.search.split('=')[1];
		var url = "applications!detail.action?app.id=" + selectAppID;
		$.post(url, null, function(data) {
			if (data.state) {
				var r = JSON.parse(data.result);
				$("#upAppId").val(r.id);
				$("#upAppName").val(r.name);
				$("#upAppIcon").attr("src", "appIcon/appIcon/" + r.icon);
				$("#upAppDescription").val(r.description);
				selectAppName = escape(r.name);
				
			} else {
				alert("服务错误");
			}

		}, 'json');
		
	});
	var selectPlatId = null;
	function update(obj) {
		var tds = $(obj).parent().parent().find('td');
		selectPlatId = tds.eq(0).text();
		$("#nowApp").text(selectPlatId);
		$("#upversion").val(tds.eq(1).text());
		$("#upIdentity").val(tds.eq(2).text());
		var s = tds.eq(8).text() == "true" ? true : false;
		$("#upstatus").attr("checked", !s);
		$("#upplatform").val(tds.eq(4).text());
		$("#uppath").val(tds.eq(5).text());
		$("#upurl").val(tds.eq(6).text());
		$("#nowPlat").text(tds.eq(4).text());
		$('#ManagerPlatModal').modal('show');
	}

	function deletePlat(obj){
		var id = $(obj).next(":hidden").val();
		var flag = confirm("确定要删除该实例吗？");
		if (flag) {
			var url = "applicationPlatform!delete.action?app.id=" + id;
			$.post(url, null, function(data) {
				if (data.success) {
					window.location.reload();
					alert("删除成功");
				} else {
					alert("删除失败");
				}

			}, 'json');

		}

	}
	function deleteApp(){
		var flag = confirm("确定要删除该应用吗？");
		if (flag) {
			var url = "applications!delete.action?app.id=" + selectAppID;
			$.post(url, null, function(data) {
				if (data.success) {
					window.location = "applications!index.action";
					alert("删除成功");
				} else {
					alert("删除失败");
				}
			}, "json");
		}
	}
	function updatePlat() {
		var v = $("#upversion").val();
		var i = $("#upIdentity").val();
		var s = $("#upstatus")[0].checked;
		var plat = $("#upplatform").val();
		var path = $("#uppath").val();
		var u = $("#upurl").val();
		var url = "applicationPlatform!update.action";
		var args = {
			"app.id" : selectPlatId,
			"app.applicationIdentity" : i,
			"app.version" : v,
			"app.status" : !s,
			"app.platform" : plat,
			"app.path" : path,
			"app.url" : u
		};
		$.post(url, args, function(data) {
			if (data.success) {
				window.location.reload();
			} else {
				alert("修改失败");
			}

		}, 'json');
	}
	function updateApp(obj) {
		var name = $("#upAppName").val();
		var description = $("#upAppDescription").val();
		var icon = $("#inputIconPath").val();
		var url = "applications!update.action";
		var args = {
			"app.id" : selectAppID,
			"app.name" : name,
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
	function addPlatform() {
		$("#platId").val(selectAppID);
		$("#ManagerNewPlatModal").modal("show");
	}
	function platformChange() {
		var t = $("#platType").val();
		if (t == "Web")
			$("#webUrl").show();
		else
			$("#webUrl").hide();
	}
	function showUploadDiv() {
		$("#inputIconPath").click();
	}
	var selectOrgId="";
	function searchTree(id){
		selectOrgId="";
		selectOrgId+=","+id;
		for(var i=0;i<zNodes.length;i++){
			var item=zNodes[i];
			if(item.pId==id&&item.pId!=item.id){
				selectOrgId+=","+item.id;
				for(var j=0;j<zNodes.length;j++){
					if(zNodes[j].pId==item.id&&item.pId!=item.id){
						selectOrgId+=","+zNodes[j].id;
						for(var k=0;k<zNodes.length;k++){
							if(zNodes[k].pId==zNodes[j].id&&zNodes[k].pId!=zNodes[k].id){
								selectOrgId+=","+zNodes[k].id;
							}
						}
					}
				}
			}
		}
	}
	function in_array(search,array){
	    for(var i=0;i<array.length;i++){
	        if(array[i]==search)
	            return true;
	    }
	    return false;
	}
	var orgUser=[];
	function zTreeOnClick(event, treeId, treeNode) {
		orgUser=[];
    	var oid=treeNode.id;
    	processSelectUser(oid);
    };
	function processSelectUser(oid){
		searchTree(oid);
    	selectOrgId=selectOrgId.substring(1);
    	var arr=selectOrgId.split(',');
    	for(var i=0;i<allUsers.length;i++){
    		if(in_array(allUsers[i].phoneNumber,arr)){
    			var item={};
    			item.id=allUsers[i].id;
    			item.name=allUsers[i].name;
    			item.phoneNumber=allUsers[i].phoneNumber;
    			for(var j in selectedUsersArray){
    				if(selectedUsersArray[j].id==item.id){
    					item.checked=true;
    					break;
    				}
    			}
    			orgUser.push(item);
    		}
    			
    	}
    	$.fn.zTree.init($("#usertree"), usersetting, orgUser);
    	changeIcon();
	}
	var setting = {
		check: {
			enable: true,
			chkboxType:{ "Y" : "", "N" : "" }
		},
		data: {
			simpleData: {
				enable: true,
			    idKey : "id",             
				pIdKey : "pId",  
			}
		},
		callback: {
			onClick: zTreeOnClick,
			onCheck: zTreeOnCheck
		}
	};
	var usersetting = {
		check: {
			enable: true,
		},
		data: {
			simpleData: {
				enable: true,
			    idKey : "id",             
				pIdKey : "pId",  
			}
		},
		callback: {
			onCheck: zTreeOnCheck
		}
	};
	
	var selectedUsersArray=[];
	var selectedOrgsArray=[];
	function zTreeOnCheck(event, treeId, treeNode) {
    	dddd();
	};
	function dddd(){
		var treeObj = $.fn.zTree.getZTreeObj("usertree");
		var nodes = treeObj.getCheckedNodes(true);
		var selName="";
		
		for(var i=0;i<nodes.length;i++){
			var has=false;
			for(var j in selectedUsersArray){
				if(selectedUsersArray[j].id==nodes[i].id){
					has=true;
					break;
				}
			}
			if(!has)
				selectedUsersArray.push({id:nodes[i].id,name:nodes[i].name});
			
		}
		nodes = treeObj.getCheckedNodes(false);
		for(var i=0;i<nodes.length;i++){
			for(var j in selectedUsersArray){
				if(selectedUsersArray[j].id==nodes[i].id){
					selectedUsersArray.splice(j,1);
					break;
				}
			}
			
		}
		for(var i in selectedUsersArray){
			selName+="<button class='btn btn-default btn-xs' style='margin:2px;' id='"+selectedUsersArray[i].id+"' isorg='false'><i class='fa fa-user green'></i>"+selectedUsersArray[i].name+"<i></i></button>";
		}
		orgTreeCheck(selName);
	}
	function orgTreeCheck(selName){
		var treeObj = $.fn.zTree.getZTreeObj("tree");
		var nodes = treeObj.getCheckedNodes(true);
		for(var i=0;i<nodes.length;i++){
			var has=false;
			for(var j in selectedOrgsArray){
				if(selectedOrgsArray[j].id==nodes[i].id){
					has=true;
					break;
				}
			}
			if(!has)
				selectedOrgsArray.push({id:nodes[i].id,name:nodes[i].name});
		}
		nodes = treeObj.getCheckedNodes(false);
		for(var i=0;i<nodes.length;i++){
			for(var j in selectedOrgsArray){
				if(selectedOrgsArray[j].id==nodes[i].id){
					selectedOrgsArray.splice(j,1);
					break;
				}
			}
			
		}
		for(var i in selectedOrgsArray){
			selName+="<button class='btn btn-default btn-xs' style='margin:2px;' id='"+selectedOrgsArray[i].id+"' isorg='true'><i class='fa fa-users green'></i>"+selectedOrgsArray[i].name+"<i></i></button>";
		}
		$("#inputSelect").html(selName);
	}
	var zNodes =[];
	function initTree(url){
		$.post(url, null, function(data) {
			if (data.state) {
				var r=JSON.parse(data.result);
				processOrgData(r);
				for(var i in zNodes){
					var has=false;
					for(var j in selectedOrgsArray){
						if(zNodes[i].id==selectedOrgsArray[j].id){
							has=true;
							break;
						}
					}
					if(has)
						zNodes[i].checked=true;
				}
				$.fn.zTree.init($("#tree"), setting, zNodes);
				var treeObj = $.fn.zTree.getZTreeObj("tree");
				var node = treeObj.getNodeByParam("id",firstId);
				treeObj.selectNode(node,false);
				initUserTree("user!getAllUser.action");
			}	
		}, "json");
		
	}
	function initUserTree(url){
		$.post(url, null, function(data) {
			if (data.state) {
				var r=JSON.parse(data.result);
				processData(r);
			}	
		}, "json");
		
	}
	 var firstId="";
	 var firstname="";
	function processOrgData(obj){
		for(var i=0;i<obj.length;i++){
			var root=obj[i];
			if(root.pid==null&&firstId==""){
				firstId=root.id;
				firstname=root.name;
				break;
			}
		}
		for(var i=0;i<obj.length;i++){
			var root=obj[i];
			var ob={};
			ob.id=root.id;
			ob.name=root.name;
			ob.pId=root.pid;
			ob.open=true;
			zNodes.push(ob);
		}
	}
	
	var allUsers=[];
	function processData(obj){
		for(var i=0;i<obj.length;i++){
			var o={};
			o.id=obj[i].id;
			o.name=obj[i].name;
			o.phoneNumber=obj[i].phoneNumber;
			var c=false;
			for(var j in selectedUsersArray){
				if(selectedUsersArray[j]==o.id){
					c=true;
					break;
				}
			}
			o.checked=c;
			allUsers.push(o);
		}
		orgUser=[];
		processSelectUser(firstId);
	}
	function findHavePreview(){
		var selName="";
		$("#divPurview > button > span").each(function(i,val){
			var isorg=val.attributes.isorg.value;
			selName+="<button class='btn btn-default btn-xs' style='margin:2px;' id='"+val.id+"' isorg='"+isorg
			+"'><i class='fa fa-user green'></i>"+val.attributes.name.value+"<i></i></button>";
			if(isorg=="false")
				selectedUsersArray.push({id:val.id,name:val.attributes.name.value});
			else
				selectedOrgsArray.push({id:val.id,name:val.attributes.name.value});
		});
		$("#inputSelect").html(selName);
		
	}
	function editOrg(){
		allUsers=[];
		zNodes=[];
		findHavePreview();
		initTree("organization!getAllOrg.action");
		$('#ManagerOrgModal').modal('show');
	}
	function allUserClick(){
		for(var i in allUsers){
			var has=false;
			for(var j in selectedUsersArray){
				if(selectedUsersArray[j].id==allUsers[i].id){
   					has=true;
   					break;
   				}
   			}
   			allUsers[i].checked=has;
		}
		$.fn.zTree.init($("#usertree"), usersetting, allUsers);
		changeIcon();
	}
	function selectPurview(){
		var ids="[";
		var oids="[";
		var res=[];
		
		$("#divPurview > button > span").each(function(i,val){
			res.push({id:val.id,isorg:val.attributes.isorg.value});
		});
		var split="";
		var osplit="";
		$("#inputSelect > button").each(function(i,val){
			var has=false;
			for(var i in res){
				if(res[i].id==val.id){
					has=true;
					break;
				}
			}
			if(!has){
				if(val.attributes.isorg.value=="false"){
					ids+=split;
					ids+="{id:\""+val.id+"\",state:\"true\"}";
					split=",";
				}
				else 
				{
					oids+=osplit;
					oids+="{id:\""+val.id+"\",state:\"true\"}";
					osplit=",";
				}
			}
			else res.splice(i,1);
			
		});
		if(split==",")
			split=",";
		if(split==",")
			osplit=",";
		for(var i in res){
			if(res[i].isorg=="false"){
				ids+=split;
				ids+="{id:\""+res[i].id+"\",state:\"false\"}";
				spilt=",";
			}
			else{
				oids+=osplit;
				oids+="{id:\""+res[i].id+"\",state:\"false\"}";
				osplit=",";
			}
		}
		ids+="]";
		oids+="]";
		var url="applications!saveUserApplication.action";
		var ourl="applications!saveUserOrg.action";
		var appid=window.location.search.split('=')[1];
		var args={"userids":ids,"app.id":appid};
		var oargs={"orgids":oids,"app.id":appid};
		$.post(url,args,function(data){
			if(data.success){
				$.post(ourl,oargs,function(){
					if(data.success){
						
					}
				},"json");
			}
			else alert("修改失败");
			var s=window.location.search;
			setTimeout(function(){
				window.location="applications!list.action"+s;
			},500)
			
		},"json");
		
	}
	
	function changeIcon(){
		$("#tree li span.button.ico_docu").css("background-position","-110px -16px");
	}
	function allCheckedClick(ch){
		var treeObj = $.fn.zTree.getZTreeObj("usertree");
		treeObj.checkAllNodes(ch);
		if(ch){
			$("#btnCheckAll").hide();
			$("#btnUncheckAll").show();
		}
		else{
			$("#btnCheckAll").show();
			$("#btnUncheckAll").hide();
		}
		dddd();
	}
</script>

</head>
<body>
	<div id="wrapper">
		<dist:navigator selectedIndex="${selectedIndex}"/>
		<div id="page-wrapper">
			<div class="row">
				<div class="col-lg-12">
					<div class="dist-breadcrumb-container">
						<ol class="breadcrumb">
							<li>${app.name}</li>
							<li><a href="applications!index.action">返回</a></li>
						</ol>
					</div>
				</div>
			</div>
			<div class="row" style="padding:0px; margin:0px;padding-top: 10px;">
				<div class="col-lg-3">
					<form action="applications!updateApp.action" method="post" enctype="multipart/form-data">
						<div class="form-group" style="display:none;">
							<label for="name">应用程序id</label> <input type="text"
								id="upAppId" name="app.id" class="form-control">
						</div>
						<div class="form-group" style="text-align: center;border: solid 1px #ccc;padding: 20px;border-radius:10px;">
							<img id="upAppIcon" style="width: 72px;height:72px;" class="img-circle" alt="" onclick="showUploadDiv();" style="cursor: pointer;">
						</div>
						<div class="form-group">
							<label for="name">应用程序名称*</label> <input type="text"
								id="upAppName" name="app.name" class="form-control">
						</div>
						<div id="uploadIcon" class="form-group" style="display:none;">
							<label for="name">上传图标*</label> <input id="inputIconPath"
								type="file" style="width:100%;" name="picfile">
						</div>
						<div class="form-group">
							<label for="name">应用程序描述*</label>
							<textarea id="upAppDescription" class="form-control" name="app.description"
								 style="height:210px;"></textarea>
						</div>
						<div class="form-group">
							<button type="submit" class="btn btn-primary">修改</button>
							<button type="button" class="btn btn-primary"
								onclick="addPlatform();">添加新平台</button>
							<button onclick="deleteApp()" type="button" class="btn btn-danger"
								style="float:right;">删除</button>
						</div>
					</form>
				</div>
				<div class="col-lg-9">
					<div>
						<div><label>可见范围</label>
						<button  class="btn btn-primary btn-xs" style="float: right;" onclick="editOrg()">编辑范围</button>
						</div>
						<div id="divPurview">
							<s:iterator value="orgs" id="op">
								<button class='btn btn-default btn-xs' style='margin:2px;'><i class='fa fa-users green'></i><span id="${op.id}" name="${op.name}" isorg="true">${op.name}</span></button>
							</s:iterator>
							<s:iterator value="users" id="us">
								<button class='btn btn-default btn-xs' style='margin:2px;'><i class='fa fa-user green'></i><span id="${us.id}" name="${us.name}" isorg="false">${us.name}</span></button>
							</s:iterator>
							
						</div>
					</div>
					<table id="dataTables" role="grid"
						class="table table-striped table-bordered table-hover dataTable no-footer"
						aria-describedby="dataTables-example_info">
						<thead>
							<tr role="row">
								<th style="display:none;">id</th>
								<th aria-controls="dataTables">版本号</th>
								<th aria-controls="dataTables">标识</th>
								<th aria-controls="dataTables">状态</th>
								<th aria-controls="dataTables">运行平台</th>
								<th aria-controls="dataTables" style="display:none;">下载地址</th>
								<th aria-controls="dataTables" style="display:none;">url</th>
								<th aria-controls="dataTables">创建时间</th>
								<th aria-controls="dataTables" style="display:none;">状态</th>
								<th>管理</th>
							</tr>
						</thead>
						<tbody>
							<s:iterator value="aplatlist" id="u">
								<tr href="" role="row" class="gradeA">
									<td style='display:none'>${u.id }</td>
									<td>${u.version}</td>
									<td>${u.applicationIdentity}</td>
									<td><s:if test="#u.status==true">
											<i class="fa fa-fw fa-check green"></i>
										</s:if> <s:elseif test="#u.status==false">
											<i class="fa fa-fw fa-remove red"></i>
										</s:elseif></td>
									<td>${u.platform}</td>
									<td style="display:none;">${u.path}</td>
									<td style="display:none;">${u.url}</td>
									<td><s:date name="createTime" format="yyyy-MM-dd" /></td>
									<td style="display:none;">${u.status}</td>
									<td><a href="javascript:void(0)" title="编辑"
										onclick="update(this);"><i class="fa fa-pencil"></i></a> <a
										role="button" data-toggle="modal" onclick="deletePlat(this)"
										style="margin-left:10px;cursor: pointer;color:Red;" title="删除">
											<i class="fa fa-trash-o"></i>
									</a> <input type="hidden" value="${u.id}" /></td>
								</tr>
							</s:iterator>
						</tbody>
					</table>
				</div>
			</div>
		</div>
	</div>
	<!-- 管理模态框（Modal） -->
	<div class="modal fade" id="ManagerPlatModal" tabindex="-1"
		role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-hidden="true">&times;</button>
					<h4 class="modal-title" id="myModalLabel">
						当前平台【<span id="nowPlat"></span>】
					</h4>
				</div>
				<div>
					<div class="modal-body">
						<div class="form-group">
							<label for="name">版本号：</label> <input type="text" id="upversion"
								class="form-control">
						</div>
						<div class="form-group">
							<label for="name">标识：</label> <input type="text" disabled="true"
								id="upIdentity" class="form-control">
						</div>
						<div class="form-group">
							<label for="name">状态：</label> <label class="checkbox-inline">
								<input type="checkbox" id="upstatus"> 禁用
							</label>
						</div>

						<div class="form-group">
							<label for="name">运行平台：</label> <input disabled="disabled"
								type="text" id="upplatform" class="form-control">
						</div>
						<div class="form-group">
							<label for="name">下载地址：</label> <input type="text" id="uppath"
								class="form-control">
						</div>
						<div class="form-group">
							<label for="name">url</label> <input type="text" id="upurl"
								class="form-control">
						</div>

					</div>
					<div class="modal-footer">
						<button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
						<button onclick="updatePlat()" class="btn btn-primary">修改</button>
					</div>
				</div>
			</div>
			<!-- /.modal-content -->
		</div>
		<!-- /.modal -->
	</div>

	<div class="modal fade" id="ManagerNewPlatModal" tabindex="-1"
		role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-hidden="true">&times;</button>
					<h4 class="modal-title" id="myModalLabel">
						当前应用【<span>${app.name}</span>】
					</h4>
				</div>
				<div>
					<form action=" applicationPlatform!save.action" method="post"
						enctype="multipart/form-data">
						<div class="modal-body">
							<div class="form-group" style="display:none;">
								<label for="name">名称：</label> <input id="platId" type="text"
									name="app.SApplications.id" class="form-control"
									value="${ap.id}">
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
						</div>
						<div class="modal-footer">
							<button type="button" class="btn btn-default"
								data-dismiss="modal">取消</button>
							<button type="submit" class="btn btn-primary">提交</button>
						</div>
					</form>
				</div>
			</div>
			<!-- /.modal-content -->
		</div>
		<!-- /.modal -->
	</div>
	
	<div class="modal fade" id="ManagerOrgModal" tabindex="-1"
		role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<label>选择可见范围</label>
					<button class="btn btn-primary" style="float: right;" onclick="allUserClick();">所有用户</button>
				</div>
				<div class="modal-body">
					<div class="form-group" id="inputSelect" style="border:solid 1px #ccc;border-radius:6px;padding:5px;min-height:50px;">
							
					</div>
					<div class="form-group">
						<div style="width: 300px;display: table-cell;">
							<label>组织机构列表：</label>
							<ul id="tree" class="ztree" ></ul>
						</div>
						<div style="display: table-cell;border-left: solid 1px #ccc;width:100%;padding-left:40px;">
							<div>
								<label>对应的用户列表：</label>
								<button id="btnCheckAll" class="btn btn-primary btn-xs" style="float:right;" onclick="allCheckedClick(true)">全选</button>
								<button id="btnUncheckAll" class="btn btn-primary btn-xs" style="float:right;display:none;" onclick="allCheckedClick(false)">反选</button>
							</div>
							<ul id="usertree" class="ztree"></ul>
						</div>
					</div>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default"
						data-dismiss="modal">取消</button>
					<button onclick="selectPurview()" class="btn btn-primary">提交</button>
				</div>
			</div>
			<!-- /.modal-content -->
		</div>
		<!-- /.modal -->
	</div>
	<dist:footerinclude />
</body>
</html>