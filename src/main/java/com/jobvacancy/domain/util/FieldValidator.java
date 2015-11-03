package com.jobvacancy.domain.util;

import java.util.regex.Matcher;

import java.util.regex.Pattern;

public class FieldValidator {

	private static final String REGULAR_EXPRESION_URL = "((https?|ftp|gopher|telnet|file):((//)|(\\\\\\\\))+[\\\\w\\\\d:#@%/;$()~_?\\\\+-=\\\\\\\\\\\\.&]*)";
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
		Pattern urlPattern = Pattern.compile(REGULAR_EXPRESION_URL,	Pattern.CASE_INSENSITIVE);
		
		
		if(url != null){
			Matcher matcher = urlPattern.matcher(url);
		
			return matcher.find();
		}else{
			return false;
		}
	}
}
