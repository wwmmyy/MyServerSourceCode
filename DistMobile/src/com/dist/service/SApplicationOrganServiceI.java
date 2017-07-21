package com.dist.service;

import java.util.List;

import com.dist.entity.SApplicationorganization;
import com.dist.entity.SApplicationpurview;
import com.dist.entity.SApplications;

public interface SApplicationOrganServiceI extends BasicServiceI<SApplicationorganization>{

//	获取不同应用类型的种类
	public Long count(int appcategory);
	
//	获取相应条件下的app list
	public List<SApplicationorganization> find( String searchinfo,int apptype, int page, int rows) ;
	
	public SApplicationorganization deleteByParams(String orgid,String appid);
	public void saveOrUpdate(SApplicationorganization o); 

	
	
}
