package com.whck.service.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.whck.dao.example.UserDao;
import com.whck.dmo.example.User;

@Service
public class UserServiceImpl implements UserService {
	@Autowired
	private UserDao userDao;

	@Override
	public User login(String username, String password) throws Exception {
		User user = userDao.findByUsername(username);
		if(user==null){
			throw new Exception("用户不存在");
		}
		if(!user.getPassword().equals(password)){
			throw new Exception("密码错误");
		}
		return user;
	}

}
