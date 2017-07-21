package com.dist.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.dist.dao.BasicDaoI;
import com.dist.entity.PUserapplicationmessage;
import com.dist.entity.SDevice;
import com.dist.entity.SSystemactivity;
import com.dist.service.SystemactivityServiceI;
@Service("SSystemactivityService")
public class SystemactivityServiceImpl implements SystemactivityServiceI {

	private BasicDaoI<SSystemactivity> SSystemactivitydao;

	private final String tablename=" SSystemactivity t ";
	
	
	@Resource
	public void setSSystemactivityDao( BasicDaoI<SSystemactivity>  SSystemactivitydao) {
		this.SSystemactivitydao = SSystemactivitydao;
	}

	
	
	@Override
	public String save(SSystemactivity t) {
		// TODO Auto-generated method stub
		return SSystemactivitydao.save(t).getId();
	}

	@Override
	public List<SSystemactivity> findAll() {
		// TODO Auto-generated method stub
		return SSystemactivitydao.find("from "+tablename);
	}

	@Override
	public int deleteById(String id) {
		// TODO Auto-generated method stub
		return SSystemactivitydao.deleteById(SSystemactivity.class, id);
	}

	@Override
	public int deleteAll() {
		// TODO Auto-generated method stub
		return SSystemactivitydao.executeHql("delete from "+tablename);
	}

	@Override
	public Long count() {
		// TODO Auto-generated method stub
		return SSystemactivitydao.count("select count(*) from "+tablename);
	}

	@Override
	public Long count(Map<String, Object> params) {
		// TODO Auto-generated method stub
		String hql="select count(*) from "+tablename;
		return SSystemactivitydao.count(hql, params);
	}

	@Override
	public List<SSystemactivity> find(int page, int rows) {
		// TODO Auto-generated method stub
		String hql="from "+tablename;
		return SSystemactivitydao.find(hql,page, rows);
	}

	@Override
	public List<SSystemactivity> find(Map<String, Object> params, int page, int rows) {
		// TODO Auto-generated method stub
		String hql="from "+tablename;
		return SSystemactivitydao.find(hql, params, page, rows);
	}

	@Override
	public SSystemactivity findById(String id) {
		// TODO Auto-generated method stub
		return SSystemactivitydao.findById(SSystemactivity.class,id);
	}



	@Override
	public List<SSystemactivity> findByProperty( String propertyName, Object value) {
		// TODO Auto-generated method stub
		return SSystemactivitydao.findByProperty(SSystemactivity.class, propertyName, value);
	}



	@Override
	public Long count(String searchinfo) {
		// TODO Auto-generated method stub
		String hql="select count(*) from "+tablename +" where 1=1 ";
		if(searchinfo!=null&& !searchinfo.equals("")){
			hql+=" and t.content like '%"+ searchinfo+"%' ";
		}
		return SSystemactivitydao.count(hql);
	}
	
	

	@Override
	public List<SSystemactivity> find(String searchinfo, int page, int rows) {
		// TODO Auto-generated method stub
		String hql="from "+tablename +" where 1=1 ";		
		if(searchinfo!=null&& !searchinfo.equals("")){			
			hql+=" and t.content like '%"+ searchinfo+"%' ";
		}		
		hql+=" order by t.publishTime desc";
		return SSystemactivitydao.find(hql,  page, rows);
	}



}
