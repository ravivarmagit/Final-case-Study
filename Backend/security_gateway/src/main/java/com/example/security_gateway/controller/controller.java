package com.example.security_gateway.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.security_gateway.model.JWTRequest;
import com.example.security_gateway.model.JWTResponse;
import com.example.security_gateway.service.UserService;
import com.example.security_gateway.utility.JWTUtility;

@RestController
@RequestMapping("/gateway")
public class controller {
	
	 @Autowired
	    private JWTUtility jwtUtility;
	 
	 @Autowired
	    private AuthenticationManager authenticationManager;
	 
	 @Autowired
	    private UserDetailsService userDetailsService;

	    @Autowired
	    private UserService userService;

    @GetMapping("/ping")
    public String ping(){
        return "ping from Gateway";
    }
    @GetMapping("/")
    public String home(){
        return "ping from Gateway";
    }
    
    @PostMapping("/authenticate")
    public JWTResponse authenticate(@RequestBody JWTRequest jwtRequest) throws Exception {
    	try {	
    	authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                     jwtRequest.getUsername(), jwtRequest.getPassword()));
    	 }catch(BadCredentialsException e) {
    		 throw new Exception("INVALID CREDENTIALS", e);
    	 }
    	
    	final UserDetails userDetails 
    				= userService.loadUserByUsername(jwtRequest.getUsername());
    	 final String token =
                 jwtUtility.generateToken(userDetails);
    	 
    	 return  new JWTResponse(token);
    }	
    }
    
    
    
    
    
    
    
