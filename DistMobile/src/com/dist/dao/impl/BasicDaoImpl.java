package com.dist.dao.impl;
import java.io.Serializable;
import java.sql.SQLException;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.transform.Transformers;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.web.context.WebApplicationContext;

import com.dist.dao.BasicDaoI;


@Repository
public class BasicDaoImpl<T> implements BasicDaoI<T> {
	
	public HibernateTemplate hibernateTemplate;
	@Resource
	public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
		this.hibernateTemplate = hibernateTemplate;
	}
	
	  
	  
    private   static   final  Logger logger = Logger  
            .getLogger(BasicDaoImpl.class );  
  
    /**  
     * 保存指定实体类  
     *   
     * @param entityobj  
     *            实体类  
     */   
    public   void  saveBase(T entity) {  
        try  {  
            hibernateTemplate.save(entity);  
            if  (logger.isDebugEnabled()) {  
                logger.debug("保存实体类成功,"  + entity.getClass().getName());  
            }  
        } catch  (RuntimeException e) {  
            logger.error("保存实体异常,"  + entity.getClass().getName(), e);  
            throw  e;  
        }  
    }  
  
    /**  
     * 删除指定实体  
     *   
     * @param entityobj  
     *            实体类  
     */   
    public   void  deleteBase(T entity) {  
        try  {  
            hibernateTemplate.delete(entity);  
            if  (logger.isDebugEnabled()) {  
                logger.debug("删除实体类成功,"  + entity.getClass().getName());  
            }  
        } catch  (RuntimeException e) {  
            logger.error("删除实体异常" , e);  
            throw  e;  
        }  
    }  
  
    /**  
     * 获取所有实体集合  
     *   
     * @param entityClass  
     *            实体  
     * @return 集合  
     */   
    public  List<T> findAllBase(Class<T> entityClass) {  
        try  {  
            if  (logger.isDebugEnabled()) {  
                logger.debug("开始删除实体："  + entityClass.getName());  
            }  
            return  hibernateTemplate.find( "from "  + entityClass.getName());  
        } catch  (RuntimeException e) {  
            logger.error("查找指定实体集合异常，实体："  + entityClass.getName(), e);  
            throw  e;  
        }  
    }  
  
    /**  
     * 更新或保存指定实体  
     *   
     * @param entity  
     *            实体类  
     */   
    public   void  saveOrUpdateBase(T entity) {  
        try  {  
            hibernateTemplate.saveOrUpdate(entity);  
            if  (logger.isDebugEnabled()) {  
                logger.debug("更新或者保存实体成功,"  + entity.getClass().getName());  
            }  
        } catch  (RuntimeException e) {  
            logger.error("更新或保存实体异常" , e);  
            throw  e;  
        }  
    }  
  
//    /**  
//     * 查找指定ID实体类对象  
//     *   
//     * @param entityClass  
//     *            实体Class  
//     * @param id  
//     *            实体ID  
//     * @return 实体对象  
//     */   
//    public  T findByIdBase(Class<T> entityClass, ID id) {  
//        try  {  
//            if  (logger.isDebugEnabled()) {  
//                logger.debug("开始查找ID为"  + id +  "的实体："  + entityClass.getName());  
//            }  
//            return  (T) hibernateTemplate.get(entityClass, id);  
//        } catch  (RuntimeException e) {  
//            logger.error("查找指定ID实体异常，ID："  + id, e);  
//            throw  e;  
//        }  
//    }  
  
    /**  
     * 查询指定HQL，并返回集合  
     *   
     * @param hql  
     *            HQL语句  
     * @param values  
     *            可变的参数列表  
     * @return 集合  
     */   
    public  List<Object> findBase(String hql, Object... values) {  
        try  {  
            if  (logger.isDebugEnabled()) {  
                logger.debug("开始查询指定HQL语句,"  + hql);  
            }  
            return  hibernateTemplate.find(hql, values);  
        } catch  (RuntimeException e) {  
            logger.error("查询指定HQL异常，HQL："  + hql, e);  
            throw  e;  
        }  
    }  
  
    /**  
     * 按照HQL语句查询唯一对象.  
     *   
     * @param hql  
     *            HQL语句  
     * @param values  
     *            可变参数集合  
     * @return OBJECT对象  
     */   
    public  Object findUniqueBase( final  String hql,  final  Object... values) {  
        try  {  
            if  (logger.isDebugEnabled()) {  
                logger.debug("开始查询返回唯一结果的HQL语句,"  + hql);  
            }  
            return  hibernateTemplate.execute( new  HibernateCallback() {  
                public  Object doInHibernate(Session s)  
                        throws  HibernateException, SQLException {  
                    Query query = createQueryBase(s, hql, values);  
                    return  query.uniqueResult();  
                }  
            });  
        } catch  (RuntimeException e) {  
            logger.error("查询指定HQL异常，HQL："  + hql, e);  
            throw  e;  
        }  
    }  
