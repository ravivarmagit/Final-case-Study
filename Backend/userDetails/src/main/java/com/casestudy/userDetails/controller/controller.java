package com.casestudy.userDetails.controller;

import com.casestudy.userDetails.model.BasicUserDetails;
import com.casestudy.userDetails.model.userDetails;
import com.casestudy.userDetails.repository.BasicUserDetailsRepository;
import com.casestudy.userDetails.repository.userDetailsRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/user")
public class controller {

    private static final Logger log = LoggerFactory.getLogger(controller.class);

    @Autowired
    private userDetailsRepository userDetailsRepository;

    @Autowired
    private BasicUserDetailsRepository basicUserDetailsRepository;

    @GetMapping(value = "/all")
    public List<userDetails> getAllUsers() {
        log.info("Get request for getting all users");
        return userDetailsRepository.findAll();
    }

    @PostMapping(value = "/add")
    public userDetails addUser(@RequestBody userDetails user) {
        log.info("Post request for adding user");
        return userDetailsRepository.save(user);
    }

    @PostMapping(value = "/update")
    public userDetails updateUserDetails(@RequestBody BasicUserDetails user) {

        log.info("Post request for updating user details");

        userDetails details = userDetailsRepository.findById(user.getUsername()).orElse(null);

        details.setCashbackPoints(user.getCashbackPoints());
            details.setListOfCoupons(user.getListOfCoupons());
        details.setPreferences(user.getPreferences());

        return userDetailsRepository.save(details);
    }

    @GetMapping(value = "/getuser/{name}")
    public Optional<BasicUserDetails> getUserDetail(@PathVariable String name) {
        log.info("Get request getting user details");
        return basicUserDetailsRepository.findById(name);
    }


}
