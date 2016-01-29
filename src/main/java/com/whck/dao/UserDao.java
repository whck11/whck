package com.whck.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.whck.dmo.User;

public interface UserDao extends JpaRepository<User, String> {

	User findByUsername(String name);

}
