package com.deloitte.magazine.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import com.deloitte.magazine.model.SUBFOR;
import com.deloitte.magazine.model.SubscriptionDuartion;
import com.deloitte.magazine.repository.SubscriptionDurationRepository;

import reactor.core.publisher.Flux;

@SpringBootTest
class SubscriptionDurationServiceTest {

	@Mock
	SubscriptionDurationRepository subscriptionDurationRepository;

	SubscriptionDurationService subscriptionDurationService;

	@BeforeEach
	void setUp() {
		subscriptionDurationService = new SubscriptionDurationService(subscriptionDurationRepository);
	}

	@Test
	void fetchSubscriptionDuration_test() {
		when(subscriptionDurationRepository.findAll())
				.thenReturn(Flux.just(new SubscriptionDuartion(SUBFOR.ONE_MONTH)));
		Flux<SubscriptionDuartion> result = subscriptionDurationService.fetchSubscriptionDuration();
		assertEquals(1, result.collectList().block().size());

	}
}
