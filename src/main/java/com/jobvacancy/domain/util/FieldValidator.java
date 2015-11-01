package com.jobvacancy.domain.util;

import java.util.regex.Matcher;

import java.util.regex.Pattern;

public class FieldValidator {

	private static final String REGULAR_EXPRESSION_EMAIL = "^[a-z0-9!#$%&'*+\\/=?^_`{|}~.-]+@[a-z0-9]([a-z0-9-]*[a-z0-9])?(\\.[a-z0-9]([a-z0-9-]*[a-z0-9])?)*$";

	public static boolean validateEmail(String email) {

		Pattern pattern = Pattern.compile(REGULAR_EXPRESSION_EMAIL);

		if (email != null) {

			Matcher matcher = pattern.matcher(email.toLowerCase());
			return matcher.matches();
		} else {
			return false;
		}
	}

	public static boolean validateUrl(String url) {
		Pattern urlPattern = Pattern.compile(
				"((https?|ftp|gopher|telnet|file):((//)|(\\\\\\\\))+[\\\\w\\\\d:#@%/;$()~_?\\\\+-=\\\\\\\\\\\\.&]*)",
				Pattern.CASE_INSENSITIVE);
		Matcher matcher = urlPattern.matcher(url);
		if (matcher.find()) {
			return true;
		} else {
			return false;
		}
	}
}
