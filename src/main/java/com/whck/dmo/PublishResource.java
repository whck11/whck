package com.whck.dmo;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "publish_resource")
public class PublishResource implements Serializable {
	
	private static final long serialVersionUID = 4841082280090067158L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String title;
	private String content;
	@JoinColumn(name = "create_by")
	@ManyToOne
	private User createBy;
	@Column(name = "update_date")
	@Temporal(TemporalType.TIMESTAMP)
	private Date updateDate;
	@Column(name = "is_top")
	private Integer isTop;
	@JoinColumn(name = "menu")
	@ManyToOne
	private PublishMenu menu;

	public String getContent() {
		return content;
	}

	public User getCreateBy() {
		return createBy;
	}

	public Integer getId() {
		return id;
	}

	public Integer getIsTop() {
		return isTop;
	}

	public PublishMenu getMenu() {
		return menu;
	}

	public String getTitle() {
		return title;
	}

	public Date getUpdateDate() {
		return updateDate;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public void setCreateBy(User createBy) {
		this.createBy = createBy;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public void setIsTop(Integer isTop) {
		this.isTop = isTop;
	}

	public void setMenu(PublishMenu menu) {
		this.menu = menu;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}

}
