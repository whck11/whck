package com.whck.dmo;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * The persistent class for the device database table.
 * 
 */
@Entity
@NamedQuery(name = "Device.findAll", query = "SELECT d FROM Device d")
public class Device implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "device_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer deviceId;

	@Column(name = "ctrl_mode")
	private Integer ctrlMode;

	@Column(name = "ctrl_way")
	private Integer ctrlWay;

	@Lob
	private String description;

	@Column(name = "device_name")
	private String deviceName;

	private String ip;

	private Integer state;
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "start_time")
	private Date startTime;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "end_time")
	private Date endTime;

	@Column(name = "run_time")
	private Integer runTime;

	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	public Integer getRunTime() {
		return runTime;
	}

	public void setRunTime(Integer runTime) {
		this.runTime = runTime;
	}

	public Integer getStopTime() {
		return stopTime;
	}

	public void setStopTime(Integer stopTime) {
		this.stopTime = stopTime;
	}

	@Column(name = "stop_time")
	private Integer stopTime;
	// bi-directional many-to-one association to Dc
	@ManyToOne
	@JoinColumn(name = "dc_id")
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

	private Integer port;

	public int getPort() {
		return port;
	}

	public void setPort(Integer port) {
		this.port = port;
	}

}