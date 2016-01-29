package com.whck.dmo;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the breed database table.
 * 
 */
@Entity
@NamedQuery(name="Breed.findAll", query="SELECT b FROM Breed b")
public class Breed implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="breed_id")
	private Integer breedId;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="birth_time")
	private Date birthTime;

	@Column(name="breed_name")
	private String breedName;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="harvest_time")
	private Date harvestTime;

	private int number;

	private String unit;

	@Column(name="zone_id")
	private Integer zoneId;

	public Breed() {
	}

	public Integer getBreedId() {
		return this.breedId;
	}

	public void setBreedId(Integer breedId) {
		this.breedId = breedId;
	}

	public Date getBirthTime() {
		return this.birthTime;
	}

	public void setBirthTime(Date birthTime) {
		this.birthTime = birthTime;
	}

	public String getBreedName() {
		return this.breedName;
	}

	public void setBreedName(String breedName) {
		this.breedName = breedName;
	}

	public Date getHarvestTime() {
		return this.harvestTime;
	}

	public void setHarvestTime(Date harvestTime) {
		this.harvestTime = harvestTime;
	}

	public int getNumber() {
		return this.number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public String getUnit() {
		return this.unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	public Integer getZoneId() {
		return this.zoneId;
	}

	public void setZoneId(Integer zoneId) {
		this.zoneId = zoneId;
	}

}