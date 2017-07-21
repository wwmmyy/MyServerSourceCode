package com.dist.action;

import org.apache.log4j.Logger;
import org.apache.struts2.convention.annotation.Action;
import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.fastjson.JSON;
import com.dist.action.admin.BasicAction;
import com.dist.pagentity.Organization;
import com.dist.service.SOrganizationServiceI;
import com.opensymphony.xwork2.ModelDriven;
/**
 * 组织管理action
 * @author 王明远
 *
 */

@Action(value = "menuAction")
public class OrganMenuAction extends BasicAction implements ModelDriven<Organization> {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6789895326889808810L;

	private static final Logger logger = Logger.getLogger(OrganMenuAction.class);

	private Organization menu = new Organization();
	private SOrganizationServiceI menuService;
	public SOrganizationServiceI getMenuService() {
		return menuService;
	}

	@Autowired
	public void setOrganizationService(SOrganizationServiceI menuService) {
		this.menuService = menuService;
	}

	@Override
	public Organization getModel() {
		return menu;
	}

//	/**
//	 * 异步获取树节点
//	 */
//	public void getTreeNode() {
//		super.writeJson(menuService.getTreeNode(menu.getId()));
//	}

	public void getAllTreeNode() {
//		System.out.println("哈哈哈哈：："+JSON.toJSONString(menuService.getAllTreeNode()));
		super.writeJson(menuService.getAllTreeNode());
	}

}
