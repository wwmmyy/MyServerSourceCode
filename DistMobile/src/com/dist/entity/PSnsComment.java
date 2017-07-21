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
@Table(name = "p_sns_comment", schema = "distmobile")
public class PSnsComment implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 9215814627562914107L;
	private String id;
	private PSns PSns;
	private String userId;
	private String commentId;
	private String content;
	private Date createTime;

	// Constructors

	/** default constructor */
	public PSnsComment() {
	}

	/** minimal constructor */
	public PSnsComment(PSns PSns) {
		this.PSns = PSns;
	}

	/** full constructor */
	public PSnsComment(PSns PSns, String userId, String commentId, String content, Date createTime) {
		this.PSns = PSns;
		this.userId = userId;
		this.commentId = commentId;
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
	@JoinColumn(name = "SNSID", nullable = false)
	public PSns getPSns() {
		return this.PSns;
	}

	public void setPSns(PSns PSns) {
		this.PSns = PSns;
	}

	@Column(name = "UserID", length = 50)
	public String getUserId() {
		return this.userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	@Column(name = "CommentID", length = 50)
	public String getCommentId() {
		return this.commentId;
	}

	public void setCommentId(String commentId) {
		this.commentId = commentId;
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