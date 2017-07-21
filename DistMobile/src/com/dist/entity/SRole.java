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
 * SRole entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "s_role", schema = "distmobile")
public class SRole implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 6270605435421837458L;
	private String id;
	private String name;
	private Set<SUserroles> SUserroleses = new HashSet<SUserroles>(0);
	private Set<SRolepermissions> SRolepermissionses = new HashSet<SRolepermissions>(0);

	// Constructors

	/** default constructor */
	public SRole() {
	}

	/** minimal constructor */
	public SRole(String name) {
		this.name = name;
	}

	/** full constructor */
	public SRole(String name, Set<SUserroles> SUserroleses, Set<SRolepermissions> SRolepermissionses) {
		this.name = name;
		this.SUserroleses = SUserroleses;
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

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "SRole")
	public Set<SUserroles> getSUserroleses() {
		return this.SUserroleses;
	}

	public void setSUserroleses(Set<SUserroles> SUserroleses) {
		this.SUserroleses = SUserroleses;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "SRole")
	public Set<SRolepermissions> getSRolepermissionses() {
		return this.SRolepermissionses;
	}

	public void setSRolepermissionses(Set<SRolepermissions> SRolepermissionses) {
		this.SRolepermissionses = SRolepermissionses;
	}

}