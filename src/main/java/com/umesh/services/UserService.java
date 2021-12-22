package com.umesh.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.umesh.config.MyUserDetails;
import com.umesh.entity.User;

@Service
public class UserService implements UserDetailsService{
     
    @Autowired
    UserRepository repository;
    

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = repository.getUserByUsername(username);
        
        if (user == null) {
        	throw new UsernameNotFoundException("User not found");
        }
        
		return new MyUserDetails(user);
	} 
     

	
}
