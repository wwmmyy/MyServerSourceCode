package com.dist.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.dist.dao.BasicDaoI;
import com.dist.entity.PSnsComment;
import com.dist.entity.PSnsPhoots;
import com.dist.entity.PUserapplicationmessage;
import com.dist.entity.SDevice;
import com.dist.entity.PSns;
import com.dist.service.PSnsServiceI;
@Service("psnsService")
public class PSnsServiceImpl implements PSnsServiceI {

	private BasicDaoI<PSns> PSnsdao;

	private final String tablename=" PSns t ";
	
	
	@Resource
	public void setPSnsDao( BasicDaoI<PSns>  PSnsdao) {
		this.PSnsdao = PSnsdao;
	}

	
	
	@Override
	public String save(PSns t) {
		// TODO Auto-generated method stub
		return PSnsdao.save(t).getId();
	}

	@Override
	public List<PSns> findAll() {
		// TODO Auto-generated method stub
		return PSnsdao.find("from "+tablename);
	}

	@Override
	public int deleteById(String id) {
		// TODO Auto-generated method stub
		return PSnsdao.deleteById(PSns.class, id);
	}

	@Override
	public int deleteAll() {
		// TODO Auto-generated method stub
		return PSnsdao.executeHql("delete from "+tablename);
	}

	@Override
	public Long count() {
		// TODO Auto-generated method stub
		return PSnsdao.count("select count(*) from "+tablename);
	}

	@Override
	public Long count(Map<String, Object> params) {
		// TODO Auto-generated method stub
		String hql="select count(*) from "+tablename;
		return PSnsdao.count(hql, params);
	}

	@Override
	public List<PSns> find(int page, int rows) {
		// TODO Auto-generated method stub
		String hql="from "+tablename;
		return PSnsdao.find(hql,page, rows);
	}

	@Override
	public List<PSns> find(Map<String, Object> params, int page, int rows) {
		// TODO Auto-generated method stub
		String hql="from "+tablename;
		return PSnsdao.find(hql, params, page, rows);
	}

	@Override
	public PSns findById(String id) {
		// TODO Auto-generated method stub
		return PSnsdao.findById(PSns.class,id);
	}



	@Override
	public List<PSns> findByProperty( String propertyName, Object value) {
		// TODO Auto-generated method stub
		return PSnsdao.findByProperty(PSns.class, propertyName, value);
	}



	@Override
	public Long count(String searchinfo) {
		// TODO Auto-generated method stub
		String hql="select count(*) from "+tablename +" where 1=1 ";
		if(searchinfo!=null&& !searchinfo.equals("")){
			hql+=" and t.content like '%"+ searchinfo+"%' ";
		}
		return PSnsdao.count(hql);
	}
	
	

	@Override
	public List<PSns> find(String searchinfo, int page, int rows) {
		// TODO Auto-generated method stub
		String hql="from "+tablename +" where 1=1 ";		
		if(searchinfo!=null&& !searchinfo.equals("")){			
			hql+=" and t.content like '%"+ searchinfo+"%' ";
		}		
		hql+=" order by t.publishTime desc";
		return PSnsdao.find(hql,  page, rows);
	}



}
