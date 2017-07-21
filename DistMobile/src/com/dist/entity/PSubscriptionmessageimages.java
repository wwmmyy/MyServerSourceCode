//package com.dist.entity;
//
//import javax.persistence.Column;
//import javax.persistence.Entity;
//import javax.persistence.FetchType;
//import javax.persistence.GeneratedValue;
//import javax.persistence.Id;
//import javax.persistence.JoinColumn;
//import javax.persistence.ManyToOne;
//import javax.persistence.Table;
//import org.hibernate.annotations.GenericGenerator;
//
///**
// * PSubscriptionmessageimages entity. @author MyEclipse Persistence Tools
// */
//@Entity
//@Table(name = "p_subscriptionmessageimages", schema = "distmobile")
//public class PSubscriptionmessageimages implements java.io.Serializable {
//
//	// Fields
//
//	/**
//	 * 
//	 */
//	private static final long serialVersionUID = -6868050337760122186L;
//	private String id;
//	private PSubscriptionmessages PSubscriptionmessages;
//	private Boolean size;
//
//	// Constructors
//
//	/** default constructor */
//	public PSubscriptionmessageimages() {
//	}
//
//	/** full constructor */
//	public PSubscriptionmessageimages(PSubscriptionmessages PSubscriptionmessages, Boolean size) {
//		this.PSubscriptionmessages = PSubscriptionmessages;
//		this.size = size;
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
//	@JoinColumn(name = "MessageID")
//	public PSubscriptionmessages getPSubscriptionmessages() {
//		return this.PSubscriptionmessages;
//	}
//
//	public void setPSubscriptionmessages(PSubscriptionmessages PSubscriptionmessages) {
//		this.PSubscriptionmessages = PSubscriptionmessages;
//	}
//
//	@Column(name = "Size")
//	public Boolean getSize() {
//		return this.size;
//	}
//
//	public void setSize(Boolean size) {
//		this.size = size;
//	}
//
//}