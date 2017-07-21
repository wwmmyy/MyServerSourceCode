package com.dist.service;

import java.util.List;
import java.util.Map;

import com.dist.entity.SDevice;
import com.dist.entity.SPermissions;

public interface BasicServiceI<T> {
	public String save(T t);	
	public  List<T>  findAll();
	public int deleteById(String id);
	public int deleteAll();
	public Long count();
	public Long count(Map<String, Object> params);
	public List<T> find( int page, int rows);
	public List<T> find(Map<String, Object> params, int page, int rows);
	public  T  findById(String id) ;
	
	public  List<T> findByProperty(String propertyName, Object value);
	
	
	
//	获取搜索及翻页条件下的 list
	public List<T> find( String searchinfo, int page, int rows) ;
//	获取搜索条件下的数目
	public Long count(String searchinfo);
}