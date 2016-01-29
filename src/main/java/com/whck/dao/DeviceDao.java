package com.whck.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.whck.dmo.Device;

public interface DeviceDao extends JpaRepository<Device, Integer>{

}
