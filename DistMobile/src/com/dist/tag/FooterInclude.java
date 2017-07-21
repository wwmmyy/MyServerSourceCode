package com.dist.tag;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;

public class FooterInclude extends TagSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6467638061960869578L;

	@Override
	public int doStartTag() throws JspException {
		JspWriter out = this.pageContext.getOut();
		try {

			HttpServletRequest hsr = (HttpServletRequest)this.pageContext.getRequest();
			String str= hsr.getRequestURI().replace("WEB-INF/template/", "");
			int level=str.split("/").length-3;
			String path = "";
			for(int i=0;i<level;i++){
				path+="../";
			}

			
			out.print("<script src=\""+path+"resources/bower_components/bootstrap/dist/js/bootstrap.min.js\"></script>");
			out.print("<script src=\""+path+"resources/bower_components/metisMenu/dist/metisMenu.min.js\"></script>");
			out.print("<script src=\""+path+"resources/dist/js/sb-admin-2.js\"></script>");
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		return SKIP_BODY;
	}

	@Override
	public int doEndTag() throws JspException {
		return EVAL_PAGE;
	}

}
