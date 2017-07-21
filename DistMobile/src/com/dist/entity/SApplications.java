package com.dist.entity;

import java.sql.Timestamp;
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
 * SApplications entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "s_applications", catalog = "distmobile")
public class SApplications implements java.io.Serializable {

	// Fields

	private String id;
	private SApplicationcategory SApplicationcategory;
	private String name;
	private String icon;
	private String description;
	private Date createTime;
	private Set<SApplicationplatform> SApplicationplatforms = new HashSet<SApplicationplatform>(0);
	private Set<SApplicationorganization> SApplicationorganizations = new HashSet<SApplicationorganization>(0);
	private Set<SApplicationpurview> SApplicationpurviews = new HashSet<SApplicationpurview>(0);
	private Set<PUserapplicationmessage> PUserapplicationmessages = new HashSet<PUserapplicationmessage>(0);
	private Set<SDeviceapplications> SDeviceapplicationses = new HashSet<SDeviceapplications>(0);

	// Constructors

	/** default constructor */
	public SApplications() {
	}

	/** full constructor */
	public SApplications(SApplicationcategory SApplicationcategory, String name, String icon, String description, Date createTime,
			Set<SApplicationplatform> SApplicationplatforms, Set<SApplicationpurview> SApplicationpurviews, Set<PUserapplicationmessage> PUserapplicationmessages,
			Set<SDeviceapplications> SDeviceapplicationses,Set<SApplicationorganization> SApplicationorganizations) {
		this.SApplicationcategory = SApplicationcategory;
		this.name = name;
		this.icon = icon;
		this.description = description;
		this.createTime = createTime;
		this.SApplicationplatforms = SApplicationplatforms;
		this.SApplicationpurviews = SApplicationpurviews;
		this.PUserapplicationmessages = PUserapplicationmessages;
		this.SDeviceapplicationses = SDeviceapplicationses;
		this.SApplicationorganizations = SApplicationorganizations;
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
	@JoinColumn(name = "CategoryID")
	public SApplicationcategory getSApplicationcategory() {
		return this.SApplicationcategory;
	}

	public void setSApplicationcategory(SApplicationcategory SApplicationcategory) {
		this.SApplicationcategory = SApplicationcategory;
	}

	@Column(name = "Name", length = 100)
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "Icon", length = 500)
	public String getIcon() {
		return this.icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	@Column(name = "Description", length = 2000)
	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "CreateTime", length = 19)
	public Date getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "SApplications")
	public Set<SApplicationplatform> getSApplicationplatforms() {
		return this.SApplicationplatforms;
	}

	public void setSApplicationplatforms(Set<SApplicationplatform> SApplicationplatforms) {
		this.SApplicationplatforms = SApplicationplatforms;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "SApplications")
	public Set<SApplicationpurview> getSApplicationpurviews() {
		return this.SApplicationpurviews;
	}

	public void setSApplicationpurviews(Set<SApplicationpurview> SApplicationpurviews) {
		this.SApplicationpurviews = SApplicationpurviews;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "SApplications")
	public Set<PUserapplicationmessage> getPUserapplicationmessages() {
		return this.PUserapplicationmessages;
	}

	public void setPUserapplicationmessages(Set<PUserapplicationmessage> PUserapplicationmessages) {
		this.PUserapplicationmessages = PUserapplicationmessages;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "SApplications")
	public Set<SDeviceapplications> getSDeviceapplicationses() {
		return this.SDeviceapplicationses;
	}
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "SApplications")
	public Set<SApplicationorganization> getSApplicationorganizations() {
		return this.SApplicationorganizations;
	}

	public void setSApplicationorganizations(Set<SApplicationorganization> SApplicationorganizations) {
		this.SApplicationorganizations = SApplicationorganizations;
	}
	public void setSDeviceapplicationses(Set<SDeviceapplications> SDeviceapplicationses) {
		this.SDeviceapplicationses = SDeviceapplicationses;
	}

}