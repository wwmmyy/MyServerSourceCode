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
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import org.hibernate.annotations.GenericGenerator;

/**
 * PSns entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "p_sns", schema = "distmobile")
public class PSns implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4520594099576085979L;
	private String id;
	private String content;
	private String userId;
	private Date publishTime;
	private Integer readCount;
	private Integer good;
	private Set<PSnsComment> PSnsComments = new HashSet<PSnsComment>(0);
	private Set<PSnsPhoots> PSnsPhootses = new HashSet<PSnsPhoots>(0);

	// Constructors

	/** default constructor */
	public PSns() {
	}

	/** full constructor */
	public PSns(String content, String userId, Date publishTime, Integer readCount, Integer good, Set<PSnsComment> PSnsComments, Set<PSnsPhoots> PSnsPhootses) {
		this.content = content;
		this.userId = userId;
		this.publishTime = publishTime;
		this.readCount = readCount;
		this.good = good;
		this.PSnsComments = PSnsComments;
		this.PSnsPhootses = PSnsPhootses;
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

	@Column(name = "Content", length = 1000)
	public String getContent() {
		return this.content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	@Column(name = "UserID", length = 50)
	public String getUserId() {
		return this.userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "PublishTime", length = 10)
	public Date getPublishTime() {
		return this.publishTime;
	}

	public void setPublishTime(Date publishTime) {
		this.publishTime = publishTime;
	}

	@Column(name = "ReadCount")
	public Integer getReadCount() {
		return this.readCount;
	}

	public void setReadCount(Integer readCount) {
		this.readCount = readCount;
	}

	@Column(name = "Good")
	public Integer getGood() {
		return this.good;
	}

	public void setGood(Integer good) {
		this.good = good;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "PSns")
	public Set<PSnsComment> getPSnsComments() {
		return this.PSnsComments;
	}

	public void setPSnsComments(Set<PSnsComment> PSnsComments) {
		this.PSnsComments = PSnsComments;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "PSns")
	public Set<PSnsPhoots> getPSnsPhootses() {
		return this.PSnsPhootses;
	}

	public void setPSnsPhootses(Set<PSnsPhoots> PSnsPhootses) {
		this.PSnsPhootses = PSnsPhootses;
	}

}