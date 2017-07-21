package com.dist.entity;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import org.hibernate.annotations.GenericGenerator;

/**
 * SDeviceactivity entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "s_deviceactivity", schema = "distmobile")
public class SDeviceactivity implements java.io.Serializable {

	// Fields

	private String id;
	private SDevice SDevice;
	private String activity;
	private Date eventTime;

	// Constructors

	/** default constructor */
	public SDeviceactivity() {
	}

	/** minimal constructor */
	public SDeviceactivity(SDevice SDevice) {
		this.SDevice = SDevice;
	}

	/** full constructor */
	public SDeviceactivity(SDevice SDevice, String activity, Date eventTime) {
		this.SDevice = SDevice;
		this.activity = activity;
		this.eventTime = eventTime;
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
	@JoinColumn(name = "DeviceID", nullable = false)
	public SDevice getSDevice() {
		return this.SDevice;
	}

	public void setSDevice(SDevice SDevice) {
		this.SDevice = SDevice;
	}

	@Column(name = "Activity", length = 2000)
	public String getActivity() {
		return this.activity;
	}

	public void setActivity(String activity) {
		this.activity = activity;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "EventTime", length = 10)
	public Date getEventTime() {
		return this.eventTime;
	}

	public void setEventTime(Date eventTime) {
		this.eventTime = eventTime;
	}

}