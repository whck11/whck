package com.whck.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.whck.dmo.PublishResource;

public interface PublishResourceDao extends JpaRepository<PublishResource, Integer> {

	List<PublishResource> findByMenu_Id(Integer id);

}
