package com.jobvacancy.util;

import org.junit.Assert;
import org.junit.Test;

import com.jobvacancy.domain.util.FieldValidator;

public class FieldValidatorTest {

	@Test
	public void whenVerifyAValidEmailThenResultIsTrue() {

		String[] validEmails = new String[] { "test@example.com", "asd@asd.com.ar", "test-1@example.com",
				"test.1@yahoo.com", "test1@example.com", "test_1@example.com", "test-1@test.net",
				"test.1@example.com.au", "test@e.com", "test@1.com", "test@example.com.com", "1@example.com",
				"test-1@example-test.com" };

		for (String validEmail : validEmails) {
			boolean isValid = FieldValidator.validateEmail(validEmail);
			Assert.assertTrue(isValid);
		}
	}

	@Test
	public void whenVerifyAInvalidEmailThenResultIsFalse() {

		String[] invalidEmails = new String[] { "example", "", "example@.com.com", "exampel1@.com", ".example@test.com",
				"example**()@test.com", "example@%*.com", "example..1@test.com", "example.@test.com",
				"test@example_1.com", "example@test@test.com", "example@test.com.a5" };

		for (String invalidEmail : invalidEmails) {
			boolean isValid = FieldValidator.validateEmail(invalidEmail);
			Assert.assertFalse(isValid);
		}
	}
}
