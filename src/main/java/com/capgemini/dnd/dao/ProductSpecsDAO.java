package com.capgemini.dnd.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.capgemini.dnd.entity.ProductSpecsEntity;

@Repository
public interface ProductSpecsDAO extends JpaRepository<ProductSpecsEntity, Integer> {

}
