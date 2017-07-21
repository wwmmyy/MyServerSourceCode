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
import org.hibernate.annotations.GenericGenerator;

/**
 * SUsers entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "s_users", schema = "distmobile")
public class SUsers implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 5758698529622832390L;
	private String id;
	private SOrganization SOrganization;
	private String name;
	private String loginName;
	private String handPassword;
	private String password;
	private Date createTime;
	private Boolean status;
	private String phoneNumber;
	private String email;
	private Boolean sex;
	private Set<SUserroles> SUserroleses = new HashSet<SUserroles>(0);
	private Set<SUserpermissions> SUserpermissionses = new HashSet<SUserpermissions>(0);

	// Constructors

	/** default constructor */
	public SUsers() {
	}

	/** minimal constructor */
	public SUsers(String name, String loginName) {
		this.name = name;
		this.loginName = loginName;
	}

	/** full constructor */
	public SUsers(SOrganization SOrganization, String name, String loginName, String handPassword, String password, Date createTime, Boolean status, String phoneNumber, String email,
			Boolean sex, Set<SUserroles> SUserroleses, Set<SUserpermissions> SUserpermissionses) {
		this.SOrganization = SOrganization;
		this.name = name;
		this.loginName = loginName;
		this.handPassword = handPassword;
		this.password = password;
		this.createTime = createTime;
		this.status = status;
		this.phoneNumber = phoneNumber;
		this.email = email;
		this.sex = sex;
		this.SUserroleses = SUserroleses;
		this.SUserpermissionses = SUserpermissionses;
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
	@JoinColumn(name = "OrganizationID")
	public SOrganization getSOrganization() {
		return this.SOrganization;
	}

	public void setSOrganization(SOrganization SOrganization) {
		this.SOrganization = SOrganization;
	}

	@Column(name = "Name", nullable = false, length = 100)
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "LoginName", nullable = false, length = 100)
	public String getLoginName() {
		return this.loginName;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}

	@Column(name = "HandPassword", length = 50)
	public String getHandPassword() {
		return this.handPassword;
	}

	public void setHandPassword(String handPassword) {
		this.handPassword = handPassword;
	}

	@Column(name = "Password", length = 50)
	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "CreateTime", length = 10)
	public Date getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	@Column(name = "Status")
	public Boolean getStatus() {
		return this.status;
	}

	public void setStatus(Boolean status) {
		this.status = status;
	}

	@Column(name = "phoneNumber", length = 50)
	public String getPhoneNumber() {
		return this.phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	@Column(name = "Email", length = 50)
	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Column(name = "Sex")
	public Boolean getSex() {
		return this.sex;
	}

	public void setSex(Boolean sex) {
		this.sex = sex;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "SUsers")
	public Set<SUserroles> getSUserroleses() {
		return this.SUserroleses;
	}

	public void setSUserroleses(Set<SUserroles> SUserroleses) {
		this.SUserroleses = SUserroleses;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "SUsers")
	public Set<SUserpermissions> getSUserpermissionses() {
		return this.SUserpermissionses;
	}

	public void setSUserpermissionses(Set<SUserpermissions> SUserpermissionses) {
		this.SUserpermissionses = SUserpermissionses;
	}

}