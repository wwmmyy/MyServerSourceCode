package com.dist.pagentity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * 规划圈动态信息
 * 
 * @author wmy
 * 
 */
public class MobilePCircle implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7394994623470558724L;
	private String id;
	private String content;
	private Date publishTime;
	private Integer readCount;
	private Integer good;

	private String userId;
	private String userName;
	private String userRole;
	private String userOrgan;
	private String userImage;
	private List<MobilePCircleContImg> conentImage;
//  每条动态的评论
    private List<MobilePComment> commentList;
    
    
    
    
	public List<MobilePComment> getCommentList() {
		return commentList;
	}

	public void setCommentList(List<MobilePComment> commentList) {
		this.commentList = commentList;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Date getPublishTime() {
		return publishTime;
	}

	public void setPublishTime(Date publishTime) {
		this.publishTime = publishTime;
	}

	public Integer getReadCount() {
		return readCount;
	}

	public void setReadCount(Integer readCount) {
		this.readCount = readCount;
	}

	public Integer getGood() {
		return good;
	}

	public void setGood(Integer good) {
		this.good = good;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserRole() {
		return userRole;
	}

	public void setUserRole(String userRole) {
		this.userRole = userRole;
	}

	public String getUserOrgan() {
		return userOrgan;
	}

	public void setUserOrgan(String userOrgan) {
		this.userOrgan = userOrgan;
	}

	public String getUserImage() {
		return userImage;
	}

	public void setUserImage(String userImage) {
		this.userImage = userImage;
	}

	public List<MobilePCircleContImg> getConentImage() {
		return conentImage;
	}

	public void setConentImage(List<MobilePCircleContImg> conentImage) {
		this.conentImage = conentImage;
	}

}
