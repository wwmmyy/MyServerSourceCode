package com.dist.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Pattern;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.dist.action.admin.BasicAction;
import com.dist.entity.PUserapplicationmessage;
import com.dist.entity.PUsersubscription;
import com.dist.entity.SApplicationorganization;
import com.dist.entity.SApplicationplatform;
import com.dist.entity.SApplicationpurview;
import com.dist.entity.SApplications;
import com.dist.entity.SDeviceapplications;
import com.dist.entity.SNews;
import com.dist.entity.SOrganization;
import com.dist.entity.SUsers;
import com.dist.pagentity.Json;
import com.dist.pagentity.MobileApplication;
import com.dist.pagentity.AppsPageShow;
import com.dist.pagentity.BasePageShow;
import com.dist.pagentity.MobileSubSource;
import com.dist.service.SApplicationOrganServiceI;
import com.dist.service.SApplicationPlatformServiceI;
import com.dist.service.SApplicationpurviewServiceI;
import com.dist.service.SApplicationsServiceI;
import com.dist.service.SDeviceapplicationsServiceI;
import com.dist.service.SOrganizationServiceI;
import com.dist.service.UserServiceI;
import com.google.gson.Gson;
import com.opensymphony.xwork2.ModelDriven;

@Controller
public class ApplicationsAction extends BasicAction implements ModelDriven<SApplications>{
	
	
	private static final long serialVersionUID = -5805035190964460569L;
	private SApplications  app=new SApplications();
	private  SDeviceapplicationsServiceI  deviceapplicationsService;
	private List<SDeviceapplications> listdeviceapplications;//设备应用列表
	
	private SApplicationsServiceI  applicationsService;
	private List<SApplications> listapplications;
	private List<SUsers> users=new ArrayList<SUsers>();
	private List<SOrganization> orgs=new ArrayList<SOrganization>();
	private UserServiceI userServiceImpl;
	private String selectUserIds;
	private String selectOrgIds;
	
//	应用程序与用户关联表
	private SApplicationpurviewServiceI  applicationpurviewService;
	private SApplicationOrganServiceI apporganService;
	private SOrganizationServiceI organizationService;
	
	
	
	
	
	
	@Resource
	public void setApporganService(SApplicationOrganServiceI apporganService) {
		this.apporganService = apporganService;
	}

	@Resource
	public void setOrganizationService(SOrganizationServiceI organizationService) {
		this.organizationService = organizationService;
	}


	private SApplicationPlatformServiceI  applicationPlatformService;

	
	private AppsPageShow pageShow=new AppsPageShow();
	private ArrayList<SApplicationplatform> aplatlist;
	private String selectedIndex="2.0";
	
	public String getSelectOrgIds() {
		return selectOrgIds;
	}

	public void setSelectOrgIds(String selectOrgIds) {
		this.selectOrgIds = selectOrgIds;
	}

	public String getSelectedIndex() {
		return selectedIndex;
	}
	public void setSelectedIndex(String selectedIndex) {
		this.selectedIndex = selectedIndex;
	}
	public ArrayList<SApplicationplatform> getAplatlist() {
		return aplatlist;
	}
	public void setAplatlist(ArrayList<SApplicationplatform> aplatlist) {
		this.aplatlist = aplatlist;
	}
	public List<SApplications> getListapplications() {
		return listapplications;
	}
	public void setListapplications(List<SApplications> listapplications) {
		this.listapplications = listapplications;
	}
	

	public List<SOrganization> getOrgs() {
		return orgs;
	}
	public void setOrgs(List<SOrganization> orgs) {
		this.orgs = orgs;
	}
	public String getSelectUserIds() {
		return selectUserIds;
	}
	public void setSelectUserIds(String selectUserIds) {
		this.selectUserIds = selectUserIds;
	}
	public void setApplicationpurviewService(SApplicationpurviewServiceI applicationpurviewService) {
		this.applicationpurviewService = applicationpurviewService;
	}
	public UserServiceI getUserServiceImpl() {
		return userServiceImpl;
	}
	public void setUserServiceImpl(UserServiceI userServiceImpl) {
		this.userServiceImpl = userServiceImpl;
	}
	public List<SUsers> getUsers() {
		return users;
	}
	public void setUsers(List<SUsers> users) {
		this.users = users;
	}
	
	
	
