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
 * SApplicationorganization entity. @author MyEclipse Persistence Tools
 */
@SuppressWarnings("serial")
@Entity
@Table(name = "s_applicationorganization", catalog = "distmobile")
public class SApplicationorganization implements java.io.Serializable {

	// Fields

	private String id;
	private SApplications SApplications;
	private SOrganization SOrganization;

	// Constructors

	/** default constructor */
	public SApplicationorganization() {
	}

	/** full constructor */
	public SApplicationorganization(SApplications SApplications, SOrganization SOrganization) {
		this.SApplications = SApplications;
		this.SOrganization = SOrganization;
	}

	// Property accessors
	 @Id
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "uuid")
	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "appid", nullable = false)
	public SApplications getSApplications() {
		return this.SApplications;
	}

	public void setSApplications(SApplications SApplications) {
		this.SApplications = SApplications;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "organizationid", nullable = false)
	public SOrganization getSOrganization() {
		return this.SOrganization;
	}

	public void setSOrganization(SOrganization SOrganization) {
		this.SOrganization = SOrganization;
	}

}