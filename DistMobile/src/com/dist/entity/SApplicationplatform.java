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
import javax.persistence.Version;
import org.hibernate.annotations.GenericGenerator;

/**
 * SApplicationplatform entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "s_applicationplatform", catalog = "distmobile")
public class SApplicationplatform implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 447206858042110012L;
	private String id;
	private String version;
	private SApplications SApplications;
	private String applicationIdentity;
	private Boolean status;
	private Date createTime;
	private String platform;  //分为  IOS  Android  Web  三种方式
	private String path;//如果应用是app则app下载路径不能为空
	private String url;//如果应用是web则url不能为空

	// Constructors

	/** default constructor */
	public SApplicationplatform() {
	}

	/** minimal constructor */
	public SApplicationplatform(String applicationIdentity) {
		this.applicationIdentity = applicationIdentity;
	}

	/** full constructor */
	public SApplicationplatform(SApplications SApplications, String applicationIdentity, Boolean status, Date createTime, String platform, String path, String url) {
		this.SApplications = SApplications;
		this.applicationIdentity = applicationIdentity;
		this.status = status;
		this.createTime = createTime;
		this.platform = platform;
		this.path = path;
		this.url = url;
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

////	@Version  此处为hibernate自动生成的容易报错，注意！！！！
	@Column(name = "Version", length = 50)
	public String getVersion() {
		return this.version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ApplicationID")
	public SApplications getSApplications() {
		return this.SApplications;
	}

	public void setSApplications(SApplications SApplications) {
		this.SApplications = SApplications;
	}

	@Column(name = "ApplicationIdentity", nullable = false, length = 100)
	public String getApplicationIdentity() {
		return this.applicationIdentity;
	}

	public void setApplicationIdentity(String applicationIdentity) {
		this.applicationIdentity = applicationIdentity;
	}

	@Column(name = "Status")
	public Boolean getStatus() {
		return this.status;
	}

	public void setStatus(Boolean status) {
		this.status = status;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "CreateTime", length = 10)
	public Date getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	@Column(name = "Platform", length = 50)
	public String getPlatform() {
		return this.platform;
	}

	public void setPlatform(String platform) {
		this.platform = platform;
	}

	@Column(name = "Path", length = 500)
	public String getPath() {
		return this.path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	@Column(name = "Url", length = 500)
	public String getUrl() {
		return this.url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

}