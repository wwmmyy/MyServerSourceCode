package com.dist.action;

import java.sql.SQLException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.struts2.convention.annotation.InterceptorRefs;
import org.hibernate.HibernateException;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Controller;

import com.alibaba.fastjson.JSON;
import com.dist.action.admin.BasicAction;
import com.dist.entity.SDevice;
import com.dist.entity.SDeviceapplications;
import com.dist.entity.SDevicetype;
import com.dist.pagentity.Json;
import com.dist.pagentity.PageShow;
import com.dist.service.SDeviceServiceI;
import com.dist.service.SDeviceapplicationsServiceI;
import com.dist.service.SDevicetypeServiceI;
import com.google.gson.Gson;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ModelDriven;

/**
 * @author Administrator
 * 
 */
@Controller
public class DeviceAction extends BasicAction implements ModelDriven<SDevice> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3456702629776676256L;

	private SDevice device = new SDevice();

	private SDeviceServiceI deviceService;
	private List<SDevice> listdevice;
	private List<SDevice> listdevicenew;
	
	private SDeviceapplicationsServiceI deviceapplicationsService;
	private List<SDeviceapplications> listdeviceapps;

	// 调用此服务主要是因为在主页面新增设备，下拉选择设备型号时需要的
	private SDevicetypeServiceI devicetypeService;
	private List<SDevicetype> listdevicetype;

	private PageShow pageShow = new PageShow();
	private String deviceid;
	private String type = "";// 用于表示设备的状态
