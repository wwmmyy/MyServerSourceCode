package com.dist.tag;

import java.io.Console;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;

import org.apache.commons.lang.StringEscapeUtils;
import org.apache.struts2.ServletActionContext;

public class Navigator extends TagSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6440470030359005875L;

	private int msgCount = 4;
	private int evtCount = 2;

	
	private String selectedIndex;
	
	public String getSelectedIndex() {
		return selectedIndex;
	}

	public void setSelectedIndex(String selectedIndex) {
		this.selectedIndex = selectedIndex;
	}

	@Override
	public int doStartTag() throws JspException {

		try {
			
			JspWriter out = this.pageContext.getOut();
			HttpServletRequest hsr = (HttpServletRequest)this.pageContext.getRequest();
			
			System.out.println(this.selectedIndex);
			
			out.print("<nav class=\"navbar navbar-default navbar-static-top\" role=\"navigation\">"
					+ "<div class=\"navbar-header\">"
					+ "<button type=\"button\" class=\"navbar-toggle\" data-toggle=\"collapse\" data-target=\".navbar-collapse\">"
					+ "<span class=\"sr-only\"></span>"
					+ "<span class=\"icon-bar\"></span>"
					+ "<span class=\"icon-bar\"></span>"
					+ "<span class=\"icon-bar\"></span>"
					+ "</button>"
					+ "<span class=\"navbar-brand\">DIST-Mobile</span>"
					+ "</div>");

			// navbar-header

			out.print("<ul class=\"nav navbar-top-links navbar-right\">");
			out.print(renderSysMessagesHTML());
			out.print(renderEventHTML());
			out.print("<li class=\"dropdown\">"
					+ "<a class=\"dropdown-toggle dist-white\" data-toggle=\"dropdown\" href=\"#\">"
					+ "<i class=\"fa fa-user fa-fw\"></i>  <i class=\"fa fa-caret-down\"></i></a>"
					+ "<ul class=\"dropdown-menu dropdown-user\">"
//					+ "<li><a href=\"#\"><i class=\"fa fa-user fa-fw\"></i> 账户信息</a></li>"
					+ "<li><a href=\"user!suserPasSet.action?user.id="
						+ServletActionContext.getRequest().getSession() .getAttribute("userid") 
						+"\"><i class=\"fa fa-gear fa-fw\"></i> 密码设置</a></li><li class=\"divider\"></li>"
					+ "<li><a href=\"index.html\"><i class=\"fa fa-sign-out fa-fw\"></i> 注销</a></li></ul></li>");

			out.print("</ul>");
			// sidebar
			out.print(renderSideBarHTML());
			out.print("</nav>");

		} catch (Exception e) {
			throw new JspException(e.getMessage());
		}
		return SKIP_BODY;
	}

	private String renderSysMessagesHTML() {

		String[] msgs = new String[] { "完成第9次自动备份", "清理临时文件", "重启服务" };
		String[] senders = new String[] { "系统", "系统", "系统" };
		String[] times = new String[] { "03:15", "04:20", "06:00" };

		StringBuilder sb = new StringBuilder(
				"<li class=\"dropdown\"><a class=\"dropdown-toggle dist-white\" data-toggle=\"dropdown\" href=\"#\"><i class=\"fa fa-bell fa-fw\"></i><span class=\"badge\">"
						+ msgCount
						+ "</span> <i class=\"fa fa-caret-down\"></i></a><ul class=\"dropdown-menu dropdown-messages\">");
		for (int i = 0; i < msgs.length; i++) {
			String msgTitle = StringEscapeUtils.escapeHtml(msgs[i]);
			String msgSender = StringEscapeUtils.escapeHtml(senders[i]);
			sb.append("<li><a href=\"#\"><div><strong>");
			sb.append(msgSender);
			sb.append("</strong><span class=\"pull-right text-muted\"><em><i class=\"fa fa-clock-o\"></i> ");
			sb.append(times[i]);
			sb.append("</em></span></div><div>");
			sb.append(msgTitle);
			sb.append("</div></a></li><li class=\"divider\"></li>");
		}
		sb.append("<li><a class=\"text-center\" href=\"#\"><strong>查看所有消息</strong><i class=\"fa fa-angle-right\"></i></a></li></ul></li>");
		return sb.toString();
	}

	private String renderEventHTML() {
		String[] events = new String[] { "试用", "清理缓存" };
		String[] ratevals = new String[] { "40", "20" };

		StringBuilder sb = new StringBuilder(
				"<li class=\"dropdown\"><a class=\"dropdown-toggle dist-white\" data-toggle=\"dropdown\" href=\"#\"><i class=\"fa fa-tasks fa-fw\"></i><span class=\"badge\">"
						+ evtCount
						+ "</span> <i class=\"fa fa-caret-down\"></i></a><ul class=\"dropdown-menu dropdown-tasks\">");
		for (int i = 0; i < events.length; i++) {
			String msgTitle = StringEscapeUtils.escapeHtml(events[i]);
			sb.append("<li><a href=\"#\"><div><p><strong>"
					+ msgTitle
					+ "</strong><span class=\"pull-right text-muted\">"
					+ ratevals[i]
					+ "% </span></p>"
					+ "<div class=\"progress progress-striped active\">"
					+ "<div class=\"progress-bar progress-bar-success\" role=\"progressbar\" aria-valuenow=\""
					+ ratevals[i]
					+ "\" aria-valuemin=\"0\" aria-valuemax=\"100\" style=\"width: "
					+ ratevals[i]
					+ "%\">"
					+ "<span class=\"sr-only\">"
					+ ratevals[i]
					+ "% Complete (success)</span></div></div></div></a></li><li class=\"divider\"></li>");
		}
		sb.append("<li><a class=\"text-center\" href=\"#\"><strong>查看所有任务</strong><i class=\"fa fa-angle-right\"></i></a></li></ul></li>");
		return sb.toString();
	}

	private String renderSideBarHTML() {
		return "<div class=\"navbar-default sidebar\" role=\"navigation\">"
				+ "<div class=\"sidebar-nav navbar-collapse\">"
				+ "<ul class=\"nav dist-sidebar-menu\" id=\"side-menu\" select=\""+this.selectedIndex+"\">"
				+ "<li>"
				+ "<a href=\"dashboard!index.action\"><i class=\"fa fa-dashboard fa-fw\"></i> 仪表盘</a>"
				+ "</li><li>"
				+ "<a><i class=\"fa fa-tablet fa-fw\"></i> 设备<span class=\"fa arrow\"></span></a>"
				+ "<ul class=\"nav nav-second-level\">"
				+ "<li><a href=\"device!index.action?type=\">所有设备<span class=\"badge pull-right\"> "+
				ServletActionContext.getRequest().getSession() .getAttribute("total") +"</span></a></li>"
				+ "<li><a href=\"device!index.action?type=1\">可用<span class=\"badge pull-right\">"+
				ServletActionContext.getRequest().getSession() .getAttribute("normal") +"</span></a></li>"
				+ "<li><a href=\"device!index.action?type=3\">已禁用<span class=\"badge pull-right\">"+
				ServletActionContext.getRequest().getSession() .getAttribute("forbidden") +"</span></a></li>"
				+ "<li><a href=\"device!index.action?type=2\">已挂失<span class=\"badge pull-right\">"+
				ServletActionContext.getRequest().getSession() .getAttribute("lose") +"</span></a></li>"
				+ "<li><a href=\"device!index.action?type=0\">新设备<span class=\"badge pull-right\">"+
				ServletActionContext.getRequest().getSession() .getAttribute("pending") +"</span></a></li>"
				+ "</ul></li>"
				+ "<li><a href=\"applications!index.action\"><i class=\"fa fa-codepen fa-fw\"></i> 应用程序</a></li>"
//				+ "<li><a href=\"#\"><i class=\"fa fa-medkit fa-fw\"></i> 监控</a></li>"
				//+ "<li><a href=\"#\"><i class=\"fa fa-bell fa-fw\"></i> 消息</a></li>"
				+"<li><a href=\"#\"><i class=\"fa fa-cog fa-fw\"></i> 移动门户<span class=\"fa arrow\"></span></a>" 
				+"<ul class=\"nav nav-second-level\"><li><a href=\"snews!index.action?selectedIndex =\">发布订阅</a></li></ul></li>"
				+ "<li><a href=\"#\"><i class=\"fa fa-users fa-fw\"></i> 用户<span class=\"fa arrow\"></span></a>"
				+ "<ul class=\"nav nav-second-level\"><li><a href=\"organization!list.action\">组织机构</a></li>"
				+ "<li><a href=\"user!userlist.action\">用户列表</a></li></ul></li>"
//				+"<li><a href=\"#\"><i class=\"fa fa-cog fa-fw\"></i> 设置</a></li>" 
				+"</ul></div></div>";
	}

	@Override
	public int doEndTag() throws JspException {
		return EVAL_PAGE;
	}
}
