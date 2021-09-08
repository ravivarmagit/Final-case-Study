package com.example.security_gateway.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


import com.example.security_gateway.repository.UserDetailsRepo;
import java.util.Optional;


@Service
public class UserService implements UserDetailsService {
	 
	@Autowired
	 UserDetailsRepo details;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		Optional<com.example.security_gateway.model.User> user  = details.findById(username);
	   
		 if(user == null) {throw new UsernameNotFoundException("User not found");}
		 
		 List<SimpleGrantedAuthority> authorities = Arrays.asList(new SimpleGrantedAuthority("user"));
		 
		  return new User(user.get().getUsername(), user.get().getPassword(), authorities);
	}

}
