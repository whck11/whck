package com.whck.service;

import com.whck.dmo.UserInfo;
/**
 * 
 * @author 马健原 2016-1-24
 *
 */
public interface UserInfoService {
	public UserInfo findByUserName(String userName);
}
