package com.umesh.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.umesh.entity.EmployeeRecord;
import com.umesh.exception.RecordNotFoundException;

@Service
public class EmployeeService {
     
    @Autowired
    EMSRepository repository;
     
    public List<EmployeeRecord> getAllEmployeeRecords()
    {
        List<EmployeeRecord> result = (List<EmployeeRecord>) repository.findAll();
         
        if(result.size() > 0) {
            return result;
        } else {
            return new ArrayList<EmployeeRecord>();
        }
    }
    
    public List<EmployeeRecord> getEmployeeRecordsByFirstname(String firstname)
    {
        List<EmployeeRecord> result = (List<EmployeeRecord>) repository.findByFirstname(firstname);
         
        if(result.size() > 0) {
            return result;
        } else {
            return new ArrayList<EmployeeRecord>();
        }
    }
     
    public EmployeeRecord getEmployeeRecordById(Long id) throws RecordNotFoundException 
    {
        Optional<EmployeeRecord> employeeRecord = repository.findById(id);
         
        if(employeeRecord.isPresent()) {
            return employeeRecord.get();
        } else {
            throw new RecordNotFoundException("No employeeRecord exist for given id");
        }
    }
     
    public EmployeeRecord createOrUpdateEmployeeRecord(EmployeeRecord entity) 
    {
        if(entity.getId()  == null) 
        {
            entity = repository.save(entity);
             
            return entity;
        } 
        else
        {
            Optional<EmployeeRecord> employeeRecord = repository.findById(entity.getId());
             
            if(employeeRecord.isPresent()) 
            {
            	EmployeeRecord newEntity = employeeRecord.get();
                newEntity.setFirstname(entity.getFirstname());
                newEntity.setLastname(entity.getLastname());
                newEntity.setEmail(entity.getEmail());
 
                newEntity = repository.save(newEntity);
                 
                return newEntity;
            } else {
                entity = repository.save(entity);
                 
                return entity;
            }
        }
    } 
     
    public void deleteEmployeeRecordById(Long id) throws RecordNotFoundException 
    {
        Optional<EmployeeRecord> employeeRecord = repository.findById(id);
         
        if(employeeRecord.isPresent()) 
        {
            repository.deleteById(id);
        } else {
            throw new RecordNotFoundException("No employeeRecord exist for given id");
        }
    }
    
    
	public List<EmployeeRecord> sortByFistname(String order) {
		List<EmployeeRecord> result;
		
		if (order.equals("asc")) {
			result = (List<EmployeeRecord>) repository.findByOrderByFirstnameAsc();
		} else {
			result = (List<EmployeeRecord>) repository.findByOrderByFirstnameDesc();
		}
		
		if (result.size() > 0) {
			return result;
		} else {
			return new ArrayList<EmployeeRecord>();
		}
	}
}