//  
//    /**  
//     * 查找指定HQL并返回INT型  
//     *   
//     * @param hql  
//     *            HQL语句  
//     * @param values  
//     *            可变参数列表  
//     * @return INT  
//     */   
//    public   int  findInt( final  String hql,  final  Object... values) {  
//        return  Tools.null2Int(findUnique(hql, values));  
//    }  
  
//    /**  
//     * 获取指定实体Class指定条件的记录总数  
//     *   
//     * @param entityClass  
//     *            实体Class  
//     * @param where  
//     *            HQL的查询条件,支持参数列表  
//     * @param values  
//     *            可变参数列表  
//     * @return 记录总数  
//     */   
//    public   int  findTotalCount(Class<T> entityClass,  final  String where,  
//            final  Object... values) {  
//        String hql = "select count(e) from "  + entityClass.getName() +  " as e "   
//                + where;  
//        return  findInt(hql, values);  
//    }  
  
//    /**  
//     * 获取指定实体Class的记录总数  
//     *   
//     * @param entityClass  
//     *            实体Class  
//     * @return 记录总数  
//     */   
//    public   int  findTotalCount(Class<T> entityClass) {  
//        return  findTotalCount(entityClass,  "" );  
//    }  
  

  
    /**  
     * 模糊查询指定条件对象集合 <br>  
     * 用法：可以实例化一个空的T对象，需要查询某个字段，就set该字段的条件然后调用本方法<br>  
     * 缺点：目前测试貌似只能支持String的模糊查询，虽然有办法重写，但没必要，其他用HQL<br>  
     *   
     * @param entity  
     *            条件实体  
     * @return 结合  
     */   
    public  List<T> findByExampleBase(T entity) {  
        try  {  
            List<T> results = hibernateTemplate.findByExample(entity);  
            return  results;  
        } catch  (RuntimeException re) {  
            logger.error("查找指定条件实体集合异常" , re);  
            throw  re;  
        }  
    }  
  
    /**  
     * 补充方法(未测) 据说可以无视session的状态持久化对象  
     *   
     * @param entity  
     *            实体类  
     * @return 持久后的实体类  
     */   
    public  T merge(T entity) {  
        try  {  
            T result = (T) hibernateTemplate.merge(entity);  
            return  result;  
        } catch  (RuntimeException re) {  
            logger.error("merge异常" , re);  
            throw  re;  
        }  
    }  
  
    /**  
     * 清除实体的锁定状态<br>  
     * 方法未测  
     *   
     * @param entity  
     *            实体  
     */   
    public   void  attachCleanBase(T entity) {  
        try  {  
            hibernateTemplate.lock(entity, LockMode.NONE);  
        } catch  (RuntimeException re) {  
            logger.error("实体解锁异常" , re);  
            throw  re;  
        }  
    }  
  
