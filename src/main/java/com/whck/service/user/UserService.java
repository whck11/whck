package com.whck.service.user;

import com.whck.dmo.example.User;

public interface UserService {

	User login(String name, String password) throws Exception;
}
