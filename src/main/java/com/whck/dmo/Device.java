package com.whck.dmo;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


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
	private int deviceId;

	@Column(name="ctrl_mode")
	private int ctrlMode;

	@Column(name="ctrl_way")
	private int ctrlWay;

	@Lob
	private String description;

	@Column(name="device_name")
	private String deviceName;

	private String ip;

	private int state;

	//bi-directional many-to-one association to Dc
	@ManyToOne
	@JoinColumn(name="dc_id")
	private Dc dc;

	//bi-directional many-to-one association to Variable
	@OneToMany(mappedBy="device")
	private List<Variable> variables;

	public Device() {
	}

	public int getDeviceId() {
		return this.deviceId;
	}

	public void setDeviceId(int deviceId) {
		this.deviceId = deviceId;
	}

	public int getCtrlMode() {
		return this.ctrlMode;
	}

	public void setCtrlMode(int ctrlMode) {
		this.ctrlMode = ctrlMode;
	}

	public int getCtrlWay() {
		return this.ctrlWay;
	}

	public void setCtrlWay(int ctrlWay) {
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

	public int getState() {
		return this.state;
	}

	public void setState(int state) {
		this.state = state;
	}

	public Dc getDc() {
		return this.dc;
	}

	public void setDc(Dc dc) {
		this.dc = dc;
	}

	public List<Variable> getVariables() {
		return this.variables;
	}

	public void setVariables(List<Variable> variables) {
		this.variables = variables;
	}

	public Variable addVariable(Variable variable) {
		getVariables().add(variable);
		variable.setDevice(this);

		return variable;
	}

	public Variable removeVariable(Variable variable) {
		getVariables().remove(variable);
		variable.setDevice(null);

		return variable;
	}

}