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
 * SDeviceapplications entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "s_deviceapplications",  schema = "distmobile")
public class SDeviceapplications implements java.io.Serializable {

	// Fields

	private String id;
	private SApplications SApplications;
	private String deviceId;

	// Constructors

	/** default constructor */
	public SDeviceapplications() {
	}

	/** full constructor */
	public SDeviceapplications(SApplications SApplications, String deviceId) {
		this.SApplications = SApplications;
		this.deviceId = deviceId;
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
	@JoinColumn(name = "ApplicationID")
	public SApplications getSApplications() {
		return this.SApplications;
	}

	public void setSApplications(SApplications SApplications) {
		this.SApplications = SApplications;
	}

	@Column(name = "DeviceID", length = 50)
	public String getDeviceId() {
		return this.deviceId;
	}

	public void setDeviceId(String deviceId) {
		this.deviceId = deviceId;
	}

}