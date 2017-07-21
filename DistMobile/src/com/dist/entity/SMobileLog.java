package com.dist.entity;

import java.sql.Timestamp;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;

/**
 * PMobileLog entity. @author MyEclipse Persistence Tools
 * 记录移动端设备登录
 * 
 */
@Entity
@Table(name = "s_mobile_log", catalog = "distmobile")
public class SMobileLog implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = -7265102940971852838L;
	private String id;
	private String userid;
	private String appidentify;
	private String deviceNumber;
	private Timestamp logtime;
	private String action;
	private Long placex;
	private Long placey;
	private Boolean online;

	// Constructors

	/** default constructor */
	public SMobileLog() {
	}

	/** minimal constructor */
	public SMobileLog(String userid, String deviceNumber) {
		this.userid = userid;
		this.deviceNumber = deviceNumber;
	}

	/** full constructor */
	public SMobileLog(String userid, String appidentify, String deviceNumber, Timestamp logtime, String action, Long placex, Long placey, Boolean online) {
		this.userid = userid;
		this.appidentify = appidentify;
		this.deviceNumber = deviceNumber;
		this.logtime = logtime;
		this.action = action;
		this.placex = placex;
		this.placey = placey;
		this.online = online;
	}

	// Property accessors
	@GenericGenerator(name = "generator", strategy = "uuid.hex")
	@Id
	@GeneratedValue(generator = "generator")
	@Column(name = "id", unique = true, nullable = false)
	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@Column(name = "userid", nullable = false, length = 50)
	public String getUserid() {
		return this.userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	
	
	
	@Column(name = "DeviceNumber", nullable = false, length = 100)
	public String getDeviceNumber() {
		return this.deviceNumber;
	}
	
	@Column(name = "appidentify",length = 100)
	public String getAppidentify() {
		return appidentify;
	}

	public void setAppidentify(String appidentify) {
		this.appidentify = appidentify;
	}

	public void setDeviceNumber(String deviceNumber) {
		this.deviceNumber = deviceNumber;
	}

	@Column(name = "logtime", length = 19)
	public Timestamp getLogtime() {
		return this.logtime;
	}

	public void setLogtime(Timestamp logtime) {
		this.logtime = logtime;
	}

	@Column(name = "action")
	public String getAction() {
		return this.action;
	}

	public void setAction(String action) {
		this.action = action;
	}

	@Column(name = "placex")
	public Long getPlacex() {
		return this.placex;
	}

	public void setPlacex(Long placex) {
		this.placex = placex;
	}

	@Column(name = "placey")
	public Long getPlacey() {
		return this.placey;
	}

	public void setPlacey(Long placey) {
		this.placey = placey;
	}

	@Column(name = "online")
	public Boolean getOnline() {
		return this.online;
	}

	public void setOnline(Boolean online) {
		this.online = online;
	}

}