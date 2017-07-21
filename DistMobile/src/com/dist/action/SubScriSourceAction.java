package com.dist.action;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.struts2.convention.annotation.Action;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;


import com.alibaba.fastjson.JSON;
import com.dist.action.admin.BasicAction;
import com.dist.entity.PSubscriptionsource;
import com.dist.entity.PUsersubscription;
import com.dist.entity.SNews;
import com.dist.pagentity.BasePageShow;
import com.dist.pagentity.Json;
import com.dist.service.PSubscriptionsourceServiceI;
import com.dist.service.PUsersubscriptionServiceI;
import com.dist.service.SNewsServiceI;
import com.dist.service.SNewspicServiceI;
import com.opensymphony.xwork2.ModelDriven;
/**
 * 描述记录action
 * @author 王明远
 *@Action(value = "subScriSourceAction")
 */

@Controller
public class SubScriSourceAction extends BasicAction implements ModelDriven<PSubscriptionsource> {




	/**
	 * 
	 */
	private static final long serialVersionUID = -8056222140614277724L;
	private PSubscriptionsource entity= new PSubscriptionsource();
	private PSubscriptionsourceServiceI entityService;
	
	private List<PSubscriptionsource> listEntity;
	
	private SNewsServiceI newsService;	

	// 用户订阅消息
	private PUsersubscriptionServiceI PUsubScriService;
	
	@Resource
	public void setPUsersubscriptionService(PUsersubscriptionServiceI PUsubScriService) {
		this.PUsubScriService = PUsubScriService;
	}

	
	@Resource
	public void setNewsService(SNewsServiceI newsService) {
		this.newsService = newsService;
	}

	private BasePageShow pageShow=new BasePageShow();
	
	public PSubscriptionsourceServiceI getMenuService() {
		return entityService;
	}

	@Autowired
	public void setSubScriSourceService(PSubscriptionsourceServiceI entityService) {
		this.entityService = entityService;
	}

	@Override
	public PSubscriptionsource getModel() {
		return entity;
	}

	
	
	/**
	 * 查询及显示主页
	 * 应用程序主页，获取主页的用户角色列表等信息
	 * @return   相应类型的listRoles 及数目 封装在了 pageShow里面
	 * 搜索时调用接口subScriSource!list.action?pageShow.searchinfo=。。。。
	 * 若pageShow.searchinfo为空，则说明显示全部角色分页  
	 */
	public String list() {
		pageShow.setRows(BasePageShow.PAGESIZE);
////		获取不同类型的应用程序
//		setAppaCategoryNum();
			long temptotal=0;
			listEntity=entityService.find(pageShow.getSearchinfo(), pageShow.getPageNow(), BasePageShow.PAGESIZE);
//				获取选中要现实的设备状态类型的总数
				temptotal=entityService.count(pageShow.getSearchinfo());
//			设置总页数
			pageShow.setTotal(temptotal % BasePageShow.PAGESIZE == 0 ? temptotal / BasePageShow.PAGESIZE : temptotal /BasePageShow.PAGESIZE + 1) ; 
			if(pageShow.getPageNow()<=0){
				pageShow.setPageNow(1);
			}	
			//System.out.println("androidJson返回结果1："+JSON.toJSONString(listEntity));
		return  "index";	
	}
	

	
	/**
	 * 添加或更新组织机构
	 * @return
	 * 调用用接口subScriSource!save.action?entity.id= 。。。。  &entity.content=...&... 
	 */
	public void save(){
		//System.out.println("获取到的：：："+JSON.toJSONString(entity));
		entityService.save(entity);
		Json j=new Json();
		j.setSuccess(true);
		super.writeJson(j);
	}

	public void update(){
		PSubscriptionsource temp=entityService.findById(entity.getId());
		temp.setDescription(entity.getDescription());
		temp.setName(entity.getName());
		entityService.save(temp);
		
		Json j=new Json();
		j.setSuccess(true);
		super.writeJson(j);
	}
	
	/**
	 * 删除 订阅栏目
	 * 
	 * @return
	 * 调用用接口subScriSource!delete.action?entity.id= 。。。。 
	 */
	public void  delete(){
		//System.out.println("获取到的application.getId()"+entity.getId());
		PSubscriptionsource  subsource=entityService.findById(entity.getId());	
//		删除该栏目的用户订阅
		 List<PUsersubscription> listsubuser=new ArrayList<PUsersubscription>(subsource.getPUsersubscriptions());
		 for(PUsersubscription subuser:listsubuser){
			 subuser.getPSubscriptionsource().getPUsersubscriptions().remove(subuser);
			 PUsubScriService.deleteById(subuser.getId());
		 }
		 listsubuser.clear();
		 
//		 删除栏目下的新闻
		 List<SNews> listnews=new ArrayList<SNews>(subsource.getSNews());
		 for(SNews subnews:listnews){
			 newsService.deleteById(subnews.getId());
		 }
		 listnews.clear();
		 	
		
		entityService.deleteById(entity.getId());
		Json j=new Json();
		j.setSuccess(true);
		super.writeJson(j);
	}
	
	/**
	 * 获取的详情
	 * @return
	 * 调用用接口subScriSource!detail.action?entity.id=。。。。  
	 * 
	 */
	public void detail() {
		entity=entityService.findById(entity.getId());
		entity.setPUsersubscriptions(null);
		entity.setSNews(null);
		Json j=new Json();
		j.setObj(entity);
		j.setSuccess(true);
		super.writeJson(j);
		
	}
	
	
	
	
	
	


	public PSubscriptionsource getEntity() {
		return entity;
	}

	public void setEntity(PSubscriptionsource entity) {
		this.entity = entity;
	}

	public List<PSubscriptionsource> getListEntity() {
		return listEntity;
	}

	public void setListEntity(List<PSubscriptionsource> listEntity) {
		this.listEntity = listEntity;
	}

	public BasePageShow getPageShow() {
		return pageShow;
	}

	public void setPageShow(BasePageShow pageShow) {
		this.pageShow = pageShow;
	}

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
