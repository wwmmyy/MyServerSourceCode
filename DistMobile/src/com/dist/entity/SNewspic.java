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
 * SNewspic entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "s_newspic", catalog = "distmobile")
public class SNewspic implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 4572578100045658403L;
	private String id;
	private SNews SNews;
	private String path;
	private String picname;
	private Integer size;
	// Constructors

	/** default constructor */
	public SNewspic() {
	}

	/** full constructor */
	public SNewspic(SNews SNews, String path, String picname,Integer size) {
		this.SNews = SNews;
		this.path = path;
		this.picname = picname;
		this.size=size;
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
	@JoinColumn(name = "newsId")
	public SNews getSNews() {
		return this.SNews;
	}

	public void setSNews(SNews SNews) {
		this.SNews = SNews;
	}

	@Column(name = "path")
	public String getPath() {
		return this.path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	@Column(name = "picname", length = 45)
	public String getPicname() {
		return this.picname;
	}

	public void setPicname(String picname) {
		this.picname = picname;
	}
	@Column(name = "size")
	public Integer getSize() {
		return size;
	}

	public void setSize(Integer size) {
		this.size = size;
	}

}