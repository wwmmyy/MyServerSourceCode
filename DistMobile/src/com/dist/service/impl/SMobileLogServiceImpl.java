package com.dist.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.dist.dao.BasicDaoI;
import com.dist.entity.SMobileLog;
import com.dist.service.SMobileLogServiceI;
@Service("mobileLogService")
public class SMobileLogServiceImpl implements SMobileLogServiceI {

	private BasicDaoI<SMobileLog> SMobileLogdao;

	private final String tablename=" SMobileLog t ";
	
	
	@Resource
	public void setSMobileLogDao( BasicDaoI<SMobileLog>  SMobileLogdao) {
		this.SMobileLogdao = SMobileLogdao;
	}

	
	
	@Override
	public String save(SMobileLog t) {
		// TODO Auto-generated method stub
		return SMobileLogdao.save(t).getId();
	}

	@Override
	public List<SMobileLog> findAll() {
		// TODO Auto-generated method stub
		return SMobileLogdao.find("from "+tablename);
	}

	@Override
	public int deleteById(String id) {
		// TODO Auto-generated method stub
		return SMobileLogdao.deleteById(SMobileLog.class, id);
	}

	@Override
	public int deleteAll() {
		// TODO Auto-generated method stub
		return SMobileLogdao.executeHql("delete from "+tablename);
	}

	@Override
	public Long count() {
		// TODO Auto-generated method stub
		return SMobileLogdao.count("select count(*) from "+tablename);
	}

	@Override
	public Long count(Map<String, Object> params) {
		// TODO Auto-generated method stub
		String hql="select count(*) from "+tablename;
		return SMobileLogdao.count(hql, params);
	}

	@Override
	public List<SMobileLog> find(int page, int rows) {
		// TODO Auto-generated method stub
		String hql="from "+tablename;
		return SMobileLogdao.find(hql,page, rows);
	}

	@Override
	public List<SMobileLog> find(Map<String, Object> params, int page, int rows) {
		// TODO Auto-generated method stub
		String hql="from "+tablename;
		return SMobileLogdao.find(hql, params, page, rows);
	}

	@Override
	public SMobileLog findById(String id) {
		// TODO Auto-generated method stub
		return SMobileLogdao.findById(SMobileLog.class,id);
	}



	@Override
	public List<SMobileLog> findByProperty( String propertyName, Object value) {
		// TODO Auto-generated method stub
		return SMobileLogdao.findByProperty(SMobileLog.class, propertyName, value);
	}



	@Override
	public Long count(String searchinfo) {
		// TODO Auto-generated method stub
		String hql="select count(*) from "+tablename +" where 1=1 ";
		if(searchinfo!=null&& !searchinfo.equals("")){
			hql+=" and t.content like '%"+ searchinfo+"%' ";
		}
		return SMobileLogdao.count(hql);
	}
	
	

	@Override
	public List<SMobileLog> find(String searchinfo, int page, int rows) {
		// TODO Auto-generated method stub
		String hql="from "+tablename +" where 1=1 ";		
		if(searchinfo!=null&& !searchinfo.equals("")){			
			hql+=" and t.content like '%"+ searchinfo+"%' ";
		}		
		hql+=" order by t.logtime desc";
		return SMobileLogdao.find(hql,  page, rows);
	}



	@Override
	public Long DeviceStatistics(String time1, String time2, String action) {
		// TODO Auto-generated method stub
//		Map<String, Object> params
//		String hql="select count(*) from "+tablename;
//		return SMobileLogdao.count(hql, params);
		
		return  null;
	}



	@Override
	public List<Map<String, Object>> monthActives(Date start, Date end) {
		String hql = "select count(*) as num, YEAR(ml.logtime) as year, MONTH(ml.logtime) as month, ml.appidentify as appidentify from SMobileLog ml where ml.logtime >= :start and ml.logtime <= :end group by YEAR(ml.logtime),MONTH(ml.logtime),ml.appidentify";
		Map<String,Object> args = new HashMap<String, Object>();
		args.put("start", start);
		args.put("end", end);
		return SMobileLogdao.hql(hql, args);
	}



	@Override
	public List<Map<String, Object>> dayActives(Date start, Date end) {
		String hql = "select count(*) as num, YEAR(ml.logtime) as year, MONTH(ml.logtime) as month, DAY(ml.logtime) as day, ml.appidentify as appidentify from SMobileLog ml where ml.logtime >= :start and ml.logtime <= :end group by YEAR(ml.logtime),MONTH(ml.logtime),DAY(ml.logtime),ml.appidentify";
		Map<String,Object> args = new HashMap<String, Object>();
		args.put("start", start);
		args.put("end", end);
		return SMobileLogdao.hql(hql, args);
	}


}
