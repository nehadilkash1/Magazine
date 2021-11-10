package com.deloitte.magazine.repository;

import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

import com.deloitte.magazine.model.Magazine;
import com.deloitte.magazine.model.Subscription;

import reactor.core.publisher.Flux;

public interface SubscriptionRepository  extends ReactiveMongoRepository<Subscription, String>{
	
	Flux<Subscription> findByIdNotNull(Pageable pageable);
	
	Flux<Subscription> findByMagazine(Magazine magazine);

}
