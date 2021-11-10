package com.deloitte.magazine.service;

import java.time.LocalDate;

import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.deloitte.magazine.model.Magazine;
import com.deloitte.magazine.model.Subscription;
import com.deloitte.magazine.repository.MagazineRepository;
import com.deloitte.magazine.repository.SubscriptionRepository;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@Slf4j
public class SubscriptionService {
	private final SubscriptionRepository subscriptionRepository;
	private final MagazineRepository magazineRepository;

	public SubscriptionService(SubscriptionRepository subscriptionRepository, MagazineRepository magazineRepository) {
		this.subscriptionRepository = subscriptionRepository;
		this.magazineRepository = magazineRepository;

	}

	public Mono<Subscription> subscribeMagazine(Subscription subscription) {

		log.info("subscribing for magazine : {} ", subscription.getMagazine().getName());
		switch (subscription.getSubscriptionPeriod()) {
		case ONE_WEEK:
			subscription.setExpiryDate(LocalDate.now().plusWeeks(1));
			break;
		case ONE_MONTH:
			subscription.setExpiryDate(LocalDate.now().plusMonths(1));
			break;
		case SIX_MONTH:
			subscription.setExpiryDate(LocalDate.now().plusMonths(6));
			break;
		case ONE_YEAR:
			subscription.setExpiryDate(LocalDate.now().plusYears(1));
			break;
		default:
			break;
		}

		return subscriptionRepository.save(subscription);

	}

	public Flux<Subscription> fetchSubscribedMagazine(Pageable pageable) {

		log.info("fetching subscribed magazine");
		return subscriptionRepository.findByIdNotNull(pageable).flatMap(subscription -> Flux.just(subscription)
				.zipWith(magazineRepository.findById(subscription.getMagazine().getId()), (s, m) -> {
					s.setMagazine(m);
					return s;
				}));
	}

	public Flux<Magazine> fetchNotSubscribedMagazine(Pageable pageable) {

		log.info("fetching not subscribed magazine");
		return magazineRepository.findByIdNotIn(
				subscriptionRepository.findAll().map(subscription -> subscription.getMagazine().getId()), pageable);

	}

}
