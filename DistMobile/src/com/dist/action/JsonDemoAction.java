package com.dist.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.ParentPackage;

import com.alibaba.fastjson.JSON;
import com.dist.action.admin.BasicAction;
import com.dist.entity.SUsers;
import com.dist.service.UserServiceI;
import com.opensymphony.xwork2.ModelDriven;

@ParentPackage("json")
public class JsonDemoAction extends BasicAction implements ModelDriven<SUsers> {

	private static final long serialVersionUID = 105155412743741566L;

	private UserServiceI userServiceImpl;

	private SUsers user;

	public SUsers getModel() {
		if (user == null)
			user = new SUsers();
		return user;
	}

	public SUsers getUser() {
		return user;
	}

	public String getUserName() throws Exception {
		return SUCCESS;
	}

	public String goView() throws Exception {
		return SUCCESS;
	}

	public void login() throws Exception {
		try {
//			HttpServletResponse response = ServletActionContext.getResponse();

			Map<String, Object> myMap = new HashMap<String, Object>();
//			myMap.put("age", user.getAge());
//			myMap.put("username", user.getName());
//			myMap.put("job", user.getJob());
//			myMap.put("sex", user.getSex());
//			getResponse().getWriter().print(JSON.toJSONString(myMap));
			user.setLoginName("hhhhhhhhhaahhahha");
//			userServiceImpl.saveUser(user);
			List<SUsers> listU = userServiceImpl.findUser("from SUsers");
			//System.out.println("login返回结果"+JSON.toJSONString(listU));
//			ServletActionContext.getResponse().setCharacterEncoding("utf-8");
//			解决乱码的问题
			getResponse().setContentType("text/html;charset=utf-8");
			getResponse().getWriter().print(JSON.toJSONString(listU));
			
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
//	直接向请求端返回json结果
	public void androidJson() throws Exception {
		try {
			//System.out.println("username :" + user.getName());
			
			List<SUsers> listU = userServiceImpl.findUser("from SUsers");
			
			String result = JSON.toJSONString(listU);
//			解决乱码的问题
			getResponse().setContentType("text/html;charset=utf-8");
			getResponse().getWriter().print(result);
			//System.out.println(result);
			
			List<SUsers> listUser = JSON.parseArray(result, SUsers.class);
			//System.out.println(listUser.size());
			//System.out.println("androidJson返回结果："+JSON.toJSONString(listU));

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void setUser(SUsers user) {
		this.user = user;
	}

	public UserServiceI getUserServiceImpl() {
		return userServiceImpl;
	}

	@Resource
	public void setUserServiceImpl(UserServiceI userServiceImpl) {
		this.userServiceImpl = userServiceImpl;
	}

	public static void main(String[] args) {
		String jsonStr = "";
		Map<String, Object> myMap = new HashMap<String, Object>();
		myMap.put("age", "1");
		myMap.put("username", "2");
		System.out.print(JSON.toJSONString(myMap));

	}

}