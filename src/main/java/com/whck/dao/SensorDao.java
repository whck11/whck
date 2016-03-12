package com.whck.dao;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;

import com.whck.dmo.Sensor;

public interface SensorDao extends JpaRepository<Sensor, Serializable>{

}
