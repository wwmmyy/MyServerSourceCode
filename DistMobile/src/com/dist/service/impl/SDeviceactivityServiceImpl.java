package com.dist.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.dist.dao.BasicDaoI;
import com.dist.entity.PSnsComment;
import com.dist.entity.SDevice;
import com.dist.entity.SDeviceactivity;
import com.dist.entity.SDeviceapplications;
import com.dist.service.SDeviceactivityServiceI;
@Service("deviceactivityService")
public class SDeviceactivityServiceImpl implements SDeviceactivityServiceI {

	private BasicDaoI<SDeviceactivity> SDeviceactivitydao;

	private final String tablename=" SDeviceactivity ";
	
	
	@Resource
	public void setSDeviceactivityDao( BasicDaoI<SDeviceactivity>  SDeviceactivitydao) {
		this.SDeviceactivitydao = SDeviceactivitydao;
	}

	
	
	@Override
	public String save(SDeviceactivity t) {
		// TODO Auto-generated method stub
		return SDeviceactivitydao.save(t).getId();
	}

	@Override
	public List<SDeviceactivity> findAll() {
		// TODO Auto-generated method stub
		return SDeviceactivitydao.find("from "+tablename);
	}

	@Override
	public int deleteById(String id) {
		// TODO Auto-generated method stub
		return SDeviceactivitydao.deleteById(SDeviceactivity.class, id);
	}

	@Override
	public int deleteAll() {
		// TODO Auto-generated method stub
		return SDeviceactivitydao.executeHql("delete from "+tablename);
	}

	@Override
	public Long count() {
		// TODO Auto-generated method stub
		return SDeviceactivitydao.count("select count(*) from "+tablename+" t");
	}

	@Override
	public Long count(Map<String, Object> params) {
		// TODO Auto-generated method stub
		String hql="select count(*) from "+tablename+"  t";
		return SDeviceactivitydao.count(hql, params);
	}

	@Override
	public List<SDeviceactivity> find(int page, int rows) {
		// TODO Auto-generated method stub
		String hql="from "+tablename+"  t";
		return SDeviceactivitydao.find(hql,page, rows);
	}

	@Override
	public List<SDeviceactivity> find(Map<String, Object> params, int page, int rows) {
		// TODO Auto-generated method stub
		String hql="from "+tablename+"  t";
		return SDeviceactivitydao.find(hql, params, page, rows);
	}

	@Override
	public SDeviceactivity findById(String id) {
		// TODO Auto-generated method stub
		return SDeviceactivitydao.findById(SDeviceactivity.class,id);
	}

	@Override
	public List<SDeviceactivity> findByProperty(String propertyName, Object value) {
		// TODO Auto-generated method stub
		return SDeviceactivitydao.findByProperty(SDeviceactivity.class, propertyName, value);
	}



	@Override
	public Long count(String searchinfo) {
		// TODO Auto-generated method stub
		String hql="select count(*) from "+tablename +" where 1=1 ";
		if(searchinfo!=null&& !searchinfo.equals("")){
			hql+=" and t.SDevice.name like '%"+ searchinfo+"%' or  t.SDevice.remark like '%"+ searchinfo+"%'";
		}
		return SDeviceactivitydao.count(hql);
	}
	
	

	@Override
	public List<SDeviceactivity> find(String searchinfo, int page, int rows) {
		// TODO Auto-generated method stub
		String hql="from "+tablename +" where 1=1 ";		
		if(searchinfo!=null&& !searchinfo.equals("")){			
			hql+=" and t.SDevice.name like '%"+ searchinfo+"%' or  t.SDevice.remark like '%"+ searchinfo+"%'";
		}		
		return SDeviceactivitydao.find(hql,  page, rows);
	}
	

}