//	private String ctype = "";// 改变设备状态
	private String selectedIndex="1.0";
	
	
	private String allClass="";
	private String iosClass="";
	private String androidClass="";
	private String phoneClass="";
	private String padClass="";
	
	
	public String getSelectedIndex() {
		return selectedIndex;
	}

	public void setSelectedIndex(String selectedIndex) {
		this.selectedIndex = selectedIndex;
	}

	@Resource
	public void setdeviceService(SDeviceServiceI deviceServiceImpl) {
		this.deviceService = deviceServiceImpl;
	}

	public List<SDevice> getListdevicenew() {
		return listdevicenew;
	}

	public void setListdevicenew(List<SDevice> listdevicenew) {
		this.listdevicenew = listdevicenew;
	}

	@Resource
	public void setDeviceapplicationsService(
			SDeviceapplicationsServiceI deviceapplicationsService) {
		this.deviceapplicationsService = deviceapplicationsService;
	}

	@Resource
	public void setDevicetypeService(SDevicetypeServiceI devicetypeService) {
		this.devicetypeService = devicetypeService;
	}

	// public DeviceAction() {
	// //System.out.println("deviceAction init");
	// }

	public SDevice getModel() {
		return device;
	}

	/**
	 * 获取所有的设备列表信息 分页查询 struts里配置的
	 * 
	 * @return 根据名称模糊搜索设备
	 */

	public String index() {
		try {
			// pageShow.setRows(PageShow.PAGESIZE);
			pageShow.setRows(1000);
			// 或去设备的所有类型，主要为新增设备选取设备类型做准备
			listdevicetype = devicetypeService.findAll();
			// 获取不同状态的设备数目
			setDeviceStatus();
			SetSelectIndex();
			long temptotal = 0;
			if ((type.equals("") || type.equals("-1")) && pageShow.getSystemtype() == null && pageShow.getHardwaretype() == null) {// 说明是获取所有的设备
				pageShow.setType(-1);
				temptotal = pageShow.getTotalnum();
				listdevice = deviceService.find(pageShow.getPageNow(), 1000);
			} else {// 获取某种条件的数据
				if (type.equals(""))
					type = "-1";// 说明不是按照设备状态查询
				pageShow.setType(Integer.parseInt(type));
				Map<String, Object> property = null;
				if (pageShow.getType() != -1) {// 说明是按照设备状态查询
					property = new HashMap<String, Object>();
					property.put("status", type);
				}

				listdevice = deviceService.find(pageShow.getSearchinfo(), property, pageShow.getSystemtype(), pageShow.getHardwaretype(), pageShow.getPageNow(), 1000);
				// 获取选中要现实的设备状态类型的总数
				temptotal = deviceService.find(pageShow.getSearchinfo(), property, pageShow.getSystemtype(), pageShow.getHardwaretype(), 0, 10000000).size();

				//			为实现选中按钮变为灰色
				if (pageShow.getSystemtype() != null && pageShow.getSystemtype().equals("IOS")) {
					iosClass = "active";
				} else if (pageShow.getSystemtype() != null && pageShow.getSystemtype().equals("Android")) {
					androidClass = "active";
				} else if (pageShow.getHardwaretype() != null && pageShow.getHardwaretype().equals("Phone")) {
					phoneClass = "active";
				} else if (pageShow.getHardwaretype() != null && pageShow.getHardwaretype().equals("PAD")) {
					padClass = "active";
				} else {
					allClass = "active";
				}

			}
			// 设置总页数
			pageShow.setTotal(temptotal % PageShow.PAGESIZE == 0 ? temptotal / PageShow.PAGESIZE : temptotal / PageShow.PAGESIZE + 1);
			if (pageShow.getPageNow() <= 0) {
				pageShow.setPageNow(1);
			}
			Map<String, Object> property = new HashMap<String, Object>();
			property.put("status", "0");
			listdevicenew = deviceService.find(null, property, null, null, 1, 1000);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return "home";
	}
	private void SetSelectIndex(){
		switch(type){
			case "0":
				this.selectedIndex="1.4";
				break;
			case "1":
				this.selectedIndex="1.1";
				break;
			case "2":
				this.selectedIndex="1.3";
				break;
			case "3":
				this.selectedIndex="1.2";
				break;
			default :
				this.selectedIndex="1.0";
				break;
		}
	}
	/**
	 * 获取某个设备的详情
	 * 
	 * @return
	 */
	public String deviceDetail() {
		// //System.out.println("deviceid结果："+deviceid);
		device = deviceService.findById(deviceid);
		listdeviceapps = deviceapplicationsService.findByProperty("deviceId",
				deviceid);
		// //System.out.println("androidJson返回结果："+JSON.toJSONString(listdeviceapps));
		return "detail";
	}

	/**
	 * 保存或更新设备
	 * 
	 * @return
	 */
	public String save() {
		//System.out.println("获取到的添加信息ID为："+device.getId());
		device.setRegisterTime(new Date());
		device.setStatus("0");// 表示待审核状态
		
		SDevicetype  tempdetype=devicetypeService.findById(device.getSDevicetype().getId());
		device.setSDevicetype(tempdetype);
		String id=deviceService.save(device);
		// return "noaction";
		// return "index";
		return index();
	}
	
	/**
	 * 更新设备
	 * 
	 * @return
	 */
	public void updateDevice() {
	
		SDevice d=deviceService.findById(device.getId());
		d.setName(device.getName());
		d.setDeviceNumber(device.getDeviceNumber());
		d.setRegisterMessage(device.getRegisterMessage());
		d.setRemark(device.getRemark());
		deviceService.save(d);
		Json j = new Json();
		j.setSuccess(true);
		j.setMsg("修改成功！");
		
	}

	/**
	 * 改变设备状态
	 * 
	 * @return
	 */
	public void changeDeviceStatus() {

		String status= device.getStatus();
		device=deviceService.findById(device.getId());

		device.setStatus(status);
		deviceService.save(device);
		
		Json j = new Json();
		j.setSuccess(true);
		j.setMsg("修改成功！");

		writeJson(j);
	}



	/**
	 * 根据名称模糊搜索设备
	 * 
	 * @return
	 */
	// public String findByDeviceName() {
	// pageShow.setRows(PageShow.PAGESIZE);
	// // 或去设备的所有类型，主要为新增设备选取设备类型做准备
	// listdevicetype=devicetypeService.findAll();
	// // 获取不同状态的设备数目
	// setDeviceStatus();
	// long temptotal=0;
	// //获取某种条件的数据
	// // if(type.equals("")) type="-1";//说明不是按照设备状态查询
	// // pageShow.setType(Integer.parseInt(type));
	// // Map<String, Object> property=null;
	// // if(pageShow.getType()!=-1){//说明是按照设备状态查询
	// // property=new HashMap<String, Object>();
	// // property.put("status", type);
	// // }
	// listdevice=deviceService.findByName(device.getName(),null,pageShow.getPageNow(),
	// PageShow.PAGESIZE);
	// // 获取选中要现实的设备状态类型的总数
	//
	// temptotal=deviceService.findByName(device.getName(),null,0,
	// 100000).size();
	//
	// // 设置总页数
	// pageShow.setTotal(temptotal % PageShow.PAGESIZE == 0 ? temptotal /
	// PageShow.PAGESIZE : temptotal /PageShow.PAGESIZE + 1) ;
	// if(pageShow.getPageNow()<=0){
	// pageShow.setPageNow(1);
	// }
	// return "index";
	// }

	// 根据配置文件会跳转到 device_list.jsp
	public String list() {
		// listdevice = deviceServiceImpl.finddevice("from deviceinfo");
		try {
			listdevice = deviceService.findAll();
			String result = JSON.toJSONString(listdevice);
			// 解决乱码的问题
			// getResponse().setContentType("text/html;charset=utf-8");
			// getResponse().getWriter().print(result);
			// List<SDevice> listdevice = JSON.parseArray(result,
			// SDevice.class);
			//System.out.println(listdevice.size());
			//System.out.println("androidJson返回结果："
//					+ JSON.toJSONString(listdevice));
			// 删除全部设备信息
			// deviceService.deleteAll();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "list";
	}

	// private InputStream inputStream;
	// public InputStream getInputStream() {
	// return inputStream;
	// }

	public void deleteDevice() {
		deviceService.deleteById(deviceid);
		Json j = new Json();
		j.setSuccess(true);
		j.setMsg("删除成功！");

		writeJson(j);
		// return "delete";
	}

	ActionContext deviceSession = ActionContext.getContext();

	/**
	 * 获取不同状态的设备数目
	 * 
	 * @return
	 */
	private void setDeviceStatus() {

		long temptotal = deviceService.count();
		pageShow.setTotalnum(temptotal);
		deviceSession.getSession().put("total", temptotal);

		Map<String, Object> property = new HashMap<String, Object>();
		property.put("status", PageShow.PENDING + "");
		long temp0 = deviceService.count(property);
		pageShow.setPending(temp0);
		deviceSession.getSession().put("pending", temp0);

		Map<String, Object> normal = new HashMap<String, Object>();
		normal.put("status", PageShow.NORMAL + "");
		long temp1 = deviceService.count(normal);
		pageShow.setNormal(temp1);
		deviceSession.getSession().put("normal", temp1);

		Map<String, Object> lose = new HashMap<String, Object>();
		lose.put("status", PageShow.LOSE + "");
		long temp2 = deviceService.count(lose);
		pageShow.setLose(temp2);
		deviceSession.getSession().put("lose", temp2);

		// Map<String, Object> forbidden=new HashMap<String, Object>();
		// forbidden.put("status", "3");
		// pageShow.setForbidden(deviceService.count(forbidden);

		pageShow.setForbidden(temptotal - temp0 - temp1 - temp2);
		deviceSession.getSession().put("forbidden",
				temptotal - temp0 - temp1 - temp2);

	}
	
	
	
	public void nubmers() {

		
		 List<Map<String,Object>>   staticsnum =deviceService.statisticsNumber();
		
			System.out.println("获得的设备类型数为：："+JSON.toJSONString(staticsnum));
		 

		writeJson(JSON.toJSONString(staticsnum));
	}
	
	
	
	
	
	

	public SDevice getDevice() {
		return device;
	}

	public void setDevice(SDevice device) {
		this.device = device;
	}

	public void login() {
		try {
			getResponse().getWriter().print(
					JSON.toJSONString(deviceService.findAll()));

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public String deviceService() {

		if (type.trim().equals("add")) {
			return SUCCESS;
		} else if (type.trim().equals("check")) {
			return list();

		} else if (type.trim().equals("jsoninfo")) {
			try {
				androidJson();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else if (type.trim().equals("register")) {
			return "noaction";
		} else {
			return "error";
		}

		return null;
	}

	// 直接向请求端返回json结果
	public void androidJson() throws Exception {
		try {
			List<SDevice> listU = deviceService.findAll();
			String result = JSON.toJSONString(listU);
			// 解决乱码的问题
			getResponse().setContentType("text/html;charset=utf-8");
			getResponse().getWriter().print(result);
			// List<SDevice> listdevice = JSON.parseArray(result,
			// SDevice.class);
			//System.out.println(listU.size());
			//System.out.println("androidJson返回结果：" + listU);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void setListdevice(List<SDevice> listdevice) {
		this.listdevice = listdevice;
	}

	public void setdevice(SDevice device) {
		this.device = device;
	}

	public List<SDevice> getListdevice() {
		return listdevice;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getDeviceid() {
		return deviceid;
	}

	public void setDeviceid(String deviceid) {
		this.deviceid = deviceid;
	}

	public PageShow getPageShow() {
		return pageShow;
	}

	public void setPageShow(PageShow pageShow) {
		this.pageShow = pageShow;
	}

	public List<SDeviceapplications> getListdeviceapps() {
		return listdeviceapps;
	}

	public void setListdeviceapps(List<SDeviceapplications> listdeviceapps) {
		this.listdeviceapps = listdeviceapps;
	}

	public List<SDevicetype> getListdevicetype() {
		return listdevicetype;
	}

	public void setListdevicetype(List<SDevicetype> listdevicetype) {
		this.listdevicetype = listdevicetype;
	}

//	public String getCtype() {
//		return ctype;
//	}
//
//	public void setCtype(String ctype) {
//		this.ctype = ctype;
//	}
	public String getAllClass() {
		return allClass;
	}

	public void setAllClass(String allClass) {
		this.allClass = allClass;
	}

	public String getIosClass() {
		return iosClass;
	}

	public void setIosClass(String iosClass) {
		this.iosClass = iosClass;
	}

	public String getAndroidClass() {
		return androidClass;
	}

	public void setAndroidClass(String androidClass) {
		this.androidClass = androidClass;
	}

	public String getPhoneClass() {
		return phoneClass;
	}

	public void setPhoneClass(String phoneClass) {
		this.phoneClass = phoneClass;
	}

	public String getPadClass() {
		return padClass;
	}

	public void setPadClass(String padClass) {
		this.padClass = padClass;
	}

	public void numbers() {
		List<Map<String, Object>> list = deviceService.statisticsNumber();
		writeJson(list);
	}
}