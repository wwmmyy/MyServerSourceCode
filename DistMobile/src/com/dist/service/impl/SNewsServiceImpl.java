package com.dist.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.dist.dao.BasicDaoI;
import com.dist.entity.SNews;
import com.dist.service.SNewsServiceI;
@Service("NewsService")
public class SNewsServiceImpl implements SNewsServiceI{

	private BasicDaoI<SNews> SNewsdao;

	private final String tablename=" SNews t ";
	
	
	@Resource
	public void setSNewsDao( BasicDaoI<SNews>  SNewsdao) {
		this.SNewsdao = SNewsdao;
	}

	
	
	@Override
	public String save(SNews t) {
		// TODO Auto-generated method stub
		return SNewsdao.save(t).getId();
	}

	@Override
	public List<SNews> findAll() {
		// TODO Auto-generated method stub
		return SNewsdao.find("from "+tablename +"  order by t.time desc ");
	}

	@Override
	public int deleteById(String id) {
		// TODO Auto-generated method stub
		SNews  t=findById(id);
		if(t !=null){
			t.getPSubscriptionsource().getSNews().remove(t);
		}
		return SNewsdao.deleteById(SNews.class, id);
	}

	@Override
	public int deleteAll() {
		// TODO Auto-generated method stub
		return SNewsdao.executeHql("delete from "+tablename);
	}

	@Override
	public Long count() {
		// TODO Auto-generated method stub
		return SNewsdao.count("select count(*) from "+tablename+" t");
	}

	@Override
	public Long count(Map<String, Object> params) {
		// TODO Auto-generated method stub
		String hql="select count(*) from "+tablename+"  t";
		return SNewsdao.count(hql, params);
	}

	@Override
	public List<SNews> find(int page, int rows) {
		// TODO Auto-generated method stub
		String hql="from "+tablename ;
		hql+=" order by t.time desc ";

		return SNewsdao.find(hql,  page, rows);
	}

	@Override
	public List<SNews> find(Map<String, Object> params, int page, int rows) {
		// TODO Auto-generated method stub
		String hql="from "+tablename ;
		return SNewsdao.find(hql, params, page, rows);
	}

	@Override
	public SNews findById(String id) {
		// TODO Auto-generated method stub
		return SNewsdao.findById(SNews.class,id);
	}



	@Override
	public List<SNews> findByProperty( String propertyName, Object value) {
		// TODO Auto-generated method stub
		return SNewsdao.findByProperty(SNews.class, propertyName, value);
	}



	@Override
	public Long count(String searchinfo) {
		// TODO Auto-generated method stub
		String hql="select count(*) from "+tablename +" where 1=1 ";
		if(searchinfo!=null&& !searchinfo.equals("")){
			hql+=" and t.title like '%"+ searchinfo+"%' ";
		}
		return SNewsdao.count(hql);
	}
	
	

	@Override
	public List<SNews> find(String searchinfo, int page, int rows) {
		// TODO Auto-generated method stub
		String hql="from "+tablename +" where 1=1 ";		
		if(searchinfo!=null&& !searchinfo.equals("")){			
			hql+=" and t.title like '%"+ searchinfo+"%' ";
		}
		hql+=" order by t.time desc ";

		return SNewsdao.find(hql,  page, rows);
	}



}
