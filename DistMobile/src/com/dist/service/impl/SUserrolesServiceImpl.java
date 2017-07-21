package com.dist.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.dist.dao.BasicDaoI;
import com.dist.entity.PSnsComment;
import com.dist.entity.SRole;
import com.dist.entity.SUserroles;
import com.dist.service.SUserrolesServiceI;
@Service("userrolesService")
public class SUserrolesServiceImpl implements SUserrolesServiceI {

	private BasicDaoI<SUserroles> SUserrolesdao;

	private final String tablename=" SUserroles ";
	
	
	@Resource
	public void setSUserrolesDao( BasicDaoI<SUserroles>  SUserrolesdao) {
		this.SUserrolesdao = SUserrolesdao;
	}

	
	
	@Override
	public String save(SUserroles t) {
		// TODO Auto-generated method stub
		return SUserrolesdao.save(t).getId();
	}

	@Override
	public List<SUserroles> findAll() {
		// TODO Auto-generated method stub
		return SUserrolesdao.find("from "+tablename);
	}

	@Override
	public int deleteById(String id) {
		// TODO Auto-generated method stub
		return SUserrolesdao.deleteById(SUserroles.class, id);
	}

	@Override
	public int deleteAll() {
		// TODO Auto-generated method stub
		return SUserrolesdao.executeHql("delete from "+tablename);
	}

	@Override
	public Long count() {
		// TODO Auto-generated method stub
		return SUserrolesdao.count("select count(*) from "+tablename+" t");
	}

	@Override
	public Long count(Map<String, Object> params) {
		// TODO Auto-generated method stub
		String hql="select count(*) from "+tablename+"  t";
		return SUserrolesdao.count(hql, params);
	}

	@Override
	public List<SUserroles> find(int page, int rows) {
		// TODO Auto-generated method stub
		String hql="from "+tablename+"  t";
		return SUserrolesdao.find(hql,page, rows);
	}

	@Override
	public List<SUserroles> find(Map<String, Object> params, int page, int rows) {
		// TODO Auto-generated method stub
		String hql="from "+tablename+"  t";
		return SUserrolesdao.find(hql, params, page, rows);
	}

	@Override
	public SUserroles findById(String id) {
		// TODO Auto-generated method stub
		return SUserrolesdao.findById(SUserroles.class,id);
	}

	@Override
	public List<SUserroles> findByProperty(String propertyName, Object value) {
		// TODO Auto-generated method stub
		return SUserrolesdao.findByProperty(SUserroles.class, propertyName, value);
	}



	@Override
	public Long count(String searchinfo) {
		// TODO Auto-generated method stub
		String hql="select count(*) from "+tablename +" where 1=1 ";
		if(searchinfo!=null&& !searchinfo.equals("")){
			hql+=" and t.SRole.name like '%"+ searchinfo+"%'  or t.SUsers.name like '%"+ searchinfo+"%'";
		}
		return SUserrolesdao.count(hql);
	}
	
	

	@Override
	public List<SUserroles> find(String searchinfo, int page, int rows) {
		// TODO Auto-generated method stub
		String hql="from "+tablename +" where 1=1 ";		
		if(searchinfo!=null&& !searchinfo.equals("")){			
			hql+=" and t.SRole.name like '%"+ searchinfo+"%'  or t.SUsers.name like '%"+ searchinfo+"%'";
		}		
		return SUserrolesdao.find(hql,  page, rows);
	}

}