	private File picfile; 
	private String picfileFileName; 
	
	@Resource
	public void setApplicationPlatformService(SApplicationPlatformServiceI applicationsService) {
		this.applicationPlatformService = applicationsService;
	}
	@Resource
	public void setDeviceapplicationsService(SDeviceapplicationsServiceI deviceapplicationsService) {
		this.deviceapplicationsService = deviceapplicationsService;
	}
	@Resource
	public void setApplicationsService(SApplicationsServiceI applicationsService) {
		this.applicationsService = applicationsService;
	}
	@Override
	public SApplications getModel() {
		// TODO Auto-generated method stub
		return app;
	}
	
	
	
//	public void saveUserApplication(){
//		 HttpServletRequest request=ServletActionContext.getRequest();
//				String userids = request.getParameter("userids");
//				String useridarray[]=userids.split(",");
//				int templeng=useridarray.length;
//				for(int i=0;i<templeng;i++){
//					SApplicationpurview tempuapp=new SApplicationpurview();
//					tempuapp.setSApplications(applicationsService.findById(app.getId()));
//					tempuapp.setUserId(useridarray[i]);
//					applicationpurviewService.saveOrUpade(tempuapp);
//					
//				}		
//	}
	
	
	
	/**
	 * 保存用户订阅的消息设置到数据库sourceId
	 * 参数  userID    userids：    [{id:"",state:""},{id:"",state:""}]
	 */
	public  void  saveUserApplication(){
		 HttpServletRequest request=ServletActionContext.getRequest();
		 String subscribe = request.getParameter("userids");
//		 System.out.println("转化后的订阅json为：：：userId：" +"::json::"+subscribe);
		  Gson gson = new Gson();
          java.lang.reflect.Type type = new com.google.gson.reflect.TypeToken<List<MobileSubSource>>() {
          }.getType();
          List<MobileSubSource> msubsourcelist = gson.fromJson(subscribe, type);
//          System.out.println("哈哈哈哈哈"+JSON.toJSONString(msubsourcelist));
          for(MobileSubSource msource:msubsourcelist){
        	  if(msource.getState()){//添加订阅
        			SApplicationpurview tempuapp=new SApplicationpurview();
					tempuapp.setSApplications(applicationsService.findById(app.getId()));
					tempuapp.setUserId(msource.getId());
					applicationpurviewService.saveOrUpade(tempuapp);
        	  }else{//取消订阅       		  
        		  try {
        			  applicationpurviewService.deleteByParams(msource.getId(),app.getId());
				} catch (Exception e) {
					// TODO: handle exception
					e.printStackTrace();
				}
//        		  System.out.println("进入取消订阅：：：userId：" +"::json::"+msource.getId());
        	  }
          }          
      	Json j = new Json();
		j.setSuccess(true);
		j.setMsg("修改成功！");
		super.writeJson(j);
	}
	
