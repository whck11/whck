package com.whck.dmo.example;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the example database table.
 * 
 */
@Entity
@NamedQuery(name="Example.findAll", query="SELECT e FROM Example e")
public class Example implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;

	private String name;

	public Example() {
	}


	public Example(Integer id, String name) {
		super();
		this.id = id;
		this.name = name;
	}



	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

}