package com.dist.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.dist.dao.BasicDaoI;
import com.dist.entity.SApplicationplatform;
import com.dist.service.SApplicationPlatformServiceI;
@Service("applicationPlatformService")
public class SApplicationPlatformServiceImpl implements SApplicationPlatformServiceI {

	private BasicDaoI<SApplicationplatform> AppPlatformdao;

	private final String tablename=" SApplicationplatform t ";
	
	
	@Resource
	public void setSApplicationsDao( BasicDaoI<SApplicationplatform>  SApplicationsdao) {
		this.AppPlatformdao = SApplicationsdao;
	}

	
	
	@Override
	public String save(SApplicationplatform t) {
		// TODO Auto-generated method stub
		return AppPlatformdao.save(t).getId();
	}

	@Override
	public List<SApplicationplatform> findAll() {
		// TODO Auto-generated method stub
		return AppPlatformdao.find("from "+tablename);
	}

	@Override
	public int deleteById(String id) {	
		
		SApplicationplatform apppaltform=findById(id);
		if(apppaltform!=null&&apppaltform.getSApplications()!=null&&apppaltform.getSApplications().getSApplicationplatforms()!=null){
			apppaltform.getSApplications().getSApplicationplatforms().remove(apppaltform);			
		}
		
		
		return AppPlatformdao.deleteById(SApplicationplatform.class, id);
		}

	@Override
	public int deleteAll() {
		// TODO Auto-generated method stub
		return AppPlatformdao.executeHql("delete from "+tablename);
	}

	@Override
	public Long count(int appcategory) {
		// TODO Auto-generated method stub
		String hql="select count(*) from "+tablename+"  where 1=1  ";
		
		
		return AppPlatformdao.count(hql);
	}


	
	@Override
	public Long count() {
		// TODO Auto-generated method stub
		return AppPlatformdao.count("select count(*) from "+tablename+" ");
	}

	@Override
	public Long count(Map<String, Object> params) {
		// TODO Auto-generated method stub
		String hql="select count(*) from "+tablename+" ";
		return AppPlatformdao.count(hql, params);
	}

	@Override
	public List<SApplicationplatform> find(int page, int rows) {
		// TODO Auto-generated method stub
		String hql="from "+tablename+" ";
		return AppPlatformdao.find(hql,page, rows);
	}

	@Override
	public List<SApplicationplatform> find(Map<String, Object> params, int page, int rows) {
		// TODO Auto-generated method stub
		String hql="from "+tablename+" ";
		return AppPlatformdao.find(hql, params, page, rows);
	}

	
	@Override
	public List<SApplicationplatform> find( String searchinfo,int apptype, int page, int rows) {
		// TODO Auto-generated method stubi
		String hql="from "+tablename +" where 1=1 ";

		
		if(searchinfo!=null&& !searchinfo.equals("")){
			
			hql+=" and t.name like '%"+ searchinfo+"%' ";
		}
		
		return AppPlatformdao.find(hql,  page, rows);
	}
	
	
	
	
	
	
	
	
	@Override
	public SApplicationplatform findById(String id) {
		// TODO Auto-generated method stub
		return AppPlatformdao.findById(SApplicationplatform.class,id);
	}


	@Override
	public List<SApplicationplatform> findByProperty(String propertyName, Object value) {
		// TODO Auto-generated method stub
		return AppPlatformdao.findByProperty(SApplicationplatform.class, propertyName, value);
	}


	@Override
	public Long count(String searchinfo) {
		// TODO Auto-generated method stub
		String hql="select count(*) from "+tablename +" where 1=1 ";
		if(searchinfo!=null&& !searchinfo.equals("")){
			hql+=" and t.name like '%"+ searchinfo+"%' ";
		}
		return AppPlatformdao.count(hql);
	}
	
	

	@Override
	public List<SApplicationplatform> find(String searchinfo, int page, int rows) {
		// TODO Auto-generated method stub
		String hql="from "+tablename +" where 1=1 ";		
		if(searchinfo!=null&& !searchinfo.equals("")){			
			hql+=" and t.name like '%"+ searchinfo+"%' ";
		}		
		return AppPlatformdao.find(hql,  page, rows);
	}



	@Override
	public void saveOrUpdate(SApplicationplatform o) {
		// TODO Auto-generated method stub
		AppPlatformdao.saveOrUpdate(o);
		
	}



	@Override
	public SApplicationplatform findByApplicationIdentify(
			String applicationIdentify) {
		String hql="from "+tablename +" where applicationIdentity = :appId ";	
		Map<String,Object> args = new HashMap<String, Object>();
		args.put("appId", applicationIdentify);
		List<SApplicationplatform> list = AppPlatformdao.find(hql, args);
		if(list!=null&&list.size()>0){
			return list.get(0);
		}else{
			return null;
		}
	}
	
	
}
