package com.dist.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.jws.WebParam;
import javax.jws.WebService;

import com.dist.entity.SOrganization;
import com.dist.entity.SPermissions;
import com.dist.entity.SRole;
import com.dist.entity.SUsers;

@WebService
public interface UserServiceI {

	public List<SUsers> findUser(String hql);
	
	public SUsers saveUser(SUsers user);
	
	public SUsers updateUser(SUsers user);
	
	public String sayHi(String text);
	public void remove(SUsers user);
	public List<SUsers> checkUser(SUsers user);
	public  List<SUsers>  findAll();
//	测试分页加载
	public List<SUsers> datagrid(int page, int rows);
	public SUsers login(SUsers user);
	public int deleteById(String id);
	public  SUsers  findById(String id) ;

	
	
	
//	public SOrganization saveOrganization(SOrganization organization);
//	
//	public SRole saveRole(SRole role);
//	
//	public SPermissions savaPermissions(SPermissions permissions);

//	public List<SOrganization> findAllOrganization();
	
//	public List<SRole> findAllRoles();
	
//	public List<SPermissions> findAllPermissions();
}