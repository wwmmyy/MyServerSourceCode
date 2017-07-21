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
	display: block;
	text-align: center;
}

.app_listitem img {
	width: 72px;
	height: 72px;
}

.app_listitem span {
	display: block;
	text-align: center;
	height: 20px;
	line-height: 20px;
	font-size: 14px;
}

.app_create {
	background: url(resources/images/newapplication.png) center no-repeat;
}
</style>
<script type="text/javascript">
	function goToAppList(id) {
		window.location = "applications!list.action?app.id=" + id;
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
							<li class="active">所有应用程序</li>
							<li><a href="applications!regist.action">新应用</a></li>
							
						</ol>
					</div>
				</div>
			</div>
			<s:iterator value="listapplications" id="u">
				<div class="app_listitem" onclick="goToAppList('${u.id}')">
					<img class="img-circle" alt="${u.name}"
						src="appIcon/appIcon/${u.icon}"> <span class="">${u.name}</span>
				</div>
			</s:iterator>
			<div class="app_listitem">
				<a href="applications!regist.action"><img class="img-circle"
					src="resources/images/newapplication.png"></a> <span class=""></span>
			</div>
		</div>
	</div>
	<dist:footerinclude />
</body>
</html>
