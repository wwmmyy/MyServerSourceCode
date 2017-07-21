package com.dist.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.dist.dao.BasicDaoI;
import com.dist.entity.PSubscriptionsource;
import com.dist.service.PSubscriptionsourceServiceI;
@Service("subScriSourceService")
public class PSubscriptionsourceServiceImpl implements PSubscriptionsourceServiceI {

	private BasicDaoI<PSubscriptionsource> PSubscriptionsourcedao;

	private final String tablename=" PSubscriptionsource ";
	
	
	@Resource
	public void setPSubscriptionsourceDao( BasicDaoI<PSubscriptionsource>  PSubscriptionsourcedao) {
		this.PSubscriptionsourcedao = PSubscriptionsourcedao;
	}

	
	
	@Override
	public String save(PSubscriptionsource t) {
		// TODO Auto-generated method stub
		return PSubscriptionsourcedao.save(t).getId();
	}

	@Override
	public List<PSubscriptionsource> findAll() {
		// TODO Auto-generated method stub
		return PSubscriptionsourcedao.find("from "+tablename);
	}

	@Override
	public int deleteById(String id) {
		// TODO Auto-generated method stub
		return PSubscriptionsourcedao.deleteById(PSubscriptionsource.class, id);
	}

	@Override
	public int deleteAll() {
		// TODO Auto-generated method stub
		return PSubscriptionsourcedao.executeHql("delete from "+tablename);
	}

	@Override
	public Long count() {
		// TODO Auto-generated method stub
		return PSubscriptionsourcedao.count("select count(*) from "+tablename+" t");
	}

	@Override
	public Long count(Map<String, Object> params) {
		// TODO Auto-generated method stub
		String hql="select count(*) from "+tablename+"  t";
		return PSubscriptionsourcedao.count(hql, params);
	}

	@Override
	public List<PSubscriptionsource> find(int page, int rows) {
		// TODO Auto-generated method stub
		String hql="from "+tablename+"  t";
		return PSubscriptionsourcedao.find(hql,page, rows);
	}

	@Override
	public List<PSubscriptionsource> find(Map<String, Object> params, int page, int rows) {
		// TODO Auto-generated method stub
		String hql="from "+tablename+"  t";
		return PSubscriptionsourcedao.find(hql, params, page, rows);
	}

	@Override
	public PSubscriptionsource findById(String id) {
		// TODO Auto-generated method stub
		return PSubscriptionsourcedao.findById(PSubscriptionsource.class,id);
	}


	@Override
	public List<PSubscriptionsource> findByProperty( String propertyName, Object value) {
		// TODO Auto-generated method stub
		return PSubscriptionsourcedao.findByProperty(PSubscriptionsource.class, propertyName, value);
	}



	@Override
	public Long count(String searchinfo) {
		// TODO Auto-generated method stub
		String hql="select count(*) from "+tablename +" where 1=1 ";
		if(searchinfo!=null&& !searchinfo.equals("")){
			hql+=" and t.name like '%"+ searchinfo+"%'";
		}
		return PSubscriptionsourcedao.count(hql);
	}
	
	

	@Override
	public List<PSubscriptionsource> find(String searchinfo, int page, int rows) {
		// TODO Auto-generated method stub
		String hql="from "+tablename +" where 1=1 ";		
		if(searchinfo!=null&& !searchinfo.equals("")){			
			hql+=" and t.name like '%"+ searchinfo+"%'";
		}		
		return PSubscriptionsourcedao.find(hql,  page, rows);
	}

}
