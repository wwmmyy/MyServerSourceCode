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
import javax.persistence.UniqueConstraint;
import org.hibernate.annotations.GenericGenerator;


/**
 * SDevicetype entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "s_devicetype", schema = "distmobile", uniqueConstraints = @UniqueConstraint(columnNames = "Name"))
public class SDevicetype implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 4869736554356014990L;
	private String id;
	private String name;
	private String systemtype;
	private String hardwaretype;
	private Set<SDevice> SDevices = new HashSet<SDevice>(0);

	// Constructors

	/** default constructor */
	public SDevicetype() {
	}

	/** minimal constructor */
	public SDevicetype(String name, String systemtype, String hardwaretype) {
		this.name = name;
		this.systemtype = systemtype;
		this.hardwaretype = hardwaretype;
	}

	/** full constructor */
	public SDevicetype(String name, String systemtype, String hardwaretype, Set<SDevice> SDevices) {
		this.name = name;
		this.systemtype = systemtype;
		this.hardwaretype = hardwaretype;
		this.SDevices = SDevices;
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

	@Column(name = "Name", unique = true, nullable = false, length = 100)
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "systemtype", nullable = false, length = 45)
	public String getSystemtype() {
		return this.systemtype;
	}

	public void setSystemtype(String systemtype) {
		this.systemtype = systemtype;
	}

	@Column(name = "hardwaretype", nullable = false, length = 45)
	public String getHardwaretype() {
		return this.hardwaretype;
	}

	public void setHardwaretype(String hardwaretype) {
		this.hardwaretype = hardwaretype;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "SDevicetype")
	public Set<SDevice> getSDevices() {
		return this.SDevices;
	}

	public void setSDevices(Set<SDevice> SDevices) {
		this.SDevices = SDevices;
	}

}