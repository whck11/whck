package com.whck.util;

import org.springframework.stereotype.Service;

import com.whck.dmo.Dc;
import com.whck.dmo.Device;

@Service
public class CommandResolverImpl implements CommandResolver{

	@Override
	public String deResolve(Dc dc) {
		return null;
	}

	@Override
	public Dc resolve(String command) {
		int index=command.indexOf("E8E8E8E8E8E8E8E8");
		index+=16;
		String c=command.substring(index, index+3);
		Dc dc=new Dc();
		Device device=new Device();
		
		return null;
	}



}
