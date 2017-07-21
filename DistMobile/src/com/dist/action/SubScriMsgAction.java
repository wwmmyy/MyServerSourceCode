//package com.dist.action;
//
//import java.util.List;
//
//import org.apache.struts2.convention.annotation.Action;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//
//import com.alibaba.fastjson.JSON;
//import com.dist.action.admin.BasicAction;
//import com.dist.entity.PSubscriptionmessages;
//import com.dist.pagentity.BasePageShow;
//import com.dist.service.PSubscriptionmessagesServiceI;
//import com.opensymphony.xwork2.ModelDriven;
///**
// * 描述记录信息action
// * @author 王明远
// *@Action(value = "subScriSourceAction")
// */
//
//@Controller
//public class SubScriMsgAction extends BasicAction implements ModelDriven<PSubscriptionmessages> {
//
//
//	/**
//	 * 
//	 */
//	private static final long serialVersionUID = 7846508460943756061L;
//	private PSubscriptionmessages entity= new PSubscriptionmessages();
//	private PSubscriptionmessagesServiceI entityService;
//	
//	private List<PSubscriptionmessages> listEntity;
//	
//	private BasePageShow pageShow=new BasePageShow();
//	
//	public PSubscriptionmessagesServiceI getMenuService() {
//		return entityService;
//	}
//
//	@Autowired
//	public void setSubScriMsgService(PSubscriptionmessagesServiceI entityService) {
//		this.entityService = entityService;
//	}
//
//	@Override
//	public PSubscriptionmessages getModel() {
//		return entity;
//	}
//
//	
//	
//	/**
//	 * 查询及显示主页
//	 * 应用程序主页，获取主页的用户角色列表等信息
//	 * @return   相应类型的listRoles 及数目 封装在了 pageShow里面
//	 * 搜索时调用接口subScriMsg!list.action?pageShow.searchinfo=。。。。
//	 * 若pageShow.searchinfo为空，则说明显示全部角色分页  
//	 */
//	public String list() {
//		pageShow.setRows(BasePageShow.PAGESIZE);
//////		获取不同类型的应用程序
////		setAppaCategoryNum();
//			long temptotal=0;
//			listEntity=entityService.find(pageShow.getSearchinfo(), pageShow.getPageNow(), BasePageShow.PAGESIZE);
////				获取选中要现实的设备状态类型的总数
//				temptotal=entityService.count(pageShow.getSearchinfo());
////			设置总页数
//			pageShow.setTotal(temptotal % BasePageShow.PAGESIZE == 0 ? temptotal / BasePageShow.PAGESIZE : temptotal /BasePageShow.PAGESIZE + 1) ; 
//			if(pageShow.getPageNow()<=0){
//				pageShow.setPageNow(1);
//			}	
//			System.out.println("androidJson返回结果1："+JSON.toJSONString(listEntity));
//		return  "index";	
//	}
//	
//	
//	
//	
//
//	
//	/**
//	 * 添加或更新组织机构
//	 * @return
//	 * 调用用接口subScriMsg!save.action?entity.id= 。。。。  &entity.content=...&... 
//	 */
//	public String save(){
//		entityService.save(entity);
//		//userServiceImpl
////		System.out.println("获取到的添加信息为："+entity.getEventData());
//		return list();
//	}
//
//	/**
//	 * 删除组织机构
//	 * @return
//	 * 调用用接口subScriMsg!delete.action?entity.id= 。。。。 
//	 */
//	public String  delete(){
//		System.out.println("获取到的application.getId()"+entity.getId());
//		entityService.deleteById(entity.getId());
//		return "delete";
////		return  index();
//	}
//	
//	/**
//	 * 获取的详情
//	 * @return
//	 * 调用用接口subScriMsg!detail.action?entity.id=。。。。  
//	 * 
//	 */
//	public String detail() {
//		entity=entityService.findById(entity.getId());
//		return  "detail";
//	}
//	
//	
//	
//	
//	
//	
//
//
//	public PSubscriptionmessages getEntity() {
//		return entity;
//	}
//
//	public void setEntity(PSubscriptionmessages entity) {
//		this.entity = entity;
//	}
//
//	public List<PSubscriptionmessages> getListEntity() {
//		return listEntity;
//	}
//
//	public void setListEntity(List<PSubscriptionmessages> listEntity) {
//		this.listEntity = listEntity;
//	}
//
//	public BasePageShow getPageShow() {
//		return pageShow;
//	}
//
//	public void setPageShow(BasePageShow pageShow) {
//		this.pageShow = pageShow;
//	}
//
//	
//	
//	
//	
//	
//	
//	
//	
//	
//	
//	
//	
//	
//	
//	
//	
//}
