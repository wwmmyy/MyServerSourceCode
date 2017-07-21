package com.dist.entity;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;
import org.hibernate.annotations.GenericGenerator;

/**
 * SDevice entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "s_device", schema = "distmobile", uniqueConstraints = { @UniqueConstraint(columnNames = "DeviceNumber"), @UniqueConstraint(columnNames = "Name") })
public class SDevice implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = -349371391265643259L;
	private String id;
	private SDevicetype SDevicetype;
	private String name;
	private String deviceNumber;
	private String status;
	private String lastLocation;
	private String remark;
	private Date registerTime;
	private String registerMessage;
	private Date joinTime;
	private Set<SDeviceactivity> SDeviceactivities = new HashSet<SDeviceactivity>(0);

	// Constructors

	/** default constructor */
	public SDevice() {
	}

	/** minimal constructor */
	public SDevice(String name, String deviceNumber) {
		this.name = name;
		this.deviceNumber = deviceNumber;
	}

	/** full constructor */
	public SDevice(SDevicetype SDevicetype, String name, String deviceNumber, String status, String lastLocation, String remark, Date registerTime, String registerMessage,
			Date joinTime, Set<SDeviceactivity> SDeviceactivities) {
		this.SDevicetype = SDevicetype;
		this.name = name;
		this.deviceNumber = deviceNumber;
		this.status = status;
		this.lastLocation = lastLocation;
		this.remark = remark;
		this.registerTime = registerTime;
		this.registerMessage = registerMessage;
		this.joinTime = joinTime;
		this.SDeviceactivities = SDeviceactivities;
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
	@JoinColumn(name = "DeviceType")
	public SDevicetype getSDevicetype() {
		return this.SDevicetype;
	}

	public void setSDevicetype(SDevicetype SDevicetype) {
		this.SDevicetype = SDevicetype;
	}

	@Column(name = "Name", nullable = false, length = 100)
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "DeviceNumber", unique = true, nullable = false, length = 100)
	public String getDeviceNumber() {
		return this.deviceNumber;
	}

	public void setDeviceNumber(String deviceNumber) {
		this.deviceNumber = deviceNumber;
	}

	@Column(name = "Status", length = 2)
	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Column(name = "LastLocation", length = 50)
	public String getLastLocation() {
		return this.lastLocation;
	}

	public void setLastLocation(String lastLocation) {
		this.lastLocation = lastLocation;
	}

	@Column(name = "Remark", length = 500)
	public String getRemark() {
		return this.remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "RegisterTime", length = 10)
	public Date getRegisterTime() {
		return this.registerTime;
	}

	public void setRegisterTime(Date registerTime) {
		this.registerTime = registerTime;
	}

	@Column(name = "RegisterMessage", length = 1000)
	public String getRegisterMessage() {
		return this.registerMessage;
	}

	public void setRegisterMessage(String registerMessage) {
		this.registerMessage = registerMessage;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "JoinTime", length = 10)
	public Date getJoinTime() {
		return this.joinTime;
	}

	public void setJoinTime(Date joinTime) {
		this.joinTime = joinTime;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "SDevice")
	public Set<SDeviceactivity> getSDeviceactivities() {
		return this.SDeviceactivities;
	}

	public void setSDeviceactivities(Set<SDeviceactivity> SDeviceactivities) {
		this.SDeviceactivities = SDeviceactivities;
	}

}