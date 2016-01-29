package com.whck.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.whck.dmo.Breed;

public interface BreedDao extends JpaRepository<Breed, Integer> {

}
