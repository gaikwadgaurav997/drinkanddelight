package com.capgemini.dnd.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.capgemini.dnd.entity.WarehouseEntity;

@Repository
public interface WarehouseDAO extends JpaRepository<WarehouseEntity, Integer> {

}
