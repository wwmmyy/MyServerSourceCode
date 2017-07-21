package com.dist.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.dist.dao.BasicDaoI;
import com.dist.entity.PSns;
import com.dist.entity.PSnsComment;
import com.dist.entity.SDevice;
import com.dist.entity.PSnsPhoots;
import com.dist.service.PSnsPhootsServiceI;
@Service("psnsPhootsService")
public class PSnsPhootsServiceImpl implements PSnsPhootsServiceI {

	private BasicDaoI<PSnsPhoots> PSnsPhootsdao;

	private final String tablename=" PSnsPhoots ";
	
	
	@Resource
	public void setPSnsPhootsDao( BasicDaoI<PSnsPhoots>  PSnsPhootsdao) {
		this.PSnsPhootsdao = PSnsPhootsdao;
	}

	
	
	@Override
	public String save(PSnsPhoots t) {
		// TODO Auto-generated method stub
		return PSnsPhootsdao.save(t).getId();
	}

	@Override
	public List<PSnsPhoots> findAll() {
		// TODO Auto-generated method stub
		return PSnsPhootsdao.find("from "+tablename);
	}

	@Override
	public int deleteById(String id) {
		// TODO Auto-generated method stub
		return PSnsPhootsdao.deleteById(PSnsPhoots.class, id);
	}

	@Override
	public int deleteAll() {
		// TODO Auto-generated method stub
		return PSnsPhootsdao.executeHql("delete from "+tablename);
	}

	@Override
	public Long count() {
		// TODO Auto-generated method stub
		return PSnsPhootsdao.count("select count(*) from "+tablename+" t");
	}

	@Override
	public Long count(Map<String, Object> params) {
		// TODO Auto-generated method stub
		String hql="select count(*) from "+tablename+"  t";
		return PSnsPhootsdao.count(hql, params);
	}

	@Override
	public List<PSnsPhoots> find(int page, int rows) {
		// TODO Auto-generated method stub
		String hql="from "+tablename+"  t";
		return PSnsPhootsdao.find(hql,page, rows);
	}

	@Override
	public List<PSnsPhoots> find(Map<String, Object> params, int page, int rows) {
		// TODO Auto-generated method stub
		String hql="from "+tablename+"  t";
		return PSnsPhootsdao.find(hql, params, page, rows);
	}

	@Override
	public PSnsPhoots findById(String id) {
		// TODO Auto-generated method stub
		return PSnsPhootsdao.findById(PSnsPhoots.class,id);
	}


	@Override
	public List<PSnsPhoots> findByProperty(String propertyName, Object value) {
		// TODO Auto-generated method stub
		return PSnsPhootsdao.findByProperty(PSnsPhoots.class,propertyName, value);
	}



	@Override
	public Long count(String searchinfo) {
		// TODO Auto-generated method stub
		String hql="select count(*) from "+tablename +" where 1=1 ";
		if(searchinfo!=null&& !searchinfo.equals("")){
			hql+=" and t.name like '%"+ searchinfo+"%' ";
		}
		return PSnsPhootsdao.count(hql);
	}
	
	

	@Override
	public List<PSnsPhoots> find(String searchinfo, int page, int rows) {
		// TODO Auto-generated method stub
		String hql="from "+tablename +" where 1=1 ";		
		if(searchinfo!=null&& !searchinfo.equals("")){			
			hql+=" and t.name like '%"+ searchinfo+"%' ";
		}		
		return PSnsPhootsdao.find(hql,  page, rows);
	}

}
