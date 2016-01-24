package com.whck.dao.example;

import org.springframework.data.jpa.repository.JpaRepository;

import com.whck.dmo.example.Example;

/**
 * 
 * @author 马健原 2016-1-24
 *
 */
public interface ExampleDao extends JpaRepository<Example, Integer> {
	public Example findByName(String name);
}
