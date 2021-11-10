package com.deloitte.magazine.model;

import org.junit.jupiter.api.Test;

import nl.jqno.equalsverifier.EqualsVerifier;

class PersonTest {
	@Test
	public void testPojo() {
		EqualsVerifier.simple().forClass(Person.class).verify();
	}
}
