package com.umesh.services;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.umesh.entity.EmployeeRecord;


@Repository
public interface EMSRepository 
extends CrudRepository<EmployeeRecord, Long> {
	
}
