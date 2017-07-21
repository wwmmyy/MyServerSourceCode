package com.dist.entity;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import org.hibernate.annotations.GenericGenerator;

/**
 * SLog entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "s_log", schema = "distmobile")
public class SLog implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 8232163528420312999L;
	private String id;
//	private SLogtype SLogtype;
	private String SLogtype;

	private Date eventTime;
	private String eventData;
	private String source;
	private Boolean scale;

	// Constructors

	/** default constructor */
	public SLog() {
	}

	/** full constructor */
	public SLog(String SLogtype, Date eventTime, String eventData, String source, Boolean scale) {
		this.SLogtype = SLogtype;
		this.eventTime = eventTime;
		this.eventData = eventData;
		this.source = source;
		this.scale = scale;
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

//	@ManyToOne(fetch = FetchType.LAZY)
//	@JoinColumn(name = "EventType")
//	public SLogtype getSLogtype() {
//		return this.SLogtype;
//	}
//
//	public void setSLogtype(SLogtype SLogtype) {
//		this.SLogtype = SLogtype;
//	}

	
	
	@Column(name = "EventType", length = 50)
	public String getSLogtype() {
		return this.SLogtype;
	}

	public void setSLogtype(String SLogtype) {
		this.SLogtype = SLogtype;
	}

	
	@Temporal(TemporalType.DATE)
	@Column(name = "EventTime", length = 10)
	public Date getEventTime() {
		return this.eventTime;
	}

	public void setEventTime(Date eventTime) {
		this.eventTime = eventTime;
	}

	@Column(name = "EventData", length = 4000)
	public String getEventData() {
		return this.eventData;
	}

	public void setEventData(String eventData) {
		this.eventData = eventData;
	}

	@Column(name = "Source", length = 500)
	public String getSource() {
		return this.source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	@Column(name = "Scale")
	public Boolean getScale() {
		return this.scale;
	}

	public void setScale(Boolean scale) {
		this.scale = scale;
	}

}