package com.dist.service;

import java.util.List;

import com.dist.entity.SApplications;

public interface SApplicationsServiceI extends BasicServiceI<SApplications>{

//	获取不同应用类型的种类
	public Long count(int appcategory);
	
//	获取相应条件下的app list
	public List<SApplications> find( String searchinfo,int apptype, int page, int rows) ;
	
	
	public void saveOrUpdate(SApplications o); 

	
	
}
