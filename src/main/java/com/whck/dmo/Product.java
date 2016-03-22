package com.whck.dmo;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
@Entity
public class Product implements Serializable {

	private static final long serialVersionUID = 2758517517476132988L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	private String name;

	private Double price;
	/**
	 * 条形码图片地址
	 */
	@Column(name = "barcode_src")
	private String barcodeSrc;
	/**
	 * 二维码图片地址
	 */
	@Column(name = "dimensional_code_src")
	private String dimensionalCodeSrc;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public String getBarcodeSrc() {
		return barcodeSrc;
	}

	public void setBarcodeSrc(String barcodeSrc) {
		this.barcodeSrc = barcodeSrc;
	}

	public String getDimensionalCodeSrc() {
		return dimensionalCodeSrc;
	}

	public void setDimensionalCodeSrc(String dimensionalCodeSrc) {
		this.dimensionalCodeSrc = dimensionalCodeSrc;
	}

}
