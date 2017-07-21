package com.dist.tag;

import java.io.IOException;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;
import javax.wsdl.Output;

public class HeadInclude extends TagSupport {
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 2232768696605029812L;

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
			
			out.print("<meta charset=\"utf-8\">");
			out.print("<meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge\">");
			out.print("<meta name=\"viewport\" content=\"width=device-width, initial-scale=1\">");
			out.print("<meta name=\"description\" content=\"\">");
			out.print("<meta name=\"author\" content=\"\">");

			out.print("<title>DIST-MOBILE</title>");
			out.print("<link href=\""+path+"resources/bower_components/bootstrap/dist/css/bootstrap.min.css\" rel=\"stylesheet\">");
			out.print("<link href=\""+path+"resources/bower_components/metisMenu/dist/metisMenu.min.css\" rel=\"stylesheet\">");
			out.print("<link href=\""+path+"resources/dist/css/timeline.css\" rel=\"stylesheet\">");
			out.print("<link href=\""+path+"resources/dist/css/sb-admin-2.css\" rel=\"stylesheet\">");
			out.print("<link href=\""+path+"resources/bower_components/morrisjs/morris.css\" rel=\"stylesheet\">");
			out.print("<link href=\""+path+"resources/bower_components/font-awesome/css/font-awesome.min.css\" rel=\"stylesheet\" type=\"text/css\">");
			out.print("<link href=\""+path+"resources/dist/css/dist-mobile.css\" rel=\"stylesheet\" type=\"text/css\" />");
			out.print("<script src=\""+path+"resources/bower_components/jquery/dist/jquery.min.js\"></script>");

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		//
		return SKIP_BODY;
	}

	@Override
	public int doEndTag() throws JspException {
		return EVAL_PAGE;
	}
	
}
