package com.dist.action.admin;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.springframework.stereotype.Controller;

import com.alibaba.fastjson.JSON;
import com.opensymphony.xwork2.ActionSupport;

@Controller
public class BasicAction extends ActionSupport {

	private static final long serialVersionUID = -7565850715613871606L;
	
	  protected HttpServletResponse response;

	public HttpServletResponse getResponse() {
		return ServletActionContext.getResponse();
	}

	
	public  void writeJson(Object boject){		
		try {			
			String json=JSON.toJSONStringWithDateFormat(boject, "yyyy-MM-dd HH:mm:ss");
			getResponse().setContentType("application/json;charset=utf-8");
			getResponse().getWriter().write(json);
			getResponse().getWriter().flush();
			getResponse().getWriter().close();			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

}