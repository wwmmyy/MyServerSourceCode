<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<HTML>
<HEAD>
<TITLE>AjaxJqueryJson</TITLE>
<META NAME="Generator" CONTENT="EditPlus">
<META NAME="Author" CONTENT="">
<META NAME="Keywords" CONTENT="">
<META NAME="Description" CONTENT="">
<script language="javascript" type="text/javascript" src="template/common/js/jquery-1.5.2.min.js"></script>
<script type="text/javascript">
	//$.ajax()方式 
	$(document).ready(function() {
		
		$('#send_ajax').click(function() { //直接把onclick事件写在了JS中，而不需要混在XHTML中了 
			/*
			var name = $("#name").val();
			var password = $("#password").val();
			 */
			var params = $('input').serialize(); //序列化表单的值，与prototype中的form.serialize()相同 
			$.ajax({
				url : 'jsonDemo!login.action', //后台处理程序 
				type : 'post', //数据发送方式 
				dataType : 'json', //接受数据格式 
				/*
				data : {
					"user.name" : name,
					"user.password" : password
				}
				 */
				data : params, //要传递的数据 
				success : update_page//回传函数(这里是函数名) 
			});
		});
	});

	function update_page(json) { //回传函数实体，参数为XMLhttpRequest.responseText 
		var str = "姓名:" + json.name + "<br />";
		str += "年龄:" + json.age + "<br />";
		str += "性别:" + json.sex + "<br />";
		str += "工作:" + json.job;
		$("#result").html(str);
	}

	//$.post()方式： 
	$(function() { //$(document).ready(function (){ 的简写 
		$('#test_post').click(
				function() {
					$.post('jsonDemo!login.action', {
						name : $('#input1').val(),
						age : $('#input2').val(),
						sex : $('#input3').val(),
						job : $('#input4').val()
					}, function(data) { //回传函数 
						var myjson = '';
						myjson = eval(data);
						$('#result').html(
								"姓名:" + myjson.name + "<br />工作:"
										+ myjson['job']);
					}, 'json');
				});
	});
	//$.get()方式： 
	$(function() {
		$('#test_get').click(function() {
			$.get('jsonDemo!login.action', {
				name : $("#input1").val(),
				age : $("#input2").val(),
				sex : $("#input3").val(),
				job : $("#input4").val()
			}, function(data) {
				var myjson = '';
				myjson = eval(data);
				$("#result").html("<h1>"+myjson.job+"</h1>");
			}, 'json');
		});
	});
</script>
<BODY>
	<h1>json</h1>
	<div id="result"
		style="background:orange;border:1px solid red;width:300px;height:100px;"></div>
	<form id="formtest" action="" method="post">
		<p>
			<span>输入姓名:</span><input type="text" name="name" id="input1" />
		</p>
		<p>
			<span>输入年龄:</span><input type="text" name="age" id="input2" />
		</p>
		<p>
			<span>输入性别:</span><input type="text" name="sex" id="input3" />
		</p>
		<p>
			<span>输入工作:</span><input type="text" name="job" id="input4" />
		</p>
	</form>
	<button id="send_ajax">AJAX提交</button>
	<button id="test_post">POST提交</button>
	<button id="test_get">GET提交</button>
</BODY>
</HTML>