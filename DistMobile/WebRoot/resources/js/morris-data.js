/// <reference path="../../jslib/jquery-easyui-1.3.1/jquery-1.8.0.min.js" />
$(function() {

	var contextPath = $('#contextPath').val();
	var basePath = $('#basePath').val();

	// 设备情况
	$.post(contextPath + '/device!nubmers.action', function(data) {
		var rs = eval(data);
		var dvalue = {
			element : 'morris-donut-chart',
			resize : true,
			data : []
		};
		for ( var i = 0; i < rs.length; i++) {
			dvalue.data.push({
				label : rs[i].name,
				value : rs[i].num
			});
		}
		Morris.Donut(dvalue);
	});

	 

//	 //设备活跃情况(年/季度)
//	 $.post(contextPath + '/mobileLog!monthActives.action', { dateStart:
//	 '2015-03-12', dateEnd: '2015-05-15' }, function (data) {
//	 data = [{ "appName": "项目审批", "appidentify": "com.dist.iBusiness",
//	 "month": 1, "num": 1000, "year": 2015 },
//	 { "appName": "项目审批", "appidentify": "com.dist.iBusiness", "month": 2,
//	 "num": 2000, "year": 2015 },
//	 { "appName": "项目审批", "appidentify": "com.dist.iBusiness", "month": 3,
//	 "num": 3000, "year": 2015 }];
//	 var dvalue = { element: 'morris-area-chart', resize: true };
//	 dvalue.data = buildData(data, function (item) {
//	 return item.year + '-' + item.month;
//	 });
//	 Morris.Area(dvalue);
//	 });

	// 设备活跃情况(月)
	$.post(contextPath + '/mobileLog!dayActives.action', {
		dateStart : '2015-04-12',
		dateEnd : '2015-09-25'
	}, function(data) {
		var rss = eval(data);
		var dvalue = {
			element : 'morris-area-chart',
			data : [],
			xkey : 'period',
			ykeys : [ 'iPortal', 'iBusiness', 'iGw' ],
			labels : [ 'iPortal', 'iBusiness', 'iGw' ],
			pointSize : 2,
			hideHover : 'auto',
			resize : true
		};
		var bData = buildData(rss);
		dvalue.data = bData[0];
		dvalue.ykeys = bData[1];
		dvalue.labels = bData[2];
		Morris.Area(dvalue);
	});
	
	loadFeedback();
	// Morris.Area({
	// element: 'morris-area-chart',
	// data: [{
	// period: '2010 Q1',
	// iPortal: 2666,
	// iBusiness: null,
	// iDocument: 2647
	// }, {
	// period: '2010 Q2',
	// iPortal: 2778,
	// iBusiness: 2294,
	// iDocument: 2441
	// }, {
	// period: '2010 Q3',
	// iPortal: 4912,
	// iBusiness: 1969,
	// iDocument: 2501
	// }, {
	// period: '2010 Q4',
	// iPortal: 3767,
	// iBusiness: 3597,
	// iDocument: 5689
	// }, {
	// period: '2011 Q1',
	// iPortal: 6810,
	// iBusiness: 1914,
	// iDocument: 2293
	// }, {
	// period: '2011 Q2',
	// iPortal: 5670,
	// iBusiness: 4293,
	// iDocument: 1881
	// }, {
	// period: '2011 Q3',
	// iPortal: 4820,
	// iBusiness: 3795,
	// iDocument: 1588
	// }, {
	// period: '2011 Q4',
	// iPortal: 15073,
	// iBusiness: 5967,
	// iDocument: 5175
	// }, {
	// period: '2012 Q1',
	// iPortal: 10687,
	// iBusiness: 4460,
	// iDocument: 2028
	// }, {
	// period: '2012 Q2',
	// iPortal: 8432,
	// iBusiness: 5713,
	// iDocument: 1791
	// }],
	// xkey: 'period',
	// ykeys: ['iPortal', 'iBusiness', 'iDocument'],
	// labels: ['iPortal', 'iBusiness', 'iDocument'],
	// pointSize: 2,
	// hideHover: 'auto',
	// resize: true
	// });

	// Morris.Donut({
	// element: 'morris-donut-chart',
	// data: [{
	// label: "iPhone",
	// value: 12
	// }, {
	// label: "iPad",
	// value: 30
	// }, {
	// label: "Phone",
	// value: 20
	// }, {
	// label: "Pad",
	// value: 20
	// }
	//        
	// ],
	// resize: true
	// });

});

