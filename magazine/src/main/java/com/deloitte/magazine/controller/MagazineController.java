package com.deloitte.magazine.controller;

import java.util.List;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.deloitte.magazine.model.Magazine;
import com.deloitte.magazine.model.Subscription;
import com.deloitte.magazine.model.SubscriptionDuartion;
import com.deloitte.magazine.service.MagazineService;
import com.deloitte.magazine.service.SubscriptionDurationService;
import com.deloitte.magazine.service.SubscriptionService;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@CrossOrigin
@RestController
@Slf4j
public class MagazineController {

	private final MagazineService magazineService;
	public final SubscriptionService subscriptionService;
	private final SubscriptionDurationService subscriptionDurationService;

	public MagazineController(MagazineService magazineService, SubscriptionService subscriptionService,
			SubscriptionDurationService subscriptionDuartionService) {
		this.magazineService = magazineService;
		this.subscriptionService = subscriptionService;
		this.subscriptionDurationService = subscriptionDuartionService;
	}

	@GetMapping(path = "/magazines")
	public Flux<Magazine> getFilteredMagazines(@RequestParam String category, @RequestParam String magazineName,
			@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "3") int size) {

		log.info("getFilteredMagazines");
		Pageable paging = PageRequest.of(page, size);

		if (StringUtils.hasText(category) && StringUtils.hasText(magazineName)) {
			return magazineService.getFilteredMagazines(category, magazineName);
		} else if (StringUtils.hasText(category)) {
			return magazineService.getFilteredMagazinesByCategory(category, paging);
		} else if (StringUtils.hasText(magazineName)) {
			return magazineService.getFilteredMagazinesByName(magazineName);
		} else {
			return magazineService.getAllMagazines(paging);
		}
	}

	@GetMapping(path = "/magazines/categories")
	public Mono<List<String>> getAllCategories() {
		return magazineService.getCategories();

	}

	@PostMapping("/magazine/subscription")
	public Mono<Subscription> subscribeMagazine(@RequestBody Subscription subscription) {
		return subscriptionService.subscribeMagazine(subscription);
	}

	@GetMapping("/magazine/subscription")
	public Flux<Subscription> fetchSubscribedMagazine(@RequestParam(defaultValue = "0") int page,
			@RequestParam(defaultValue = "3") int size) {
		Pageable paging = PageRequest.of(page, size);

		return subscriptionService.fetchSubscribedMagazine(paging);
	}

	@GetMapping("/magazine/nosubscription")
	public Flux<Magazine> fetchNotSubscribedMagazine(@RequestParam(defaultValue = "0") int page,
			@RequestParam(defaultValue = "3") int size) {
		Pageable paging = PageRequest.of(page, size);

		return subscriptionService.fetchNotSubscribedMagazine(paging);
	}

	@GetMapping("/magazine/subscription/duration")
	public Flux<SubscriptionDuartion> fetchSubscriptionDuration() {
		return subscriptionDurationService.fetchSubscriptionDuration();
	}

}
