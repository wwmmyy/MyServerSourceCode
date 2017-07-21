package com.dist.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.dist.dao.BasicDaoI;
import com.dist.entity.PSnsComment;
import com.dist.entity.SDevice;
import com.dist.entity.SOrganization;
import com.dist.entity.SPermissions;
import com.dist.service.SPermissionsServiceI;
@Service("permissionsService")
public class SPermissionsServiceImpl implements SPermissionsServiceI {

	private BasicDaoI<SPermissions> SPermissionsdao;

	private final String tablename=" SPermissions ";
	
	
	@Resource
	public void setSPermissionsDao( BasicDaoI<SPermissions>  SPermissionsdao) {
		this.SPermissionsdao = SPermissionsdao;
	}

	
	
	@Override
	public String save(SPermissions t) {
		// TODO Auto-generated method stub
		return SPermissionsdao.save(t).getId();
	}

	@Override
	public List<SPermissions> findAll() {
		// TODO Auto-generated method stub
		return SPermissionsdao.find("from "+tablename);
	}

	@Override
	public int deleteById(String id) {
		// TODO Auto-generated method stub
		return SPermissionsdao.deleteById(SPermissions.class, id);
	}

	@Override
	public int deleteAll() {
		// TODO Auto-generated method stub
		return SPermissionsdao.executeHql("delete from "+tablename);
	}

	@Override
	public Long count() {
		// TODO Auto-generated method stub
		return SPermissionsdao.count("select count(*) from "+tablename+" t");
	}

	@Override
	public Long count(Map<String, Object> params) {
		// TODO Auto-generated method stub
		String hql="select count(*) from "+tablename+"  t";
		return SPermissionsdao.count(hql, params);
	}

	@Override
	public List<SPermissions> find(int page, int rows) {
		// TODO Auto-generated method stub
		String hql="from "+tablename+"  t";
		return SPermissionsdao.find(hql,page, rows);
	}

	@Override
	public List<SPermissions> find(Map<String, Object> params, int page, int rows) {
		// TODO Auto-generated method stub
		String hql="from "+tablename+"  t";
		return SPermissionsdao.find(hql, params, page, rows);
	}

	@Override
	public SPermissions findById(String id) {
		// TODO Auto-generated method stub
		return SPermissionsdao.findById(SPermissions.class,id);
	}


	@Override
	public List<SPermissions> findByProperty(String propertyName, Object value) {
		// TODO Auto-generated method stub
		return SPermissionsdao.findByProperty(SPermissions.class, propertyName, value);
	}
	
	
	@Override
	public Long count(String searchinfo) {
		// TODO Auto-generated method stub
		String hql="select count(*) from "+tablename +" where 1=1 ";
		if(searchinfo!=null&& !searchinfo.equals("")){
			hql+=" and t.name like '%"+ searchinfo+"%' ";
		}
		return SPermissionsdao.count(hql);
	}
	
	

	@Override
	public List<SPermissions> find(String searchinfo, int page, int rows) {
		// TODO Auto-generated method stub
		String hql="from "+tablename +" where 1=1 ";		
		if(searchinfo!=null&& !searchinfo.equals("")){			
			hql+=" and t.name like '%"+ searchinfo+"%' ";
		}		
		return SPermissionsdao.find(hql,  page, rows);
	}

}
