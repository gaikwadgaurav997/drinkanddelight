package com.capgemini.dnd.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.capgemini.dnd.entity.ProductStockEntity;

@Repository
public interface ProductStockDAO extends JpaRepository<ProductStockEntity, Integer> {

}
