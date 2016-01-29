package com.whck.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.whck.dmo.Variable;

public interface VariableDao extends JpaRepository<Variable, Integer> {

}