	/**
	 * 保存用户订阅的消息设置到数据库sourceId
	 * 参数  userID    userids：    [{id:"",state:""},{id:"",state:""}]
	 */
	public  void  saveUserOrg(){
		 HttpServletRequest request=ServletActionContext.getRequest();
		 String subscribe = request.getParameter("orgids");
		 System.out.println("转化后的订阅json为：：：orgids：" +"::json::"+subscribe);
		  Gson gson = new Gson();
          java.lang.reflect.Type type = new com.google.gson.reflect.TypeToken<List<MobileSubSource>>() {
          }.getType();
          List<MobileSubSource> msubsourcelist = gson.fromJson(subscribe, type);
          System.out.println("哈哈哈哈哈"+JSON.toJSONString(msubsourcelist));
          for(MobileSubSource msource:msubsourcelist){
        	  if(msource.getState()){//添加
        		   SApplicationorganization tempuapp=new SApplicationorganization();
        		   tempuapp.setSApplications(applicationsService.findById(app.getId()));
        		   tempuapp.setSOrganization(organizationService.findById(msource.getId()));
				   apporganService.saveOrUpdate(tempuapp);
				 
        	  }else{//取消     		  
        		  try {
        			  apporganService.deleteByParams(msource.getId(),app.getId());
				} catch (Exception e) {
					// TODO: handle exception
					e.printStackTrace();
				}
    	  	}
          } 
//          msubsourcelist.clear();
      	Json j = new Json();
		j.setSuccess(true);
		j.setMsg("修改成功！");
		super.writeJson(j);
	}
	
	
	
	
	
