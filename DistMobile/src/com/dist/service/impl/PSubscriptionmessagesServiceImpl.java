//package com.dist.service.impl;
//
//import java.util.List;
//import java.util.Map;
//
//import javax.annotation.Resource;
//
//import org.springframework.stereotype.Service;
//
//import com.dist.dao.BasicDaoI;
//import com.dist.entity.PSubscriptionmessages;
//import com.dist.service.PSubscriptionmessagesServiceI;
//@Service("subScriMsgService")
//public class PSubscriptionmessagesServiceImpl implements PSubscriptionmessagesServiceI {
//
//	private BasicDaoI<PSubscriptionmessages> PSubscriptionmessagesdao;
//
//	private final String tablename=" PSubscriptionmessages ";
//	
//	
//	@Resource
//	public void setPSubscriptionmessagesDao( BasicDaoI<PSubscriptionmessages>  PSubscriptionmessagesdao) {
//		this.PSubscriptionmessagesdao = PSubscriptionmessagesdao;
//	}
//
//	
//	
//	@Override
//	public String save(PSubscriptionmessages t) {
//		// TODO Auto-generated method stub
//		return PSubscriptionmessagesdao.save(t).getId();
//	}
//
//	@Override
//	public List<PSubscriptionmessages> findAll() {
//		// TODO Auto-generated method stub
//		return PSubscriptionmessagesdao.find("from "+tablename);
//	}
//
//	@Override
//	public int deleteById(String id) {
//		// TODO Auto-generated method stub
//		return PSubscriptionmessagesdao.deleteById(PSubscriptionmessages.class, id);
//	}
//
//	@Override
//	public int deleteAll() {
//		// TODO Auto-generated method stub
//		return PSubscriptionmessagesdao.executeHql("delete from "+tablename);
//	}
//
//	@Override
//	public Long count() {
//		// TODO Auto-generated method stub
//		return PSubscriptionmessagesdao.count("select count(*) from "+tablename+" t");
//	}
//
//	@Override
//	public Long count(Map<String, Object> params) {
//		// TODO Auto-generated method stub
//		String hql="select count(*) from "+tablename+"  t";
//		return PSubscriptionmessagesdao.count(hql, params);
//	}
//
//	@Override
//	public List<PSubscriptionmessages> find(int page, int rows) {
//		// TODO Auto-generated method stub
//		String hql="from "+tablename+"  t";
//		return PSubscriptionmessagesdao.find(hql,page, rows);
//	}
//
//	@Override
//	public List<PSubscriptionmessages> find(Map<String, Object> params, int page, int rows) {
//		// TODO Auto-generated method stub
//		String hql="from "+tablename+"  t";
//		return PSubscriptionmessagesdao.find(hql, params, page, rows);
//	}
//
//	@Override
//	public PSubscriptionmessages findById(String id) {
//		// TODO Auto-generated method stub
//		return PSubscriptionmessagesdao.findById(PSubscriptionmessages.class,id);
//	}
//
//
//	@Override
//	public List<PSubscriptionmessages> findByProperty( String propertyName, Object value) {
//		// TODO Auto-generated method stub
//		return PSubscriptionmessagesdao.findByProperty(PSubscriptionmessages.class, propertyName, value);
//	}
//
//
//
//	@Override
//	public Long count(String searchinfo) {
//		// TODO Auto-generated method stub
//		String hql="select count(*) from "+tablename +" where 1=1 ";
//		if(searchinfo!=null&& !searchinfo.equals("")){
//			hql+=" and t.content like '%"+ searchinfo+"%'";
//		}
//		return PSubscriptionmessagesdao.count(hql);
//	}
//	
//	
//
//	@Override
//	public List<PSubscriptionmessages> find(String searchinfo, int page, int rows) {
//		// TODO Auto-generated method stub
//		String hql="from "+tablename +" where 1=1 ";		
//		if(searchinfo!=null&& !searchinfo.equals("")){			
//			hql+=" and t.content like '%"+ searchinfo+"%'";
//		}		
//		return PSubscriptionmessagesdao.find(hql,  page, rows);
//	}
//
//}
