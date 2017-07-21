package com.dist.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.regex.Pattern;

import javax.annotation.Resource;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.dist.action.admin.BasicAction;
import com.dist.entity.SApplicationplatform;
import com.dist.entity.SApplications;
import com.dist.entity.SDeviceapplications;
import com.dist.pagentity.Json;
import com.dist.pagentity.MobileApplication;
import com.dist.pagentity.AppsPageShow;
import com.dist.pagentity.BasePageShow;
import com.dist.service.SApplicationPlatformServiceI;
import com.dist.service.SDeviceapplicationsServiceI;
import com.opensymphony.xwork2.ModelDriven;

@Controller
public class ApplicationPlatformAction extends BasicAction implements ModelDriven<SApplicationplatform>{ 
	private static final long serialVersionUID = -8590888301301201563L;
	private SApplicationplatform  app=new SApplicationplatform();
//	private  SDeviceapplicationsServiceI  deviceapplicationsService;
	private List<SDeviceapplications> listdeviceapplications;//设备应用列表
	
	private SApplicationPlatformServiceI  applicationPlatformService;
	private List<SApplicationplatform> listapplications;
	
	private AppsPageShow pageShow=new AppsPageShow();
	
	private SApplications  ap=new SApplications();
	
	public SApplications getAp() {
		return ap;
	}
	public void setAp(SApplications ap) {
		this.ap = ap;
	}
	public List<SApplicationplatform> getListapplications() {
		return listapplications;
	}
	public void setListapplications(List<SApplicationplatform> listapplications) {
		this.listapplications = listapplications;
	}
	private File file; 
	private String fileFileName; 
	private String selectedIndex="2.0";
	

public String getSelectedIndex() {
		return selectedIndex;
	}
	public void setSelectedIndex(String selectedIndex) {
		this.selectedIndex = selectedIndex;
	}
	//	@Resource
//	public void setDeviceapplicationsService(SDeviceapplicationsServiceI deviceapplicationsService) {
//		this.deviceapplicationsService = deviceapplicationsService;
//	}
	@Resource
	public void setApplicationPlatformService(SApplicationPlatformServiceI applicationsService) {
		this.applicationPlatformService = applicationsService;
	}
	@Override
	public SApplicationplatform getModel() {
		// TODO Auto-generated method stub
		return app;
	}
	
	
	/**
	 * 查询及显示主页
	 * 应用程序主页，获取主页的应用程序列表等信息
	 * @return   相应类型的app应用 listapplications 及 不同类型的应用的数目 封装在了 pageShow里面
	 * 搜索时调用接口applications!index.action?pageShow.searchinfo=  &pageShow.type=0,1,2,3,4....
	 */
	/*public String index() {
		pageShow.setRows(BasePageShow.PAGESIZE);
//		获取不同类型的应用程序
		setAppaCategoryNum();
			long temptotal=0;
				listapplications=applicationsService.find(pageShow.getSearchinfo(),pageShow.getType(), pageShow.getPageNow(), 1000);
//				获取选中要现实的设备状态类型的总数
				temptotal=applicationsService.find(pageShow.getSearchinfo(),pageShow.getType(),0, 1000).size();
//			设置总页数
			pageShow.setTotal(temptotal % BasePageShow.PAGESIZE == 0 ? temptotal / BasePageShow.PAGESIZE : temptotal /BasePageShow.PAGESIZE + 1) ; 
			if(pageShow.getPageNow()<=0){
				pageShow.setPageNow(1);
			}	
			System.out.println("androidJson返回结果1："+JSON.toJSONString(listapplications));
		return  "index";	
	}
		
	*/
	
	//@Action("/application/app_create")  
	public String createApp(){
		return SUCCESS;
	}
	
	//@Action("/application/app_list")  
	public String index() {
		return SUCCESS;
    }
	
	public String regplat() {
			return "regplat";
    }
	
