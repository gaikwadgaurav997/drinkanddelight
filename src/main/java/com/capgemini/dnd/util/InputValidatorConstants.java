package com.capgemini.dnd.util;

public class InputValidatorConstants {
	public static final char PHONENO_VALIDATION_CHECK_DIGIT_ZERO = '0';
	public static final char PHONENO_VALIDATION_CHECK_DIGIT_NINE = '9';
	public static final String EMAILVALIDATOR_PATTERN_CHECK = "^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$";
	public static final String INVALID_EMAILID_EXCEPTION_MESSAGE = "E-mail ID has wrong format!!!";
	public static final String EMPTY_INPUT_EXCEPTION_MESSAGE = "This input cannot be empty!!!";

	public static final String GENDER_VALIDATOR_CHECK_M = "m";
	public static final String GENDER_VALIDATOR_CHECK_MALE = "male";
	public static final String GENDER_VALIDATOR_CHECK_F = "f";
	public static final String GENDER_VALIDATOR_CHECK_FEMALE = "female";
	public static final char GENDER_VALIDATOR_CHECK_O = 'o';
	public static final String GENDER_VALIDATOR_CHECK_OTHERS = "others";
	public static final String INVALID_GENDER_EXCEPTION_MESSAGE = "Invalid gender entered!!!";
	public static final String PHONE_NO_VALIDATOR_PATTERN_CHECK = "^^[789]\\d{9}$";
	public static final String PHONE_NO_EXCEPTION_MESSAGE = "Phone No entered is incorrect!!!";
	public static final String USER_NAME_VALIDATOR_PATTERN_CHECK = "^[A-Za-z0-9_]{5,15}$";
	public static final String USER_NAME_EXCEPTION_MESSAGE= "Username entered is invalid";
	public static final String PASSWORD_VALIDATOR_PATTERN_CHECK = "((?=.*[a-z])(?=.*d)(?=.*[@#$%])(?=.*[A-Z]).{8,20})";     
	public static final String INVALID_PASSWORD_EXCEPTION_MESSAGE = "Password entered  is invalid!!!";
	public static final String FULL_NAME_VALIDATOR_PATTERN_CHECK = "^[\\p{L} .'-]+$";
	public static final String FULL_NAME_EXCEPTION_MESSAGE = "Password entered  is invalid!!!";

}