package com.dist.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;

/**
 * SSystemuser entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "s_systemuser", catalog = "distmobile")
public class SSystemuser implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 8938876478775188065L;
	private String id;
	private String loginName;
	private String password;

	// Constructors

	/** default constructor */
	public SSystemuser() {
	}

	/** full constructor */
	public SSystemuser(String loginName, String password) {
		this.loginName = loginName;
		this.password = password;
	}

	// Property accessors
	@GenericGenerator(name = "generator", strategy = "uuid.hex")
	@Id
	@GeneratedValue(generator = "generator")
	@Column(name = "id", unique = true, nullable = false, length = 50)
	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@Column(name = "LoginName", length = 100)
	public String getLoginName() {
		return this.loginName;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}

	@Column(name = "Password", length = 50)
	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}