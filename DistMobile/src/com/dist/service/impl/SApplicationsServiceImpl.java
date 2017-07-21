package com.dist.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.dist.dao.BasicDaoI;
import com.dist.entity.PSnsComment;
import com.dist.entity.SApplicationplatform;
import com.dist.entity.SDevice;
import com.dist.entity.SApplications;
import com.dist.entity.SDeviceapplications;
import com.dist.pagentity.AppsPageShow;
import com.dist.service.SApplicationsServiceI;
@Service("applicationsService")
public class SApplicationsServiceImpl implements SApplicationsServiceI {

	private BasicDaoI<SApplications> SApplicationsdao;

	private final String tablename=" SApplications t ";
	
	
	@Resource
	public void setSApplicationsDao( BasicDaoI<SApplications>  SApplicationsdao) {
		this.SApplicationsdao = SApplicationsdao;
	}

	
	
	@Override
	public String save(SApplications t) {
		// TODO Auto-generated method stub
		return SApplicationsdao.save(t).getId();
	}

	@Override
	public List<SApplications> findAll() {
		// TODO Auto-generated method stub
		return SApplicationsdao.find("from "+tablename);
	}

	@Override
	public int deleteById(String id) {
		// TODO Auto-generated method stub
		
//		SApplications app=findById(id);
//		if(app!=null){
//			app.getSApplicationcategory().getSApplicationses().remove(app);
//			app.getSApplicationpurviews().
//			.remove(app);			
//		}
		
		return SApplicationsdao.deleteById(SApplications.class, id);
	}

	@Override
	public int deleteAll() {
		// TODO Auto-generated method stub
		return SApplicationsdao.executeHql("delete from "+tablename);
	}

	@Override
	public Long count(int appcategory) {
		// TODO Auto-generated method stub
		String hql="select count(*) from "+tablename+"  where 1=1  ";
		hql = getHqlByApptype(appcategory, hql);
		
		
		return SApplicationsdao.count(hql);
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
		return SApplicationsdao.count("select count(*) from "+tablename+" ");
	}

	@Override
	public Long count(Map<String, Object> params) {
		// TODO Auto-generated method stub
		String hql="select count(*) from "+tablename+" ";
		return SApplicationsdao.count(hql, params);
	}

	@Override
	public List<SApplications> find(int page, int rows) {
		// TODO Auto-generated method stub
		String hql="from "+tablename+" ";
		return SApplicationsdao.find(hql,page, rows);
	}

	@Override
	public List<SApplications> find(Map<String, Object> params, int page, int rows) {
		// TODO Auto-generated method stub
		String hql="from "+tablename+" ";
		return SApplicationsdao.find(hql, params, page, rows);
	}

	
	@Override
	public List<SApplications> find( String searchinfo,int apptype, int page, int rows) {
		// TODO Auto-generated method stubi
		String hql="from "+tablename +" where 1=1 ";

		
		if(searchinfo!=null&& !searchinfo.equals("")){
			
			hql+=" and t.name like '%"+ searchinfo+"%' ";
		}
		
		return SApplicationsdao.find(getHqlByApptype( apptype,hql),  page, rows);
	}
	
	
	
	
	
	
	
	
	@Override
	public SApplications findById(String id) {
		// TODO Auto-generated method stub
		return SApplicationsdao.findById(SApplications.class,id);
	}


	@Override
	public List<SApplications> findByProperty(String propertyName, Object value) {
		// TODO Auto-generated method stub
		return SApplicationsdao.findByProperty(SApplications.class, propertyName, value);
	}


	@Override
	public Long count(String searchinfo) {
		// TODO Auto-generated method stub
		String hql="select count(*) from "+tablename +" where 1=1 ";
		if(searchinfo!=null&& !searchinfo.equals("")){
			hql+=" and t.name like '%"+ searchinfo+"%' ";
		}
		return SApplicationsdao.count(hql);
	}
	
	

	@Override
	public List<SApplications> find(String searchinfo, int page, int rows) {
		// TODO Auto-generated method stub
		String hql="from "+tablename +" where 1=1 ";		
		if(searchinfo!=null&& !searchinfo.equals("")){			
			hql+=" and t.name like '%"+ searchinfo+"%' ";
		}		
		return SApplicationsdao.find(hql,  page, rows);
	}



	@Override
	public void saveOrUpdate(SApplications o) {
		// TODO Auto-generated method stub
		SApplicationsdao.saveOrUpdate(o);
		
	}
}
