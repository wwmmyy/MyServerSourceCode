package com.dist.action;

import java.util.List;

import javax.annotation.Resource;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.hibernate.annotations.Index;
import org.omg.PortableInterceptor.SYSTEM_EXCEPTION;
import org.springframework.stereotype.Controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.dist.action.admin.BasicAction;
import com.dist.entity.SDevice;
import com.dist.entity.SDevicetype;
import com.dist.entity.SOrganization;
import com.dist.entity.SPermissions;
import com.dist.entity.SRole;
import com.dist.entity.SRolepermissions;
import com.dist.entity.SSystemuser;
import com.dist.entity.SUserroles;
import com.dist.entity.SUsers;
import com.dist.pagentity.Json;
import com.dist.service.SDeviceServiceI;
import com.dist.service.SDevicetypeServiceI;
import com.dist.service.SOrganizationServiceI;
import com.dist.service.SystemuserServiceI;
import com.dist.service.UserServiceI;
import com.dist.util.Encrypt;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ModelDriven;

@Controller
public class UserAction extends BasicAction implements ModelDriven<SUsers> {

	private static final long serialVersionUID = 105155412743741566L;

	private SUsers user = new SUsers();;

	private String userid;
	private String type;

	private UserServiceI userServiceImpl;

	private List<SUsers> listUser;

	
	
	

	private SystemuserServiceI systemuserService;

	@Resource
	public void setSystemuserServiceImpl(SystemuserServiceI systemuserService) {
		this.systemuserService = systemuserService;
	}

	
	
	
	
	
	
	
	
	// 调用此服务主要是因为在主页面新增用户，下拉选择用户组织机构时需要的
	private SOrganizationServiceI organizationService;
	private List<SOrganization> organizationtype;
	private String selectedIndex="4.1";
	
	public String getSelectedIndex() {
		return selectedIndex;
	}

	public void setSelectedIndex(String selectedIndex) {
		this.selectedIndex = selectedIndex;
	}

	public SOrganizationServiceI getOrganizationService() {
		return organizationService;
	}

	public void setOrganizationService(SOrganizationServiceI organizationService) {
		this.organizationService = organizationService;
	}

	@Resource
	public void setUserServiceImpl(UserServiceI userServiceImpl) {
		this.userServiceImpl = userServiceImpl;
	}

	public SUsers getModel() {
		// if (user == null)

		return user;
	}

	ActionContext usersession = ActionContext.getContext();

	public String login() {
		SUsers u = userServiceImpl.login(user);
		if (u != null) {
			usersession.getSession().put("userid", u.getId());
			usersession.getSession().put("loginName", u.getLoginName());
			usersession.getSession().put("name", u.getName());
			usersession.getSession().put("password", u.getPassword());
			return "success";
		} else {
			return ERROR;
		}
	}

	
	
	
	
	
	
	
	
	
	
	/**
	 * 
	 * 系统管理员登录
	 * @return
	 */
	public String loginsys() {
		SSystemuser suser=new SSystemuser();
		suser.setLoginName(user.getLoginName());
		suser.setPassword(user.getPassword());
		
//		//System.out.println("得到的登录查询结果为：：；"+JSON.toJSONString( systemuserService.login(suser)));
		
		
		if(suser.getLoginName()==null||suser.getPassword()==null||suser.getLoginName().equals("")||suser.getPassword().equals("")){
			return ERROR;
		}
		
		SSystemuser u = systemuserService.login(suser);
		if (u != null) {
			usersession.getSession().put("userid", u.getId());
			usersession.getSession().put("loginName", u.getLoginName());
			usersession.getSession().put("password", u.getPassword());
			return "success";
		} else {
			return ERROR;
		}
	}
	
	
	
	
	
