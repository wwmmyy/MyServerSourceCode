package com.dist.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.dist.dao.BasicDaoI;
import com.dist.entity.SUserdevices;
import com.dist.service.SUserdevicesServiceI;
@Service("userdevicesService")
public class SUserdevicesServiceImpl implements SUserdevicesServiceI {

	private BasicDaoI<SUserdevices> SUserdevicesdao;

	private final String tablename=" SUserdevices ";
	
	
	@Resource
	public void setSUserdevicesDao( BasicDaoI<SUserdevices>  SUserdevicesdao) {
		this.SUserdevicesdao = SUserdevicesdao;
	}

	
	
	@Override
	public String save(SUserdevices t) {
		// TODO Auto-generated method stub
		return SUserdevicesdao.save(t).getId();
	}

	@Override
	public List<SUserdevices> findAll() {
		// TODO Auto-generated method stub
		return SUserdevicesdao.find("from "+tablename);
	}

	@Override
	public int deleteById(String id) {
		// TODO Auto-generated method stub
		return SUserdevicesdao.deleteById(SUserdevices.class, id);
	}

	@Override
	public int deleteAll() {
		// TODO Auto-generated method stub
		return SUserdevicesdao.executeHql("delete from "+tablename);
	}

	@Override
	public Long count() {
		// TODO Auto-generated method stub
		return SUserdevicesdao.count("select count(*) from "+tablename+" t");
	}

	@Override
	public Long count(Map<String, Object> params) {
		// TODO Auto-generated method stub
		String hql="select count(*) from "+tablename+"  t";
		return SUserdevicesdao.count(hql, params);
	}

	@Override
	public List<SUserdevices> find(int page, int rows) {
		// TODO Auto-generated method stub
		String hql="from "+tablename+"  t";
		return SUserdevicesdao.find(hql,page, rows);
	}

	@Override
	public List<SUserdevices> find(Map<String, Object> params, int page, int rows) {
		// TODO Auto-generated method stub
		String hql="from "+tablename+"  t";
		return SUserdevicesdao.find(hql, params, page, rows);
	}

	@Override
	public SUserdevices findById(String id) {
		// TODO Auto-generated method stub
		return SUserdevicesdao.findById(SUserdevices.class,id);
	}

	@Override
	public List<SUserdevices> findByProperty(String propertyName, Object value) {
		// TODO Auto-generated method stub
		return SUserdevicesdao.findByProperty(SUserdevices.class, propertyName, value);
	}



	@Override
	public Long count(String searchinfo) {
		// TODO Auto-generated method stub
		String hql="select count(*) from "+tablename +" where 1=1 ";
		if(searchinfo!=null&& !searchinfo.equals("")){
			hql+=" and t.SDevice.name like '%"+ searchinfo+"%'  or t.SUsers.name like '%"+ searchinfo+"%'";
		}
		return SUserdevicesdao.count(hql);
	}
	
	

	@Override
	public List<SUserdevices> find(String searchinfo, int page, int rows) {
		// TODO Auto-generated method stub
		String hql="from "+tablename +" where 1=1 ";		
		if(searchinfo!=null&& !searchinfo.equals("")){			
			hql+=" and t.SDevice.name like '%"+ searchinfo+"%'  or t.SUsers.name like '%"+ searchinfo+"%'";
		}		
		return SUserdevicesdao.find(hql,  page, rows);
	}

}
