package com.dist.service;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.dist.entity.SMobileLog;

public interface SMobileLogServiceI extends BasicServiceI<SMobileLog>{

	public Long  DeviceStatistics(String  time1 ,String time2,String action);
	
	/**
	 * 月活跃量统计
	 * 
	 * @param start
	 *            开始时间
	 * @param end
	 *            结束时间
	 * @return
	 */
	public List<Map<String, Object>> monthActives(Date start, Date end);

	/**
	 * 日活跃量统计
	 * 
	 * @param start
	 *            开始时间
	 * @param end
	 *            结束时间
	 * @return
	 */
	public List<Map<String, Object>> dayActives(Date start, Date end);
}
