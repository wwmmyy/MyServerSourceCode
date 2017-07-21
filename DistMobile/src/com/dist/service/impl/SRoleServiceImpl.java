package com.dist.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.dist.dao.BasicDaoI;
import com.dist.entity.PSnsComment;
import com.dist.entity.SApplications;
import com.dist.entity.SDevice;
import com.dist.entity.SRole;
import com.dist.service.SRoleServiceI;
@Service("roleService")
public class SRoleServiceImpl implements SRoleServiceI {

	private BasicDaoI<SRole> SRoledao;

	private final String tablename=" SRole ";
	
	
	@Resource
	public void setSRoleDao( BasicDaoI<SRole>  SRoledao) {
		this.SRoledao = SRoledao;
	}

	
	
	@Override
	public String save(SRole t) {
		// TODO Auto-generated method stub
		return SRoledao.save(t).getId();
	}

	@Override
	public List<SRole> findAll() {
		// TODO Auto-generated method stub
		return SRoledao.find("from "+tablename);
	}

	@Override
	public int deleteById(String id) {
		// TODO Auto-generated method stub
		return SRoledao.deleteById(SRole.class, id);
	}

	@Override
	public int deleteAll() {
		// TODO Auto-generated method stub
		return SRoledao.executeHql("delete from "+tablename);
	}

	@Override
	public Long count() {
		// TODO Auto-generated method stub
		return SRoledao.count("select count(*) from "+tablename+" t");
	}

	@Override
	public Long count(Map<String, Object> params) {
		// TODO Auto-generated method stub
		String hql="select count(*) from "+tablename+"  t";
		return SRoledao.count(hql, params);
	}

	@Override
	public List<SRole> find(int page, int rows) {
		// TODO Auto-generated method stub
		String hql="from "+tablename+"  t";
		return SRoledao.find(hql,page, rows);
	}

	@Override
	public List<SRole> find(Map<String, Object> params, int page, int rows) {
		// TODO Auto-generated method stub
		String hql="from "+tablename+"  t";
		return SRoledao.find(hql, params, page, rows);
	}

	@Override
	public SRole findById(String id) {
		// TODO Auto-generated method stub
		return SRoledao.findById(SRole.class,id);
	}

	
	@Override
	public List<SRole> findByProperty(String propertyName, Object value) {
		// TODO Auto-generated method stub
		return SRoledao.findByProperty(SRole.class, propertyName, value);
	}


	
	@Override
	public Long count(String searchinfo) {
		// TODO Auto-generated method stub
		String hql="select count(*) from "+tablename +" where 1=1 ";
		if(searchinfo!=null&& !searchinfo.equals("")){
			hql+=" and t.name like '%"+ searchinfo+"%'";
		}
		return SRoledao.count(hql);
	}
	
	

	@Override
	public List<SRole> find(String searchinfo, int page, int rows) {
		// TODO Auto-generated method stub
		String hql="from "+tablename +" where 1=1 ";		
		if(searchinfo!=null&& !searchinfo.equals("")){			
			hql+=" and t.name like '%"+ searchinfo+"%'";
		}		
		return SRoledao.find(hql,  page, rows);
	}
	

}
