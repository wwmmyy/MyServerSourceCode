package com.dist.action;

import java.util.List;

import org.apache.struts2.convention.annotation.Action;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.alibaba.fastjson.JSON;
import com.dist.action.admin.BasicAction;
import com.dist.entity.PSnsPhoots;
import com.dist.pagentity.BasePageShow;
import com.dist.service.PSnsPhootsServiceI;
import com.opensymphony.xwork2.ModelDriven;
/**
 * 查看日志记录action
 * @author 王明远
 *@Action(value = "psnsAction")
 */

@Controller
public class PsnsPhootAction extends BasicAction implements ModelDriven<PSnsPhoots> {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6789895326889808810L;


	private PSnsPhoots entity= new PSnsPhoots();
	private PSnsPhootsServiceI entityService;
	
	private List<PSnsPhoots> listEntity;
	
	private BasePageShow pageShow=new BasePageShow();
	
	public PSnsPhootsServiceI getMenuService() {
		return entityService;
	}

	@Autowired
	public void setPsnsPhootsService(PSnsPhootsServiceI entityService) {
		this.entityService = entityService;
	}

	@Override
	public PSnsPhoots getModel() {
		return entity;
	}

	
	
	/**
	 * 查询及显示主页
	 * 应用程序主页，获取主页的用户角色列表等信息
	 * @return   相应类型的listRoles 及数目 封装在了 pageShow里面
	 * 搜索时调用接口psnsPhoot!list.action?pageShow.searchinfo=。。。。
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
	 * 调用用接口psns!save.action?entity.id= 。。。。  &entity.content=...&... 
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
	 * 调用用接口psns!delete.action?entity.id= 。。。。 
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
	 * 调用用接口psns!detail.action?entity.id=。。。。  
	 * 
	 */
	public String detail() {
		entity=entityService.findById(entity.getId());
		return  "detail";
	}
	
	
	
	
	
	


	public PSnsPhoots getEntity() {
		return entity;
	}

	public void setEntity(PSnsPhoots entity) {
		this.entity = entity;
	}

	public List<PSnsPhoots> getListEntity() {
		return listEntity;
	}

	public void setListEntity(List<PSnsPhoots> listEntity) {
		this.listEntity = listEntity;
	}

	public BasePageShow getPageShow() {
		return pageShow;
	}

	public void setPageShow(BasePageShow pageShow) {
		this.pageShow = pageShow;
	}

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
