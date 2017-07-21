package com.dist.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.dist.dao.BasicDaoI;
import com.dist.entity.SNewsComment;
import com.dist.service.SNewsCommentServiceI;
@Service("SncommentService")
public class SNewsCommentServiceImpl implements SNewsCommentServiceI {

	private BasicDaoI<SNewsComment> SNewsCommentdao;

	private final String tablename=" SNewsComment t ";
	
	
	@Resource
	public void setSNewsCommentDao( BasicDaoI<SNewsComment>  SNewsCommentdao) {
		this.SNewsCommentdao = SNewsCommentdao;
	}

	
	
	@Override
	public String save(SNewsComment t) {
		// TODO Auto-generated method stub
		return SNewsCommentdao.save(t).getId();
	}

	@Override
	public List<SNewsComment> findAll() {
		// TODO Auto-generated method stub
		return SNewsCommentdao.find("from "+tablename);
	}

	@Override
	public int deleteById(String id) {
		// TODO Auto-generated method stub
		return SNewsCommentdao.deleteById(SNewsComment.class, id);
	}

	@Override
	public int deleteAll() {
		// TODO Auto-generated method stub
		return SNewsCommentdao.executeHql("delete from "+tablename);
	}

	@Override
	public Long count() {
		// TODO Auto-generated method stub
		return SNewsCommentdao.count("select count(*) from "+tablename+" t");
	}

	@Override
	public Long count(Map<String, Object> params) {
		// TODO Auto-generated method stub
		String hql="select count(*) from "+tablename;
		return SNewsCommentdao.count(hql, params);
	}

	@Override
	public List<SNewsComment> find(int page, int rows) {
		// TODO Auto-generated method stub
		String hql="from "+tablename;
		return SNewsCommentdao.find(hql,page, rows);
	}

	@Override
	public List<SNewsComment> find(Map<String, Object> params, int page, int rows) {
		// TODO Auto-generated method stub
		String hql="from "+tablename;
		return SNewsCommentdao.find(hql, params, page, rows);
	}

	@Override
	public SNewsComment findById(String id) {
		// TODO Auto-generated method stub
		return SNewsCommentdao.findById(SNewsComment.class,id);
	}



	@Override
	public List<SNewsComment> findByProperty( String propertyName, Object value) {
		// TODO Auto-generated method stub
		return SNewsCommentdao.findByProperty(SNewsComment.class, propertyName, value);
	}


	@Override
	public Long count(String searchinfo) {
		// TODO Auto-generated method stub
		String hql="select count(*) from "+tablename +" where 1=1 ";
		if(searchinfo!=null&& !searchinfo.equals("")){
			hql+=" and t.content like '%"+ searchinfo+"%' ";
		}
		return SNewsCommentdao.count(hql);
	}
	
	

	@Override
	public List<SNewsComment> find(String searchinfo, int page, int rows) {
		// TODO Auto-generated method stub
		String hql="from "+tablename +" where 1=1 ";		
		if(searchinfo!=null&& !searchinfo.equals("")){			
			hql+=" and t.content like '%"+ searchinfo+"%' ";
		}	
		hql+=" order by t.createTime desc";
		return SNewsCommentdao.find(hql,  page, rows);
	}



	

}
