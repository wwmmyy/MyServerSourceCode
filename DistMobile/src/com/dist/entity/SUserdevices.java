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
 * SUserdevices entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "s_userdevices", schema = "distmobile")
public class SUserdevices implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 6345976141644663540L;
	private String id;
//	private String deviceId;
//	private String userId;
	private SUsers SUsers;
	private SDevice SDevice;
	// Constructors

	/** default constructor */
	public SUserdevices() {
	}

	/** full constructor */
	public SUserdevices(SDevice SDevice, SUsers SUsers) {
		this.SDevice = SDevice;
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

//	@Column(name = "DeviceID", length = 50)
//	public String getDeviceId() {
//		return this.deviceId;
//	}
//
//
//
//	@Column(name = "UserID", length = 50)
//	public String getUserId() {
//		return this.userId;
//	}


	
	
	
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "deviceId")
	public SDevice getSDevice() {
		return this.SDevice;
	}

	public void setSDevice(SDevice sDevice) {
		SDevice = sDevice;
	}

	public void setSUsers(SUsers sUsers) {
		SUsers = sUsers;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "userId")
	public SUsers getSUsers() {
		return this.SUsers;
	}

}