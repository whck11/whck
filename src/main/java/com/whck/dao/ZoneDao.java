package com.whck.dao;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.whck.dmo.Zone;

public interface ZoneDao extends JpaRepository<Zone, Integer> {

	List<Zone> findByUser_NameLike(String name);

	Page<Zone> findAll(Pageable pageable);
}
