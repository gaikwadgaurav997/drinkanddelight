package com.capgemini.dnd.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.capgemini.dnd.entity.ProductOrdersEntity;

@Repository
public interface ProductOrdersDAO extends JpaRepository<ProductOrdersEntity, Integer> {

}
