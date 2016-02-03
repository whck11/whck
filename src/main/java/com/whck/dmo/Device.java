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
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="device_id")
	private Integer deviceId;

	@Column(name="ctrl_mode")
	private Integer ctrlMode;

	@Column(name="ctrl_way")
	private Integer ctrlWay;

	@Column(name="device_name")
	private String deviceName;

	private String ip;

	//bi-directional many-to-one association to Zone
	@ManyToOne
	@JoinColumn(name="zone_id")
	private Zone zone;

	//bi-directional many-to-one association to Variable
	@OneToMany(mappedBy="device")
	private List<Variable> variables;

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


	public Zone getZone() {
		return this.zone;
	}

	public void setZone(Zone zone) {
		this.zone = zone;
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