	/**
	 * 删除应用程序
	 * @return
	 * 调用用接口applications!delete.action?app.id=  
	 */
	public void  delete(){
		System.out.println("获取到的application.getId()"+app.getId());
		applicationPlatformService.deleteById(app.getId());
		Json j = new Json();
		j.setSuccess(true);
		j.setMsg("删除成功！");
		super.writeJson(j);
//		return  index();
	}
	
	

//	/**
//	 *获取终端应用列表信息
//	 */
//	public void appList() {
//		listapplications=applicationsService.findAll();
//		JSONObject obj=new JSONObject();
//		List<Application> apps = new ArrayList<Application >();
//		if (listapplications != null && listapplications.size() > 0) {
//			for (SApplicationplatform t : listapplications) {
//				Application u = new Application();
//				BeanUtils.copyProperties(t, u);
//				apps.add(u);
//			}
//		}
//		obj.put("result", JSON.toJSONString(apps));
//		obj.put("state", "true");
//		super.writeJson(obj);
//	}
	
	
	
	
	/**
	 * 获取某个应用程序的详情
	 * @return
	 * 调用用接口applications!detail.action?app.id=。。。。  
	 * 
	 */
	public String detail() {
		app=applicationPlatformService.findById(app.getId());
//		System.out.println("androidJson返回结果："+JSON.toJSONString(app));
		return  "detail";
	}
		
	
	/**
	 * 更新应用程序
	 * @return
	 * 调用用接口applications!update.action?app.id =.. &app.name=........  
	 */
		public void update() {
//			System.out.println("获取到application.name=："+app.getName());
			SApplicationplatform d = applicationPlatformService.findById(app.getId());
			d.setApplicationIdentity(app.getApplicationIdentity());
			d.setPath(app.getPath());
			d.setPlatform(app.getPlatform());
			d.setStatus(app.getStatus());
			d.setUrl(app.getUrl());
			d.setVersion(app.getVersion());
	
			applicationPlatformService.saveOrUpdate(d);
			Json j = new Json();
			j.setSuccess(true);
			j.setMsg("修改成功！");
			super.writeJson(j);
		}
		
	
	
		
			/**
			 *添加应用程序 将自己的应用程序发布注册到后台服务端
			 * @return
			 * 调用用接口 app.SApplications.id
			 */
			public String save() { 				
//				如果用户没有填写应用标识号，则标记为当前时间        在网页中应判断，如果是Android或IOS程序，getApplicationIdentity不能为空，因为程序访问的时需要通过其打开相应的app
				if(("").equals(app.getApplicationIdentity())||app.getApplicationIdentity()==null){
					  //取当前日期 
				     SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss"); 
				     Calendar calendar = Calendar.getInstance(); 
				     app.setApplicationIdentity(sdf.format(calendar.getTime()));
				}
				app.setCreateTime(new Date());
				app.setStatus(true);//默认新增程序后显示红点
				if(fileFileName!=null){
			    //取文件后缀名 
			     String filename=app.getApplicationIdentity() +fileFileName.substring( fileFileName.lastIndexOf('.'));  		     
			     app.setPath(filename);
			     String root; 
			     try {		
				     InputStream is=new FileInputStream(file); 
//				     root= ServletActionContext.getRequest().getRealPath("D:\\project/cloudManu/uploadfile/MySqlTest/upload") ;//存储到apache项目发布目录上 
				     root= "C:\\project/appIcon/upload";//存储到apache项目发布目录上 
				       File dir = new File(root);
	                     if (dir.exists() == false) {
	                         boolean b = dir.mkdirs();
	                     }
				    File copyFile=new File(root,filename); 	     
				    OutputStream os=new FileOutputStream(copyFile);	     
				    byte[] buffer=new byte[400]; 	     
				    int length=0; 				     
				    while((length=is.read(buffer)) > 0 ){ 
				       os.write(buffer, 0, length); 
				     } 
				     is.close(); 
				     os.close();
//				     System.out.println("ApplicationPlatformAction.save()::"+app.getId());				     
//				     ap.setId(app.getSApplications().getId());
//				     ap.setName(app.getSApplications().getName());				     
						} catch (Exception e) {
							// TODO: handle exception
							e.printStackTrace();
//							 return "error"; 
							return  SUCCESS;
						}
					}
					applicationPlatformService.save(app);
					
				return  SUCCESS;
			}
		
	
//	/**
//	 * 获取不同应用程序数目
//	 * @return
//	 */
//	private void setAppaCategoryNum( ) {		
//		long temptotal=applicationPlatformService.count();
//		pageShow.setTotalnum(temptotal);
//		pageShow.setAll(temptotal);
//				
//		long androidall=applicationPlatformService.count(AppsPageShow.ANDROIDALL);
//		pageShow.setAndroidall(androidall);			
//		long droidphone=applicationPlatformService.count(AppsPageShow.ANDROIDPHONE);
//		pageShow.setAndroidphone(droidphone);
//		pageShow.setAndroidpad(androidall-droidphone);
//		
//		long iosall=applicationPlatformService.count(AppsPageShow.IOSALL);
//		pageShow.setIosall(iosall);
//		long iospad=applicationPlatformService.count(AppsPageShow.IOSPAD);
//		pageShow.setIospad(iospad);
//		pageShow.setIosphone(iosall-iospad);
//	}
	

	public SApplicationplatform getApp() {
		return app;
	}
	public void setApp(SApplicationplatform app) {
		this.app = app;
	}
	public List<SDeviceapplications> getListdeviceapplications() {
		return listdeviceapplications;
	}
	public void setListdeviceapplications(List<SDeviceapplications> listdeviceapplications) {
		this.listdeviceapplications = listdeviceapplications;
	}
	public AppsPageShow getPageShow() {
		return pageShow;
	}
	public void setPageShow(AppsPageShow pageShow) {
		this.pageShow = pageShow;
	}
	public File getFile() {
		return file;
	}
	public void setFile(File file) {
		this.file = file;
	}
	public String getFileFileName() {
		return fileFileName;
	}
	public void setFileFileName(String fileFileName) {
		this.fileFileName = fileFileName;
	}


}
