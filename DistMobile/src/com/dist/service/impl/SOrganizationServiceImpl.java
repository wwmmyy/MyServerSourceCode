package com.dist.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import com.dist.dao.BasicDaoI;
import com.dist.entity.PSnsComment;
import com.dist.entity.SDevice;
import com.dist.entity.SOrganization;
import com.dist.entity.SRole;
import com.dist.pagentity.Organization;
import com.dist.service.SOrganizationServiceI;
@Service("organizationService")
public class SOrganizationServiceImpl implements SOrganizationServiceI {

	private BasicDaoI<SOrganization> SOrganizationdao;

	private final String tablename=" SOrganization ";
	
	
	@Resource
	public void setSOrganizationDao( BasicDaoI<SOrganization>  SOrganizationdao) {
		this.SOrganizationdao = SOrganizationdao;
	}

	
	
	@Override
	public String save(SOrganization t) {
		// TODO Auto-generated method stub
		return SOrganizationdao.save(t).getId();
	}

	@Override
	public List<SOrganization> findAll() {
		// TODO Auto-generated method stub
		return SOrganizationdao.find("from "+tablename);
	}

	@Override
	public int deleteById(String id) {
		// TODO Auto-generated method stub
		return SOrganizationdao.deleteById(SOrganization.class, id);
	}

	@Override
	public int deleteAll() {
		// TODO Auto-generated method stub
		return SOrganizationdao.executeHql("delete from "+tablename);
	}

	@Override
	public Long count() {
		// TODO Auto-generated method stub
		return SOrganizationdao.count("select count(*) from "+tablename+" t");
	}

	@Override
	public Long count(Map<String, Object> params) {
		// TODO Auto-generated method stub
		String hql="select count(*) from "+tablename+"  t";
		return SOrganizationdao.count(hql, params);
	}

	@Override
	public List<SOrganization> find(int page, int rows) {
		// TODO Auto-generated method stub
		String hql="from "+tablename+"  t";
		return SOrganizationdao.find(hql,page, rows);
	}

	@Override
	public List<SOrganization> find(Map<String, Object> params, int page, int rows) {
		// TODO Auto-generated method stub
		String hql="from "+tablename+"  t";
		return SOrganizationdao.find(hql, params, page, rows);
	}

	@Override
	public SOrganization findById(String id) {
		// TODO Auto-generated method stub
		return SOrganizationdao.findById(SOrganization.class,id);
	}


	@Override
	public List<SOrganization> findByProperty(String propertyName, Object value) {
		// TODO Auto-generated method stub
		return SOrganizationdao.findByProperty(SOrganization.class, propertyName, value);
	}

	
	
	
	
	@Override
	public Long count(String searchinfo) {
		// TODO Auto-generated method stub
		String hql="select count(*) from "+tablename +" where 1=1 ";
		if(searchinfo!=null&& !searchinfo.equals("")){
			hql+=" and t.name like '%"+ searchinfo+"%' ";
		}
		return SOrganizationdao.count(hql);
	}
	
	

	@Override
	public List<SOrganization> find(String searchinfo, int page, int rows) {
		// TODO Auto-generated method stub
		String hql="from "+tablename +" where 1=1 ";		
		if(searchinfo!=null&& !searchinfo.equals("")){			
			hql+=" and t.name like '%"+ searchinfo+"%' ";
		}		
		return SOrganizationdao.find(hql,  page, rows);
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	/**
	 * 异步加载获取组织目录树
	 */
	@Override
	public List<Organization> getTreeNode(String id) {
		List<Organization> nl = new ArrayList<Organization>();
		String hql = null;
		Map<String, Object> params = new HashMap<String, Object>();
		if (id == null || id.equals("")) {
			// 查询所有根节点
			hql = "from SOrganization t where t.sorganization is null";
		} else {
			// 异步加载当前id下的子节点
			hql = "from SOrganization t where t.sorganization.id = :id ";
			params.put("id", id);
		}
		List<SOrganization> l = SOrganizationdao.find(hql, params);
		if (l != null && l.size() > 0) {
			for (SOrganization t : l) {
				Organization m = new Organization();
				BeanUtils.copyProperties(t, m);
				Set<SOrganization> set = t.getSorganizations();
				if (set != null && !set.isEmpty()) {
					m.setState("closed");// 节点以文件夹的形式体现
				} else {
					m.setState("open");// 节点以文件的形式体现
				}
				nl.add(m);
			}
		}
		return nl;
	}

	@Override
	public List<Organization> getAllTreeNode() {
		List<Organization> nl = new ArrayList<Organization>();
		String hql = "from SOrganization t";
		List<SOrganization> l = SOrganizationdao.find(hql);
		if (l != null && l.size() > 0) {
			for (SOrganization t : l) {
				Organization m = new Organization();
				BeanUtils.copyProperties(t, m);
				m.setText(t.getName());
				Map<String, Object> attributes = new HashMap<String, Object>();
				attributes.put("url", t.getId());
				m.setAttributes(attributes);
				SOrganization tm = t.getSorganization();// 获得当前节点的父节点对象
				if (tm != null) {
					m.setPid(tm.getId());
				}
//				m.setState("closed");
				nl.add(m);
			}
		}
		return nl;
	}

	
}
