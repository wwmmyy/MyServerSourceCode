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
 * SNews entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "s_news", catalog = "distmobile")
public class SNews implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = -7773108681364642079L;
	private String id;
	private String title;
	private String type;
	private Date time;
	private PSubscriptionsource PSubscriptionsource;
	private String content;
	private String infoUrl;
	private Integer browse;
	private Integer comment;
	private Integer good;
	private Set<SNewsComment> SNewsComment = new HashSet<SNewsComment>(0);
	private Set<SNewspic> SNewspics = new HashSet<SNewspic>(0);

	// Constructors

	/** default constructor */
	public SNews() {
	}

	/** full constructor */
	public SNews(PSubscriptionsource PSubscriptionsource, String title, String type, 
			Date time, String content, String infourl, Integer browse, Integer good,
			Integer comment, Set<SNewspic> SNewspics, Set<SNewsComment> SNewsComment) {
		this.title = title;
		this.PSubscriptionsource = PSubscriptionsource;
		this.type = type;
		this.time = time;
		this.content = content;
		this.infoUrl = infourl;
		this.browse = browse;
		this.comment = comment;
		this.SNewspics = SNewspics;
		this.SNewsComment = SNewsComment;
		this.good = good;
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

	@Column(name = "title", length = 100)
	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	@Column(name = "type", length = 20)
	public String getType() {
		return this.type;
	}

	public void setType(String type) {
		this.type = type;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "time", length = 10)
	public Date getTime() {
		return this.time;
	}

	public void setTime(Date time) {
		this.time = time;
	}

	@Column(name = "content")
	public String getContent() {
		return this.content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	
	@Column(name = "infoUrl", length = 200)
	public String getInfoUrl() {
		return infoUrl;
	}

	public void setInfoUrl(String infoUrl) {
		this.infoUrl = infoUrl;
	}

	@Column(name = "browse")
	public Integer getBrowse() {
		return this.browse;
	}

	
	@Column(name = "Good")
	public Integer getGood() {
		return this.good;
	}

	public void setGood(Integer good) {
		this.good = good;
	}

	
	
	public void setBrowse(Integer browse) {
		this.browse = browse;
	}

	@Column(name = "comment")
	public Integer getComment() {
		return this.comment;
	}

	public void setComment(Integer comment) {
		this.comment = comment;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "SNews")
	public Set<SNewspic> getSNewspics() {
		return SNewspics;
	}

	public void setSNewspics(Set<SNewspic> sNewspics) {
		SNewspics = sNewspics;
	}
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "SNews")
	public Set<SNewsComment> getSNewsComment() {
		return SNewsComment;
	}

	public void setSNewsComment(Set<SNewsComment> sNewsComment) {
		SNewsComment = sNewsComment;
	}


	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "SourceID")
	public PSubscriptionsource getPSubscriptionsource() {
		return this.PSubscriptionsource;
	}

	public void setPSubscriptionsource(PSubscriptionsource PSubscriptionsource) {
		this.PSubscriptionsource = PSubscriptionsource;
	}


}