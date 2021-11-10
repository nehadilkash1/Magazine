package com.deloitte.magazine.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import com.deloitte.magazine.model.Magazine;
import com.deloitte.magazine.model.SUBFOR;
import com.deloitte.magazine.model.Subscription;
import com.deloitte.magazine.repository.MagazineRepository;
import com.deloitte.magazine.repository.SubscriptionRepository;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@SpringBootTest
class SubscriptionServiceTest {

	@Mock
	SubscriptionRepository subscriptionRepository;

	@Mock
	MagazineRepository magazineRepository;

	SubscriptionService subscriptionService;

	Pageable pageable;

	@BeforeEach
	void setUp() {
		subscriptionService = new SubscriptionService(subscriptionRepository, magazineRepository);
		pageable = PageRequest.of(0, 1);
	}

	@Test
	void subscribeMagazine_test() throws InterruptedException {
		Magazine magazine = Magazine.builder().id(1).build();
		Subscription subscription = Subscription.builder().subscriptionPeriod(SUBFOR.ONE_WEEK).magazine(magazine)
				.build();
		when(subscriptionRepository.save(subscription)).thenReturn(Mono.just(subscription));
		Mono<Subscription> result = subscriptionService.subscribeMagazine(subscription);
		assertEquals(SUBFOR.ONE_WEEK, result.block().getSubscriptionPeriod());
	}

	@Test
	void fetchSubscribedMagazine_test() {
		Magazine magazine = Magazine.builder().id(1).build();
		Subscription subscription = Subscription.builder().magazine(magazine).build();
		when(subscriptionRepository.findByIdNotNull(pageable)).thenReturn(Flux.just(subscription));
		when(magazineRepository.findById(1)).thenReturn(Mono.just(magazine));
		Flux<Subscription> result = subscriptionService.fetchSubscribedMagazine(pageable);
		assertEquals(subscription, result.collectList().block().get(0));
	}

	@Test
	void fetchNotSubscribedMagazine_test() {
		Magazine magazine = Magazine.builder().id(1).build();
		Magazine magazine1 = Magazine.builder().id(2).build();
		Subscription subscription = Subscription.builder().magazine(magazine).build();
		when(subscriptionRepository.findAll()).thenReturn(Flux.just(subscription));
		when(magazineRepository.findByIdNotIn(Flux.just(any(Integer.class)), any())).thenReturn(Flux.just(magazine1));
		Flux<Magazine> result = subscriptionService.fetchNotSubscribedMagazine(pageable);
		assertEquals(null, result);
	}

}
