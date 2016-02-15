package com.whck.dmo;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the zone database table.
 * 
 */
@Entity
@NamedQuery(name="Zone.findAll", query="SELECT z FROM Zone z")
public class Zone implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="zone_id")
	private int zoneId;

	private double area;

	private String latitude;

	private String longitude;

	private String remarks;

	@Column(name="zone_name")
	private String zoneName;

	//bi-directional many-to-one association to Dc
	@OneToMany(mappedBy="zone")
	private List<Dc> dcs;

	//bi-directional many-to-one association to User
	@ManyToOne
	@JoinColumn(name="user_id")
	private User user;

	public Zone() {
	}

	public int getZoneId() {
		return this.zoneId;
	}

	public void setZoneId(int zoneId) {
		this.zoneId = zoneId;
	}

	public double getArea() {
		return this.area;
	}

	public void setArea(double area) {
		this.area = area;
	}

	public String getLatitude() {
		return this.latitude;
	}

	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}

	public String getLongitude() {
		return this.longitude;
	}

	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}

	public String getRemarks() {
		return this.remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public String getZoneName() {
		return this.zoneName;
	}

	public void setZoneName(String zoneName) {
		this.zoneName = zoneName;
	}

	public List<Dc> getDcs() {
		return this.dcs;
	}

	public void setDcs(List<Dc> dcs) {
		this.dcs = dcs;
	}

	public Dc addDc(Dc dc) {
		getDcs().add(dc);
		dc.setZone(this);

		return dc;
	}

	public Dc removeDc(Dc dc) {
		getDcs().remove(dc);
		dc.setZone(null);

		return dc;
	}

	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}

}