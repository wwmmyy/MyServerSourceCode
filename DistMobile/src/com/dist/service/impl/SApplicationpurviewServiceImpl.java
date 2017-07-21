package com.dist.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.dist.dao.BasicDaoI;
import com.dist.entity.PSnsComment;
import com.dist.entity.PUsersubscription;
import com.dist.entity.SApplications;
import com.dist.entity.SDevice;
import com.dist.entity.SApplicationpurview;
import com.dist.entity.SUsers;
import com.dist.service.SApplicationpurviewServiceI;
import com.dist.util.Encrypt;
@Service("applicationpurviewService")
public class SApplicationpurviewServiceImpl implements SApplicationpurviewServiceI {

	private BasicDaoI<SApplicationpurview> SApplicationpurviewdao;

	private final String tablename=" SApplicationpurview ";
	
	
	@Resource
	public void setSApplicationpurviewDao( BasicDaoI<SApplicationpurview>  SApplicationpurviewdao) {
		this.SApplicationpurviewdao = SApplicationpurviewdao;
	}

	
	
	@Override
	public String save(SApplicationpurview t) {
		// TODO Auto-generated method stub
		return SApplicationpurviewdao.save(t).getId();
	}

	@Override
	public List<SApplicationpurview> findAll() {
		// TODO Auto-generated method stub
		return SApplicationpurviewdao.find("from "+tablename);
	}

	@Override
	public int deleteById(String id) {
		// TODO Auto-generated method stub
		return SApplicationpurviewdao.deleteById(SApplicationpurview.class, id);
	}

	@Override
	public int deleteAll() {
		// TODO Auto-generated method stub
		return SApplicationpurviewdao.executeHql("delete from "+tablename);
	}

	@Override
	public Long count() {
		// TODO Auto-generated method stub
		return SApplicationpurviewdao.count("select count(*) from "+tablename+" t");
	}

	@Override
	public Long count(Map<String, Object> params) {
		// TODO Auto-generated method stub
		String hql="select count(*) from "+tablename+"  t";
		return SApplicationpurviewdao.count(hql, params);
	}

	@Override
	public List<SApplicationpurview> find(int page, int rows) {
		// TODO Auto-generated method stub
		String hql="from "+tablename+"  t";
		return SApplicationpurviewdao.find(hql,page, rows);
	}

	@Override
	public List<SApplicationpurview> find(Map<String, Object> params, int page, int rows) {
		// TODO Auto-generated method stub
		String hql="from "+tablename+"  t";
		return SApplicationpurviewdao.find(hql, params, page, rows);
	}

	@Override
	public SApplicationpurview findById(String id) {
		// TODO Auto-generated method stub
		return SApplicationpurviewdao.findById(SApplicationpurview.class,id);
	}


	@Override
	public List<SApplicationpurview> findByProperty(String propertyName, Object value) {
		// TODO Auto-generated method stub
		return SApplicationpurviewdao.findByProperty(SApplicationpurview.class, propertyName, value);
	}



	@Override
	public Long count(String searchinfo) {
		// TODO Auto-generated method stub
		String hql="select count(*) from "+tablename +" where 1=1 ";
		if(searchinfo!=null&& !searchinfo.equals("")){
			hql+=" and t.name like '%"+ searchinfo+"%' ";
		}
		return SApplicationpurviewdao.count(hql);
	}
	
	

	@Override
	public List<SApplicationpurview> find(String searchinfo, int page, int rows) {
		// TODO Auto-generated method stub
		String hql="from "+tablename +" where 1=1 ";		
		if(searchinfo!=null&& !searchinfo.equals("")){			
			hql+=" and t.name like '%"+ searchinfo+"%' ";
		}		
		return SApplicationpurviewdao.find(hql,  page, rows);
	}



	@Override
	public String saveOrUpade(SApplicationpurview userapp) {

			// TODO Auto-generated method stub
	//		logger.info("测试日志记录能否成功");  
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("app",userapp.getSApplications());
			params.put("userId", userapp.getUserId());
			SUsers t =null;
			List<SApplicationpurview>  list= SApplicationpurviewdao.get("from SApplicationpurview t where t.SApplications= :app" +
					" and t.userId = :userId", params);
			if (list != null && list.size() > 0) {
				return "";
			}	else{
				SApplicationpurviewdao.save(userapp);
			}
			return null;
	}



	@Override
	public SApplicationpurview deleteByParams(String userid, String appid) {
		
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("userId", userid);
		params.put("appId",appid);
		List<SApplicationpurview>  list= SApplicationpurviewdao.get("from SApplicationpurview t where t.userId = :userId and t.SApplications.id = :appId", params);
		SApplicationpurview t=null;
		if (list != null && list.size() > 0) {
			t=list.get(0);
		}		
//		System.out.println("查询到的订阅信息为：：：：："+JSON.toJSONString(t));
		if (t != null) {
			t.getSApplications().getSApplicationpurviews().remove(t);
			SApplicationpurviewdao.delete(t);
			return null;
		}
		return null;	
	}


}
