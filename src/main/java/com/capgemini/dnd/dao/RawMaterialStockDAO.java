package com.capgemini.dnd.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.capgemini.dnd.entity.RawMaterialStockEntity;

@Repository
public interface RawMaterialStockDAO extends JpaRepository<RawMaterialStockEntity, Integer> {

}
