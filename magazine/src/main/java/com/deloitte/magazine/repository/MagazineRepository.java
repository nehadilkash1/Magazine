package com.deloitte.magazine.repository;

import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

import com.deloitte.magazine.model.Magazine;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface MagazineRepository extends ReactiveMongoRepository<Magazine, String> {

	Flux<Magazine> findByCategoryAndName(String category, String name);

	Flux<Magazine> findByCategory(String category,Pageable pageable);

	Flux<Magazine> findByName(String name);
	
	Mono<Magazine> findById(int id);
	
	Flux<Magazine> findByIdNotIn(Flux<Integer> ids,Pageable pageable);
	
	Flux<Magazine> findByIdNotNull(Pageable pageable);
	
}
