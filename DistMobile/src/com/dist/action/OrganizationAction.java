package com.dist.action;

import java.util.List;

import org.apache.log4j.Logger;
import org.apache.struts2.convention.annotation.Action;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.dist.action.admin.BasicAction;
import com.dist.entity.SDevice;
import com.dist.entity.SOrganization;
import com.dist.pagentity.BasePageShow;
import com.dist.pagentity.Json;
import com.dist.pagentity.Organization;
import com.dist.service.SOrganizationServiceI;
import com.opensymphony.xwork2.ModelDriven;
/**
 * 组织管理action
 * @author 王明远
 *@Action(value = "organizationAction")
 */

@Controller
public class OrganizationAction extends BasicAction implements ModelDriven<SOrganization> {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6789895326889808810L;


	private SOrganization entity= new SOrganization();
	private SOrganizationServiceI organizationService;
	
	private List<SOrganization> listEntity;
	private List<SOrganization> allListEntity;
	
	private BasePageShow pageShow=new BasePageShow();
	private String selectedIndex="5.0";
	private List<Organization> myOrg;
	public String getSelectedIndex() {
		return selectedIndex;
	}

	public void setSelectedIndex(String selectedIndex) {
		this.selectedIndex = selectedIndex;
	}

	public SOrganizationServiceI getMenuService() {
		return organizationService;
	}
	
	public List<Organization> getMyOrg() {
		return myOrg;
	}

	public void setMyOrg(List<Organization> myOrg) {
		this.myOrg = myOrg;
	}

	@Autowired
	public void setOrganizationService(SOrganizationServiceI organizationService) {
		this.organizationService = organizationService;
	}

	@Override
	public SOrganization getModel() {
		return entity;
	}

	
	
	public List<SOrganization> getAllListEntity() {
		return allListEntity;
	}

	public void setAllListEntity(List<SOrganization> allListEntity) {
		this.allListEntity = allListEntity;
	}

	/**
	 * 查询及显示主页
	 * 应用程序主页，获取主页的用户角色列表等信息
	 * @return   相应类型的listRoles 及数目 封装在了 pageShow里面
	 * 搜索时调用接口organizatio!index.action?pageShow.searchinfo=。。。。
	 * 若pageShow.searchinfo为空，则说明显示全部角色分页  
	 */
	public String list() {
		pageShow.setRows(BasePageShow.PAGESIZE);
////		获取不同类型的应用程序
//		setAppaCategoryNum();
			long temptotal=0;
			allListEntity=organizationService.findAll();
			listEntity=organizationService.find(pageShow.getSearchinfo(), pageShow.getPageNow(), BasePageShow.PAGESIZE);
//				获取选中要现实的设备状态类型的总数
				temptotal=organizationService.count(pageShow.getSearchinfo());
//			设置总页数
			pageShow.setTotal(temptotal % BasePageShow.PAGESIZE == 0 ? temptotal / BasePageShow.PAGESIZE : temptotal /BasePageShow.PAGESIZE + 1) ; 
			if(pageShow.getPageNow()<=0){
				pageShow.setPageNow(1);
			}	
			//System.out.println("androidJson返回结果1："+JSON.toJSONString(listEntity));
		return  "index";	
	}
	
	public void getAllOrg(){
//		allListEntity=organizationService.findAll();
//		for(int i=0;i<allListEntity.size();i++){
//			SOrganization item=allListEntity.get(i);
//			//System.out.println("组织机构"+JSON.toJSONString(item));
//			Organization o=new Organization();
//			
//			o.setId(item.getId());
//			o.setName(item.getName());
//			
//			if(item.getSorganization()!=null){
//				//System.out.println("哈哈哈哈：："+item.getSorganization().getName());
//				o.setPid(item.getSorganization().getId());
//			}
//			myOrg.add(o);
//		}
		myOrg=organizationService.getAllTreeNode();
		//System.out.println("所有组织机构"+JSON.toJSONString(myOrg));
		JSONObject obj=new JSONObject();
		obj.put("result", JSON.toJSONString(myOrg));
		obj.put("state", "true");
		super.writeJson(obj);
	}
	
	
	
	
	
	
//	//获取组织机构列表
//	public String list() {
//		listEntity = organizationService.findAll();
//		//System.out.println("哈哈哈哈：："+JSON.toJSONString(listEntity));
//		
//		return  "index";
//	}
	
	
	//添加或更新组织机构
	public String save(){
		organizationService.save(entity);
		//userServiceImpl
		//System.out.println("获取到的添加信息为："+entity.getName());
		return list();
	}
	/**
	 * 更新组织机构
	 * 
	 * @return
	 */
	public void updateOrganization() {
	
		SOrganization d=organizationService.findById(entity.getId());
		//System.out.println("获取到的添加信息ID为："+entity.getId());
		d.setName(entity.getName());
		SOrganization s=organizationService.findById(entity.getSorganization().getId());
		//System.out.println("获取到的添加信息组织机构ID为："+s.getId());
		d.setSorganization(s);
		organizationService.save(d);
		
		Json j = new Json();
		j.setSuccess(true);
		j.setMsg("删除成功！");
		writeJson(j);
	}
	/**
	 * 删除组织机构
	 * @return
	 * 调用用接口organization!delete.action?entity.id= 。。。。 
	 */
	public void  delete(){
		//System.out.println("获取到的application.getId()"+entity.getId());
		organizationService.deleteById(entity.getId());
		
		Json j = new Json();
		j.setSuccess(true);
		j.setMsg("删除成功！");
		writeJson(j);
		//return "delete";
//		return  index();
	}
	
	/**
	 * 获取某个组织机构的详情
	 * @return
	 * 调用用接口organization!detail.action?entity.id=。。。。  
	 * 
	 */
	public String detail() {
		entity=organizationService.findById(entity.getId());
		return  "detail";
	}
	
	
	
	
	
	


	public SOrganization getEntity() {
		return entity;
	}

	public void setEntity(SOrganization entity) {
		this.entity = entity;
	}

	public List<SOrganization> getListEntity() {
		return listEntity;
	}

	public void setListEntity(List<SOrganization> listEntity) {
		this.listEntity = listEntity;
	}

	public BasePageShow getPageShow() {
		return pageShow;
	}

	public void setPageShow(BasePageShow pageShow) {
		this.pageShow = pageShow;
	}

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
