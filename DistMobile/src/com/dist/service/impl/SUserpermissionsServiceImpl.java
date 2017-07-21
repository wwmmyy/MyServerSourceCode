package com.dist.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.dist.dao.BasicDaoI;
import com.dist.entity.PSnsComment;
import com.dist.entity.SDevice;
import com.dist.entity.SUserpermissions;
import com.dist.service.SUserpermissionsServiceI;
@Service("userpermissionsService")
public class SUserpermissionsServiceImpl implements SUserpermissionsServiceI {

	private BasicDaoI<SUserpermissions> SUserpermissionsdao;

	private final String tablename=" SUserpermissions ";
	
	
	@Resource
	public void setSUserpermissionsDao( BasicDaoI<SUserpermissions>  SUserpermissionsdao) {
		this.SUserpermissionsdao = SUserpermissionsdao;
	}

	
	
	@Override
	public String save(SUserpermissions t) {
		// TODO Auto-generated method stub
		return SUserpermissionsdao.save(t).getId();
	}

	@Override
	public List<SUserpermissions> findAll() {
		// TODO Auto-generated method stub
		return SUserpermissionsdao.find("from "+tablename);
	}

	@Override
	public int deleteById(String id) {
		// TODO Auto-generated method stub
		return SUserpermissionsdao.deleteById(SUserpermissions.class, id);
	}

	@Override
	public int deleteAll() {
		// TODO Auto-generated method stub
		return SUserpermissionsdao.executeHql("delete from "+tablename);
	}

	@Override
	public Long count() {
		// TODO Auto-generated method stub
		return SUserpermissionsdao.count("select count(*) from "+tablename+" t");
	}

	@Override
	public Long count(Map<String, Object> params) {
		// TODO Auto-generated method stub
		String hql="select count(*) from "+tablename+"  t";
		return SUserpermissionsdao.count(hql, params);
	}

	@Override
	public List<SUserpermissions> find(int page, int rows) {
		// TODO Auto-generated method stub
		String hql="from "+tablename+"  t";
		return SUserpermissionsdao.find(hql,page, rows);
	}

	@Override
	public List<SUserpermissions> find(Map<String, Object> params, int page, int rows) {
		// TODO Auto-generated method stub
		String hql="from "+tablename+"  t";
		return SUserpermissionsdao.find(hql, params, page, rows);
	}

	@Override
	public SUserpermissions findById(String id) {
		// TODO Auto-generated method stub
		return SUserpermissionsdao.findById(SUserpermissions.class,id);
	}

	

	@Override
	public List<SUserpermissions> findByProperty( String propertyName, Object value) {
		// TODO Auto-generated method stub
		return SUserpermissionsdao.findByProperty(SUserpermissions.class, propertyName, value);
	}



	@Override
	public List<SUserpermissions> find(String searchinfo, int page, int rows) {
		// TODO Auto-generated method stub
		return null;
	}



	@Override
	public Long count(String searchinfo) {
		// TODO Auto-generated method stub
		return null;
	}
}
