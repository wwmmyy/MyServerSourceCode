package com.dist.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.dist.dao.BasicDaoI;
import com.dist.entity.PSnsComment;
import com.dist.entity.SDeviceapplications;
import com.dist.entity.SDevicetype;
import com.dist.service.SDeviceapplicationsServiceI;
@Service("deviceapplicationsService")
public class SDeviceapplicationsServiceImpl implements SDeviceapplicationsServiceI {

	private BasicDaoI<SDeviceapplications> SDeviceapplicationsdao;

	private final String tablename=" SDeviceapplications ";
	
	
	@Resource
	public void setSDeviceapplicationsDao( BasicDaoI<SDeviceapplications>  SDeviceapplicationsdao) {
		this.SDeviceapplicationsdao = SDeviceapplicationsdao;
	}

	
	
	@Override
	public String save(SDeviceapplications t) {
		// TODO Auto-generated method stub
		return SDeviceapplicationsdao.save(t).getId();
	}

	@Override
	public List<SDeviceapplications> findAll() {
		// TODO Auto-generated method stub
		return SDeviceapplicationsdao.find("from "+tablename);
	}

	@Override
	public int deleteById(String id) {
		// TODO Auto-generated method stub
		return SDeviceapplicationsdao.deleteById(SDeviceapplications.class, id);
	}

	@Override
	public int deleteAll() {
		// TODO Auto-generated method stub
		return SDeviceapplicationsdao.executeHql("delete from "+tablename);
	}

	@Override
	public Long count() {
		// TODO Auto-generated method stub
		return SDeviceapplicationsdao.count("select count(*) from "+tablename+" t");
	}

	@Override
	public Long count(Map<String, Object> params) {
		// TODO Auto-generated method stub
		String hql="select count(*) from "+tablename+"  t";
		return SDeviceapplicationsdao.count(hql, params);
	}

	@Override
	public List<SDeviceapplications> find(int page, int rows) {
		// TODO Auto-generated method stub
		String hql="from "+tablename+"  t";
		return SDeviceapplicationsdao.find(hql,page, rows);
	}

	@Override
	public List<SDeviceapplications> find(Map<String, Object> params, int page, int rows) {
		// TODO Auto-generated method stub
		String hql="from "+tablename+"  t";
		return SDeviceapplicationsdao.find(hql, params, page, rows);
	}

	@Override
	public SDeviceapplications findById(String id) {
		// TODO Auto-generated method stub
		return SDeviceapplicationsdao.findById(SDeviceapplications.class,id);
	}

	

	@Override
	public List<SDeviceapplications> findByProperty(String propertyName, Object value) {
		// TODO Auto-generated method stub
		return SDeviceapplicationsdao.findByProperty(SDeviceapplications.class, propertyName, value);
	}
	
	
	@Override
	public Long count(String searchinfo) {
		// TODO Auto-generated method stub
		String hql="select count(*) from "+tablename +" where 1=1 ";
		if(searchinfo!=null&& !searchinfo.equals("")){
			hql+=" and t.name like '%"+ searchinfo+"%' ";
		}
		return SDeviceapplicationsdao.count(hql);
	}
	
	

	@Override
	public List<SDeviceapplications> find(String searchinfo, int page, int rows) {
		// TODO Auto-generated method stub
		String hql="from "+tablename +" where 1=1 ";		
		if(searchinfo!=null&& !searchinfo.equals("")){			
			hql+=" and t.name like '%"+ searchinfo+"%' ";
		}		
		return SDeviceapplicationsdao.find(hql,  page, rows);
	}
}
