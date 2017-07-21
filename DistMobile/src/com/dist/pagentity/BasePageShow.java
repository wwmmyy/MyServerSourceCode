package com.dist.pagentity;
/**
 * 所有的类公用的分页查询的基类
 * @author wmy
 *
 */

public class BasePageShow {

	
	public final static int PAGESIZE=20;
	private int pageNow;
	private int rows;
	private long total;
	private long totalnum;
	
	private String sort;
	private String order;
	private String ids;
	
//	记录当前获取到的设备列表类型，页面根据类型选中不同的设备状态按钮
	private int type;//用于记录设备的状态
	private  String systemtype;//用于记录设备操作系统的类型
	private  String hardwaretype;//用于记录设备硬件型号
	
//	表示用户搜索的用户信息
	private String searchinfo;
	
	
	




	public String getSearchinfo() {
		return searchinfo;
	}

	public void setSearchinfo(String searchinfo) {
		this.searchinfo = searchinfo;
	}

	public String getSystemtype() {
		return systemtype;
	}

	public void setSystemtype(String systemtype) {
		this.systemtype = systemtype;
	}

	public String getHardwaretype() {
		return hardwaretype;
	}

	public void setHardwaretype(String hardwaretype) {
		this.hardwaretype = hardwaretype;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public long getTotalnum() {
		return totalnum;
	}

	public void setTotalnum(long totalnum) {
		this.totalnum = totalnum;
	}



	public long getTotal() {
		return total;
	}

	public void setTotal(long total) {
		this.total = total;
	}

	public String getIds() {
		return ids;
	}

	public void setIds(String ids) {
		this.ids = ids;
	}

	public String getSort() {
		return sort;
	}

	public void setSort(String sort) {
		this.sort = sort;
	}

	public String getOrder() {
		return order;
	}

	public void setOrder(String order) {
		this.order = order;
	}



	public int getPageNow() {
		return pageNow;
	}

	public void setPageNow(int pageNow) {
		this.pageNow = pageNow;
	}

	public int getRows() {
		return rows;
	}

	public void setRows(int rows) {
		this.rows = rows;
	}



}
