package com.dist.entity;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import org.hibernate.annotations.GenericGenerator;

/**
 * PSnsComment entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "s_news_comment", schema = "distmobile")
public class SNewsComment implements java.io.Serializable {

	// Fields

	/**
	 * SNews.java
	 */
	private static final long serialVersionUID = 9215814627562914107L;
	private String id;
	private SNews SNews;
	private String userId;
	private String content;
	private Date createTime;

	// Constructors

	/** default constructor */
	public SNewsComment() {
	}

	/** minimal constructor */
	public SNewsComment(SNews SNews) {
		this.SNews = SNews;
	}

	/** full constructor */
	public SNewsComment(SNews PSns, String userId,  String content, Date createTime) {
		this.SNews = PSns;
		this.userId = userId;
		this.content = content;
		this.createTime = createTime;
	}

	// Property accessors
    @Id
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "uuid")
	@Column(name = "id", nullable = false)
	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}


	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "SNEWSID", nullable = false)
	public SNews getSNews() {
		return SNews;
	}

	public void setSNews(SNews sNews) {
		SNews = sNews;
	}

	@Column(name = "UserID", length = 50)
	public String getUserId() {
		return this.userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	@Column(name = "Content", length = 500)
	public String getContent() {
		return this.content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "CreateTime", length = 10)
	public Date getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

}