package com.dist.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.dist.dao.BasicDaoI;
import com.dist.entity.SNewspic;
import com.dist.service.SNewspicServiceI;
@Service("NewsPicService")
public class SNewspicServiceImpl implements SNewspicServiceI{

	private BasicDaoI<SNewspic> SNewspicdao;

	private final String tablename=" SNewspic ";
	
	
	@Resource
	public void setSNewspicDao( BasicDaoI<SNewspic>  SNewspicdao) {
		this.SNewspicdao = SNewspicdao;
	}

	
	
	@Override
	public String save(SNewspic t) {
		// TODO Auto-generated method stub
		return SNewspicdao.save(t).getId();
	}

	@Override
	public List<SNewspic> findAll() {
		// TODO Auto-generated method stub
		return SNewspicdao.find("from "+tablename);
	}

	@Override
	public int deleteById(String id) {
		// TODO Auto-generated method stub
		return SNewspicdao.deleteById(SNewspic.class, id);
	}

	@Override
	public int deleteAll() {
		// TODO Auto-generated method stub
		return SNewspicdao.executeHql("delete from "+tablename);
	}

	@Override
	public Long count() {
		// TODO Auto-generated method stub
		return SNewspicdao.count("select count(*) from "+tablename+" t");
	}

	@Override
	public Long count(Map<String, Object> params) {
		// TODO Auto-generated method stub
		String hql="select count(*) from "+tablename+"  t";
		return SNewspicdao.count(hql, params);
	}

	@Override
	public List<SNewspic> find(int page, int rows) {
		// TODO Auto-generated method stub
		String hql="from "+tablename+"  t";
		return SNewspicdao.find(hql,page, rows);
	}

	@Override
	public List<SNewspic> find(Map<String, Object> params, int page, int rows) {
		// TODO Auto-generated method stub
		String hql="from "+tablename+"  t";
		return SNewspicdao.find(hql, params, page, rows);
	}

	@Override
	public SNewspic findById(String id) {
		// TODO Auto-generated method stub
		return SNewspicdao.findById(SNewspic.class,id);
	}



	@Override
	public List<SNewspic> findByProperty( String propertyName, Object value) {
		// TODO Auto-generated method stub
		return SNewspicdao.findByProperty(SNewspic.class, propertyName, value);
	}



	@Override
	public Long count(String searchinfo) {
		// TODO Auto-generated method stub
		String hql="select count(*) from "+tablename +" where 1=1 ";
		if(searchinfo!=null&& !searchinfo.equals("")){
			hql+=" and t.title like '%"+ searchinfo+"%' ";
		}
		return SNewspicdao.count(hql);
	}
	
	

	@Override
	public List<SNewspic> find(String searchinfo, int page, int rows) {
		// TODO Auto-generated method stub
		String hql="from "+tablename +" where 1=1 ";		
		if(searchinfo!=null&& !searchinfo.equals("")){			
			hql+=" and t.title like '%"+ searchinfo+"%' ";
		}		
		return SNewspicdao.find(hql,  page, rows);
	}



}
