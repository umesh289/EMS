package com.umesh.services;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.umesh.entity.EmployeeRecord;
import com.umesh.entity.User;


@Repository
public interface UserRepository 
extends CrudRepository<User, Long> {
	public User getUserByUsername(String user);
}
