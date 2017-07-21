package com.dist.pagentity;

import java.util.Date;

import com.dist.entity.PSns;
/**
 * 用于表示单挑评价的信息详情
 * @author wmy
 *
 */
public class MobilePComment {
	private String id;
	private String PsnsId;
	private String userId;//该评论人的用户id
	private String userName;//该评论人的用户名
	private String commentId;//评论的上一个评论的id
	private String content;
	private String userRole;
	private Date createTime;
	
	private String commentIdUerName;//评论的上一个评论的用户名
	private String commentIdUerId;//评论的上一个评论的用户id

	
	
	
	
	public String getCommentIdUerName() {
		return commentIdUerName;
	}
	public void setCommentIdUerName(String commentIdUerName) {
		this.commentIdUerName = commentIdUerName;
	}
	public String getCommentIdUerId() {
		return commentIdUerId;
	}
	public void setCommentIdUerId(String commentIdUerId) {
		this.commentIdUerId = commentIdUerId;
	}
	public String getUserRole() {
		return userRole;
	}
	public void setUserRole(String userRole) {
		this.userRole = userRole;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPsnsId() {
		return PsnsId;
	}
	public void setPsnsId(String psnsId) {
		PsnsId = psnsId;
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
	public String getCommentId() {
		return commentId;
	}
	public void setCommentId(String commentId) {
		this.commentId = commentId;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	
	
	
	
	
	
	
}
