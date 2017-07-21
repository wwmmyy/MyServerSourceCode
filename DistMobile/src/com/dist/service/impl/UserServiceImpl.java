package com.dist.service.impl;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;
import javax.jws.WebService;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import com.dist.dao.BasicDaoI;
import com.dist.entity.PSnsComment;
import com.dist.entity.PUsersubscription;
import com.dist.entity.SOrganization;
import com.dist.entity.SPermissions;
import com.dist.entity.SRole;
import com.dist.entity.SUserroles;
import com.dist.entity.SUsers;
import com.dist.service.UserServiceI;
import com.dist.util.Encrypt;

@Service
@WebService(endpointInterface = "com.dist.service.UserServiceI",serviceName="UserService")
public class UserServiceImpl implements UserServiceI {

	private BasicDaoI<SUsers> userDaoImpl;
	
//	private BasicDaoI<SOrganization> organizationDao;
	final Logger logger = Logger.getLogger(UserServiceImpl.class);  
//	private BasicDaoI<SRole> roleDao;
	
//	private BasicDaoI<SPermissions> permissionsDaoImpl;
	
//	@Resource
//	public void setPermissionsDao(BasicDaoI<SPermissions> permissionsDaoImpl) {
//		this.permissionsDaoImpl = permissionsDaoImpl;
//	}
	
	@Resource
	public void setUserDao(BasicDaoI<SUsers> userDaoImpl) {
		this.userDaoImpl = userDaoImpl;
	}
	
//	@Resource
//	public void setUserDao(BasicDaoI<SRole> roleImpl) {
//		this.roleDao = roleImpl;
//	}
	
//	@Resource
//	public void setOrganizationDao(BasicDaoI<SOrganization> organizationDapImpl) {
//		this.organizationDao = organizationDapImpl;
//	}
//	
	@Override
	public List<SUsers> findUser(String hql) {
		return userDaoImpl.find(hql);
	}

	public List<SUsers> checkUser(SUsers user) {
//		return userDaoImpl.check(user);
		return findAll();
	}
	
	public String sayHi(String text) {
		System.out.println("sayHi called "+new Date());
		return "Hello " + text;
	}



	//查询所有的用户信息
	@Override
	public List<SUsers> findAll() {
		// TODO Auto-generated method stub
		return userDaoImpl.find("from SUsers" );
	}
	

//	//查询所有的角色列表
//	@Override
//	public List<SRole> findAllRoles() {
//		// TODO Auto-generated method stub
//		return roleDao.find("from SRole");
//	}
	//查询所有用户权限列表
//	@Override
//	public List<SPermissions> findAllPermissions() {
//		// TODO Auto-generated method stub
//		return permissionsDaoImpl.find("from SPermissions");
//	}
//	
	
	@Override
	public List<SUsers> datagrid(int page, int rows) {
		// TODO Auto-generated method stub
			String hql = "from SUsers t ";
			Map<String, Object> params = new HashMap<String, Object>();
//			hql = addWhere(user, hql, params);
			String totalHql = "select count(*) " + hql;
//			hql = addOrder(user, hql);
			List<SUsers> l = userDaoImpl.find(hql, params,page, rows);
//			List<User> nl = new ArrayList<User>();
//			changeModel(l, nl);
//			dg.setTotal(userDao.count(totalHql, params));
//			dg.setRows(nl);
			return l;
		
	}

	@Override
	public SUsers login(SUsers user) {
		// TODO Auto-generated method stub
//		logger.info("测试日志记录能否成功");  
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("pwd", Encrypt.e(user.getPassword()));
//		params.put("pwd", user.getPassword());
		params.put("loginName", user.getLoginName());
		SUsers t =null;
		List<SUsers>  list= userDaoImpl.get("from SUsers t where t.loginName = :loginName and t.password = :pwd", params);
		if (list != null && list.size() > 0) {
			t=list.get(0);
		}			
		if (t != null) {
			return t;
		}
		return null;
	}

	@Override
	public void remove(SUsers user) {
		// TODO Auto-generated method stub
		userDaoImpl.delete(user);
	}

	//根据用户Id来删除用户
	
	@Override
	public int deleteById(String id) {
		// TODO Auto-generated method stub
		return userDaoImpl.executeHql("delete  from SUsers t where t.id='"+id+"'");
	}

		
	
	public SUsers saveUser(SUsers user) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("name", user.getName());
		//判断用户是否存在
		SUsers t =null;
		List<SUsers>  list=  userDaoImpl.get("from  SUsers t where t.name = :name", params);
		if (list != null && list.size() > 0) {
			t=list.get(0);
		}	
		if (t != null) {
			return null;
		}else{
			user.setCreateTime(new Date());
			user.setPassword(Encrypt.e(user.getPassword()));
			user.setStatus(true);
			 userDaoImpl.save(user);
			 
				SUsers t1 =null;
				List<SUsers>  list1=  userDaoImpl.get("from  SUsers t where t.name = :name", params);
				if (list1 != null && list1.size() > 0) {
					t1=list1.get(0);
				}	
			 return t1;
		}
	}

	@Override
	public SUsers findById(String id) {
		// TODO Auto-generated method stub
		return userDaoImpl.findById(SUsers.class,id);
	}

	@Override
	public SUsers updateUser(SUsers user) {
		// TODO Auto-generated method stub
		if(user.getPassword()!=null){
			user.setPassword(Encrypt.e(user.getPassword()));

		}
		 userDaoImpl.saveOrUpdate(user);
		
		
		
		return null;
	}

	
//	@Override
//	public SOrganization saveOrganization(SOrganization organization) {
//		// TODO Auto-generated method stub
//		return null;
//	}


}
