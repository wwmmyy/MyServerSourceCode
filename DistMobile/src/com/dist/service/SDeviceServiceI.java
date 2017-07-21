package com.dist.service;

import java.util.List;
import java.util.Map;

import com.dist.dao.BasicDaoI;
import com.dist.entity.SDevice;
import com.dist.entity.SDeviceactivity;

public interface SDeviceServiceI   extends BasicServiceI<SDevice>{

	public List<SDevice> find( String searchinfo, Map<String, Object> params, String software, String hardware,int page, int rows);
	
////	根据设备名称模糊搜索设备
//	public List<SDevice> findByName( String searchinfo,Map<String, Object> params, int page, int rows);


	/**
	 * 统计设备数量
	 * 
	 * @return key:name,num<br>
	 *         &nbsp;&nbsp;name:设备类型名称<br>
	 *         &nbsp;&nbsp;num:设备数量<br>
	 */
	public List<Map<String, Object>> statisticsNumber();
}
