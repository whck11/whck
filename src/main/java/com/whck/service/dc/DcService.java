package com.whck.service.dc;

import java.util.Map;

import com.whck.dmo.Dc;

public interface DcService {
	public void save(Dc dc);

	public Map<Integer, Boolean> sendCommand(Dc dc) throws Exception;
}
