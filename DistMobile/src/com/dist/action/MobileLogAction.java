package com.dist.action;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.dist.action.admin.BasicAction;
import com.dist.entity.SApplicationplatform;
import com.dist.entity.SMobileLog;
import com.dist.service.SApplicationPlatformServiceI;
import com.dist.service.SApplicationsServiceI;
import com.dist.service.SMobileLogServiceI;
import com.opensymphony.xwork2.ModelDriven;
/**
 * 移动设备登录日志记录
 * @author wmy
 *
 */
public class MobileLogAction extends BasicAction implements
		ModelDriven<SMobileLog> {

	/**
	 * 
	 */
	private static final long serialVersionUID = -940508409600290944L;

	@Autowired
	private SMobileLogServiceI mobileLogService;

	@Autowired
	private SApplicationPlatformServiceI applicationPlatformService;

	@Autowired
	private SApplicationsServiceI applicationsService;

	private String dateStart;
	private String dateEnd;

	public String getDateStart() {
		return dateStart;
	}

	public void setDateStart(String dateStart) {
		this.dateStart = dateStart;
	}

	public String getDateEnd() {
		return dateEnd;
	}

	public void setDateEnd(String dateEnd) {
		this.dateEnd = dateEnd;
	}

	@Override
	public SMobileLog getModel() {
		// TODO Auto-generated method stub
		return null;
	}

	public void monthActives() throws ParseException {
		Date start = parseDate(this.dateStart);
		Date end = parseDate(this.dateEnd);
		List<Map<String, Object>> list = mobileLogService.monthActives(start,
				end);
		Map<String,String> nameMap = new HashMap<String, String>();
		for (Map<String, Object> map : list) {
			String appidentify = map.get("appidentify").toString();
			String appName = getApplicationName(nameMap,appidentify);
			map.put("appName", appName);
		}

		writeJson(list);
	}

	public void dayActives() throws ParseException {
		Date start = parseDate(this.dateStart);
		Date end = parseDate(this.dateEnd);
		List<Map<String, Object>> list = mobileLogService
				.dayActives(start, end);
		Map<String,String> nameMap = new HashMap<String, String>();
		for (Map<String, Object> map : list) {
			String appidentify = map.get("appidentify").toString();
			String appName = getApplicationName(nameMap,appidentify);
			map.put("appName", appName);
		}

		writeJson(list);
	}
	
	private String getApplicationName(Map<String,String> map,String appidentify){
		if(map.containsKey(appidentify)){
			return map.get(appidentify);
		}else{
			SApplicationplatform ap =	applicationPlatformService.findByApplicationIdentify(appidentify);
			if(ap!=null&&ap.getSApplications()!=null){
				map.put(appidentify, ap.getSApplications().getName());
				return map.get(appidentify);
			}else{
				return null;
			}
		}
	}

	private  Date parseDate(String dateString) throws ParseException {
		String formatString = null;
		if (dateString.contains("/")) {
			formatString = "yyyy/MM/dd";
		} else {
			formatString = "yyyy-MM-dd";
		}
		if (dateString.length() > 10) {
			formatString += " HH:mm:ss";
		}
		SimpleDateFormat format = new SimpleDateFormat(formatString);
		return format.parse(dateString);
	}

}
