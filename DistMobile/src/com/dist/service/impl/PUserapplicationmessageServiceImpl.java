package com.dist.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.dist.dao.BasicDaoI;
import com.dist.entity.PSnsComment;
import com.dist.entity.SApplicationcategory;
import com.dist.entity.SDevice;
import com.dist.entity.PUserapplicationmessage;
import com.dist.service.PUserapplicationmessageServiceI;
@Service("userapplicationmessageService")
public class PUserapplicationmessageServiceImpl implements PUserapplicationmessageServiceI {

	private BasicDaoI<PUserapplicationmessage> PUserapplicationmessagedao;

	private final String tablename=" PUserapplicationmessage ";
	
	
	@Resource
	public void setPUserapplicationmessageDao( BasicDaoI<PUserapplicationmessage>  PUserapplicationmessagedao) {
		this.PUserapplicationmessagedao = PUserapplicationmessagedao;
	}

	
	
	@Override
	public String save(PUserapplicationmessage t) {
		// TODO Auto-generated method stub
		return PUserapplicationmessagedao.save(t).getId();
	}

	@Override
	public List<PUserapplicationmessage> findAll() {
		// TODO Auto-generated method stub
		return PUserapplicationmessagedao.find("from "+tablename);
	}

	@Override
	public int deleteById(String id) {
		// TODO Auto-generated method stub
		return PUserapplicationmessagedao.deleteById(PUserapplicationmessage.class, id);
	}

	@Override
	public int deleteAll() {
		// TODO Auto-generated method stub
		return PUserapplicationmessagedao.executeHql("delete from "+tablename);
	}

	@Override
	public Long count() {
		// TODO Auto-generated method stub
		return PUserapplicationmessagedao.count("select count(*) from "+tablename+" t");
	}

	@Override
	public Long count(Map<String, Object> params) {
		// TODO Auto-generated method stub
		String hql="select count(*) from "+tablename+"  t";
		return PUserapplicationmessagedao.count(hql, params);
	}

	@Override
	public List<PUserapplicationmessage> find(int page, int rows) {
		// TODO Auto-generated method stub
		String hql="from "+tablename+"  t";
		return PUserapplicationmessagedao.find(hql,page, rows);
	}

	@Override
	public List<PUserapplicationmessage> find(Map<String, Object> params, int page, int rows) {
		// TODO Auto-generated method stub
		String hql="from "+tablename+"  t";
		return PUserapplicationmessagedao.find(hql, params, page, rows);
	}

	@Override
	public PUserapplicationmessage findById(String id) {
		// TODO Auto-generated method stub
		return PUserapplicationmessagedao.findById(PUserapplicationmessage.class,id);
	}


	@Override
	public List<PUserapplicationmessage> findByProperty( String propertyName, Object value) {
		// TODO Auto-generated method stub
		return PUserapplicationmessagedao.findByProperty(PUserapplicationmessage.class, propertyName, value);
	}



	@Override
	public Long count(String searchinfo) {
		// TODO Auto-generated method stub
		String hql="select count(*) from "+tablename +" where 1=1 ";
		if(searchinfo!=null&& !searchinfo.equals("")){
			hql+=" and t.message like '%"+ searchinfo+"%' or t.SUsers.name like '%"+ searchinfo+"%' or t.SApplications.name like '%"+ searchinfo+"%' ";
		}
		return PUserapplicationmessagedao.count(hql);
	}
	
	

	@Override
	public List<PUserapplicationmessage> find(String searchinfo, int page, int rows) {
		// TODO Auto-generated method stub
		String hql="from "+tablename +" where 1=1 ";		
		if(searchinfo!=null&& !searchinfo.equals("")){			
			hql+=" and t.message like '%"+ searchinfo+"%' or t.SUsers.name like '%"+ searchinfo+"%' or t.SApplications.name like '%"+ searchinfo+"%' ";
		}		
		return PUserapplicationmessagedao.find(hql,  page, rows);
	}

}
