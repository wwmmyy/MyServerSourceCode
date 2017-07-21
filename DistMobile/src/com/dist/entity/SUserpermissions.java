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
 * SUserpermissions entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "s_userpermissions", schema = "distmobile")
public class SUserpermissions implements java.io.Serializable {

	// Fields

	private String id;
	private SPermissions SPermissions;
	private SUsers SUsers;

	// Constructors

	/** default constructor */
	public SUserpermissions() {
	}

	/** full constructor */
	public SUserpermissions(SPermissions SPermissions, SUsers SUsers) {
		this.SPermissions = SPermissions;
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
	@JoinColumn(name = "PermissionID")
	public SPermissions getSPermissions() {
		return this.SPermissions;
	}

	public void setSPermissions(SPermissions SPermissions) {
		this.SPermissions = SPermissions;
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