//    /**  
//     * 按HQL分页查询.  
//     *   
//     * @param page  
//     *            页面对象  
//     * @param hql  
//     *            HQL语句  
//     * @param values  
//     *            可变参数列表  
//     * @return 分页数据  
//     */   
//    public  Page<T> findByPage( final  Page<T> page,  final  String hql,  
//            final  Object... values) {  
//        try  {  
//            if  (logger.isDebugEnabled()) {  
//                logger.debug("开始查找指定HQL分页数据,"  + hql);  
//            }  
//            return  (Page<T>) hibernateTemplate.execute(  
//                    new  HibernateCallback() {  
//                        public  Object doInHibernate(Session s)  
//                                throws  HibernateException, SQLException {  
//                            Query query = createQuery(s, hql, values);  
//                            if  (page.isFirstSetted()) {  
//                                query.setFirstResult(page.getFirst());  
//                            }  
//                            if  (page.isPageSizeSetted()) {  
//                                query.setMaxResults(page.getPageSize());  
//                            }  
//                            page.setResult(query.list());  
//                            if  (logger.isDebugEnabled()) {  
//                                logger.debug("查找指定HQL分页数据成功,"  + hql);  
//                            }  
//                            return  page;  
//                        }  
//                    });  
//        } catch  (RuntimeException e) {  
//            logger.error("分页查询异常，HQL："  + hql, e);  
//            throw  e;  
//        }  
//    }  
  
    /**  
     * 根据查询条件与参数列表创建Query对象  
     *   
     * @param session  
     *            Hibernate会话  
     * @param hql  
     *            HQL语句  
     * @param objects  
     *            参数列表  
     * @return Query对象  
     */   
    public  Query createQueryBase(Session session, String hql, Object... objects) {  
        Query query = session.createQuery(hql);  
        if  (objects !=  null ) {  
            for  ( int  i =  0 ; i < objects.length; i++) {  
                query.setParameter(i, objects[i]);  
            }  
        }  
        return  query;  
    }  
  
    /**  
     * 从Spring上下文中获取本类对象<br>  
     * 此方法可能存在线程并发问题（待测）  
     *   
     * @param context  
     *            Spring上下文  
     * @return 本类对象  
     */   
    public   static  BasicDaoImpl getFromApplicationContext(  
            WebApplicationContext context) {  
        return  (BasicDaoImpl) context.getBean( "BaseHibernateDAO" );  
    }

    
    
    
    
    
    
    
    
    


    
    
    
    /**  
     * 查找指定属性的实体集合  
     *   
     * @param entityClass  
     *            实体  
     * @param propertyName  
     *            属性名  
     * @param value  
     *            条件  
     * @return 实体集合  
     */   
    @Override
    public  List<T> findByProperty(Class<T> entityClass, String propertyName,  
            Object value) {  
        try  {  
            if  (logger.isDebugEnabled()) {  
                logger.debug("开始查找指定属性："  + propertyName +  "为"  + value +  "的实体"   
                        + entityClass.getName());  
            }  
            String queryStr = "from "  + entityClass.getName()  
                    + " as model where model."  + propertyName +  "=?" ;  
            return  hibernateTemplate.find(queryStr, value);  
        } catch  (RuntimeException e) {  
            logger.error("查找指定条件实体集合异常，条件："  + propertyName, e);  
            throw  e;  
        }  
    }  
    
    
    
    
    
    
    
    
	@Override
	public void saveOrUpdate(T o) {
		hibernateTemplate.saveOrUpdate(o);
	}
    
    
    
	@Override
	public List<T> find(String hql) {
		// TODO Auto-generated method stub
		return hibernateTemplate.find(hql);
	}

	@Override
	public T save(T t) {
		// TODO Auto-generated method stub
		 hibernateTemplate.getSessionFactory().getCurrentSession().save(t);
		return t;
	}

//	@Override
//	public List<T> check(T t) {
//		// TODO Auto-generated method stub
//		return hibernateTemplate.findByExample(t);
//	}


	@Override
	public T findById(Class<T> entityClass, String id) {  
      try  {  
      if  (logger.isDebugEnabled()) {  
          logger.debug("开始查找ID为"  + id +  "的实体："  + entityClass.getName());  
      }  
      return  (T) hibernateTemplate.get(entityClass, id);  
	  } catch  (RuntimeException e) {  
	      logger.error("查找指定ID实体异常，ID："  + id, e);  
	      throw  e;  
	  }  
	}

	@Override
	public int deleteById(Class<T> entityClass, String id) {  
      try  {  
      if  (logger.isDebugEnabled()) {  
          logger.debug("开始查找ID为"  + id +  "的实体："  + entityClass.getName());  
      }  
      return executeHql("delete  from "+entityClass.getName()+" t where t.id='"+id+"'");
      
//      return  (T) hibernateTemplate.get(entityClass, id);  
	  } catch  (RuntimeException e) {  
	      logger.error("查找指定ID实体异常，ID："  + id, e);  
	      throw  e;  
	  }  
	}
	
	
	
	@Override
	public void delete(T t) {
		// TODO Auto-generated method stub
		hibernateTemplate.delete(t);
	}

	@Override
	public List<T> find(T t) {
		// TODO Auto-generated method stub
        try  {  
            if  (logger.isDebugEnabled()) {  
                logger.debug("开始删除实体："  + t.getClass().getName());  
            }  
            return  hibernateTemplate.find( "from "  +  t.getClass().getName());  
        } catch  (RuntimeException e) {  
            logger.error("查找指定实体集合异常，实体："  +  t.getClass().getName(), e);  
            throw  e;  
        }  
	}

