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
 * SUserroles entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "s_userroles", schema = "distmobile")
public class SUserroles implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = -2782295292195821871L;
	private String id;
	private SRole SRole;
	private SUsers SUsers;

	// Constructors

	/** default constructor */
	public SUserroles() {
	}

	/** full constructor */
	public SUserroles(SRole SRole, SUsers SUsers) {
		this.SRole = SRole;
		this.SUsers = SUsers;
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
	@JoinColumn(name = "RoleID")
	public SRole getSRole() {
		return this.SRole;
	}

	public void setSRole(SRole SRole) {
		this.SRole = SRole;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "UserID")
	public SUsers getSUsers() {
		return this.SUsers;
	}

	public void setSUsers(SUsers SUsers) {
		this.SUsers = SUsers;
	}

}