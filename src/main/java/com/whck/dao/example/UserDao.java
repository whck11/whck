package com.whck.dao.example;

import org.springframework.data.jpa.repository.JpaRepository;

import com.whck.dmo.example.User;

public interface UserDao extends JpaRepository<User, String> {

	User findByUsername(String name);

}
