package com.dist.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URISyntaxException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//import org.jivesoftware.admin.AuthCheckFilter;
//import org.jivesoftware.smackx.filetransfer.FileTransferManager;
//import org.xmpp.component.Component;
//import org.xmpp.component.ComponentException;
//import org.xmpp.component.ComponentManager;
//import org.xmpp.component.ComponentManagerFactory;
//import org.xmpp.packet.JID;
//import org.xmpp.packet.Message;
//import org.xmpp.packet.Packet;


public class TestServlet extends HttpServlet {

/**
 * Constructor of the object.
 */
public TestServlet() {
	super();
}

/**
 * Destruction of the servlet. <br>
 */
public void destroy() {
	super.destroy(); // Just puts "destroy" string in log
	// Put your code here
}

public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	doPost(request, response);
}

public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

//	System.out.println("one: " + request.getParameter("one"));
//	response.getWriter().print(JSON.toJSONString(JSON.toJSONString(request.getParameter("one"))));
	
	try {

		this.getClass().getResource("");
		this.getClass().getClassLoader().getResource("");
		this.getClass().getResource("/");
		Thread.currentThread().getContextClassLoader().getResource("/com/dist").toURI().getPath();
		request.getRequestDispatcher("/error.jsp").forward(request, response); 
//		request.getRequestDispatcher("WEB-INF/template/login.html").forward(request, response); 


//		 response.sendRedirect("/success.jsp");

	} catch (URISyntaxException e) {
		e.printStackTrace();
	}

}

/**
 * Initialization of the servlet. <br>
 * 
 * @throws ServletException
 *             if an error occurs
 */
public void init() throws ServletException {
	// Put your code here
}

} 














//implements Component {
//	private static final long serialVersionUID = -5404916983906926869L;
//
//	private static final String SERVICE_NAME = "servlet/TestServlet";//服务名
//	private ComponentManager componentManager;//组件管理
//	private static final String COMPONENTNAME = "sendservlet";//组件名
//	private static final String domain = "@chat.com";//服务器域名
//
//	@Override
//	public void init(ServletConfig config) throws ServletException {
//		super.init(config);
//
//		//给验证器添加排除的路径
//		AuthCheckFilter.addExclude(SERVICE_NAME);
//		System.out.println("aaaaaaaaaa");
//		//注册组件
//		componentManager = ComponentManagerFactory.getComponentManager();
//		try {
//			componentManager.addComponent(COMPONENTNAME, this);
//		} catch (Exception err) {
//			err.printStackTrace();
//		}
//	}
//
//	/**
//	 * servlet的get方法,在这里发送信息
//	 * */
//	@Override
//	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		try {
//			String massg=request.getParameter("massg");
//			//构建信息
//			Message msg = new Message();
//			msg.setBody(massg);
//			msg.setFrom("wwmmyy" + domain);//发信人
//			msg.setTo("aa" + domain);//接收人
//			msg.setType(Message.Type.chat);//为聊天信息
//
//			componentManager.sendPacket(this, msg);//发关
//			
//			
//		} catch (ComponentException err) {
//			err.printStackTrace();
//		}
//
//		response.setContentType("text/html;setchar=UTF-8");
//		PrintWriter out = new PrintWriter(response.getOutputStream());
//		out.print("GET Methodx");
//		out.flush();
//	}
//
//	/**
//	 * servlet的post方法，直接调用get方法
//	 * */
//	@Override
//	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		doGet(request, response);
//	}
//
//	@Override
//	public void destroy() {
//		super.destroy();
//		if (componentManager != null) {
//			try {
//				componentManager.removeComponent(COMPONENTNAME);
//			} catch (Exception err) {
//				err.printStackTrace();
//			}
//		}
//		componentManager = null;
//		AuthCheckFilter.addExclude(SERVICE_NAME);
//	}
//
//	@Override
//	public String getDescription() {
//		return "没有描述..";
//	}
//
//	@Override
//	public String getName() {
//		return COMPONENTNAME;
//	}
//
//	@Override
//	public void initialize(JID jid, ComponentManager comManager) throws ComponentException {
//
//	}
//
//	@Override
//	public void processPacket(Packet arg0) {
//
//	}
//
//	@Override
//	public void shutdown() {
//	}
//
//	@Override
//	public void start() {
//	}
//}













