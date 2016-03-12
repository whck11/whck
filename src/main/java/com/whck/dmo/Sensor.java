package com.whck.dmo;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
@Entity
public class Sensor implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@ManyToOne
	@JoinColumn(name = "dc_id")
	private Dc dc;

	@Column(name="collect_point_name")
	private String collectPointName;
	
	@Column(name="collect_point_unit")
	private String collectPointUnit;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Dc getDc() {
		return dc;
	}

	public void setDc(Dc dc) {
		this.dc = dc;
	}

	public String getCollectPointName() {
		return collectPointName;
	}

	public void setCollectPointName(String collectPointName) {
		this.collectPointName = collectPointName;
	}

	public String getCollectPointUnit() {
		return collectPointUnit;
	}

	public void setCollectPointUnit(String collectPointUnit) {
		this.collectPointUnit = collectPointUnit;
	}
	
}
