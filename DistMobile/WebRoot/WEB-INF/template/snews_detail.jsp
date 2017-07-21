
<%@ page language="java" import="java.util.*,java.lang.*"
	pageEncoding="utf-8" isELIgnored="false"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib uri="/disttags" prefix="dist"%>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title></title>
<dist:headinclude />

<script type="text/javascript" src="resources/ckeditor/ckeditor.js"></script>
<style type="text/css">
	.subImg{width: 72px;height: 72px;cursor: pointer;}
</style>
<script type="text/javascript">
	window.onload = function() {
		if (!CKEDITOR.instances.newsEdit) { //判定content2是否存在  
			editor = CKEDITOR.replace("newsEdit");
		}

	};
	
	function  deleteImg(imgId){
		if(confirm("确定要删除这张图片吗?")){
			var url="snews!deleteImg.action?news.id="+imgId;
			var search=window.location.search;
			$.post(url,null,function(data){
				if(data.success)
					window.location="snews!detail.action"+search;
			},"json");
		}
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
							<li><a href="snews!index.action"> 返回</a></li>
						</ol>
					</div>
				</div>
			</div>
			<div class="row">
				<div class="col-lg-12">
					<form action="snews!update.action" method="post"
						enctype="multipart/form-data">
						<div class="col-lg-3">
							<div class="form-group" style="display:none;">
								<label for="name">ID：</label> 
								<input type="text" value="${news.id }" name="news.id" class="form-control">
							</div>
							<div class="form-group">
								<label for="name">栏目类别：</label> 
								<select id="newsSource" name="news.PSubscriptionsource.id" value="${news.PSubscriptionsource.id }" class="form-control">
									<s:iterator id="ps" value="subSourceList">
										<option value="${ps.id }">${ps.name }</option>
									</s:iterator>
								</select>
							</div>
							<div class="form-group">
								<label for="name">标题：</label> 
								<input type="text" value="${news.title }" name="news.title" class="form-control">
							</div>
							<div class="form-group">
								<label for="name">详情地址：</label> 
								<input type="text"	value="${news.infoUrl }" name="news.infoUrl" class="form-control">
							</div>
							<div class="form-group">
								<label for="name">简介图片上传：</label> 
								<button type="submit" class="btn btn-primary btn-xs" style="float:right;">上传</button>
								<input type="file" class="form-control" name="picfile">
							</div>
							<div class="form-group">
								<label for="name">简介图片列表：</label> 
								<div>
									<s:iterator value="news.SNewspics" id="pic">
										<img src="appIcon/newsIcon/${pic.picname}" class="subImg img-circle" 
										onclick="deleteImg('${pic.id}')">
									</s:iterator>
								</div>
							</div>
							<div class="form-group">
								<button class="btn btn-primary" type="submit" style="float:right;">保存</button>
							</div>
						</div>
						<div class="col-lg-9">
							<div class="form-group">
								<label for="name">内容编辑：</label>
								<textarea id="newsEdit" class="ckeditor form-control" name="news.content">${news.content }</textarea>
							</div>
						</div>
					</form>
				</div>
			</div>
		</div>
	</div>
	<dist:footerinclude />
</body>

</html>