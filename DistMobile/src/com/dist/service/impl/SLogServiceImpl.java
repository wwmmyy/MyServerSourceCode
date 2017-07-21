package com.dist.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.dist.dao.BasicDaoI;
import com.dist.entity.SLog;
import com.dist.entity.SOrganization;
import com.dist.service.SLogServiceI;
/**
 * 只用于 在页面上查找展示日志的记录信息，在AOP日志保存中，由于session冲突的原因，没有采用此服务保存日志信息
 * @author wmy
 *
 */
@Service("slogService")
public class SLogServiceImpl implements SLogServiceI{

	private BasicDaoI<SLog> SLogdao;

	private final String tablename=" SLog t ";
	
	
	@Resource
	public void setPSnsDao( BasicDaoI<SLog>  SLogdao) {
		this.SLogdao = SLogdao;
	}

	
	
	@Override
	public String save(SLog t) {
		// TODO Auto-generated method stub
		return SLogdao.save(t).getId();
	}

	@Override
	public List<SLog> findAll() {
		// TODO Auto-generated method stub
		return SLogdao.find("from "+tablename);
	}

	@Override
	public int deleteById(String id) {
		// TODO Auto-generated method stub
		return SLogdao.deleteById(SLog.class, id);
	}

	@Override
	public int deleteAll() {
		// TODO Auto-generated method stub
		return SLogdao.executeHql("delete from "+tablename);
	}

	@Override
	public Long count() {
		// TODO Auto-generated method stub
		return SLogdao.count("select count(*) from "+tablename+" t");
	}

	@Override
	public Long count(Map<String, Object> params) {
		// TODO Auto-generated method stub
		String hql="select count(*) from "+tablename+"  t";
		return SLogdao.count(hql, params);
	}

	@Override
	public List<SLog> find(int page, int rows) {
		// TODO Auto-generated method stub
		String hql="from "+tablename+"  t";
		return SLogdao.find(hql,page, rows);
	}

	@Override
	public List<SLog> find(Map<String, Object> params, int page, int rows) {
		// TODO Auto-generated method stub
		String hql="from "+tablename+"  t";
		return SLogdao.find(hql, params, page, rows);
	}

	@Override
	public SLog findById(String id) {
		// TODO Auto-generated method stub
		return SLogdao.findById(SLog.class,id);
	}



	@Override
	public List<SLog> findByProperty( String propertyName, Object value) {
		// TODO Auto-generated method stub
		return SLogdao.findByProperty(SLog.class, propertyName, value);
	}


	@Override
	public Long count(String searchinfo) {
		// TODO Auto-generated method stub
		String hql="select count(*) from "+tablename +" where 1=1 ";
		if(searchinfo!=null&& !searchinfo.equals("")){
			hql+=" and t.SLogtype like '%"+ searchinfo+"%' or t.eventTime ='"+ searchinfo+"'  ";
		}
		return SLogdao.count(hql);
	}
	
	

	@Override
	public List<SLog> find(String searchinfo, int page, int rows) {
		// TODO Auto-generated method stub
		String hql="from "+tablename +" where 1=1 ";		
		if(searchinfo!=null&& !searchinfo.equals("")){			
			hql+=" and t.SLogtype like '%"+ searchinfo+"%' or t.eventTime ='"+ searchinfo+"'  ";
		}		
		return SLogdao.find(hql,  page, rows);
	}

}
