//package com.dist.dao.impl;
//import java.sql.SQLException;
//import java.util.ArrayList;
//import java.util.Collection;
//import java.util.List;
//import java.util.Map;
//
//import org.apache.log4j.Logger;
//import org.hibernate.Criteria;
//import org.hibernate.HibernateException;
//import org.hibernate.Query;
//import org.hibernate.Session;
//import org.hibernate.criterion.MatchMode;
//import org.hibernate.criterion.Projections;
//import org.hibernate.criterion.Property;
//import org.springframework.core.annotation.Order;
//import org.springframework.orm.hibernate3.HibernateCallback;
//import org.springframework.orm.hibernate3.HibernateTemplate;
//
//import com.dist.dao.BasicDaoI;
//
//public class BaseHibernate<T> implements BasicDaoI<T>{
//	private static HibernateTemplate hibernateTemplate;
//    private   static   final  Logger logger = Logger  
//            .getLogger(BasicDaoImpl.class );  
//	@SuppressWarnings("static-access")
//	public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
//		this.hibernateTemplate = hibernateTemplate;
//	}
//
//	/**
//	 * 插入对象
//	 * 
//	 * @param entity
//	 * @return
//	 */
//	@Override
//	public int save(Object entity) {
//		return (Integer) hibernateTemplate.save(entity);
//	}
//
//	/**
//	 * 删除对象
//	 * 
//	 * @param entity
//	 */
//	@Override
//	public void delete(Object entity) {
//		hibernateTemplate.delete(entity);
//	}
//
//	/**
//	 * 修改对象
//	 * 
//	 * @param entity
//	 */
//	public void update(Object entity) {
//		hibernateTemplate.update(entity);
//	}
//
//	/**
//	 * 根据ID查询对象
//	 * 
//	 * @param entityClass
//	 *            类名,如:String.class
//	 * @param id
//	 *            ID值
//	 */
//	public <T> T query(Class<T> entityClass, int id) {
//		return hibernateTemplate.get(entityClass, id);
//	}
//
//	/**
//	 * 查询全部
//	 * 
//	 * @param className
//	 *            类名
//	 * @return
//	 */
//	@SuppressWarnings("unchecked")
//	public <T> List<T> queryAll(Class<?> className) {
//		return hibernateTemplate.find("from " + className.getName());
//	}
//
//	/**
//	 * 分页
//	 * 
//	 * @param entityClass
//	 *            类名
//	 * @param index
//	 *            当前页数
//	 * @param size
//	 *            每页显示的大小
//	 * @param order
//	 *            排序类型
//	 * @param propertyName
//	 *            要排序的属性名
//	 * @return
//	 */
//	@SuppressWarnings("unchecked")
//	public <T> List<T> paging(final Class<?> entityClass, final int index,
//			final int size, final Order order, final String... propertyName) {
//		List<T> list = hibernateTemplate.executeFind(new HibernateCallback() {
//			public Object doInHibernate(Session session)
//					throws HibernateException, SQLException {
//				Criteria criteria = session.createCriteria(entityClass);
//				if (propertyName != null && propertyName.length > 0) {
////					switch (order.value()) {
////					
////					case ASC:
//						criteria.addOrder(org.hibernate.criterion.Order
//								.asc(propertyName[0]));
////						break;
////					case DESC:
////						criteria.addOrder(org.hibernate.criterion.Order
////								.desc(propertyName[0]));
////						break;
////					}
//				}
//				criteria.setFirstResult((index - 1) * size);
//				criteria.setMaxResults(size);
//				return criteria.list();
//			}
//		});
//		return list;
//	}
//
//	@SuppressWarnings("unchecked")
//	public long getSumRecord(final Class<?> className) {
//		List<Long> count = new ArrayList<Long>();
//		count = hibernateTemplate.find("select COUNT(*) from "
//				+ className.getName());
//		return count.size() > 0 ? (count.get(0)) : 0L;
//	}
//
//	/**
//	 * 获取总页数
//	 * 
//	 * @param className
//	 *            类名
//	 * @param size
//	 *            每页显示的大小
//	 * @return
//	 */
//	@SuppressWarnings("unchecked")
//	public long sumPage(final Class<?> className, int size) {
//		List<Long> count = new ArrayList<Long>();
//		count = hibernateTemplate.find("select COUNT(*) from "
//				+ className.getName());
//		return count.size() > 0 ? (count.get(0) - 1 + size) / size : 0L;
//	}
//
//	/**
//	 * 根据字段查询
//	 * 
//	 * @param params
//	 *            字段列表
//	 * @param values
//	 *            值列表
//	 * @return
//	 */
//	@SuppressWarnings("unchecked")
//	public <T> List<T> queryByProperty(final Class<?> className,
//			final Map<String, Object> params) {
//		return hibernateTemplate.executeFind(new HibernateCallback() {
//			public List<?> doInHibernate(Session session)
//					throws HibernateException, SQLException {
//				Criteria criteria = session.createCriteria(className);
//				for (String field : params.keySet())
//					criteria.add(Property.forName(field).eq(params.get(field)));
//				return criteria.list();
//			}
//		});
//	}
//
//	/**
//	 * 根据字段查询可以分的页数
//	 * 
//	 * @param className
//	 *            要查询的实体类
//	 * @param params
//	 *            属性列表
//	 * @param size
//	 *            每页显示的大小
//	 * @return
//	 */
//	@SuppressWarnings("unchecked")
//	public long queryByPropertyGetSumPage(final Class<?> className,
//			final Map<String, Object> params, final int size) {
//		List<Integer> object = hibernateTemplate
//				.executeFind(new HibernateCallback() {
//					public Object doInHibernate(Session session)
//							throws HibernateException, SQLException {
//						Criteria criteria = session.createCriteria(className);
//						for (String field : params.keySet())
//							criteria.add(Property.forName(field).eq(
//									params.get(field)));
//						criteria.setProjection(Projections.rowCount());
//						return criteria.list();
//					}
//				});
//		int count = object == null ? 0 : object.get(0);
//		return count > 0 ? (count + size - 1) / size : 0L;
//	}
//
//	/**
//	 * 获取总记录数根据属性
//	 * 
//	 * @param className
//	 * @param params
//	 * @param size
//	 * @return
//	 */
//	@SuppressWarnings("unchecked")
//	public long queryByPropertyGetSumRecord(final Class<?> className,
//			final Map<String, Object> params) {
//		List<Integer> object = hibernateTemplate
//				.executeFind(new HibernateCallback() {
//					public Object doInHibernate(Session session)
//							throws HibernateException, SQLException {
//						Criteria criteria = session.createCriteria(className);
//						for (String field : params.keySet())
//							criteria.add(Property.forName(field).eq(
//									params.get(field)));
//						criteria.setProjection(Projections.rowCount());
//						return criteria.list();
//					}
//				});
//		return object == null ? 0 : object.get(0);
//	}
//
//	/**
//	 * 根据字段查询并分页显示
//	 * 
//	 * @param className
//	 *            要分页的实体类
//	 * @param params
//	 *            字段列表
//	 * @param index
//	 *            当前页
//	 * @param size
//	 *            每页显示的大小
//	 * @param order
//	 *            排序
//	 * @return
//	 */
//	@SuppressWarnings("unchecked")
//	public <T> List<T> queryByPropertyPaging(final Class<?> className,
//			final Map<String, Object> params, final int index, final int size,
//			final Order order, final String... field) {
//		return hibernateTemplate.executeFind(new HibernateCallback() {
//			public List<?> doInHibernate(Session session)
//					throws HibernateException, SQLException {
//				Criteria criteria = session.createCriteria(className);
//				for (String f : params.keySet())
//					criteria.add(Property.forName(f).eq(params.get(f)));
//				if (field != null && field.length != 0) {
////					switch (order.value()) {
////					case ASC:
//						criteria.addOrder(org.hibernate.criterion.Order
//								.asc(field[0]));
////						break;
////					case DESC:
////						criteria.addOrder(org.hibernate.criterion.Order
////								.desc(field[0]));
////						break;
////					}
//				}
//				criteria.setFirstResult((index - 1) * size);
//				criteria.setMaxResults(size);
//				return criteria.list();
//			}
//		});
//	}
//
//	/**
//	 * 保存或更新对象
//	 * 
//	 * @param entity
//	 *            对象
//	 */
//	public void saveOrUpdate(Object entity) {
//		hibernateTemplate.saveOrUpdate(entity);
//	}
//
//	/**
//	 * 批量修改
//	 * 
//	 * @param queryString
//	 *            HQL语句
//	 * @return 受影响行数
//	 */
//	public int bulkUpdate(String queryString) {
//		return hibernateTemplate.bulkUpdate(queryString);
//	}
//
//	/**
//	 * 批量修改
//	 * 
//	 * @param queryString
//	 *            HQL语句
//	 * @param values
//	 *            参数的值
//	 * @return 受影响行数
//	 */
//	public int bulkUpdate(String queryString, Object... values) {
//		return hibernateTemplate.bulkUpdate(queryString, values);
//	}
//
//	/**
//	 * 批量删除
//	 * 
//	 * @param collection
//	 *            要删除的集合
//	 */
//	public void deleteAll(Collection<?> collection) {
//		hibernateTemplate.deleteAll(collection);
//	}
//
//	/**
//	 * 模糊查询
//	 * 
//	 * @param className
//	 *            类名
//	 * @param field
//	 *            字段名
//	 * @param value
//	 *            值
//	 * @param matchMode
//	 *            匹配模式:ANYWHERE->任意位置、END->结束、START->开始、EXACT->精确匹配
//	 */
//	@SuppressWarnings("unchecked")
//	public <T> List<T> Querylike(final Class<?> className, final String field,
//			final String value, final MatchMode matchMode) {
//		List objects = new ArrayList<Object>();
//		objects = hibernateTemplate.executeFind(new HibernateCallback() {
//			public Object doInHibernate(Session session)
//					throws HibernateException, SQLException {
//				Criteria criteria = session.createCriteria(className);
//				criteria.add(Property.forName(field).like(value, matchMode));
//				return criteria.list();
//			}
//		});
//		return objects;
//	}
//
//	/**
//	 * 执行hql查询语句
//	 * 
//	 * @param hql
//	 *            hql语句
//	 * @param values
//	 *            值列表
//	 * @return
//	 */
//	@SuppressWarnings("unchecked")
//	public <T> Object executeQuery(final String hql, final Object... values) {
//		return hibernateTemplate.execute(new HibernateCallback() {
//
//			public Object doInHibernate(Session session)
//					throws HibernateException, SQLException {
//				Query query = session.createQuery(hql);
//				setParams(query, values);
//				return query.list();
//			}
//		});
//	}
//
//	/**
//	 * 查询单个值
//	 * 
//	 * @param hql
//	 *            hql语句
//	 * @param values
//	 *            参数列表
//	 * @return 返回单个值
//	 */
//	@SuppressWarnings("unchecked")
//	public <T> Object executeSacale(final String hql, final Object... values) {
//		return hibernateTemplate.execute(new HibernateCallback() {
//			public Object doInHibernate(Session session)
//					throws HibernateException, SQLException {
//				Query query = session.createQuery(hql);
//				setParams(query, values);
//				query.setMaxResults(1);
//				return query.uniqueResult();
//			}
//		});
//	}
//
//	/**
//	 * 执行hql删除、修改语句
//	 * 
//	 * @param hql
//	 *            hql语句
//	 * @param values
//	 *            值列表
//	 * @return
//	 */
//	@SuppressWarnings("unchecked")
//	public int executNonQuery(final String hql, final Object... values) {
//		return hibernateTemplate.execute(new HibernateCallback() {
//			public Integer doInHibernate(Session session)
//					throws HibernateException, SQLException {
//				Query query = session.createQuery(hql);
//				setParams(query, values);
//				return query.executeUpdate();
//			}
//		});
//	}
//
//	/**
//	 * 删除表数据
//	 * @param tableName 表名
//	 */
//	
//	@SuppressWarnings("unchecked")
//	public void truncate(final String tableName) {
//		hibernateTemplate.execute(new HibernateCallback() {
//			public Object doInHibernate(Session session)
//					throws HibernateException, SQLException {
//				session.createSQLQuery("truncate table " + tableName).executeUpdate();
//				return new ArrayList();
//			}
//		});
//
//	}
//
//	private void setParams(Query query, Object... values) {
//		if (!isEmptyOrNull(values)) {
//			for (int i = 0; i < values.length; i++) {
//				query.setParameter(i, values[i]);
//			}
//
//		}
//	}
//
//	/**
//	 * 判断值是否为空或
//	 * 
//	 * @param values
//	 * @return
//	 */
//	private boolean isEmptyOrNull(Object... values) {
//		if (values == null || values.length == 0)
//			return true;
//		return false;
//	}
//
//	@Override
//	public List<T> find(String hql) {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//	
//
//
//	@Override
//	public List<T> check(T t) {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//	@Override
//	public List<T> findAll() {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//	@Override
//	public T findById(String id) {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//
//
//}