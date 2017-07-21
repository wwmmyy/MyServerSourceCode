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
 * SLogtype entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "s_logtype", schema = "distmobile")
public class SLogtype implements java.io.Serializable {

	// Fields

	private String id;
	private String name;
//	private Set<SLog> SLogs = new HashSet<SLog>(0);

	// Constructors

	/** default constructor */
	public SLogtype() {
	}

	/** minimal constructor */
	public SLogtype(String name) {
		this.name = name;
	}

	/** full constructor */
	public SLogtype(String name, Set<SLog> SLogs) {
		this.name = name;
//		this.SLogs = SLogs;
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

//	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "SLogtype")
//	public Set<SLog> getSLogs() {
//		return this.SLogs;
//	}
//
//	public void setSLogs(Set<SLog> SLogs) {
//		this.SLogs = SLogs;
//	}

}