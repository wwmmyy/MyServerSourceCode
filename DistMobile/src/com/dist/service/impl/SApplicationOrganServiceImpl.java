package com.dist.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.dist.dao.BasicDaoI;
import com.dist.entity.SApplicationorganization;
import com.dist.entity.SApplicationpurview;
import com.dist.entity.SUsers;
import com.dist.pagentity.AppsPageShow;
import com.dist.service.SApplicationOrganServiceI;
@Service("apporganService")
public class SApplicationOrganServiceImpl implements SApplicationOrganServiceI {

	private BasicDaoI<SApplicationorganization> SApplicationorganizationdao;

	private final String tablename=" SApplicationorganization t ";
	
	
	@Resource
	public void setSApplicationorganizationDao( BasicDaoI<SApplicationorganization>  SApplicationorganizationdao) {
		this.SApplicationorganizationdao = SApplicationorganizationdao;
	}

	
	
	@Override
	public String save(SApplicationorganization t) {
		// TODO Auto-generated method stub
		return SApplicationorganizationdao.save(t).getId();
	}

	@Override
	public List<SApplicationorganization> findAll() {
		// TODO Auto-generated method stub
		return SApplicationorganizationdao.find("from "+tablename);
	}

	@Override
	public int deleteById(String id) {
		// TODO Auto-generated method stub
		return SApplicationorganizationdao.deleteById(SApplicationorganization.class, id);
	}

	@Override
	public int deleteAll() {
		// TODO Auto-generated method stub
		return SApplicationorganizationdao.executeHql("delete from "+tablename);
	}

	@Override
	public Long count(int appcategory) {
		// TODO Auto-generated method stub
		String hql="select count(*) from "+tablename+"  where 1=1  ";
		hql = getHqlByApptype(appcategory, hql);
		
		
		return SApplicationorganizationdao.count(hql);
	}


/**
 * 根据app类型获取拼接的 hql
 * @param appcategory
 * @param hql
 * @return
 */
	private String getHqlByApptype(int appcategory, String hql) {
		switch (appcategory) {
			case AppsPageShow.ALL:
				break;
			case AppsPageShow.ANDROIDALL:
				hql+=" and t.SApplicationcategory.name like '%android%' ";
					break;
			case AppsPageShow.ANDROIDPAD:
				hql+=" and t.SApplicationcategory.name = 'android平板' ";
				break;
			case AppsPageShow.ANDROIDPHONE:
				hql+=" and t.SApplicationcategory.name = 'android手机' ";
				break;
			case AppsPageShow.IOSALL:
				hql+=" and t.SApplicationcategory.name like '%IOS%' ";
				break;
			case AppsPageShow.IOSPAD:
				hql+=" and t.SApplicationcategory.name = 'IOS平板' ";
				break;
			case AppsPageShow.IOSPHONE:
				hql+=" and t.SApplicationcategory.name = 'IOS手机' ";
			break;
			default:
				break;
		}
		return hql;
	}
	
	
	@Override
	public Long count() {
		// TODO Auto-generated method stub
		return SApplicationorganizationdao.count("select count(*) from "+tablename+" ");
	}

	@Override
	public Long count(Map<String, Object> params) {
		// TODO Auto-generated method stub
		String hql="select count(*) from "+tablename+" ";
		return SApplicationorganizationdao.count(hql, params);
	}

	@Override
	public List<SApplicationorganization> find(int page, int rows) {
		// TODO Auto-generated method stub
		String hql="from "+tablename+" ";
		return SApplicationorganizationdao.find(hql,page, rows);
	}

	@Override
	public List<SApplicationorganization> find(Map<String, Object> params, int page, int rows) {
		// TODO Auto-generated method stub
		String hql="from "+tablename+" ";
		return SApplicationorganizationdao.find(hql, params, page, rows);
	}

	
	@Override
	public List<SApplicationorganization> find( String searchinfo,int apptype, int page, int rows) {
		// TODO Auto-generated method stubi
		String hql="from "+tablename +" where 1=1 ";

		
		if(searchinfo!=null&& !searchinfo.equals("")){
			
			hql+=" and t.name like '%"+ searchinfo+"%' ";
		}
		
		return SApplicationorganizationdao.find(getHqlByApptype( apptype,hql),  page, rows);
	}
	
	
	
	
	
	
	
	
	@Override
	public SApplicationorganization findById(String id) {
		// TODO Auto-generated method stub
		return SApplicationorganizationdao.findById(SApplicationorganization.class,id);
	}


	@Override
	public List<SApplicationorganization> findByProperty(String propertyName, Object value) {
		// TODO Auto-generated method stub
		return SApplicationorganizationdao.findByProperty(SApplicationorganization.class, propertyName, value);
	}


	@Override
	public Long count(String searchinfo) {
		// TODO Auto-generated method stub
		String hql="select count(*) from "+tablename +" where 1=1 ";
		if(searchinfo!=null&& !searchinfo.equals("")){
			hql+=" and t.name like '%"+ searchinfo+"%' ";
		}
		return SApplicationorganizationdao.count(hql);
	}
	
	

	@Override
	public List<SApplicationorganization> find(String searchinfo, int page, int rows) {
		// TODO Auto-generated method stub
		String hql="from "+tablename +" where 1=1 ";		
		if(searchinfo!=null&& !searchinfo.equals("")){			
			hql+=" and t.name like '%"+ searchinfo+"%' ";
		}		
		return SApplicationorganizationdao.find(hql,  page, rows);
	}



	@Override
	public void saveOrUpdate(SApplicationorganization o) {
		// TODO Auto-generated method stub
//		SApplicationorganizationdao.saveOrUpdate(o);
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("app",o.getSApplications());
		params.put("org", o.getSOrganization());
		List<SApplicationorganization>  list= SApplicationorganizationdao.get(
				"from SApplicationorganization t " +"where t.SApplications= :app" +
				" and t.SOrganization= :org", params);
		if (list != null && list.size() > 0) {
			
		}	else{
			SApplicationorganizationdao.save(o);
		}
	}
	
	@Override
	public SApplicationorganization deleteByParams(String orgid, String appid) {
		
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("orgId", orgid);
		params.put("appId",appid);
		List<SApplicationorganization>  list= SApplicationorganizationdao.get("from SApplicationorganization t where t.SOrganization.id = :orgId and t.SApplications.id = :appId", params);
		SApplicationorganization t=null;
		if (list != null && list.size() > 0) {
			t=list.get(0);
		}		
		System.out.println("查询到的订阅信息为：：：：："+JSON.toJSONString(t));
		if (t != null) {
			t.getSApplications().getSApplicationorganizations().remove(t);
			t.getSOrganization().getSApplicationorganizations().remove(t);
			SApplicationorganizationdao.delete(t);
			return null;
		}
		return null;	
	}

}
