<%@ page language="java" import="java.util.*,java.lang.*"
	pageEncoding="utf-8" isELIgnored="false"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib uri="/disttags" prefix="dist"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<dist:headinclude />
<link href="resources/dist/css/zTreeStyle.css" rel="stylesheet">
<script src="resources/dist/js/jquery.ztree.core-3.5.js"></script>
<script src="resources/dist/js/jquery.ztree.excheck-3.5.js"></script>
<script type="text/javascript">
	function gotoList() {
		editOrg();
		$('#UserModal').modal('show');
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
	function editOrg(){
		allUsers=[];
		zNodes=[];
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
		

$("#formSelectUser").html($("#inputSelect").html());
		var ids="";
		var split="";
		var oids="";
		var osplit="";
		$("#inputSelect >button").each(function(i,val){
			if(val.attributes.isorg.value=="false"){
				ids+=split;
				ids+=val.id;
				split=",";
			}
			else{
				oids+=osplit;
				oids+=val.id;
				osplit=",";
			}
		});
		
		$("#seltext").val(ids);
		$("#oseltext").val(oids);
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
		<dist:navigator selectedIndex="${selectedIndex}" />
		<div id="page-wrapper">
			<div class="row">
				<div class="col-lg-12">
					<div class="dist-breadcrumb-container">
						<ol class="breadcrumb">
							<li><a href="applications!index.action">所有应用程序</a></li>
							<li class="active">新应用</li>
						</ol>
					</div>
				</div>
			</div>
			<div class="col-lg-3"></div>
			<div class="col-lg-6" style="padding-top:30px;">
				<form action="applications!save.action" method="post"
					enctype="multipart/form-data">
					<div class="form-group">
						<label for="name">应用名称：</label> <input type="text" name="app.name"
							class="form-control" placeholder="AppName">
					</div>
					<div class="form-group">
						<label for="name">应用描述：</label>
						<textarea name="app.description" class="form-control" cols="60"
							placeholder="请对APP做概述（字数不大于500字）" rows="7"></textarea>
					</div>
					<div class="form-group">
						<label for="name">可见范围：</label> 
						<div id="formSelectUser" onclick="gotoList()" class="form-group" 
						style="border:solid 1px #ccc;border-radius:6px;padding:5px;min-height:50px;cursor: pointer;">
							
						</div>
						<input id="seltext" type="text" name="selectUserIds" class="form-control" style="display: none;">
						<input id="oseltext" type="text" name="selectOrgIds" class="form-control" style="display: none;"> 
					</div>
					<div class="form-group" style="display:none;">
						<label for="name">所属类别： </label> <select id="category"
							name="app.SApplicationcategory.id" class="form-control">
							<option value="3" selected="selected">IOS平板</option>
							<option value="4">IOS手机</option>
							<option value="1">android平板</option>
							<option value="2">android手机</option>
							<option value="5">IOS设备</option>
							<option value="7">android设备</option>
							<option value="7">全部</option>
						</select>
					</div>
					<div class="form-group">
						<label for="name">应用图标：</label> <input name="picfile" type="file">
						<br> <font color="#0066FF">(允许格式：.bmp,.png,.gif,.jpeg,.jpg,其他上传不显示)
						</font>
					</div>
					<div class="form-group">
						<button type="submit" class="btn btn-primary">提交</button>
					</div>
				</form>
			</div>
		</div>
	</div>

	<div class="modal fade" id="UserModal" tabindex="-1"
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
					<button onclick="selectPurview()" class="btn btn-primary" data-dismiss="modal">确定</button>
				</div>
			</div>
			<!-- /.modal-content -->
		</div>
		<!-- /.modal -->
	</div>

	<dist:footerinclude />
</body>
</html>
