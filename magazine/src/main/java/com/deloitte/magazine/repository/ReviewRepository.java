package com.deloitte.magazine.repository;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

import com.deloitte.magazine.model.Review;

public interface ReviewRepository  extends ReactiveMongoRepository<Review, String>{

}
