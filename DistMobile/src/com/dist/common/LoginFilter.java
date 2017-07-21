//package com.dist.common;
//
//import java.io.IOException;
//import java.lang.reflect.Method;
//
//import javax.servlet.Filter;
//import javax.servlet.FilterChain;
//import javax.servlet.FilterConfig;
//import javax.servlet.RequestDispatcher;
//import javax.servlet.ServletException;
//import javax.servlet.ServletRequest;
//import javax.servlet.ServletResponse;
//import javax.servlet.http.HttpServlet;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import javax.servlet.http.HttpSession;
//
///**
// * 登录拦截器，与web中的xml配置相联系，但是目测只能拦截jsp之类的，无法拦截action请求处理
// * @author 王明远
// *
// */
//public class LoginFilter extends HttpServlet implements Filter {
//
//	/**
//	 * 
//	 */
//	private static final long serialVersionUID = 3870449246203279881L;
//
//	public void doFilter(ServletRequest req, ServletResponse resp,
//			FilterChain chain) throws IOException, ServletException {
//
//		HttpServletRequest request = (HttpServletRequest) req;
//		HttpServletResponse response = (HttpServletResponse) resp;
//		HttpSession session = request.getSession(true);
//
//		String url = request.getRequestURI();
//		String admin = (String) session.getAttribute("loginName");
//		System.out.println("成功拦截到外星人企图入侵网站后台   :  " + admin);
//		if (admin == null && url.indexOf("index.jsp") == -1 && url.indexOf("error_page_404.jsp") == -1 
////				&& !url.equals("/fiss/") && !url.equals("/fiss/admin.do")
//				) {
//			String location = "/index.html";
//			request.getRequestDispatcher(location).forward(request, response);
//
//			System.out.println("成功拦截到外星人企图入侵网站后台   :  " + url);
//			response.setHeader("Cache-Control", "no-store");
//			response.setDateHeader("Expires", 0);
//			response.setHeader("Pragma", "no-cache");
//		} else {
//			chain.doFilter(request, response);
//		}
//	}
//
//	public void init(FilterConfig arg0) throws ServletException {
//	}
//
//}