package com.capgemini.dnd.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.capgemini.dnd.entity.RawMaterialSpecsEntity;

@Repository
public interface RawMaterialSpecsDAO extends JpaRepository<RawMaterialSpecsEntity, Integer> {

}
