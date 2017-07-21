
<%@ page language="java" import="java.util.*,java.lang.*"
	pageEncoding="utf-8" isELIgnored="false"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib uri="/disttags" prefix="dist"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<dist:headinclude />
<script src="resources/bower_components/raphael/raphael-min.js"></script>
    <script src="resources/bower_components/morrisjs/morris.min.js"></script>
    <script src="resources/js/morris-data.js"></script>

</head>
<body>
	<!-- model field end -->
	<div id="wrapper">
		<dist:navigator selectedIndex="${selectedIndex}" />
		<div id="page-wrapper">
    		
    		<div class="row">
                <div class="col-lg-12">
                  <div class="dist-breadcrumb-container">
               <ol class="breadcrumb">
                  <li class="active">概览</li>
                
                  <li><a target="_black" href="druid">Database</a></li>
                  <li><a target="_black" href="http://192.168.1.239:9090/login.jsp">Openfire</a></li>
               </ol>
            </div>
                </div>
                <!-- /.col-lg-12    <li><a href="index_sysbackup.html">备份</a></li>-->
            </div>
            <!-- /.row -->
            
            <!-- /.row -->
            <div class="row">
                <div class="col-lg-8">
                    <div class="alert alert-warning alert-dismissible" role="alert">
                      <button type="button" class="close" data-dismiss="alert"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
                      <i class="fa fa-clock-o"></i> 09:20 有新设备申请，请及时受理。
                    </div>
                    <div class="alert alert-danger alert-dismissible" role="alert">
                      <button type="button" class="close" data-dismiss="alert"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
                      <i class="fa fa-clock-o"></i> 06:20 重启服务。
                    </div> 
                    <div class="alert alert-info alert-dismissible" role="alert">
                      <button type="button" class="close" data-dismiss="alert"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
                      <i class="fa fa-clock-o"></i> 04:20 清理临时文件。
                    </div>
                    <div class="alert alert-success alert-dismissible" role="alert">
                      <button type="button" class="close" data-dismiss="alert"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
                      <i class="fa fa-clock-o"></i> 03:15 <strong>成功!</strong> 第9次自动备份。
                    </div>
                
                    <div class="panel panel-default">
                        <div class="panel-heading">
                            <i class="fa fa-bar-chart-o fa-fw"></i> 系统活跃情况
                            <div class="pull-right">
                                <!-- <div class="btn-group">
                                    <button type="button" class="btn btn-default btn-xs dropdown-toggle" data-toggle="dropdown">时间
                                        <span class="caret"></span>
                                    </button>
                                    <ul class="dropdown-menu pull-right" role="menu">
                                        <li><a href="#">本月</a>
                                        </li>
                                        <li><a href="#">本季度</a>
                                        </li>
                                        <li><a href="#">本年</a>
                                        </li>
                                        <li class="divider"></li>
                                        <li><a href="#">更多</a>
                                        </li>
                                    </ul>
                                </div>
                                 -->
                            </div>
                        </div>
                        <!-- /.panel-heading -->
                        <div class="panel-body">
                            <div id="morris-area-chart"></div>
                        </div>
                        <!-- /.panel-body -->
                    </div>
                    
                </div>
                <!-- /.col-lg-8 -->
                <div class="col-lg-4">
                    <div class="chat-panel panel panel-default">
                        <div class="panel-heading">
                            <i class="fa fa-comments fa-fw"></i>用户反馈
                            <div class="btn-group pull-right">
                                <button type="button" class="btn btn-default btn-xs dropdown-toggle" data-toggle="dropdown">
                                    <i class="fa fa-chevron-down"></i>
                                </button>
                                <ul class="dropdown-menu slidedown">
                                    <li>
                                        <a href="#" onclick="loadFeedback()">
                                            <i id="btnRefershFeedback" class="fa fa-refresh fa-fw"></i> 刷新
                                        </a>
                                    </li>
                                </ul>
                            </div>
                        </div>
                        <!-- /.panel-heading -->
                        <div class="panel-body">
                            <ul id="chat" class="chat">
                                
                            </ul>
                        </div>
                        <!-- /.panel-body 
                        <div class="panel-footer">
                            <div class="input-group">
                                <input id="btn-input" type="text" class="form-control input-sm" placeholder="输入您的回复" />
                                <span class="input-group-btn">
                                    <button class="btn btn-warning btn-sm" id="btn-chat">发送</button>
                                </span>
                            </div>
                        </div>-->
                        <!-- /.panel-footer -->
                    </div>
                    <!-- /.panel .chat-panel -->
      
                    <!-- /.panel -->
                    <div class="panel panel-default">
                        <div class="panel-heading">
                            <i class="fa fa-bar-chart-o fa-fw"></i> 设备
                        </div>
                        <div class="panel-body">
                            <div id="morris-donut-chart"></div>
                            <a href="device!index.action?type=" class="btn btn-default btn-block">查看详细</a>
                        </div>
                        <!-- /.panel-body -->
                    </div>
                    <!-- /.panel -->
                    
                </div>
                <!-- /.col-lg-4 -->
            </div>
            <!-- /.row -->
    		
    	</div>
	</div>
	<dist:footerinclude />
</body>
</html>