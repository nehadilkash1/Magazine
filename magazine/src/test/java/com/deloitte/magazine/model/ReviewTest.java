package com.deloitte.magazine.model;

import org.junit.jupiter.api.Test;

import nl.jqno.equalsverifier.EqualsVerifier;

class ReviewTest {
	@Test
	public void testPojo() {
		EqualsVerifier.simple().forClass(Review.class).verify();
	}

}
