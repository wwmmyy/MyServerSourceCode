package com.dist.entity;

import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;

/**
 * SPermissions entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "s_permissions", schema = "distmobile")
public class SPermissions implements java.io.Serializable {

	// Fields

	private String id;
	private String name;
	private Set<SUserpermissions> SUserpermissionses = new HashSet<SUserpermissions>(0);
	private Set<SRolepermissions> SRolepermissionses = new HashSet<SRolepermissions>(0);

	// Constructors

	/** default constructor */
	public SPermissions() {
	}

	/** minimal constructor */
	public SPermissions(String name) {
		this.name = name;
	}

	/** full constructor */
	public SPermissions(String name, Set<SUserpermissions> SUserpermissionses, Set<SRolepermissions> SRolepermissionses) {
		this.name = name;
		this.SUserpermissionses = SUserpermissionses;
		this.SRolepermissionses = SRolepermissionses;
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

	@Column(name = "Name", nullable = false, length = 100)
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "SPermissions")
	public Set<SUserpermissions> getSUserpermissionses() {
		return this.SUserpermissionses;
	}

	public void setSUserpermissionses(Set<SUserpermissions> SUserpermissionses) {
		this.SUserpermissionses = SUserpermissionses;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "SPermissions")
	public Set<SRolepermissions> getSRolepermissionses() {
		return this.SRolepermissionses;
	}

	public void setSRolepermissionses(Set<SRolepermissions> SRolepermissionses) {
		this.SRolepermissionses = SRolepermissionses;
	}

}