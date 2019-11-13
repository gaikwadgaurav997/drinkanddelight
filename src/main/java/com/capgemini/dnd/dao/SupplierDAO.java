package com.capgemini.dnd.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.capgemini.dnd.entity.SupplierEntity;

@Repository
public interface SupplierDAO extends JpaRepository<SupplierEntity, Integer> {
	

}
