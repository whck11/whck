package com.whck.service.example;

import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;

import com.whck.dmo.example.Example;
import com.whck.service.example.ExampleService;

/**
 * 
 * @author 马健原 2016-1-24
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring-*.xml")
@TransactionConfiguration(transactionManager = "transactionManager", defaultRollback = false)
@Transactional
public class ExampleServiceTest {
	@Autowired
	protected ExampleService exampleService;

	@Test
	public void testFindExampleByName() {
		Example e = this.exampleService.findByUserName("whck@163.com");
		System.out.println(e);
	}

	@Test
	public void testAdd() {
		Example e = new Example(null, "sdfs");
		this.exampleService.add(e);
	}

	@Test
	public void testDelete() {
		Example e = new Example();
		e.setId(2);
		this.exampleService.delete(e);
	}

	@Test
	public void testUpdate() {
		Example e = new Example(3, "123");
		this.exampleService.update(e);
	}
}
