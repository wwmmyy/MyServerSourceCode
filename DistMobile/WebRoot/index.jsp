<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="/disttags" prefix="dist"%>
<%
	String server = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort();
	String contextPath = request.getContextPath();
	String basePath = server + contextPath;
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
	<dist:headinclude/>
  </head>
  
  <body>
  	<div>
  		<input id="contextPath" type="hidden" value="<%=contextPath%>">
  		<input id="basePath" type="hidden" value="<%=basePath%>">
  	<div/>
    <div id="wrapper">
    	<dist:navigator/>
    	<div id="page-wrapper">
    		
    		<div class="row">
                <div class="col-lg-12">
                  <div class="dist-breadcrumb-container">
               <ol class="breadcrumb">
                  <li class="active">概览</li>
                  <li><a href="index_sysbackup.html">备份</a></li>
                  <li><a href="#">Database</a></li>
                  <li><a href="#">Openfire</a></li>
               </ol>
            </div>
                </div>
                <!-- /.col-lg-12 -->
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
                                <div class="btn-group">
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
                                        <a href="#">
                                            <i class="fa fa-refresh fa-fw"></i> 刷新
                                        </a>
                                    </li>
                                </ul>
                            </div>
                        </div>
                        <!-- /.panel-heading -->
                        <div class="panel-body">
                            <ul class="chat">
                                <li class="left clearfix">
                                    <span class="chat-img pull-left">
                                        <img src="http://placehold.it/50/55C1E7/fff" alt="User Avatar" class="img-circle" />
                                    </span>
                                    <div class="chat-body clearfix">
                                        <div class="header">
                                            <strong class="primary-font">李光</strong>
                                            <small class="pull-right text-muted">
                                                <i class="fa fa-clock-o fa-fw"></i> 2015/03/08 10:12:23
                                            </small>
                                        </div>
                                        <p>审批方便多了，是否可以在审批时加入指纹识别的功能？输字太麻烦。另外，能打开CAD材料吗？多一些审批依据就更好了。</p>
                                    </div>
                                </li>
                                <li class="right clearfix">
                                    <span class="chat-img pull-right">
                                        <img src="http://placehold.it/50/FA6F57/fff" alt="User Avatar" class="img-circle" />
                                    </span>
                                    <div class="chat-body clearfix">
                                        <div class="header">
                                            <small class=" text-muted">
                                                <i class="fa fa-clock-o fa-fw"></i> 2015/03/04 10:12:23</small>
                                            <strong class="pull-right primary-font">陈胜</strong>
                                        </div>
                                        <p>第一次用，很不错。以后在哪能都办公了，赞一个。</p>
                                    </div>
                                </li>
                                <li class="left clearfix">
                                    <span class="chat-img pull-left">
                                        <img src="http://placehold.it/50/55C1E7/fff" alt="User Avatar" class="img-circle" />
                                    </span>
                                    <div class="chat-body clearfix">
                                        <div class="header">
                                            <strong class="primary-font">张诚武</strong>
                                            <small class="pull-right text-muted">
                                                <i class="fa fa-clock-o fa-fw"></i> 2015/03/04 09:23:14</small>
                                        </div>
                                        <p>遇到问题可以在规划圈里直接向领导请示，办事效率大大提高呀。</p>
                                    </div>
                                </li>
                                <li class="right clearfix">
                                    <span class="chat-img pull-right">
                                        <img src="http://placehold.it/50/FA6F57/fff" alt="User Avatar" class="img-circle" />
                                    </span>
                                    <div class="chat-body clearfix">
                                        <div class="header">
                                            <small class=" text-muted">
                                                <i class="fa fa-clock-o fa-fw"></i> 2015/03/03 16:33:10</small>
                                            <strong class="pull-right primary-font">黄庆</strong>
                                        </div>
                                        <p>没想到移动互联的发展这么快，咱们搞规划的也能这样工作了。</p>
                                    </div>
                                </li>
                            </ul>
                        </div>
                        <!-- /.panel-body -->
                        <div class="panel-footer">
                            <div class="input-group">
                                <input id="btn-input" type="text" class="form-control input-sm" placeholder="输入您的回复" />
                                <span class="input-group-btn">
                                    <button class="btn btn-warning btn-sm" id="btn-chat">发送</button>
                                </span>
                            </div>
                        </div>
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
                            <a href="#" class="btn btn-default btn-block">查看详细</a>
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
	<dist:footerinclude/>
	
	<script src="resources/bower_components/raphael/raphael-min.js"></script>
    <script src="resources/bower_components/morrisjs/morris.min.js"></script>
    <script src="resources/js/morris-data.js"></script>
    
  </body>
</html>
