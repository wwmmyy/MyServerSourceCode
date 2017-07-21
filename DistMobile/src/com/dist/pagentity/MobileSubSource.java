package com.dist.pagentity;
/**
 * 
 * @author 消息订阅的类型列表类
 *
 */
public class MobileSubSource {

	private String id;//sourceid   订阅资源类型的id
	private String name;
	private String description;
//	private String userId;
	private  boolean  state ;//用户消息类型用户订阅状态
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
//	public String getUserId() {
//		return userId;
//	}
//	public void setUserId(String userId) {
//		this.userId = userId;
//	}
	public boolean getState() {
		return state;
	}
	public void setState(boolean state) {
		this.state = state;
	}
	
	
	
}
