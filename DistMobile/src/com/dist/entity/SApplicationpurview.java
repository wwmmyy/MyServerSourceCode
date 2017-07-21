package com.dist.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;

/**
 * SApplicationpurview entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "s_applicationpurview", schema = "distmobile")
public class SApplicationpurview implements java.io.Serializable {

	// Fields

	private String id;
	private SApplications SApplications;
	private String userId;

	// Constructors

	/** default constructor */
	public SApplicationpurview() {
	}

	/** minimal constructor */
	public SApplicationpurview(SApplications SApplications) {
		this.SApplications = SApplications;
	}

	/** full constructor */
	public SApplicationpurview(SApplications SApplications, String userId) {
		this.SApplications = SApplications;
		this.userId = userId;
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
	@JoinColumn(name = "ApplicationID", nullable = false)
	public SApplications getSApplications() {
		return this.SApplications;
	}

	public void setSApplications(SApplications SApplications) {
		this.SApplications = SApplications;
	}

	@Column(name = "UserId", length = 50)
	public String getUserId() {
		return this.userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

}