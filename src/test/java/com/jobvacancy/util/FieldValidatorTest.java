package com.jobvacancy.util;

import org.junit.Assert;
import org.junit.Test;

import com.jobvacancy.domain.util.FieldValidator;

public class FieldValidatorTest {

	@Test
	public void whenVerifyAValidEmailThenResultIsTrue() {

		String[] validEmails = new String[] { "test@example.com", "asd@asd.comun", "test-1@example.com",
				"test.1@yahoo.com", "test1@example.com.ar", "test_1@example.com", "test-1@test.net",
				"test.1@example.com", "test@e.com", "test@1.com.ar", "test@example.com", "1@example.com",
				"test-1@example-test.com","example@te.st.coms.ar.c" };

		for (String validEmail : validEmails) {
			boolean isValid = FieldValidator.validateEmail(validEmail);
			Assert.assertTrue(isValid);
		}
	}

	@Test
	public void whenVerifyAInvalidEmailThenResultIsFalse() {

		String[] invalidEmails = new String[] { "example", "", "example@.com.com", "exampel1@.com", 
				"example**()@test.com", "example@%*.com","example.(.1@test.com","test@example_=1.com",
				"example@test@test.com", "example@&%#$%.c", "example@asd.", null };

		for (String invalidEmail : invalidEmails) {
			boolean isValid = FieldValidator.validateEmail(invalidEmail);
			Assert.assertFalse(isValid);
		}
	}
	
	@Test
	public void whenVerifyAValidUrlThenResultIsTrue() {

		String[] validUrls = new String[] { "https://www.google.com", "https://www.google.com/test", 
				"https://www.google.com/#$&$%&%/$", "https://www.test.com", "http://www.google.com"};

		for (String validUrl : validUrls) {
			boolean isValid = FieldValidator.validateUrl(validUrl);
			Assert.assertTrue(isValid);
		}
	}
	
	@Test
	public void whenVerifyAValidUrlThenResultIsFalse() {

		String[] validUrls = new String[] { "google.com", "http:test/test", 
				"www.google.com/#$&$%&%/$", "https:%$//w&ww.test.com", null};

		for (String validUrl : validUrls) {
			boolean isInvalid = FieldValidator.validateUrl(validUrl);
			Assert.assertFalse(isInvalid);
		}
	}
}
