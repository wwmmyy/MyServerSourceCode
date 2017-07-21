package com.dist.dao;

import java.util.List;
import java.util.Map;

public interface BasicDaoI<T> {

	public List<T> find(String hql);	
	public T save(T t);	
	public  List<T>  find(T t);
	public  T  findById(Class<T> entityClass, String id) ;
	public  int  deleteById(Class<T> entityClass, String id) ;
	public void delete(T t);
//	public int deleteAll(String hql);
	public List<T> get(String hql,Map<String, Object> params);
	public List<T> find(String hql, Map<String, Object> params);
	public List<T> find(String hql, int page, int rows);
	public List<T> find(String hql, Map<String, Object> params, int page, int rows);
	public void saveOrUpdate(T o); 
	public Long count(String hql);
	public Long count(String hql, Map<String, Object> params);
	public int executeHql(String hql);
//	查找指定属性的实体集合  
	public  List<T> findByProperty(Class<T> entityClass, String propertyName, Object value);
	
	public List<Map<String, Object>> hql(String hql);

	public List<Map<String, Object>> hql(String hql, Map<String, Object> args);
}