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
 * PUsersubscription entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "p_usersubscription", schema = "distmobile")
public class PUsersubscription implements java.io.Serializable {

	// Fields

	/**
	 * 用户订阅表
	 */
	private static final long serialVersionUID = -8503997593988395142L;
	private String id;
	private PSubscriptionsource PSubscriptionsource;
	private String userId;
	private Date subscribeTime;

	// Constructors

	/** default constructor */
	public PUsersubscription() {
	}

	/** full constructor */
	public PUsersubscription(PSubscriptionsource PSubscriptionsource, String userId, Date subscribeTime) {
		this.PSubscriptionsource = PSubscriptionsource;
		this.userId = userId;
		this.subscribeTime = subscribeTime;
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
	@JoinColumn(name = "SourceID")
	public PSubscriptionsource getPSubscriptionsource() {
		return this.PSubscriptionsource;
	}

	public void setPSubscriptionsource(PSubscriptionsource PSubscriptionsource) {
		this.PSubscriptionsource = PSubscriptionsource;
	}

	@Column(name = "UserID", length = 50)
	public String getUserId() {
		return this.userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "SubscribeTime", length = 10)
	public Date getSubscribeTime() {
		return this.subscribeTime;
	}

	public void setSubscribeTime(Date subscribeTime) {
		this.subscribeTime = subscribeTime;
	}

}