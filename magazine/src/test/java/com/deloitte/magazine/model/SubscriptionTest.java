package com.deloitte.magazine.model;

import org.junit.jupiter.api.Test;

import nl.jqno.equalsverifier.EqualsVerifier;

class SubscriptionTest {

	@Test
	public void testPojo() {
		EqualsVerifier.simple().forClass(Subscription.class).verify();
	}

}
