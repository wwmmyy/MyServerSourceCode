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
 * PSubscriptionsource entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "p_subscriptionsource", schema = "distmobile")
public class PSubscriptionsource implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 8428148785040331711L;
	private String id;
	private String name;
	private String description;
	private Set<SNews> SNews= new HashSet<SNews>(0);
	private Set<PUsersubscription> PUsersubscriptions = new HashSet<PUsersubscription>(0);

	// Constructors

	/** default constructor */
	public PSubscriptionsource() {
	}

	/** minimal constructor */
	public PSubscriptionsource(String name) {
		this.name = name;
	}

	/** full constructor */
	public PSubscriptionsource(String name, String description, Set<SNews> SNews, Set<PUsersubscription> PUsersubscriptions) {
		this.name = name;
		this.description = description;
		this.SNews = SNews;
		this.PUsersubscriptions = PUsersubscriptions;
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

	@Column(name = "Description", length = 1000)
	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "PSubscriptionsource")
	public Set<SNews> getSNews() {
		return this.SNews;
	}

	public void setSNews(Set<SNews> SNews) {
		this.SNews = SNews;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "PSubscriptionsource")
	public Set<PUsersubscription> getPUsersubscriptions() {
		return this.PUsersubscriptions;
	}

	public void setPUsersubscriptions(Set<PUsersubscription> PUsersubscriptions) {
		this.PUsersubscriptions = PUsersubscriptions;
	}

}