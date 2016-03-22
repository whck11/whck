package com.whck.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.whck.dmo.Product;

public interface ProductDao extends JpaRepository<Product,Integer>{

}
