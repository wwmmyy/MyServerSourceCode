package com.dist.entity;

import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;

/**
 * SApplicationcategory entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "s_applicationcategory", schema = "distmobile")
public class SApplicationcategory implements java.io.Serializable {

	// Fields

	private String id;
	private String name;
	private Set<SApplications> SApplicationses = new HashSet<SApplications>(0);

	// Constructors

	/** default constructor */
	public SApplicationcategory() {
	}

	/** full constructor */
	public SApplicationcategory(String name, Set<SApplications> SApplicationses) {
		this.name = name;
		this.SApplicationses = SApplicationses;
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

	@Column(name = "Name", length = 100)
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "SApplicationcategory")
	public Set<SApplications> getSApplicationses() {
		return this.SApplicationses;
	}

	public void setSApplicationses(Set<SApplications> SApplicationses) {
		this.SApplicationses = SApplicationses;
	}

}