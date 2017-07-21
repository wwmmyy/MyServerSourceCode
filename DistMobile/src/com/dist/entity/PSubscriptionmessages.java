//package com.dist.entity;
//
//import java.util.Date;
//import java.util.HashSet;
//import java.util.Set;
//import javax.persistence.CascadeType;
//import javax.persistence.Column;
//import javax.persistence.Entity;
//import javax.persistence.FetchType;
//import javax.persistence.GeneratedValue;
//import javax.persistence.Id;
//import javax.persistence.JoinColumn;
//import javax.persistence.ManyToOne;
//import javax.persistence.OneToMany;
//import javax.persistence.Table;
//import javax.persistence.Temporal;
//import javax.persistence.TemporalType;
//import org.hibernate.annotations.GenericGenerator;
//
///**
// * PSubscriptionmessages entity. @author MyEclipse Persistence Tools
// */
//@Entity
//@Table(name = "p_subscriptionmessages", schema = "distmobile")
//public class PSubscriptionmessages implements java.io.Serializable {
//
//	// Fields
//
//	/**
//	 * 
//	 */
//	private static final long serialVersionUID = 8780472249401735811L;
//	private String id;
//	private PSubscriptionsource PSubscriptionsource;
//	private String title;
//	private String content;
//	private Date publishTime;
//	private Boolean readCount;
//	private Boolean good;
//	private Set<PSubscriptionmessageimages> PSubscriptionmessageimageses = new HashSet<PSubscriptionmessageimages>(0);
//
//	// Constructors
//
//	/** default constructor */
//	public PSubscriptionmessages() {
//	}
//
//	/** minimal constructor */
//	public PSubscriptionmessages(String title) {
//		this.title = title;
//	}
//
//	/** full constructor */
//	public PSubscriptionmessages(PSubscriptionsource PSubscriptionsource, String title, String content, Date publishTime, Boolean readCount, Boolean good,
//			Set<PSubscriptionmessageimages> PSubscriptionmessageimageses) {
//		this.PSubscriptionsource = PSubscriptionsource;
//		this.title = title;
//		this.content = content;
//		this.publishTime = publishTime;
//		this.readCount = readCount;
//		this.good = good;
//		this.PSubscriptionmessageimageses = PSubscriptionmessageimageses;
//	}
//
//	// Property accessors
//    @Id
//	@GeneratedValue(generator = "system-uuid")
//	@GenericGenerator(name = "system-uuid", strategy = "uuid")
//	@Column(name = "id", nullable = false)
//	public String getId() {
//		return this.id;
//	}
//
//	public void setId(String id) {
//		this.id = id;
//	}
//
//	@ManyToOne(fetch = FetchType.LAZY)
//	@JoinColumn(name = "SourceID")
//	public PSubscriptionsource getPSubscriptionsource() {
//		return this.PSubscriptionsource;
//	}
//
//	public void setPSubscriptionsource(PSubscriptionsource PSubscriptionsource) {
//		this.PSubscriptionsource = PSubscriptionsource;
//	}
//
//	@Column(name = "Title", nullable = false, length = 1000)
//	public String getTitle() {
//		return this.title;
//	}
//
//	public void setTitle(String title) {
//		this.title = title;
//	}
//
//	@Column(name = "Content", length = 65535)
//	public String getContent() {
//		return this.content;
//	}
//
//	public void setContent(String content) {
//		this.content = content;
//	}
//
//	@Temporal(TemporalType.DATE)
//	@Column(name = "PublishTime", length = 10)
//	public Date getPublishTime() {
//		return this.publishTime;
//	}
//
//	public void setPublishTime(Date publishTime) {
//		this.publishTime = publishTime;
//	}
//
//	@Column(name = "ReadCount")
//	public Boolean getReadCount() {
//		return this.readCount;
//	}
//
//	public void setReadCount(Boolean readCount) {
//		this.readCount = readCount;
//	}
//
//	@Column(name = "Good")
//	public Boolean getGood() {
//		return this.good;
//	}
//
//	public void setGood(Boolean good) {
//		this.good = good;
//	}
//
//	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "PSubscriptionmessages")
//	public Set<PSubscriptionmessageimages> getPSubscriptionmessageimageses() {
//		return this.PSubscriptionmessageimageses;
//	}
//
//	public void setPSubscriptionmessageimageses(Set<PSubscriptionmessageimages> PSubscriptionmessageimageses) {
//		this.PSubscriptionmessageimageses = PSubscriptionmessageimageses;
//	}
//
//}