package com.dist.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.dist.dao.BasicDaoI;
import com.dist.entity.PSnsComment;
import com.dist.entity.SDevice;
import com.dist.entity.SDevicetype;
import com.dist.entity.SOrganization;
import com.dist.entity.SUsers;
import com.dist.service.SDevicetypeServiceI;
@Service("devicetypeService")
public class SDevicetypeServiceImpl implements SDevicetypeServiceI {

	private BasicDaoI<SDevicetype> SDevicetypedao;

	private final String tablename=" SDevicetype ";
	
	
	@Resource
	public void setSDevicetypeDao( BasicDaoI<SDevicetype>  SDevicetypedao) {
		this.SDevicetypedao = SDevicetypedao;
	}

	
	
	@Override
	public String save(SDevicetype t) {
		// TODO Auto-generated method stub
		return SDevicetypedao.save(t).getId();
	}

	@Override
	public List<SDevicetype> findAll() {
		// TODO Auto-generated method stub
		return SDevicetypedao.find("from "+tablename);
	}

	@Override
	public int deleteById(String id) {
		// TODO Auto-generated method stub
		return SDevicetypedao.deleteById(SDevicetype.class, id);
	}

	@Override
	public int deleteAll() {
		// TODO Auto-generated method stub
		return SDevicetypedao.executeHql("delete from "+tablename);
	}

	@Override
	public Long count() {
		// TODO Auto-generated method stub
		return SDevicetypedao.count("select count(*) from "+tablename+" t");
	}

	@Override
	public Long count(Map<String, Object> params) {
		// TODO Auto-generated method stub
		String hql="select count(*) from "+tablename+"  t";
		return SDevicetypedao.count(hql, params);
	}

	@Override
	public List<SDevicetype> find(int page, int rows) {
		// TODO Auto-generated method stub
		String hql="from "+tablename+"  t";
		return SDevicetypedao.find(hql,page, rows);
	}

	@Override
	public List<SDevicetype> find(Map<String, Object> params, int page, int rows) {
		// TODO Auto-generated method stub
		String hql="from "+tablename+"  t";
		return SDevicetypedao.find(hql, params, page, rows);
	}

	@Override
	public SDevicetype findById(String id) {
		// TODO Auto-generated method stub
		return SDevicetypedao.findById(SDevicetype.class,id);
	}


	@Override
	public List<SDevicetype> findByProperty(String propertyName, Object value) {
		// TODO Auto-generated method stub
		return SDevicetypedao.findByProperty(SDevicetype.class, propertyName, value);
	}


	
	@Override
	public Long count(String searchinfo) {
		// TODO Auto-generated method stub
		String hql="select count(*) from "+tablename +" where 1=1 ";
		if(searchinfo!=null&& !searchinfo.equals("")){
			hql+=" and t.name like '%"+ searchinfo+"%' ";
		}
		return SDevicetypedao.count(hql);
	}
	
	

	@Override
	public List<SDevicetype> find(String searchinfo, int page, int rows) {
		// TODO Auto-generated method stub
		String hql="from "+tablename +" where 1=1 ";		
		if(searchinfo!=null&& !searchinfo.equals("")){			
			hql+=" and t.name like '%"+ searchinfo+"%' ";
		}		
		return SDevicetypedao.find(hql,  page, rows);
	}



	@Override
	public List<SDevicetype> findByPropertys(String system, String hardware) {
		// TODO Auto-generated method stub
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("systemtype", system.trim());
		params.put("hardwaretype", hardware.trim());
		return  SDevicetypedao.get("from  SDevicetype t where t.systemtype = :systemtype and t.hardwaretype = :hardwaretype", params);
	}
	
	
	
}