	public String suserPasSet() {
		if(user.getPassword()==null||user.getPassword().equals("")
				||user.getHandPassword()==null||user.getHandPassword().equals("")){//用user.password存储旧密码user.handpassword存储新密码
					return "set";
			}else {
				SSystemuser suser =null;
				if(user.getId()!=null){suser =systemuserService.findById(user.getId());	
				}
				if(suser!=null){
					if(Encrypt.e(user.getPassword().trim()).equals(suser.getPassword().trim())){//说明用户输入的密码正确
						suser.setPassword(Encrypt.e(user.getHandPassword()));
						systemuserService.save(suser);
						return "setsuccess";
					}else{
						return "set";
					}
					
				}
				
				return "set";
			}			
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	// 获取用户列表
	public String userlist() {

		listUser = userServiceImpl.findAll();
		organizationtype = organizationService.findAll();
		//System.out.println("listUser.size()："+listUser.size()+"user.getStatus()："+user.getStatus());
		if(user.getStatus()!=null){
			for(int i=0;i<listUser.size();i++){
				SUsers su=listUser.get(i);
				//System.out.println("su.getStatus()："+su.getStatus()+";user.getStatus()："+user.getStatus());
				if(su.getStatus()!=user.getStatus())
					listUser.remove(i);
			}
		}
		return "homeIndex";
	}

	// 添加用户
	// 添加完成之后重新跳会到用户页面
	public String save() {
//		//System.out.println("获取到的添加信息为：" + user.getName() + user.getPassword()
//				+ user.getEmail() + user.getPhoneNumber());
		SOrganization  tempdetype=organizationService.findById(user.getSOrganization().getId());
		user.setSOrganization(tempdetype);
		userServiceImpl.saveUser(user);

		// 查询出添加的所有的用户
		listUser = userServiceImpl.findAll();
		organizationtype = organizationService.findAll();
		return "homeIndex";
	}
	
	
	
	
	public void getAllUser(){
		listUser = userServiceImpl.findAll();
		for(int i=0;i<listUser.size();i++){
			SUsers tempuser=new SUsers();
			if(listUser.get(i).getSOrganization()!=null)
				tempuser.setPhoneNumber(listUser.get(i).getSOrganization().getId());
			else tempuser.setPhoneNumber("");
			tempuser.setId(listUser.get(i).getId());
			tempuser.setName(listUser.get(i).getName());
			listUser.set(i, tempuser);
		}
		JSONObject obj=new JSONObject();
		obj.put("result", JSON.toJSONString(listUser));
		obj.put("state", "true");
//       //System.out.println("得到的user  json 为：："+ JSON.toJSONString(listUser));
		super.writeJson(obj);
	}
	/**
	 * 更新用户
	 * 
	 * @return
	 */
	public void updateUser() {

		SUsers d = userServiceImpl.findById(user.getId());
		d.setName(user.getName());
		d.setEmail(user.getEmail());
		d.setLoginName(user.getLoginName());
		d.setPassword(user.getPassword());
		d.setPhoneNumber(user.getPhoneNumber());
		d.setSex(user.getSex());
		userServiceImpl.updateUser(d);

		Json j = new Json();
		j.setSuccess(true);
		j.setMsg("修改成功！");
		writeJson(j);
	}

	public void remove() {
		//System.out.println("获取到的userid:::" + userid);
		userServiceImpl.deleteById(userid);

		// listUser = userServiceImpl.findAll();
		Json j = new Json();
		j.setSuccess(true);
		j.setMsg("删除成功！");
		writeJson(j);
	}

	public void reg() {
		// Json j = new Json();
		// try {
		// userServiceImpl.saveUser(user);
		// j.setSuccess(true);
		// j.setMsg("注册成功！");
		// } catch (Exception e) {
		// j.setMsg(e.getMessage());
		// }
		// super.writeJson(j);

		Json j = new Json();
		SUsers tuser = userServiceImpl.saveUser(user);
		if (tuser != null) {
			user = tuser;
			j.setSuccess(true);
			j.setMsg("注册成功！");
		} else {
			j.setSuccess(true);
			j.setMsg("注册失败！");
		}
		super.writeJson(j);
	}

	public void add() {
		Json j = new Json();
		SUsers tuser = userServiceImpl.saveUser(user);
		if (tuser != null) {
			j.setSuccess(true);
			j.setMsg("添加成功！");
			j.setObj(tuser);
		} else {
			j.setSuccess(true);
			j.setMsg("添加失败！");
		}
		super.writeJson(j);
	}

	// 根据配置文件会跳转到 user_list.jsp
	public String list() {
		// listUser = userServiceImpl.findUser("from Userinfo");

		try {
			List<SUsers> listU = userServiceImpl.datagrid(2, 3);

			String result = JSON.toJSONString(listU);
			// 解决乱码的问题
			// getResponse().setContentType("text/html;charset=utf-8");
			// getResponse().getWriter().print(result);
			List<SUsers> listUser = JSON.parseArray(result, SUsers.class);
			//System.out.println(listUser.size());
//			//System.out.println("androidJson返回结果：" + JSON.toJSONString(listU));
			
		} catch (Exception e) {
			e.printStackTrace();
		}

		return "list";
	}

	public String userService() {

		if (type.trim().equals("add")) {
			return method2();
		} else if (type.trim().equals("check")) {
			return list();

		} else if (type.trim().equals("jsoninfo")) {
			try {
				androidJson();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else if (type.trim().equals("register")) {
			return "jsonDemo";
		} else {
			return "error";
		}

		// String name=ServletActionContext.getRequest().getParameter("name");

		return null;
	}

	// 直接向请求端返回json结果
	public void androidJson() throws Exception {
		try {
			// List<SUsers> listU = userServiceImpl.findUser("from SUsers");
			List<SUsers> listU = userServiceImpl.findAll();
			String result = JSON.toJSONString(listU);
			// 解决乱码的问题
			getResponse().setContentType("text/html;charset=utf-8");
			getResponse().getWriter().print(result);
			List<SUsers> listUser = JSON.parseArray(result, SUsers.class);
			//System.out.println(listUser.size());
//			//System.out.println("androidJson返回结果：" + JSON.toJSONString(listU));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// 当然，简单的通过默认的方式来进行配置不能完全满足实际项目的需要。所幸，convention的零配置是非常灵活的。
	// 通过@Action注释
	// package com.example.web;
	//
	// import com.opensymphony.xwork2.Action;
	// import com.opensymphony.xwork2.ActionSupport;
	//
	// public class HelloAction extends ActionSupport {
	// @Action("action1")
	// public String method1() {
	// return SUCCESS;
	// }
	//
	// @Action("/user/action2")
	// public String method2() {
	// return SUCCESS;
	// }
	// }

	// 方法名 默认调用路径 默认映射路径
	// method1 /hello!method1.action . /WEB-INF/content/hello.jsp
	// method2 /hello!method2.action. /WEB-INF/content/hello.jsp
	// 通过@Action注释后
	// 方法名 @Action注释后调用路径 @Action注释 后映射路径
	// method1 /action1!method1.action. /WEB-INF/content/action1.jsp
	// method1 /user/action2!method2.action /WEB-INF/content/user/action2.jsp

	// @Action("/user/action2") public String method2() { }
	// 那么访问时 /user/action2!method2.action 对应 /WEB-INF/template/user/action2.jsp
	// /admin/user!method2.action 对应 /WEB-INF/template/admin/user.jsp
	@Action("/admin/user")
	public String method2() {
		return SUCCESS;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	// 经测试必须添加set方法，要不然无法获取传过来的值
	public SUsers getUser() {
		return user;
	}

	public void setUser(SUsers user) {
		this.user = user;
	}

	public void setListUser(List<SUsers> listUser) {
		this.listUser = listUser;
	}

	public List<SUsers> getListUser() {
		return listUser;
	}

	public List<SOrganization> getOrganizationtype() {
		return organizationtype;
	}

	public void setOrganizationtype(List<SOrganization> organizationtype) {
		this.organizationtype = organizationtype;
	}

	//
}