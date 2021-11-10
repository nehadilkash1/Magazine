package com.deloitte.magazine.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;

import com.deloitte.magazine.model.Magazine;
import com.deloitte.magazine.repository.MagazineRepository;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@SpringBootTest
class MagazineServiceTest {

	@Mock
	MagazineRepository magazineRepository;

	@Mock
	ReactiveMongoTemplate mongoTemplate;
	MagazineService magazineService;

	Pageable pageable;

	@BeforeEach
	void setUp() {
		magazineService = new MagazineService(magazineRepository, mongoTemplate);
		pageable = PageRequest.of(0, 1);
	}

	@Test
	void getAllMagazines_test() {

		when(magazineRepository.findByIdNotNull(pageable)).thenReturn(Flux.just(new Magazine()));
		Flux<Magazine> result = magazineService.getAllMagazines(pageable);
		assertEquals(1, result.collectList().block().size());
	}

	@Test
	void getFilteredMagazines_test() {
		when(magazineRepository.findByCategoryAndName("testCategory", "testName"))
				.thenReturn(Flux.just(new Magazine()));
		Flux<Magazine> result = magazineService.getFilteredMagazines("testCategory", "testName");
		assertEquals(1, result.collectList().block().size());
	}

	@Test
	void getFilteredMagazinesByCategory_test() {
		when(magazineRepository.findByCategory("testCtaegory", pageable)).thenReturn(Flux.just(new Magazine()));
		Flux<Magazine> result = magazineService.getFilteredMagazinesByCategory("testCtaegory", pageable);
		assertEquals(1, result.collectList().block().size());
	}

	@Test
	void getFilteredMagazinesByName_test() {
		when(magazineRepository.findByName(any())).thenReturn(Flux.just(new Magazine()));
		Flux<Magazine> result = magazineService.getFilteredMagazinesByName("testName");
		assertEquals(1, result.collectList().block().size());
	}

	@Test
	void getCategories_test() {
		List<String> categories = new ArrayList<>();
		categories.add("testCategory");
		when(mongoTemplate.findDistinct(any(), any(), any())).thenReturn(Flux.just(categories));
		Mono<List<String>> result = magazineService.getCategories();
		assertEquals(1, result.block().size());
	}

}
