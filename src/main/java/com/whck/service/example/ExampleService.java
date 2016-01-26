package com.whck.service.example;

import com.whck.dmo.example.Example;

/**
 * 
 * @author 马健原 2016-1-24
 *
 */
public interface ExampleService {
	public Example findByUserName(String userName);

	public void add(Example example);

	public void update(Example example) throws Exception;

	public void delete(Example example);
}
