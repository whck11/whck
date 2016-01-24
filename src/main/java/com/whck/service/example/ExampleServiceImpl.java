package com.whck.service.example;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.whck.dao.example.ExampleDao;
import com.whck.dmo.example.Example;

/**
 * 
 * @author 马健原 2016-1-24
 *
 */
@Transactional
@Service
public class ExampleServiceImpl implements ExampleService {
	@Autowired
	protected ExampleDao exampleDao;

	@Override
	public Example findByUserName(String name) {
		return this.exampleDao.findByName(name);
	}

	public void add(Example example) {
		this.exampleDao.save(example);
	}

	public void update(Example example) {
		this.exampleDao.save(example);
	}

	public void delete(Example example) {
		this.exampleDao.delete(example);
	}
}
