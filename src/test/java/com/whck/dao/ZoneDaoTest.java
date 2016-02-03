package com.whck.dao;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;

import com.whck.dmo.Zone;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring-*.xml")
@TransactionConfiguration(transactionManager = "transactionManager", defaultRollback = false)
public class ZoneDaoTest {
	@Autowired
	private ZoneDao zoneDao;

	@Test
	public void testZoneFind() {
		List<Zone> zones = this.zoneDao.findAll();
		for (Zone zone : zones) {
			System.out.println(zone.getZoneId() + zone.getZoneName());
		}
	}

	@Test
	public void testZonePage() {
		Page<Zone> zonePages = this.zoneDao.findAll(new PageRequest(1, 2));
		for (Zone zone : zonePages) {
			System.out.println(zone.getZoneId() + zone.getZoneName());
		}

	}
}
