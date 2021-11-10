package com.deloitte.magazine.model;

import org.junit.jupiter.api.Test;

import nl.jqno.equalsverifier.EqualsVerifier;

class SUBFORTest {

	 @Test
		public void testPojo() {
			EqualsVerifier.simple().forClass(Magazine.class).verify();
		}
}
