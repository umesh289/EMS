package com.umesh.services;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.umesh.entity.EmployeeRecord;


@Repository
public interface EMSRepository 
extends CrudRepository<EmployeeRecord, Long> {
	
	List<EmployeeRecord> findByFirstname(String firstname);

    List<EmployeeRecord> findByOrderByFirstnameAsc();
    
    List<EmployeeRecord> findByOrderByFirstnameDesc();


}