	/**
	 * 删除应用程序
	 * @return
	 * 调用用接口applications!delete.action?app.id=  
	 */
	public void  delete(){
//		System.out.println("获取到的application.getId()" + app.getId());
		
		SApplications app1=applicationsService.findById(app.getId());
		if(app1!=null){
			 List<SApplicationorganization> apporglist=new ArrayList<SApplicationorganization>(app1.getSApplicationorganizations());
			 app1.getSApplicationorganizations().clear();
			 for(SApplicationorganization msource:apporglist){
//				 apporganService.deleteByParams(msource.getId(),app.getId());
				 apporganService.deleteById(msource.getId());
			 }
			 apporglist.clear();
			 
			 List<SApplicationpurview> appniewlist=new ArrayList<SApplicationpurview>(app1.getSApplicationpurviews());
			 app1.getSApplicationpurviews().clear();
			 for(SApplicationpurview msource:appniewlist){
				 applicationpurviewService.deleteById(msource.getId());
			 }
			 appniewlist.clear();
			 
			 List<SDeviceapplications> appdevicelist=new ArrayList<SDeviceapplications>(app1.getSDeviceapplicationses());
			 app1.getSDeviceapplicationses().clear();
			 for(SDeviceapplications msource:appdevicelist){
				 deviceapplicationsService.deleteById(msource.getId() );
			 } 
			 appdevicelist.clear();
			 
			 List<SApplicationplatform> appformlist=new ArrayList<SApplicationplatform>(app1.getSApplicationplatforms());
			 app1.getSApplicationplatforms().clear();
			 for(SApplicationplatform msource:appformlist){
				 applicationPlatformService.deleteById(msource.getId());
			 }
			 appformlist.clear();
//			  	private Set<PUserapplicationmessage> PUserapplicationmessages = new HashSet<PUserapplicationmessage>(0);
			 
		 }
		
		
		
		
		
		applicationsService.deleteById(app.getId());
		
		
		
		
		
		
		Json j = new Json();
		j.setSuccess(true);
		j.setMsg("删除成功！");
		super.writeJson(j);
		// return "delete";
		// return index();
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

	
	public String index() {
		pageShow.setRows(BasePageShow.PAGESIZE);
		// 获取不同类型的应用程序
		setAppaCategoryNum();
		long temptotal = 0;
		listapplications = applicationsService.find(pageShow.getSearchinfo(),
				pageShow.getType(), pageShow.getPageNow(), 1000);
		// 获取选中要现实的设备状态类型的总数
		temptotal = applicationsService.find(pageShow.getSearchinfo(),
				pageShow.getType(), 0, 1000).size();
		// 设置总页数
		pageShow.setTotal(temptotal % BasePageShow.PAGESIZE == 0 ? temptotal
				/ BasePageShow.PAGESIZE : temptotal / BasePageShow.PAGESIZE + 1);
		if (pageShow.getPageNow() <= 0) {
			pageShow.setPageNow(1);
		}
		if (listapplications != null && listapplications.size() > 0) {
			 List<SApplications> templist=new ArrayList<SApplications>();			
			for(SApplications app:listapplications){
				app.setSApplicationorganizations(null);
				app.setSApplicationcategory(null);
				app.setPUserapplicationmessages(null);
				app.setSApplicationpurviews(null);
				app.setSDeviceapplicationses(null);
				app.setSApplicationplatforms(null);
				templist.add(app);
			}
			listapplications=templist;
//			templist.clear();
			
			
			
			
			
			
			
			
			
			
			
			
//			// 根据时间进行排序
			Collections.sort(listapplications, new Comparator<SApplications>() {
				@Override
				public int compare(SApplications o1, SApplications o2) {
					// TODO Auto-generated method stub
					Date d1 = o1.getCreateTime();
					Date d2 = o2.getCreateTime();
					if (d1 == null && d2 == null)
						return 0;
					if (d1 == null)
						return -1;
					if (d2 == null)
						return 1;
					return d1.compareTo(d2);
				}
			});
		   }
			
//		System.out.println("androidJson返回结果1："+ JSON.toJSONString(listapplications));
		
			
		
		return "index";
    }
	
	
	public String regist(){
		return "regist";
	}


	

//	/**
//	 *获取终端应用列表信息
//	 */
//	public void appList() {
//		listapplications=applicationsService.findAll();
//		JSONObject obj=new JSONObject();
//		List<Application> apps = new ArrayList<Application >();
//		if (listapplications != null && listapplications.size() > 0) {
//			for (SApplications t : listapplications) {
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
	public void detail() {
		System.out.println("ApplicationsAction.detail():"+app.getId());
		app=applicationsService.findById(app.getId());

		JSONObject obj=new JSONObject();
		obj.put("result", JSON.toJSONString(app));
		obj.put("state", "true");
		super.writeJson(obj);
	}
	public String list() {
		System.out.println("得到的appid为.list()"+app.getId());
		app=applicationsService.findById(app.getId());
		Set<SApplicationpurview> purview=app.getSApplicationpurviews();
		for(SApplicationpurview item : purview){
			String userId=item.getUserId();
			if(userId!=null){
				SUsers user=userServiceImpl.findById(userId);
				users.add(user);
			}
		}
		 Set<SApplicationorganization> appOrgs=app.getSApplicationorganizations();
		 for(SApplicationorganization item : appOrgs){
			 orgs.add(item.getSOrganization());
		 }
		aplatlist= new ArrayList<SApplicationplatform>(app.getSApplicationplatforms());
		
		return "list";
	}
	
	
	/**
	 * 更新应用程序
	 * @return
	 * 调用用接口applications!update.action?app.id =.. &app.name=........  
	 */
		public void update() {
			// System.out.println("获取到application.name=："+app.getName());
			SApplications d = applicationsService.findById(app.getId());
			d.setName(app.getName());
			d.setDescription(app.getDescription());
			//d.setIcon(app.getIcon());
	
			applicationsService.saveOrUpdate(d);
			Json j = new Json();
			j.setSuccess(true);
			j.setMsg("修改成功！");
			super.writeJson(j);

			// return "success";
			// return index();
		}
		
	
	
		
			/**
			 *添加应用程序 将自己的应用程序发布注册到后台服务端
			 * @return
			 * 调用用接口applications!update.action?app.id =.. &app.name=........  
			 */
			public String save() { 				
//				如果用户没有填写应用标识号，则标记为当前时间				
//				 SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss"); 
//			     Calendar calendar = Calendar.getInstance(); 
//				app.setCreateTime(new Date());
//				sdf.format(calendar.getTime())
//				 
				app.setCreateTime(new Timestamp((new java.util.Date()).getTime()));
				String appid=applicationsService.save(app);
				app.setId(appid);
				System.out.println("提交的div内容为： ：："+selectUserIds);
				String userId[]=selectUserIds.split(",");
				for(int i=0;i<userId.length;i++){
					SApplicationpurview tempuapp=new SApplicationpurview();
					tempuapp.setSApplications(app);
					tempuapp.setUserId(userId[i]);
					applicationpurviewService.saveOrUpade(tempuapp);
				}
				String orgId[]=selectOrgIds.split(",");
				for(int i=0;i<orgId.length;i++){
					   SApplicationorganization tempuapp=new SApplicationorganization();
	        		   tempuapp.setSApplications(applicationsService.findById(app.getId()));
	        		   tempuapp.setSOrganization(organizationService.findById(orgId[i]));
					   apporganService.saveOrUpdate(tempuapp);
				}
			   String result=  saveAppIcon();
			   if(result=="error")
				   return "error";
			   else return index();
				
			}
			private String saveAppIcon() {
				if(picfile==null)
			    	 return "success";
			      //取文件后缀名 
			     String picfilename=app.getId()+picfileFileName.substring( picfileFileName.lastIndexOf('.'));  
			     app.setIcon(picfilename);
			     applicationsService.saveOrUpdate(app);
			     
				  String root1; 
			     try {
				     InputStream is1=new FileInputStream(picfile); 
//				     root1= ServletActionContext.getRequest().getRealPath("/appIcon/appIcon") ;//存储到apache项目发布目录上 	 
			         root1= "C:\\project/appIcon/appIcon";
                     File dir1 = new File(root1);
                     if (dir1.exists() == false) {
                         boolean b = dir1.mkdirs();
                     }
				     File copyFile1=new File(root1,picfilename); 	     
				     OutputStream os1=new FileOutputStream(copyFile1);	     
				    byte[] buffer1=new byte[400]; 	     
				    int length1=0; 	     
				    while((length1=is1.read(buffer1)) > 0 ){ 
				       os1.write(buffer1, 0, length1); 
				     } 
				     is1.close(); 
				     os1.close(); 
						} catch (Exception e) {
							// TODO: handle exception
							e.printStackTrace();
							 return "error"; 
						}
			     return "success";
			}
		
	
			
			
			/**
			 *添加应用程序 将自己的应用程序发布注册到后台服务端
			 * @return
			 * 调用用接口applications!update.action?app.id =.. &app.name=........  
			 */
			public String updateApp() { 	
				String tempName=app.getName();
				String tempDescription=app.getDescription();
				app=applicationsService.findById(app.getId());
				app.setName(tempName);
				app.setDescription(tempDescription);				
				applicationsService.saveOrUpdate(app);
				return saveAppIcon();
			}
			
			
			
			
			
			
			
			
			
			
			
			
			
			
	/**
	 * 获取不同应用程序数目
	 * @return
	 */
	private void setAppaCategoryNum( ) {		
		long temptotal=applicationsService.count();
		pageShow.setTotalnum(temptotal);
		pageShow.setAll(temptotal);
				
		long androidall=applicationsService.count(AppsPageShow.ANDROIDALL);
		pageShow.setAndroidall(androidall);			
		long droidphone=applicationsService.count(AppsPageShow.ANDROIDPHONE);
		pageShow.setAndroidphone(droidphone);
		pageShow.setAndroidpad(androidall-droidphone);
		
		long iosall=applicationsService.count(AppsPageShow.IOSALL);
		pageShow.setIosall(iosall);
		long iospad=applicationsService.count(AppsPageShow.IOSPAD);
		pageShow.setIospad(iospad);
		pageShow.setIosphone(iosall-iospad);
	}
	

	public SApplications getApp() {
		return app;
	}
	public void setApp(SApplications app) {
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
	public File getPicfile() {
		return picfile;
	}
	public void setPicfile(File picfile) { 
		this.picfile = picfile;
	}
	public String getPicfileFileName() {
		return picfileFileName;
	}
	public void setPicfileFileName(String picfileFileName) {
		this.picfileFileName = picfileFileName;
	}


}
