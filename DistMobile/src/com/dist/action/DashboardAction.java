package com.dist.action;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;

import com.dist.action.admin.BasicAction;
import com.dist.pagentity.PageShow;
import com.dist.service.SDeviceServiceI;
import com.opensymphony.xwork2.ActionContext;

@Controller
public class DashboardAction extends BasicAction{
	
	private static final long serialVersionUID = -6059520394052454945L;
	private String selectedIndex="0.0";
	private SDeviceServiceI deviceService;
	
	
	
	
	
	@Resource
	public void setdeviceService(SDeviceServiceI deviceServiceImpl) {
		this.deviceService = deviceServiceImpl;
	}

	
	
	public String getSelectedIndex() {
		return selectedIndex;
	}

	public void setSelectedIndex(String selectedIndex) {
		this.selectedIndex = selectedIndex;
	}

	public String index(){
		setDeviceStatus();
		return "index";
	}
	
	
	
	
	
	
	ActionContext deviceSession = ActionContext.getContext();

	/**
	 * 获取不同状态的设备数目
	 * 
	 * @return
	 */
	private void setDeviceStatus() {

		long temptotal = deviceService.count();
		deviceSession.getSession().put("total", temptotal);

		Map<String, Object> property = new HashMap<String, Object>();
		property.put("status", PageShow.PENDING + "");
		long temp0 = deviceService.count(property);
//		pageShow.setPending(temp0);
		deviceSession.getSession().put("pending", temp0);

		Map<String, Object> normal = new HashMap<String, Object>();
		normal.put("status", PageShow.NORMAL + "");
		long temp1 = deviceService.count(normal);
//		pageShow.setNormal(temp1);
		deviceSession.getSession().put("normal", temp1);

		Map<String, Object> lose = new HashMap<String, Object>();
		lose.put("status", PageShow.LOSE + "");
		long temp2 = deviceService.count(lose);
		
		deviceSession.getSession().put("lose", temp2);
		deviceSession.getSession().put("forbidden",
				temptotal - temp0 - temp1 - temp2);

	}
	
	
	
	
	
	
}