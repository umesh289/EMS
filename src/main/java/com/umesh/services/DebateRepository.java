package com.umesh.services;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.umesh.entity.DebateRecord;


@Repository
public interface DebateRepository 
extends CrudRepository<DebateRecord, Long> {
	
}
