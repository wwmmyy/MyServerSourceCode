package com.dist.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.dist.dao.BasicDaoI;
import com.dist.entity.SSystemuser;
import com.dist.entity.SUsers;
import com.dist.service.SystemuserServiceI;
import com.dist.util.Encrypt;
@Service("systemuserService")
public class SystemuserServiceImpl implements SystemuserServiceI {

	private BasicDaoI<SSystemuser> SSystemuserdao;

	private final String tablename=" SSystemuser t ";
	
	
	@Resource
	public void setSSystemuserDao( BasicDaoI<SSystemuser>  SSystemuserdao) {
		this.SSystemuserdao = SSystemuserdao;
	}

	
	
	@Override
	public String save(SSystemuser t) {
		// TODO Auto-generated method stub
		return SSystemuserdao.save(t).getId();
	}

	@Override
	public List<SSystemuser> findAll() {
		// TODO Auto-generated method stub
		return SSystemuserdao.find("from "+tablename);
	}

	@Override
	public int deleteById(String id) {
		// TODO Auto-generated method stub
		return SSystemuserdao.deleteById(SSystemuser.class, id);
	}

	@Override
	public int deleteAll() {
		// TODO Auto-generated method stub
		return SSystemuserdao.executeHql("delete from "+tablename);
	}

	@Override
	public Long count() {
		// TODO Auto-generated method stub
		return SSystemuserdao.count("select count(*) from "+tablename);
	}

	@Override
	public Long count(Map<String, Object> params) {
		// TODO Auto-generated method stub
		String hql="select count(*) from "+tablename;
		return SSystemuserdao.count(hql, params);
	}

	@Override
	public List<SSystemuser> find(int page, int rows) {
		// TODO Auto-generated method stub
		String hql="from "+tablename;
		return SSystemuserdao.find(hql,page, rows);
	}

	@Override
	public List<SSystemuser> find(Map<String, Object> params, int page, int rows) {
		// TODO Auto-generated method stub
		String hql="from "+tablename;
		return SSystemuserdao.find(hql, params, page, rows);
	}

	@Override
	public SSystemuser findById(String id) {
		// TODO Auto-generated method stub
		return SSystemuserdao.findById(SSystemuser.class,id);
	}



	@Override
	public List<SSystemuser> findByProperty( String propertyName, Object value) {
		// TODO Auto-generated method stub
		return SSystemuserdao.findByProperty(SSystemuser.class, propertyName, value);
	}



	@Override
	public Long count(String searchinfo) {
		// TODO Auto-generated method stub
		String hql="select count(*) from "+tablename +" where 1=1 ";
		if(searchinfo!=null&& !searchinfo.equals("")){
			hql+=" and t.loginName like '%"+ searchinfo+"%' ";
		}
		return SSystemuserdao.count(hql);
	}
	
	

	@Override
	public List<SSystemuser> find(String searchinfo, int page, int rows) {

		return null;
	}



	@Override
	public SSystemuser login(SSystemuser suser) {
		// TODO Auto-generated method stub

		// TODO Auto-generated method stub
//		logger.info("测试日志记录能否成功");  
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("pwd", Encrypt.e(suser.getPassword()));
//		params.put("pwd", user.getPassword());
		params.put("loginName", suser.getLoginName());
		SSystemuser t =null;
		List<SSystemuser>  list= SSystemuserdao.get("from SSystemuser t where t.loginName = :loginName and t.password = :pwd", params);
		if (list != null && list.size() > 0) {
			t=list.get(0);
		}			
		if (t != null) {
			return t;
		}
		return null;
	
	}



}