//	@Override
//	public T get(String hql, Object[] params) {
//		// TODO Auto-generated method stub
//        Query query =hibernateTemplate.getSessionFactory().getCurrentSession().createQuery(hql); 	
//		if(params!=null&&params.length>0){
//			for(int i=0;i<params.length;i++){
//				query.setParameter(i, params[i]);			
//			}
//		}
//		List<T> l=query.list();
//		if(l!=null&&l.size()>0){			
//			return l.get(0);			
//		}
//		
//		return null;
//	}  
	
	
	@Override
	public List<T> get(String hql, Map<String, Object> params) {
		Query q =hibernateTemplate.getSessionFactory().getCurrentSession().createQuery(hql);
		if (params != null && !params.isEmpty()) {
			for (String key : params.keySet()) {
				q.setParameter(key, params.get(key));
			}
		}
		List<T> l = q.list();
//		if (l != null && l.size() > 0) {
//			return l.get(0);
//		}
		return l;
	}
	
  
	@Override
	public List<T> find(String hql, Map<String, Object> params) {
		Query q = hibernateTemplate.getSessionFactory().getCurrentSession().createQuery(hql);
		if (params != null && !params.isEmpty()) {
			for (String key : params.keySet()) {
				q.setParameter(key, params.get(key));
			}
		}
		return q.list();
	}

	@Override
	public List<T> find(String hql, Map<String, Object> params, int page, int rows) {
//		hql=addWhere( hql, params);
//		hql="from SDevice t where t.status=:status and t.SDevicetype.systemtype=:systemtype";
		Query q = hibernateTemplate.getSessionFactory().getCurrentSession().createQuery(hql);
		if (params != null && !params.isEmpty()) {
			for (String key : params.keySet()) {
				q.setParameter(key, params.get(key));
			}
		}
		return q.setFirstResult((page - 1) * rows).setMaxResults(rows).list();
	}

	@Override
	public List<T> find(String hql, int page, int rows) {
//		System.out.println("拼接的语句为：：：："+hql);

		Query q = hibernateTemplate.getSessionFactory().getCurrentSession().createQuery(hql);
		return q.setFirstResult((page - 1) * rows).setMaxResults(rows).list();
	}

	@Override
	public Long count(String hql) {
		Query q = hibernateTemplate.getSessionFactory().getCurrentSession().createQuery(hql);
		return (Long) q.uniqueResult();
	}


	
	
	@Override
	public Long count(String hql, Map<String, Object> params) {
//		hql=addWhere( hql, params);
		Query q = hibernateTemplate.getSessionFactory().getCurrentSession().createQuery(hql); 
		if (params != null && !params.isEmpty()) {
			for (String key : params.keySet()) {
				q.setParameter(key, params.get(key));
			}
		}

		return (Long) q.uniqueResult();
	}

	
//	from Tuser t where t.name = :name and t.pwd = :pwd", params);
	private String addWhere(String hql, Map<String, Object> params) {
			hql += " where ";
			int temp=0;
			if (params != null && !params.isEmpty()) {
				for (String key : params.keySet()) {
					if(temp!=0){
						hql+=" and ";
					}
					hql += "t."+key+"=:"+key;
					temp++;
				}
			}
			return hql;
	}
	
	
	
	@Override
	public int executeHql(String hql) {
		Query q = hibernateTemplate.getSessionFactory().getCurrentSession().createQuery(hql);
		return q.executeUpdate();
	}

	@Override
	public List<Map<String,Object>> hql(String hql) {
		Query q = hibernateTemplate.getSessionFactory().getCurrentSession().createQuery(hql);
		return q.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP).list();
	}

	@Override
	public List<Map<String, Object>> hql(String hql, Map<String, Object> args) {
		Query q = hibernateTemplate.getSessionFactory().getCurrentSession().createQuery(hql);
		setParameters(q, args);
		return q.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP).list();
	}
	
	private void setParameters(Query query, Map<String, Object> args) {
		if (query != null && args != null) {
			for (String key : args.keySet()) {
				Object value = args.get(key);
				if (value == null) {
					query.setParameter(key, value);
				} else if (value.getClass().isArray()) {
					query.setParameterList(key, (Object[])value);
				} else if (value instanceof Collection) {
					query.setParameterList(key, (Collection) value);
				} else {
					query.setParameter(key, value);
				}
			}
		}
	}

	
//	@Override
//	public int deleteAll(String hql) {
//		// TODO Auto-generated method stub
//		return  executeHql(hql);
//		
//	}

}