function buildData(data) {
	var result = [];
	var appKeys = [];
	
	for ( var i = 0; i < data.length; i++) {
		var appId = data[i].appidentify;
		var appName = data[i].appName;
		if (!appName )
			appName=appId;
		
		var period =data[i].year + '-' + data[i].month + '-' + data[i].day;
		var item = null;
		for ( var j = 0; j < result.length; j++) {
			if (result[j].period == period) {
				item = result[j];
				break;
			}
		}
		if (!item) {
			item = {
				period : period
			};
			result.push(item);
		}
		var key = appId;
		var hasKey = false;
		for(var k=0;k<appKeys.length;k++){
			var kItem = appKeys[k];
			if(kItem.key == key){
				hasKey = true;
				break;
			}
		}
		if(!hasKey){
			appKeys.push({key:key,label:appName});
		}
		//if (!item[data[i].appidentify])
		item[data[i].appidentify] = data[i].num;
		//item[data[i].appidentify] += data[i].num;
	}
	var chartKey = [];
	var chartLabel = [];
	for(var i=0;i<appKeys.length;i++){
		chartKey.push(appKeys[i].key);
		chartLabel.push(appKeys[i].label);
	}
	return [result,chartKey,chartLabel];
}

function loadFeedback(){
	// 用户反馈
	var basePath = $('#basePath').val();
	 $('#chat').children('li').remove();
	 $.post(basePath + '/feedBack!list.action?page=1&rows=50', function (data)
	 {
		 var rs = eval(data);
		 var lis="";
		 for ( var i = 0; i < rs.length; i++) {
			 //if(i%2==0){
				 lis+=  "<li class=\"left clearfix\"/>"
		             +"<span class='chat-img pull-left'>"
		             +"  <img width='48px' height='48px' src=\"appIcon/userIcon/"+rs[i].id+"\" alt=\"User Avatar\" class=\"img-circle\" />"
		            	 +"  </span>"
		            	 +"  <div class=\"chat-body clearfix\">"
		            	 +"    <div class=\"header\">"
		            	 +"      <strong class=\"primary-font\">&nbsp;&nbsp;"+rs[i].username+"</strong>"
		            	 +"      <small class=\"pull-right text-muted\">"
		            	 +"         <i class=\"fa fa-clock-o fa-fw\"></i> "+rs[i].feedtime
		            	 +"   </small>"
		            	 +"     </div>"
		            	 +"    <p>  &nbsp;&nbsp;"+rs[i].content+"</p>"
		            	 +" </div>"
		            	 +"   </li>" ;
			 /*}else{				 
				 lis+=  " <li class=\"right clearfix\">"
				 +" <span class=\"chat-img pull-right\">"
				 +"  <img src=\"http://placehold.it/50/FA6F57/fff\" alt=\"User Avatar\" class=\"img-circle\" />"
					 +"  </span>"
					 +" <div class=\"chat-body clearfix\">"
					 +"  <div class=\"header\">"
				 +"      <small class=\" text-muted\">"
				 +"         <i class=\"fa fa-clock-o fa-fw\"></i> "+rs[i].feedtime
		            	 +" </small>"
				 +"     <strong class=\"pull-right primary-font\">"+rs[i].username+"</strong>"
				 +"  </div>"
				 +"   <p>"+rs[i].content+"</p>"
				 +"</div>"
				 +"</li>";
			 }			 
			 */

		 }		 
		 $('#chat').html(lis);		 
	 });
}

