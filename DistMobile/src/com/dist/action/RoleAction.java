package com.dist.action;

import java.util.List;

import org.apache.log4j.Logger;
import org.apache.struts2.convention.annotation.Action;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.alibaba.fastjson.JSON;
import com.dist.action.admin.BasicAction;
import com.dist.entity.SRole;
import com.dist.entity.SRole;
import com.dist.entity.SUsers;
import com.dist.pagentity.AppsPageShow;
import com.dist.pagentity.BasePageShow;
import com.dist.pagentity.Json;
import com.dist.pagentity.Organization;
import com.dist.service.SOrganizationServiceI;
import com.dist.service.SRoleServiceI;
import com.opensymphony.xwork2.ModelDriven;
/**
 * 组织管理action
 * @author 王明远
 *@Action(value = "organizationAction")
 */

@Controller
public class RoleAction extends BasicAction implements ModelDriven<SRole> {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6789895326889808810L;

	private static final Logger logger = Logger.getLogger(RoleAction.class);

	private SRole entity= new SRole();
	private SRoleServiceI roleService;
	private List<SRole> listEntity;
	
	private BasePageShow pageShow=new BasePageShow();
	
	private String selectedIndex="5.2";
	
	public String getSelectedIndex() {
		return selectedIndex;
	}

	public void setSelectedIndex(String selectedIndex) {
		this.selectedIndex = selectedIndex;
	}

	
	public SRoleServiceI getMenuService() {
		return roleService;
	}

	@Autowired
	public void setRoleService(SRoleServiceI organizationService) {
		this.roleService = organizationService;
	}

	@Override
	public SRole getModel() {
		return entity;
	}

	
	
	
	
	/**
	 * 查询及显示主页
	 * 应用程序主页，获取主页的用户角色列表等信息
	 * @return   相应类型的listRoles 及数目 封装在了 pageShow里面
	 * 搜索时调用接口role!index.action?pageShow.searchinfo=。。。。
	 * 若pageShow.searchinfo为空，则说明显示全部角色分页  
	 */
	public String list() {
		pageShow.setRows(BasePageShow.PAGESIZE);
////		获取不同类型的应用程序
//		setAppaCategoryNum();
			long temptotal=0;
			listEntity=roleService.find(pageShow.getSearchinfo(), pageShow.getPageNow(), BasePageShow.PAGESIZE);
//				获取选中要现实的设备状态类型的总数
				temptotal=roleService.count(pageShow.getSearchinfo());
//			设置总页数
			pageShow.setTotal(temptotal % BasePageShow.PAGESIZE == 0 ? temptotal / BasePageShow.PAGESIZE : temptotal /BasePageShow.PAGESIZE + 1) ; 
			if(pageShow.getPageNow()<=0){
				pageShow.setPageNow(1);
			}	
			//System.out.println("androidJson返回结果1："+JSON.toJSONString(listEntity));
		return  "index";	
	}
	
	
	
	
	
	
	//获取全部用户角色列表，不翻页
	public String index() {
		listEntity = roleService.findAll();
//		//System.out.println("哈哈哈哈：："+JSON.toJSONString(listEntity));
		pageShow.setPageNow(1);
		pageShow.setTotal(1);
		
		return  "index";
	}
		
	//添加或更新用户角色
	public String save(){
		roleService.save(entity);
		//userServiceImpl
		//System.out.println("获取到的添加信息为："+entity.getName());
		return list();
	}
	
	/**
	 * 更新角色
	 * 
	 * @return
	 */
	public void updateRole() {
	
		SRole d=roleService.findById(entity.getId());
		d.setName(entity.getName());
		roleService.save(d);
		
		Json j = new Json();
		j.setSuccess(true);
		j.setMsg("修改成功！");
		writeJson(j);
	}
	
	
	/**
	 * 删除用户角色
	 * @return
	 * 调用用接口role!delete.action?entity.id=  
	 */
	public void  delete(){
		//System.out.println("获取到的application.getId()"+entity.getId());
		roleService.deleteById(entity.getId());
		Json j = new Json();
		j.setSuccess(true);
		j.setMsg("删除成功！");
		writeJson(j);
	//	return "delete";
//		return  index();
	}

	/**
	 * 获取某个用户角色的详情
	 * @return
	 * 调用用接口role!detail.action?entity.id=。。。。  
	 * 
	 */
	public String detail() {
		entity=roleService.findById(entity.getId());
//		//System.out.println("androidJson返回结果："+JSON.toJSONString(app));
		return  "detail";
	}
		
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	public SRole getEntity() {
		return entity;
	}

	public void setEntity(SRole entity) {
		this.entity = entity;
	}

	public List<SRole> getListEntity() {
		return listEntity;
	}

	public void setListEntity(List<SRole> listEntity) {
		this.listEntity = listEntity;
	}

	public BasePageShow getPageShow() {
		return pageShow;
	}

	public void setPageShow(BasePageShow pageShow) {
		this.pageShow = pageShow;
	}



	
}
