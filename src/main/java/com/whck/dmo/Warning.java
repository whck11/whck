package com.whck.dmo;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the warning database table.
 * 
 */
@Entity
@NamedQuery(name="Warning.findAll", query="SELECT w FROM Warning w")
public class Warning implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="warning_id")
	private int warningId;

	private String category;

	@Column(name="current_state")
	private String currentState;

	@Temporal(TemporalType.TIMESTAMP)
	private Date date;

	private String details;

	public Warning() {
	}

	public int getWarningId() {
		return this.warningId;
	}

	public void setWarningId(int warningId) {
		this.warningId = warningId;
	}

	public String getCategory() {
		return this.category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getCurrentState() {
		return this.currentState;
	}

	public void setCurrentState(String currentState) {
		this.currentState = currentState;
	}

	public Date getDate() {
		return this.date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getDetails() {
		return this.details;
	}

	public void setDetails(String details) {
		this.details = details;
	}

}