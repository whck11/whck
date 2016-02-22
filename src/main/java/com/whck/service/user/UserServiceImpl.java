package com.whck.service.user;

import java.util.Date;

import javax.transaction.Transactional;
import javax.transaction.Transactional.TxType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.whck.dao.UserDao;
import com.whck.dmo.User;

@Service
public class UserServiceImpl implements UserService {
	@Autowired
	private UserDao userDao;

	@Override
	public User login(String username, String password) throws Exception {
		User user = userDao.findOne(username);
		if (user == null) {
			throw new Exception("用户不存在");
		}
		if (!user.getPassword().equals(password)) {
			throw new Exception("密码错误");
		}
		return user;
	}

	@Override
	public User findByUsername(String email) {
		return this.userDao.findOne(email);
	}

	@Override
	@Transactional(value=TxType.REQUIRED,rollbackOn=Throwable.class)
	public void add(User user) {
		user.setRegDate(new Date());
		this.userDao.save(user);
	}

}
