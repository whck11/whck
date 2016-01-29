package com.whck.dmo;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the variable database table.
 * 
 */
@Entity
@NamedQuery(name="Variable.findAll", query="SELECT v FROM Variable v")
public class Variable implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="variable_id")
	private Integer variableId;

	@Column(name="max_value")
	private double maxValue;

	@Column(name="min_value")
	private double minValue;

	private String name;

	private int type;

	private String unit;

	@Column(name="var_set")
	private byte varSet;

	//bi-directional many-to-one association to Device
	@ManyToOne
	@JoinColumn(name="ctrl_device_id")
	private Device device;

	public Variable() {
	}

	public Integer getVariableId() {
		return this.variableId;
	}

	public void setVariableId(Integer variableId) {
		this.variableId = variableId;
	}

	public double getMaxValue() {
		return this.maxValue;
	}

	public void setMaxValue(double maxValue) {
		this.maxValue = maxValue;
	}

	public double getMinValue() {
		return this.minValue;
	}

	public void setMinValue(double minValue) {
		this.minValue = minValue;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getType() {
		return this.type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public String getUnit() {
		return this.unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	public byte getVarSet() {
		return this.varSet;
	}

	public void setVarSet(byte varSet) {
		this.varSet = varSet;
	}

	public Device getDevice() {
		return this.device;
	}

	public void setDevice(Device device) {
		this.device = device;
	}

}