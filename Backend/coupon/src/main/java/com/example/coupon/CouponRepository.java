package com.example.coupon;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.web.bind.annotation.CrossOrigin;

//@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)
public interface CouponRepository extends MongoRepository<Coupon, String>{
	
	Coupon findProductById(String Id);
}
