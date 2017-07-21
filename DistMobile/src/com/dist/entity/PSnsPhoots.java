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
 * PSnsPhoots entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "p_sns_phoots", schema = "distmobile")
public class PSnsPhoots implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7600196704030096582L;
	// Fields

	private String id;
	private PSns PSns;
	private float size;

	// Constructors

	/** default constructor */
	public PSnsPhoots() {
	}

	/** minimal constructor */
	public PSnsPhoots(PSns PSns) {
		this.PSns = PSns;
	}

	/** full constructor */
	public PSnsPhoots(PSns PSns, float size) {
		this.PSns = PSns;
		this.size = size;
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
	@JoinColumn(name = "SNSID", nullable = false)
	public PSns getPSns() {
		return this.PSns;
	}

	public void setPSns(PSns PSns) {
		this.PSns = PSns;
	}

	@Column(name = "Size")
	public float getSize() {
		return this.size;
	}

	public void setSize(float size) {
		this.size = size;
	}

}