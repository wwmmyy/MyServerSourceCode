package com.dist.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.dist.dao.BasicDaoI;
import com.dist.entity.PSnsPhoots;
import com.dist.entity.SDevice;
import com.dist.entity.PSnsComment;
import com.dist.service.PSnsCommentServiceI;
@Service("psnsCommentService")
public class PSnsCommentServiceImpl implements PSnsCommentServiceI {

	private BasicDaoI<PSnsComment> PSnsCommentdao;

	private final String tablename=" PSnsComment ";
	
	
	@Resource
	public void setPSnsCommentDao( BasicDaoI<PSnsComment>  PSnsCommentdao) {
		this.PSnsCommentdao = PSnsCommentdao;
	}

	
	
	@Override
	public String save(PSnsComment t) {
		// TODO Auto-generated method stub
		return PSnsCommentdao.save(t).getId();
	}

	@Override
	public List<PSnsComment> findAll() {
		// TODO Auto-generated method stub
		return PSnsCommentdao.find("from "+tablename);
	}

	@Override
	public int deleteById(String id) {
		// TODO Auto-generated method stub
		return PSnsCommentdao.deleteById(PSnsComment.class, id);
	}

	@Override
	public int deleteAll() {
		// TODO Auto-generated method stub
		return PSnsCommentdao.executeHql("delete from "+tablename);
	}

	@Override
	public Long count() {
		// TODO Auto-generated method stub
		return PSnsCommentdao.count("select count(*) from "+tablename+" t");
	}

	@Override
	public Long count(Map<String, Object> params) {
		// TODO Auto-generated method stub
		String hql="select count(*) from "+tablename+"  t";
		return PSnsCommentdao.count(hql, params);
	}

	@Override
	public List<PSnsComment> find(int page, int rows) {
		// TODO Auto-generated method stub
		String hql="from "+tablename+"  t";
		return PSnsCommentdao.find(hql,page, rows);
	}

	@Override
	public List<PSnsComment> find(Map<String, Object> params, int page, int rows) {
		// TODO Auto-generated method stub
		String hql="from "+tablename+"  t";
		return PSnsCommentdao.find(hql, params, page, rows);
	}

	@Override
	public PSnsComment findById(String id) {
		// TODO Auto-generated method stub
		return PSnsCommentdao.findById(PSnsComment.class,id);
	}



	@Override
	public List<PSnsComment> findByProperty( String propertyName, Object value) {
		// TODO Auto-generated method stub
		return PSnsCommentdao.findByProperty(PSnsComment.class, propertyName, value);
	}


	@Override
	public Long count(String searchinfo) {
		// TODO Auto-generated method stub
		String hql="select count(*) from "+tablename +" where 1=1 ";
		if(searchinfo!=null&& !searchinfo.equals("")){
			hql+=" and t.content like '%"+ searchinfo+"%' ";
		}
		return PSnsCommentdao.count(hql);
	}
	
	

	@Override
	public List<PSnsComment> find(String searchinfo, int page, int rows) {
		// TODO Auto-generated method stub
		String hql="from "+tablename +" where 1=1 ";		
		if(searchinfo!=null&& !searchinfo.equals("")){			
			hql+=" and t.content like '%"+ searchinfo+"%' ";
		}	
		hql+=" order by t.createTime desc";
		return PSnsCommentdao.find(hql,  page, rows);
	}



	@Override
	public PSnsComment saveResult(PSnsComment t) {
		// TODO Auto-generated method stub
		return PSnsCommentdao.save(t);
	}



	

}
