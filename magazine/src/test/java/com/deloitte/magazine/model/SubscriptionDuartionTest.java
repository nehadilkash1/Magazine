package com.deloitte.magazine.model;

import org.junit.jupiter.api.Test;

import nl.jqno.equalsverifier.EqualsVerifier;

class SubscriptionDuartionTest {
	@Test
	public void testPojo() {
		EqualsVerifier.simple().forClass(SubscriptionDuartion.class).verify();
	}
}
