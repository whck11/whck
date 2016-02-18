package com.whck.dmo;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;

import org.codehaus.jackson.annotate.JsonIgnore;

/**
 * The persistent class for the dc database table.
 * 
 */
@Entity
@NamedQuery(name = "Dc.findAll", query = "SELECT d FROM Dc d")
public class Dc implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "DC_ID")
	private String id;

	// bi-directional many-to-one association to Zone
	@ManyToOne
	@JoinColumn(name = "zone_id")
	private Zone zone;

	// bi-directional many-to-one association to Device
	@OneToMany(mappedBy = "dc")
	@JsonIgnore
	private List<Device> devices;

	public Dc() {
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Zone getZone() {
		return this.zone;
	}

	public void setZone(Zone zone) {
		this.zone = zone;
	}

	public List<Device> getDevices() {
		return this.devices;
	}

	public void setDevices(List<Device> devices) {
		this.devices = devices;
	}

	public Device addDevice(Device device) {
		getDevices().add(device);
		device.setDc(this);

		return device;
	}

	public Device removeDevice(Device device) {
		getDevices().remove(device);
		device.setDc(null);

		return device;
	}

}