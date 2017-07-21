package com.dist.common;

import java.util.Map;

import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;
/**
 * 另一种拦截器，在struts.xml配置后，默认全部拦截，对于登录部分不需要拦截的，在struts.xml声明可以不拦截
 * @author 王明远
 *
 */
public class MyInterceptor extends AbstractInterceptor {
	/**
	 * 
	 */
	private static final long serialVersionUID = 8622708310000532124L;

	//拦截Action处理的拦截方法  
    public String intercept(ActionInvocation invocation) throws Exception {  
        // 取得请求相关的ActionContext实例  
        ActionContext ctx=invocation.getInvocationContext();  
        Map session=ctx.getSession();  
        //取出名为user的session属性  
       String userid=(String)session.get("userid");  
       //如果没有登陆，或者登陆所有的用户名不是aumy，都返回重新登陆  && loginName.equals("35")
       if(userid!=null ){  
    	   System.out.println("启动了拦截器MyInterceptor.java，登录成功userid：：：："+userid);
           return invocation.invoke();  
       }  
       		System.out.println("启动了拦截器MyInterceptor.java：：：："+userid);
       //没有登陆，将服务器提示设置成一个HttpServletRequest属性  
       ctx.put("tip","您还没有登录，请登陆系统"); 
       return "success";
//       return Action.LOGIN;          
   }  
}




 