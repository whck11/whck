package com.whck.util;

import com.whck.dmo.Dc;

public interface CommandResolver {
	
	public byte[] deResolve(Dc dc);

	public Dc resolve(byte[] command);
}
