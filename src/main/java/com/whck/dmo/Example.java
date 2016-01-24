package com.whck.dmo;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 
 * @author 马健原 2016-1-24
 *
 */
@Entity
@Table(name = "example")
public class Example implements Serializable {

	public Example() {
		super();
	}

	public Example(Integer id, String name) {
		super();
		this.id = id;
		this.name = name;
	}

	private static final long serialVersionUID = 8883827772773239109L;
	
	@Column(name = "id")
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(name = "name")
	private String name;

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

}
