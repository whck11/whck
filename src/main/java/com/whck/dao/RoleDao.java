package com.whck.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.whck.dmo.Role;

public interface RoleDao extends JpaRepository<Role, Integer> {

}
