package com.whck.service.dc;

import com.whck.dmo.Dc;

public interface CommandResolver {
	public Dc resolve(byte[] command);

	public byte[] deResolve(Dc dc);
}
