package com.dist.entity;

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
import org.hibernate.annotations.GenericGenerator;


/**
 * SOrganization entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "s_organization", schema = "distmobile")
public class SOrganization implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5899989748473130692L;
	// Fields

	private String id;
	private String name;
//	private String parentId;
	private SOrganization sorganization;
	private Set<SOrganization> sorganizations = new HashSet<SOrganization>(0);
//	private Set<SUsers> SUsers = new HashSet<SUsers>(0);
	// Constructors
	private Set<SApplicationorganization> SApplicationorganizations = new HashSet<SApplicationorganization>(0);

	/** default constructor */
	public SOrganization() {
	}

	/** minimal constructor */
	public SOrganization(String name) {
		this.name = name;
	}
	
//	/** full constructor */
//	public SOrganization(String name, SOrganization sorganization) {
//		this.name = name;
//		this.sorganization = sorganization;
//	}
	
	/** full constructor */
	public SOrganization(SOrganization SOrganization, String name, Set<SOrganization> SOrganizations,  Set<SApplicationorganization> SApplicationorganizations) {
		this.sorganization = SOrganization;
		this.name = name;
		this.sorganizations = SOrganizations;
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

	@Column(name = "Name", nullable = false, length = 100)
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}


//	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "SOrganization")
//	public Set<SUsers> getSUserses() {
//		return this.SUserses;
//	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ParentID")
	public SOrganization getSorganization() {
		return sorganization;
	}

	public void setSorganization(SOrganization sorganization) {
		this.sorganization = sorganization;
	}
	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "sorganization")
	public Set<SOrganization> getSorganizations() {
		return sorganizations;
	}

	public void setSorganizations(Set<SOrganization> sorganizations) {
		this.sorganizations = sorganizations;
	}

	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "SOrganization")
	public Set<SApplicationorganization> getSApplicationorganizations() {
		return this.SApplicationorganizations;
	}

	public void setSApplicationorganizations(Set<SApplicationorganization> SApplicationorganizations) {
		this.SApplicationorganizations = SApplicationorganizations;
	}
	
	
//	public void setSUserses(Set<SUsers> SUserses) {
//		this.SUserses = SUserses;
//	}

}