package com.whck.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.whck.dao.UserInfoDao;
import com.whck.dmo.UserInfo;

/**
 * 
 * @author 马健原 2016-1-24
 *
 */
@Transactional
@Service
public class UserInfoServiceImpl implements UserInfoService {
	@Autowired
	protected UserInfoDao userInfoDao;

	@Override
	public UserInfo findByUserName(String userName) {
		return this.userInfoDao.findByUserName(userName);
	}

}
