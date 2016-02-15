package com.whck.dmo;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the dc database table.
 * 
 */
@Entity
@NamedQuery(name="Dc.findAll", query="SELECT d FROM Dc d")
public class Dc implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private String DC_id;

	//bi-directional many-to-one association to Zone
	@ManyToOne
	@JoinColumn(name="zone_id")
	private Zone zone;

	//bi-directional many-to-one association to Device
	@OneToMany(mappedBy="dc")
	private List<Device> devices;

	public Dc() {
	}

	public String getDC_id() {
		return this.DC_id;
	}

	public void setDC_id(String DC_id) {
		this.DC_id = DC_id;
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