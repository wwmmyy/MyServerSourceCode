package com.dist.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.dist.dao.BasicDaoI;
import com.dist.entity.PUsersubscription;
import com.dist.entity.SUsers;
import com.dist.service.PUsersubscriptionServiceI;
import com.dist.util.Encrypt;
/**
 * 用户订阅消息
 * @author wmy
 *
 */
@Service("PUsersubscriptionService")
public class PUsersubscriptionServiceImpl implements PUsersubscriptionServiceI {

	private BasicDaoI<PUsersubscription> PUsersubscriptiondao;

	private final String tablename=" PUsersubscription t ";
	
	
	@Resource
	public void setPUsersubscriptionDao( BasicDaoI<PUsersubscription>  PUsersubscriptiondao) {
		this.PUsersubscriptiondao = PUsersubscriptiondao;
	}

	
	
	@Override
	public String save(PUsersubscription t) {
		// TODO Auto-generated method stub
		return PUsersubscriptiondao.save(t).getId();
	}

	@Override
	public List<PUsersubscription> findAll() {
		// TODO Auto-generated method stub
		return PUsersubscriptiondao.find("from "+tablename);
	}

	@Override
	public int deleteById(String id) {
		// TODO Auto-generated method stub
		return PUsersubscriptiondao.deleteById(PUsersubscription.class, id);
	}

	@Override
	public int deleteAll() {
		// TODO Auto-generated method stub
		return PUsersubscriptiondao.executeHql("delete from "+tablename);
	}

	@Override
	public Long count() {
		// TODO Auto-generated method stub
		return PUsersubscriptiondao.count("select count(*) from "+tablename+" t");
	}

	@Override
	public Long count(Map<String, Object> params) {
		// TODO Auto-generated method stub
		String hql="select count(*) from "+tablename+"  t";
		return PUsersubscriptiondao.count(hql, params);
	}

	@Override
	public List<PUsersubscription> find(int page, int rows) {
		// TODO Auto-generated method stub
		String hql="from "+tablename+"  t";
		return PUsersubscriptiondao.find(hql,page, rows);
	}

	@Override
	public List<PUsersubscription> find(Map<String, Object> params, int page, int rows) {
		// TODO Auto-generated method stub
		String hql="from "+tablename+"  t";
		return PUsersubscriptiondao.find(hql, params, page, rows);
	}

	@Override
	public PUsersubscription findById(String id) {
		// TODO Auto-generated method stub
		return PUsersubscriptiondao.findById(PUsersubscription.class,id);
	}



	@Override
	public List<PUsersubscription> findByProperty( String propertyName, Object value) {
		// TODO Auto-generated method stub
		return PUsersubscriptiondao.findByProperty(PUsersubscription.class, propertyName, value);
	}



	@Override
	public Long count(String searchinfo) {
		// TODO Auto-generated method stub
		String hql="select count(*) from "+tablename +" where 1=1 ";
		if(searchinfo!=null&& !searchinfo.equals("")){
			hql+=" and t.content like '%"+ searchinfo+"%' ";
		}
		return PUsersubscriptiondao.count(hql);
	}
	
	

	@Override
	public List<PUsersubscription> find(String searchinfo, int page, int rows) {
		// TODO Auto-generated method stub
		String hql="from "+tablename +" where 1=1 ";		
		if(searchinfo!=null&& !searchinfo.equals("")){			
			hql+=" and t.content like '%"+ searchinfo+"%' ";
		}		
		hql+=" order by t.publishTime desc";
		return PUsersubscriptiondao.find(hql,  page, rows);
	}



	@Override
	public PUsersubscription deleteByParams(String userid, String sourceid) {
		
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("userId", userid);
		params.put("SourceId",sourceid);
		List<PUsersubscription>  list= PUsersubscriptiondao.get("from PUsersubscription t where t.userId = :userId and t.PSubscriptionsource.id = :SourceId", params);
		PUsersubscription t=null;
		if (list != null && list.size() > 0) {
			t=list.get(0);
		}		
		System.out.println("查询到的订阅信息为：：：：："+JSON.toJSONString(t));
		if (t != null) {
			t.getPSubscriptionsource().getPUsersubscriptions().remove(t);
			PUsersubscriptiondao.delete(t);
			return null;
		}
		return null;	
	}



}
