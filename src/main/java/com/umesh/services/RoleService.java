package com.umesh.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.umesh.entity.Role;
import com.umesh.exception.RecordNotFoundException;

@Service
public class RoleService {
     
	@Autowired
    RoleRepository repository;
     
    public List<Role> getAllRoles()
    {
        List<Role> result = (List<Role>) repository.findAll();
         
        if(result.size() > 0) {
            return result;
        } else {
            return new ArrayList<Role>();
        }
    }
     
    public Role getRoleById(Long id) throws RecordNotFoundException 
    {
        Optional<Role> Role = repository.findById(id);
         
        if(Role.isPresent()) {
            return Role.get();
        } else {
            throw new RecordNotFoundException("No Role exist for given id");
        }
    }
     
    public Role createOrUpdateRole(Role entity) 
    {
        if(entity.getId()  == null) 
        {
            entity = repository.save(entity);
             
            return entity;
        } 
        else
        {
            Optional<Role> Role = repository.findById(entity.getId());
             
            if(Role.isPresent()) 
            {
            	Role newEntity = Role.get();
                newEntity.setName(entity.getName());
 
                newEntity = repository.save(newEntity);
                 
                return newEntity;
            } else {
                entity = repository.save(entity);
                 
                return entity;
            }
        }
    } 
     
    public void deleteRoleById(Long id) throws RecordNotFoundException 
    {
        Optional<Role> Role = repository.findById(id);
         
        if(Role.isPresent()) 
        {
            repository.deleteById(id);
        } else {
            throw new RecordNotFoundException("No Role exist for given id");
        }
    }

	
}
