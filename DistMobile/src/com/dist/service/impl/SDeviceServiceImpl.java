package com.dist.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Service;

import com.dist.dao.BasicDaoI;
import com.dist.dao.impl.BasicDaoImpl;
import com.dist.entity.PSnsComment;
import com.dist.entity.SDevice;
import com.dist.entity.SDevicetype;
import com.dist.entity.SUsers;
import com.dist.service.SDeviceServiceI;


@Service("deviceService")
public class SDeviceServiceImpl  implements SDeviceServiceI {

	private static final Logger logger = Logger.getLogger(SDeviceServiceImpl.class);

	private BasicDaoI<SDevice> deviceDaoImpl;

	@Resource
	public void setDeviceDaoImpl(BasicDaoI<SDevice>  deviceDaoImpl) {
		this.deviceDaoImpl = deviceDaoImpl;
	}

	@Override
	public List<SDevice> findAll() {
		// TODO Auto-generated method stub
		return deviceDaoImpl.find("from SDevice");
	}
	
	@Override
	public Long count() {
		// TODO Auto-generated method stub
		return deviceDaoImpl.count("select count(*) from SDevice t");
	}

	@Override
	public int deleteAll() {
		// TODO Auto-generated method stub
		return deviceDaoImpl.executeHql("delete from SDevice");
	}

//	@Override
//	public List<SDevice> find(String hql) {
//		// TODO Auto-generated method stub
//		return deviceDaoImpl.find(hql);
//	}

	@Override
	public String save(SDevice t) {
		// TODO Auto-generated method stub
		deviceDaoImpl.saveOrUpdate(t);
		return "";

//		return deviceDaoImpl.save(t).getId();
	}


	@Override
	public SDevice findById(String id) {
		// TODO Auto-generated method stub
		
		return deviceDaoImpl.findById(SDevice.class,id);
	}

	@Override
	public int deleteById(String id) {
		// TODO Auto-generated method stub		
		return deviceDaoImpl.executeHql("delete  from SDevice t where t.id='"+id+"'");
		
	}

	@Override
	public Long count( Map<String, Object> params) {
		// TODO Auto-generated method stub
		String hql="select count(*) from SDevice t where t.status=:status";
		return deviceDaoImpl.count(hql, params);
	}

//	@Override
//	public int executeHql(String hql) {
//		// TODO Auto-generated method stub
//		return deviceDaoImpl.executeHql(hql);
//	}

	@Override
	public List<SDevice> find( int page, int rows) {
		// TODO Auto-generated method stub
		
		String hql="from SDevice t";
		return deviceDaoImpl.find(hql, page, rows);
	}

	@Override
	public List<SDevice> find( Map<String, Object> params, int page, int rows) {
		// TODO Auto-generated method stub
		String hql="from SDevice t where t.status=:status";
		return deviceDaoImpl.find(hql, params, page, rows);
	}

	@Override
	public List<SDevice> findByProperty(String propertyName, Object value) {
		// TODO Auto-generated method stub
		return deviceDaoImpl.findByProperty(SDevice.class, propertyName, value);
	}

	@Override
	public List<SDevice> find( String searchinfo,Map<String, Object> params, String software, String hardware, int page, int rows) {
		// TODO Auto-generated method stubi
		String hql;
		if(params!=null){
			 hql="from SDevice t where t.status=:status";
		}else{
			params=new HashMap<String, Object>(); 
			 hql="from SDevice t where 1=1";
		}
		
		if(searchinfo!=null&& !searchinfo.equals("")){
			
			hql+=" and t.name like '%"+ searchinfo+"%' ";
		}
		
		
		if(software!=null&&!software.isEmpty()){
			hql+=" and t.SDevicetype.systemtype = :systemtypes" ;
			params.put("systemtypes", software);
			
//			hql+=" and t.SDevicetype.systemtype = 'IOS'" ;
		}
		if(hardware!=null&&!hardware.isEmpty()){
			hql+=" and t.SDevicetype.hardwaretype=:hardwaretypes" ;
			params.put("hardwaretypes", hardware);
		}
		return deviceDaoImpl.find(hql, params, page, rows);
	}

	@Override
	public List<SDevice> find(String searchinfo, int page, int rows) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Long count(String searchinfo) {
		// TODO Auto-generated method stub
		return null;
	}

	
////	根据设备名称模糊搜索设备
//	@Override
//	public List<SDevice> findByName(String searchinfo, Map<String, Object> params, int page, int rows) {
//		// TODO Auto-generated method stub
//		
//		String hql;
////		if(params!=null){
////			 hql="from SDevice t where t.status=:status";
////		}else{
//			params=new HashMap<String, Object>(); 
//			 hql="from SDevice t where 1=1";
////		}
//		
//		hql+=" and t.name like '%"+ searchinfo+"%'";
//		
//		
////		if(software!=null&&!software.isEmpty()){
////			hql+=" and t.SDevicetype.systemtype = :systemtypes" ;
////			params.put("systemtypes", software);
////			
//////			hql+=" and t.SDevicetype.systemtype = 'IOS'" ;
////		}
////		if(hardware!=null&&!hardware.isEmpty()){
////			hql+=" and t.SDevicetype.hardwaretype=:hardwaretypes" ;
////			params.put("hardwaretypes", hardware);
////		}
//		return deviceDaoImpl.find(hql, params, page, rows);
//		
//		
//		
//		
//		
//		
//	}

	

	@Override
	public List<Map<String, Object>> statisticsNumber() {
		String hql = "select count(*) as num, dt.name as name from SDevice d left join d.SDevicetype as dt where dt.name is not null group by dt.name";
		return deviceDaoImpl.hql(hql);
	}

	
	
	
	
	
	
	
	
	
	


	

}
