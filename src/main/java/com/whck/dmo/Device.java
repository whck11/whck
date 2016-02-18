package com.whck.dmo;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;


/**
 * The persistent class for the device database table.
 * 
 */
@Entity
@NamedQuery(name="Device.findAll", query="SELECT d FROM Device d")
public class Device implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="device_id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer deviceId;

	@Column(name="ctrl_mode")
	private Integer ctrlMode;

	@Column(name="ctrl_way")
	private Integer ctrlWay;

	@Lob
	private String description;

	@Column(name="device_name")
	private String deviceName;

	private String ip;

	private Integer state;

	//bi-directional many-to-one association to Dc
	@ManyToOne
	@JoinColumn(name="dc_id")
	private Dc dc;

	public Device() {
	}

	public Integer getDeviceId() {
		return this.deviceId;
	}

	public void setDeviceId(Integer deviceId) {
		this.deviceId = deviceId;
	}

	public Integer getCtrlMode() {
		return this.ctrlMode;
	}

	public void setCtrlMode(Integer ctrlMode) {
		this.ctrlMode = ctrlMode;
	}

	public Integer getCtrlWay() {
		return this.ctrlWay;
	}

	public void setCtrlWay(Integer ctrlWay) {
		this.ctrlWay = ctrlWay;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getDeviceName() {
		return this.deviceName;
	}

	public void setDeviceName(String deviceName) {
		this.deviceName = deviceName;
	}

	public String getIp() {
		return this.ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public Integer getState() {
		return this.state;
	}

	public void setState(Integer state) {
		this.state = state;
	}

	public Dc getDc() {
		return this.dc;
	}

	public void setDc(Dc dc) {
		this.dc = dc;
	}


}