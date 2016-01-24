package com.whck.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.whck.dao.ExampleDao;
import com.whck.dmo.Example;

/**
 * 
 * @author 马健原 2016-1-24
 *
 */
@Transactional
@Service
public class UserInfoServiceImpl implements ExampleService {
	@Autowired
	protected ExampleDao exampleDao;

	@Override
	public Example findByUserName(String name) {
		return this.exampleDao.findByName(name);
	}

}
