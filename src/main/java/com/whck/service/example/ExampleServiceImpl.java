package com.whck.service.example;

import javax.transaction.Transactional;

import org.apache.commons.logging.Log;
import org.apache.log4j.Logger;
import org.slf4j.LoggerFactory;
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
	
	private static Logger logger = Logger.getLogger(Example.class);  


	@Override
	public Example findByUserName(String name) {
		return this.exampleDao.findByName(name);
	}

	public void add(Example example) {
		example.setId(null);
		this.exampleDao.save(example);
		logger.debug("添加成功");
	}

	public void update(Example example) {
		this.exampleDao.save(example);
		logger.debug("跟新成功");
	}

	public void delete(Example example) {
		this.exampleDao.delete(example);
		logger.debug("删除成功");
	}
}
