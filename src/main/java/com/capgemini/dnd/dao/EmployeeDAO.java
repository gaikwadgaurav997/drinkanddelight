package com.capgemini.dnd.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.capgemini.dnd.entity.EmployeeCredentialEntity;

@Repository
public interface EmployeeDAO extends JpaRepository<EmployeeCredentialEntity, String> {

	public boolean existsByUserName(String userName);

	List<EmployeeCredentialEntity> findByUserName(String userName);

}
