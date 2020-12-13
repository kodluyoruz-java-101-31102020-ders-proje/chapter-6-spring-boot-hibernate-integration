package com.integration.app.dao.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "titles")
public class Title {

	@EmbeddedId
	private TitlePK id;
	
	@Column(name = "to_date")
	private Date toDate;

	public TitlePK getId() {
		return id;
	}

	public void setId(TitlePK id) {
		this.id = id;
	}

	public Date getToDate() {
		return toDate;
	}

	public void setToDate(Date toDate) {
		this.toDate = toDate;
	}

	@Override
	public String toString() {
		return "Title [id=" + id + ", toDate=" + toDate + "]";
	}
}
