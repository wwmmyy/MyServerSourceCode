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
 * SRolepermissions entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "s_rolepermissions", schema = "distmobile")
public class SRolepermissions implements java.io.Serializable {

	// Fields

	private String id;
	private SRole SRole;
	private SPermissions SPermissions;

	// Constructors

	/** default constructor */
	public SRolepermissions() {
	}

	/** full constructor */
	public SRolepermissions(SRole SRole, SPermissions SPermissions) {
		this.SRole = SRole;
		this.SPermissions = SPermissions;
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
	@JoinColumn(name = "RoldID")
	public SRole getSRole() {
		return this.SRole;
	}

	public void setSRole(SRole SRole) {
		this.SRole = SRole;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "PermissionID")
	public SPermissions getSPermissions() {
		return this.SPermissions;
	}

	public void setSPermissions(SPermissions SPermissions) {
		this.SPermissions = SPermissions;
	}

}