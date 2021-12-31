package com.umesh.services;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.umesh.entity.Role;

@Repository
public interface RoleRepository extends CrudRepository<Role, Long> {

}