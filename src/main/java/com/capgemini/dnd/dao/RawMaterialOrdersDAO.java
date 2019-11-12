package com.capgemini.dnd.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.capgemini.dnd.dto.DisplayRawMaterialOrder;
import com.capgemini.dnd.entity.RawMaterialOrderEntity;

@Repository
public interface RawMaterialOrdersDAO extends JpaRepository<RawMaterialOrderEntity, Integer> {

  
}
