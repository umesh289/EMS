package com.umesh.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.umesh.entity.DebateRecord;
import com.umesh.exception.RecordNotFoundException;

@Service
public class DebateService {
     
    @Autowired
    DebateRepository repository;
     
    public List<DebateRecord> getAllDebateRecords()
    {
        List<DebateRecord> result = (List<DebateRecord>) repository.findAll();
         
        if(result.size() > 0) {
            return result;
        } else {
            return new ArrayList<DebateRecord>();
        }
    }
     
    public DebateRecord getDebateRecordById(Long id) throws RecordNotFoundException 
    {
        Optional<DebateRecord> debateRecord = repository.findById(id);
         
        if(debateRecord.isPresent()) {
            return debateRecord.get();
        } else {
            throw new RecordNotFoundException("No DebateRecord exist for given id");
        }
    }
     
    public DebateRecord createOrUpdateDebateRecord(DebateRecord entity) 
    {
        if(entity.getId()  == null) 
        {
            entity = repository.save(entity);
             
            return entity;
        } 
        else
        {
            Optional<DebateRecord> debateRecord = repository.findById(entity.getId());
             
            if(debateRecord.isPresent()) 
            {
            	DebateRecord newEntity = debateRecord.get();
                newEntity.setName(entity.getName());
                newEntity.setDepartment(entity.getDepartment());
                newEntity.setCountry(entity.getCountry());
 
                newEntity = repository.save(newEntity);
                 
                return newEntity;
            } else {
                entity = repository.save(entity);
                 
                return entity;
            }
        }
    } 
     
    public void deleteDebateRecordById(Long id) throws RecordNotFoundException 
    {
        Optional<DebateRecord> debateRecord = repository.findById(id);
         
        if(debateRecord.isPresent()) 
        {
            repository.deleteById(id);
        } else {
            throw new RecordNotFoundException("No DebateRecord exist for given id");
        }
    }

	
}
