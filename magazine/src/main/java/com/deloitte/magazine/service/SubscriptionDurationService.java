package com.deloitte.magazine.service;

import java.util.LinkedList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Component;

import com.deloitte.magazine.model.SUBFOR;
import com.deloitte.magazine.model.SubscriptionDuartion;
import com.deloitte.magazine.repository.SubscriptionDurationRepository;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;

@Component
@Slf4j
public class SubscriptionDurationService {
	private final SubscriptionDurationRepository subscriptionDurationRepository;

	public SubscriptionDurationService(SubscriptionDurationRepository subscriptionDurationRepository) {
		this.subscriptionDurationRepository = subscriptionDurationRepository;
	}

	@PostConstruct
	public void saveSubscriptionDuration() {
		log.info("saving duration");
		List<SubscriptionDuartion> duartions = new LinkedList<>();
		for (SUBFOR subfor : SUBFOR.values()) {
			duartions.add(SubscriptionDuartion.builder().subfor(subfor).build());
		}
		subscriptionDurationRepository.saveAll(duartions).subscribe();

	}

	public Flux<SubscriptionDuartion> fetchSubscriptionDuration() {
		log.info("fetching duration");
		return subscriptionDurationRepository.findAll();

	}
}
