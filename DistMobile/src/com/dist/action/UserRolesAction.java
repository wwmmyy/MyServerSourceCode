package com.dist.action;

import java.util.List;

import javax.annotation.Resource;

import org.apache.struts2.convention.annotation.Action;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.alibaba.fastjson.JSON;
import com.dist.action.admin.BasicAction;
import com.dist.entity.SUserroles;
import com.dist.pagentity.BasePageShow;
import com.dist.service.SUserrolesServiceI;
import com.opensymphony.xwork2.ModelDriven;
/**
 * action
 * @author 王明远
 *@Action(value = "userRolesAction") 由于用户角色表已经关联，此方法的功能在useraction及roleaction中已经实现了，可以不调用此action
 */

@Controller
public class UserRolesAction extends BasicAction implements ModelDriven<SUserroles> {


	/**
	 * 
	 */
	private static final long serialVersionUID = -3733793231965072901L;
	private SUserroles entity= new SUserroles();
	private SUserrolesServiceI entityService;
	
	private List<SUserroles> listEntity;
	
	private BasePageShow pageShow=new BasePageShow();
	
	public SUserrolesServiceI getMenuService() {
		return entityService;
	}

	@Resource
	public void setUserrolesService(SUserrolesServiceI entityService) {
		this.entityService = entityService;
	}

	@Override
	public SUserroles getModel() {
		return entity;
	}

	
	
	/**
	 * 查询及显示主页
	 * 应用程序主页，获取主页的用户角色列表等信息
	 * @return   相应类型的listRoles 及数目 封装在了 pageShow里面
	 * 搜索时调用接口userRoles!list.action?pageShow.searchinfo=。。。。
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
	 * 调用用接口userRoles!save.action?entity.id= 。。。。  &entity.content=...&... 
	 */
	public String save(){
		entityService.save(entity);
		//userServiceImpl
//		//System.out.println("获取到的添加信息为："+entity.getEventData());
		return list();
	}

	/**
	 * 删除组织机构
	 * @return
	 * 调用用接口userRoles!delete.action?entity.id= 。。。。 
	 */
	public String  delete(){
		//System.out.println("获取到的application.getId()"+entity.getId());
		entityService.deleteById(entity.getId());
		return "delete";
//		return  index();
	}
	
	/**
	 * 获取某个组织机构的详情
	 * @return
	 * 调用用接口userRoles!detail.action?entity.id=。。。。  
	 * 
	 */
	public String detail() {
		entity=entityService.findById(entity.getId());
		return  "detail";
	}
	
	
	/**
	 * 通过用户的id获取用户角色关系列表
	 * @return
	 */
	public String detailByUserid() {
		listEntity=entityService.findByProperty("SUsers.id", entity.getSUsers().getId());
		return  "index";
	}
	
	
	/**
	 * 通过角色的id获取用户角色关系列表
	 * @return
	 */
	public String detailByRoleid() {
		listEntity=entityService.findByProperty("SDevice.id", entity.getSRole().getId());
		return  "index";
	}


	public SUserroles getEntity() {
		return entity;
	}

	public void setEntity(SUserroles entity) {
		this.entity = entity;
	}

	public List<SUserroles> getListEntity() {
		return listEntity;
	}

	public void setListEntity(List<SUserroles> listEntity) {
		this.listEntity = listEntity;
	}

	public BasePageShow getPageShow() {
		return pageShow;
	}

	public void setPageShow(BasePageShow pageShow) {
		this.pageShow = pageShow;
	}

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
