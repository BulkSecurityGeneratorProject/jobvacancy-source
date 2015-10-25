package com.jobvacancy.domain.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FieldValidator {

	
	 private static final String REGULAR_EXPRESSION_EMAIL = "^[_a-z0-9-]+(\\.[_a-z0-9-]+)*@"+
			 		"[a-z0-9-]+(\\.[a-z0-9-]+)*(\\.[a-z]{2,3})$";
	 
	public static boolean validateEmail(String email) {
		
		Pattern pattern = Pattern.compile(REGULAR_EXPRESSION_EMAIL);
		
		Matcher matcher = pattern.matcher(email.toLowerCase());
		return matcher.matches();
	}

}
