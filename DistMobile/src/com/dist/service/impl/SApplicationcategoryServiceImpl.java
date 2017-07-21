package com.dist.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.dist.dao.BasicDaoI;
import com.dist.entity.SApplicationpurview;
import com.dist.entity.SDevice;
import com.dist.entity.SApplicationcategory;
import com.dist.entity.SDevicetype;
import com.dist.service.SApplicationcategoryServiceI;
@Service("applicationcategoryService")
public class SApplicationcategoryServiceImpl implements SApplicationcategoryServiceI {

	private BasicDaoI<SApplicationcategory> SApplicationcategorydao;

	private final String tablename=" SApplicationcategory ";
	
	
	@Resource
	public void setSApplicationcategoryDao( BasicDaoI<SApplicationcategory>  SApplicationcategorydao) {
		this.SApplicationcategorydao = SApplicationcategorydao;
	}

	
	
	@Override
	public String save(SApplicationcategory t) {
		// TODO Auto-generated method stub
		return SApplicationcategorydao.save(t).getId();
	}

	@Override
	public List<SApplicationcategory> findAll() {
		// TODO Auto-generated method stub
		return SApplicationcategorydao.find("from "+tablename);
	}

	@Override
	public int deleteById(String id) {
		// TODO Auto-generated method stub
		return SApplicationcategorydao.deleteById(SApplicationcategory.class, id);
	}

	@Override
	public int deleteAll() {
		// TODO Auto-generated method stub
		return SApplicationcategorydao.executeHql("delete from "+tablename);
	}

	@Override
	public Long count() {
		// TODO Auto-generated method stub
		return SApplicationcategorydao.count("select count(*) from "+tablename+" t");
	}

	@Override
	public Long count(Map<String, Object> params) {
		// TODO Auto-generated method stub
		String hql="select count(*) from "+tablename+"  t";
		return SApplicationcategorydao.count(hql, params);
	}

	@Override
	public List<SApplicationcategory> find(int page, int rows) {
		// TODO Auto-generated method stub
		String hql="from "+tablename+"  t";
		return SApplicationcategorydao.find(hql,page, rows);
	}

	@Override
	public List<SApplicationcategory> find(Map<String, Object> params, int page, int rows) {
		// TODO Auto-generated method stub
		String hql="from "+tablename+"  t";
		return SApplicationcategorydao.find(hql, params, page, rows);
	}

	@Override
	public SApplicationcategory findById(String id) {
		// TODO Auto-generated method stub
		return SApplicationcategorydao.findById(SApplicationcategory.class,id);
	}



	@Override
	public List<SApplicationcategory> findByProperty(String propertyName, Object value) {
		// TODO Auto-generated method stub
		return SApplicationcategorydao.findByProperty(SApplicationcategory.class, propertyName, value);	}



	@Override
	public Long count(String searchinfo) {
		// TODO Auto-generated method stub
		String hql="select count(*) from "+tablename +" where 1=1 ";
		if(searchinfo!=null&& !searchinfo.equals("")){
			hql+=" and t.name like '%"+ searchinfo+"%' ";
		}
		return SApplicationcategorydao.count(hql);
	}
	
	

	@Override
	public List<SApplicationcategory> find(String searchinfo, int page, int rows) {
		// TODO Auto-generated method stub
		String hql="from "+tablename +" where 1=1 ";		
		if(searchinfo!=null&& !searchinfo.equals("")){			
			hql+=" and t.name like '%"+ searchinfo+"%' ";
		}		
		return SApplicationcategorydao.find(hql,  page, rows);
	}
	

}
