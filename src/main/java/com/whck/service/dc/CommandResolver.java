package com.whck.service.dc;

import com.whck.dmo.Dc;

public interface CommandResolver {
	
	public String deResolve(Dc dc);

	public Dc resolve(String command);
}
