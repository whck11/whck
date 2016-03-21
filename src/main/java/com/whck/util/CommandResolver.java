package com.whck.util;

import java.io.File;

import com.whck.dmo.Dc;
import com.whck.dmo.Zone;

public interface CommandResolver {
	
	public String deResolve(Dc dc);

	public Dc resolve(String command);
	
	public Zone resoleZoneFromXml(File file);
}
