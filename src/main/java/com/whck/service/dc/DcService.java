package com.whck.service.dc;

import com.whck.dmo.Dc;

public interface DcService {
	public void save(Dc dc);

	public void sendCommand(Dc dc) throws Exception;
}
