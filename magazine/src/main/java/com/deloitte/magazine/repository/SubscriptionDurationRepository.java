package com.deloitte.magazine.repository;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

import com.deloitte.magazine.model.SUBFOR;
import com.deloitte.magazine.model.SubscriptionDuartion;

public interface SubscriptionDurationRepository extends ReactiveMongoRepository<SubscriptionDuartion , SUBFOR>{

}