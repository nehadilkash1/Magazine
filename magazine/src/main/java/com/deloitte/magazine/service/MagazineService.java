package com.deloitte.magazine.service;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.stereotype.Component;

import com.deloitte.magazine.model.Magazine;
import com.deloitte.magazine.repository.MagazineRepository;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Component
@Slf4j
public class MagazineService {

	private final MagazineRepository magazineRepository;
	private final ReactiveMongoTemplate mongoTemplate;

	public MagazineService(MagazineRepository magazineRepository, ReactiveMongoTemplate mongoTemplate) {
		this.magazineRepository = magazineRepository;
		this.mongoTemplate = mongoTemplate;
	}

	public void saveAllMagazines(List<Magazine> magazines) {
		log.debug("fetching Magazines");
		magazineRepository.saveAll(magazines).subscribe();
	}

	public Flux<Magazine> getAllMagazines(Pageable pageable) {
		log.debug("fetching Magazines");
		return magazineRepository.findByIdNotNull(pageable);

	}

	public Flux<Magazine> getFilteredMagazines(String category, String name) {
		log.debug("fetching Magazines");
		return magazineRepository.findByCategoryAndName(category, name);
	}

	public Flux<Magazine> getFilteredMagazinesByCategory(String category, Pageable pageable) {
		log.debug("fetching Magazines");
		return magazineRepository.findByCategory(category, pageable);

	}

	public Flux<Magazine> getFilteredMagazinesByName(String name) {
		log.debug("fetching Magazines");
		return magazineRepository.findByName(name);

	}

	public Mono<List<String>> getCategories() {
		log.debug("fetching Categories");
		return mongoTemplate.findDistinct("category", Magazine.class, String.class).collectList();

	}

}
