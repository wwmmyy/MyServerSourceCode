package com.dist.action;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.alibaba.fastjson.JSON;
import com.dist.action.admin.BasicAction;
import com.dist.entity.SPermissions;
import com.dist.entity.SRole;
import com.dist.pagentity.BasePageShow;
import com.dist.pagentity.Json;
import com.dist.service.SPermissionsServiceI;
import com.opensymphony.xwork2.ModelDriven;
/**
 * 组织管理action
 * @author 王明远
 *@Action(value = "entityAction")
 */

@Controller
public class PermissionsAction extends BasicAction implements ModelDriven<SPermissions> {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6789895326889808810L;

	private SPermissions entity= new SPermissions();
	private SPermissionsServiceI entityService;
	
	private List<SPermissions> listEntity;
	private BasePageShow pageShow=new BasePageShow();
	private String selectedIndex="5.3";
	
	public String getSelectedIndex() {
		return selectedIndex;
	}

	public void setSelectedIndex(String selectedIndex) {
		this.selectedIndex = selectedIndex;
	}

	public SPermissionsServiceI getMenuService() {
		return entityService;
	}

	@Autowired
	public void setPermissionsService(SPermissionsServiceI entityService) {
		this.entityService = entityService;
	}

	@Override
	public SPermissions getModel() {
		return entity;
	}

	
	
	/**
	 * 查询及显示主页
	 * 应用程序主页，获取主页的用户角色列表等信息
	 * @return   相应类型的listRoles 及数目 封装在了 pageShow里面
	 * 搜索时调用接口permissions!index.action?pageShow.searchinfo=。。。。
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
	
	
//	
//	//获取功能权限列表
//	public String list() {
//		listEntity = entityService.findAll();
//		//System.out.println("哈哈哈哈：："+JSON.toJSONString(listEntity));
//		
//		return  "index";
//	}
	
	
	

	//添加或更新功能权限
	public String save(){
		entityService.save(entity);
		//userServiceImpl
		//System.out.println("获取到的添加信息为："+entity.getName());
		return list();
	}

	
	/**
	 * 删除用户角色
	 * @return
	 * 调用用接口permissions!delete.action?entity.id=  
	 */
	public void  delete(){
		//System.out.println("获取到的application.getId()"+entity.getId());
		entityService.deleteById(entity.getId());
		
		Json j = new Json();
		j.setSuccess(true);
		j.setMsg("删除成功！");
		writeJson(j);
		//return "delete";
//		return  index();
	}
	
	/**
	 * 更新权限
	 * 
	 * @return
	 */
	public void updatePermissions() {
	
		SPermissions d=entityService.findById(entity.getId());
		d.setName(entity.getName());
		entityService.save(d);
		
		Json j = new Json();
		j.setSuccess(true);
		j.setMsg("修改成功！");
		writeJson(j);
	}
	
	
	/**
	 * 获取某个用户角色的详情
	 * @return
	 * 调用用接口permissions!detail.action?entity.id=。。。。  
	 * 
	 */
	public String detail() {
		entity=entityService.findById(entity.getId());
		return  "detail";
	}

	public SPermissions getEntity() {
		return entity;
	}

	public void setEntity(SPermissions entity) {
		this.entity = entity;
	}

	public List<SPermissions> getListEntity() {
		return listEntity;
	}

	public void setListEntity(List<SPermissions> listEntity) {
		this.listEntity = listEntity;
	}

	public BasePageShow getPageShow() {
		return pageShow;
	}

	public void setPageShow(BasePageShow pageShow) {
		this.pageShow = pageShow;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

	
	
	
	
	
	
	
	
}
