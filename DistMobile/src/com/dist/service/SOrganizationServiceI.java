package com.dist.service;

import java.util.List;

import com.dist.entity.SOrganization;
import com.dist.entity.SRole;
import com.dist.pagentity.Organization;

public interface SOrganizationServiceI extends BasicServiceI<SOrganization>{

////	获取相应条件下的app list
//	public List<SOrganization> find( String searchinfo, int page, int rows) ;
//	
//	public Long count(String searchinfo);
	

	public List<Organization> getTreeNode(String id);

	public List<Organization> getAllTreeNode();

	
	
	
	
}
