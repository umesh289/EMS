package com.umesh.services;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.umesh.entity.DebateRecord;
import com.umesh.entity.User;


@Repository
public interface UserRepository 
extends CrudRepository<User, Long> {
	public User getUserByUsername(String user);
}
