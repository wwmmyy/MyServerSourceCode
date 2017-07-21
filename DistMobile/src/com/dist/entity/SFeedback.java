package com.dist.entity;

import java.sql.Timestamp;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;

/**
 * SFeedback entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "s_feedback", catalog = "distmobile")
public class SFeedback implements java.io.Serializable {

	// Fields

	private String id;
	private String userId;
	private String content;
	private Timestamp feedTime;
	private String appidentify;


	// Constructors

	/** default constructor */
	public SFeedback() {
	}

	/** full constructor */
	public SFeedback(String userId,  String appidentify,String content, Timestamp feedTime) {
		this.userId = userId;
		this.content = content;
		this.feedTime = feedTime;		
		this.appidentify = appidentify;

	}

	// Property accessors
	@GenericGenerator(name = "generator", strategy = "uuid.hex")
	@Id
	@GeneratedValue(generator = "generator")
	@Column(name = "id", unique = true, nullable = false, length = 50)
	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}
	
	@Column(name = "appidentify",length = 100)
	public String getAppidentify() {
		return appidentify;
	}

	public void setAppidentify(String appidentify) {
		this.appidentify = appidentify;
	}

	@Column(name = "userID", length = 50)
	public String getUserId() {
		return this.userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	@Column(name = "content")
	public String getContent() {
		return this.content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	@Column(name = "feedTime", length = 19)
	public Timestamp getFeedTime() {
		return this.feedTime;
	}

	public void setFeedTime(Timestamp feedTime) {
		this.feedTime = feedTime;
	}

}