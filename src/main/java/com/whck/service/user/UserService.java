package com.whck.service.user;

import com.whck.dmo.User;

public interface UserService {

	User login(String name, String password) throws Exception;

	void add(User user);
}
