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
 * PUserapplicationmessage entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "p_userapplicationmessage", schema = "distmobile")
public class PUserapplicationmessage implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = -6849052235094012459L;
	private String id;
//	private String applicationId;
//	private String userId;
	private String message;
	
	
	private SUsers SUsers;
	private SApplications SApplications;

	// Constructors

	/** default constructor */
	public PUserapplicationmessage() {
	}

	/** minimal constructor */
	public PUserapplicationmessage(SApplications SApplications, SUsers SUsers) {
		this.SApplications = SApplications;
		this.SUsers = SUsers;
	}

	/** full constructor */
	public PUserapplicationmessage(SApplications SApplications, SUsers SUsers, String message) {
		this.SApplications = SApplications;
		this.SUsers = SUsers;
		this.message = message;
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
	@JoinColumn(name = "userId")
	public SUsers getSUsers() {
		return SUsers;
	}

	public void setSUsers(SUsers sUsers) {
		SUsers = sUsers;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ApplicationID")
	public SApplications getSApplications() {
		return SApplications;
	}

	public void setSApplications(SApplications sApplications) {
		SApplications = sApplications;
	}
	
	@Column(name = "Message", length = 50)
	public String getMessage() {
		return this.message;
	}
	
	public void setMessage(String message) {
		this.message = message;
	}

}