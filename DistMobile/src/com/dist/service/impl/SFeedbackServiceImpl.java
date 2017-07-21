package com.dist.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.dist.dao.BasicDaoI;
import com.dist.entity.SFeedback;
import com.dist.service.SFeedbackServiceI;
@Service("feedbackService")
public class SFeedbackServiceImpl implements SFeedbackServiceI {

	private BasicDaoI<SFeedback> SFeedbackdao;

	private final String tablename=" SFeedback t ";
	
	
	@Resource
	public void setSFeedbackDao( BasicDaoI<SFeedback>  SFeedbackdao) {
		this.SFeedbackdao = SFeedbackdao;
	}

	
	
	@Override
	public String save(SFeedback t) {
		// TODO Auto-generated method stub
		return SFeedbackdao.save(t).getId();
	}

	@Override
	public List<SFeedback> findAll() {
		// TODO Auto-generated method stub
		return SFeedbackdao.find("from "+tablename);
	}

	@Override
	public int deleteById(String id) {
		// TODO Auto-generated method stub
		return SFeedbackdao.deleteById(SFeedback.class, id);
	}

	@Override
	public int deleteAll() {
		// TODO Auto-generated method stub
		return SFeedbackdao.executeHql("delete from "+tablename);
	}

	@Override
	public Long count() {
		// TODO Auto-generated method stub
		return SFeedbackdao.count("select count(*) from "+tablename);
	}

	@Override
	public Long count(Map<String, Object> params) {
		// TODO Auto-generated method stub
		String hql="select count(*) from "+tablename;
		return SFeedbackdao.count(hql, params);
	}

	@Override
	public List<SFeedback> find(int page, int rows) {
		// TODO Auto-generated method stub
		String hql="from "+tablename;
		return SFeedbackdao.find(hql,page, rows);
	}

	@Override
	public List<SFeedback> find(Map<String, Object> params, int page, int rows) {
		// TODO Auto-generated method stub
		String hql="from "+tablename;
		return SFeedbackdao.find(hql, params, page, rows);
	}

	@Override
	public SFeedback findById(String id) {
		// TODO Auto-generated method stub
		return SFeedbackdao.findById(SFeedback.class,id);
	}



	@Override
	public List<SFeedback> findByProperty( String propertyName, Object value) {
		// TODO Auto-generated method stub
		return SFeedbackdao.findByProperty(SFeedback.class, propertyName, value);
	}



	@Override
	public Long count(String searchinfo) {
		// TODO Auto-generated method stub
		String hql="select count(*) from "+tablename +" where 1=1 ";
		if(searchinfo!=null&& !searchinfo.equals("")){
			hql+=" and t.content like '%"+ searchinfo+"%' ";
		}
		return SFeedbackdao.count(hql);
	}
	
	

	@Override
	public List<SFeedback> find(String searchinfo, int page, int rows) {
		// TODO Auto-generated method stub
		String hql="from "+tablename +" where 1=1 ";		
		if(searchinfo!=null&& !searchinfo.equals("")){			
			hql+=" and t.content like '%"+ searchinfo+"%' ";
		}		
		hql+=" order by t.feedTime desc";
		return SFeedbackdao.find(hql,  page, rows);
	}



}
