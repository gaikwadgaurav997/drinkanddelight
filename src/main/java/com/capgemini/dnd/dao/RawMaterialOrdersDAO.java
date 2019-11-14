package com.capgemini.dnd.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.capgemini.dnd.entity.RawMaterialOrderEntity;

@Repository
public interface RawMaterialOrdersDAO extends JpaRepository<RawMaterialOrderEntity, Integer> {

  
}
