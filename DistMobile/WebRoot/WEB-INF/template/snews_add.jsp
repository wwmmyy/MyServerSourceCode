
<%@ page language="java" import="java.util.*,java.lang.*"
	pageEncoding="utf-8" isELIgnored="false"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib uri="/disttags" prefix="dist"%>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title></title>
<dist:headinclude />
<dist:footerinclude />
<script type="text/javascript" src="resources/ckeditor/ckeditor.js"></script>
<script type="text/javascript">
	window.onload = function() {
		if (!CKEDITOR.instances.newsEdit) { //判定content2是否存在  
			editor = CKEDITOR.replace("newsEdit");
		}
	};
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
							<li><a href="snews!index.action">发布订阅</a></li>
							<li class="active" style="cursor:pointer;">添加</li>
						</ol>
					</div>
				</div>
			</div>
			<div class="row">
				<div class="col-lg-12">
					<form action="snews!save.action" method="post"
						enctype="multipart/form-data">
						<div class="col-lg-3">
							<div class="form-group">
								<label for="name">栏目类别：</label> 
								<select id="newsSource" name="news.PSubscriptionsource.id" class="form-control">
									<s:iterator id="ps" value="subSourceList">
										<option value="${ps.id }">${ps.name }</option>
									</s:iterator>
								</select>
							</div>
							<div class="form-group">
								<label for="name">标题：</label> 
								<input type="text" id="newsTitle" name="news.title" class="form-control">
							</div>
							<div class="form-group">
								<label for="name">详情地址：</label> 
								<input type="text"	id="newsInfoUrl" name="news.infoUrl" class="form-control">
							</div>
							<div class="form-group">
								<label for="name">简介图片：</label> 
								<input type="file" class="form-control" id="newsInfoUrl" name="picfile">
							</div>
						</div>
						<div class="col-lg-9">
							<div class="form-group">
								<label for="name">内容编辑：</label>
								<button class="btn btn-primary" type="submit" style="float:right;">添加</button>
							</div>
							<div class="form-group">
								<textarea id="newsEdit" class="ckeditor form-control" name="news.content" />
							</div>
						</div>
					</form>
				</div>
			</div>
		</div>
	</div>
	
</body>

</html>