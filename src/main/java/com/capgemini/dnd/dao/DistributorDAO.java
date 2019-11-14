package com.capgemini.dnd.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.capgemini.dnd.entity.DistributorEntity;

@Repository
public interface DistributorDAO extends JpaRepository<DistributorEntity, Integer> {

}
