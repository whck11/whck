package com.whck.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.whck.dmo.UserInfo;
/**
 * 
 * @author 马健原 2016-1-24
 *
 */
public interface UserInfoDao extends JpaRepository<UserInfo, Integer> {
	public UserInfo findByUserName(String userName);
}
