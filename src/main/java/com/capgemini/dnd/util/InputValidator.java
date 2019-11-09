package com.capgemini.dnd.util;

import java.util.regex.Pattern;

import com.capgemini.dnd.customexceptions.EmptyInputException;
import com.capgemini.dnd.customexceptions.FullNameException;
import com.capgemini.dnd.customexceptions.InvalidEmailIdException;
import com.capgemini.dnd.customexceptions.InvalidGenderException;
import com.capgemini.dnd.customexceptions.PasswordException;
import com.capgemini.dnd.customexceptions.PhoneNoException;
import com.capgemini.dnd.customexceptions.UserNameException;

public class InputValidator {

	public InputValidator() {

	}

	public static void emailIdValidator(String emailId) throws InvalidEmailIdException {
		String regex = InputValidatorConstants.EMAILVALIDATOR_PATTERN_CHECK;
		if (!Pattern.compile(regex).matcher(emailId).matches())
			throw new InvalidEmailIdException(InputValidatorConstants.INVALID_EMAILID_EXCEPTION_MESSAGE);
	}

	public static void notEmptyValidator(String input) throws EmptyInputException {
		input = input.trim();
		if (input.isEmpty())
			throw new EmptyInputException(InputValidatorConstants.EMPTY_INPUT_EXCEPTION_MESSAGE);
	}

	@SuppressWarnings("unlikely-arg-type")
	public static void genderValidator(String gender) throws InvalidGenderException {
		gender = gender.toLowerCase();
		if (gender.equals(InputValidatorConstants.GENDER_VALIDATOR_CHECK_M)
				|| gender.equals(InputValidatorConstants.GENDER_VALIDATOR_CHECK_MALE)
				|| gender.equals(InputValidatorConstants.GENDER_VALIDATOR_CHECK_F)
				|| gender.equals(InputValidatorConstants.GENDER_VALIDATOR_CHECK_FEMALE)
				|| gender.equals(InputValidatorConstants.GENDER_VALIDATOR_CHECK_O)
				|| gender.equals(InputValidatorConstants.GENDER_VALIDATOR_CHECK_OTHERS)) {
		} else
			throw new InvalidGenderException(InputValidatorConstants.INVALID_GENDER_EXCEPTION_MESSAGE);
	}

	/*
	 * (?=.*[a-z]) : This matches the presence of at least one lowercase letter.
	 * (?=.*d) : This matches the presence of at least one digit i.e. 0-9.
	 * (?=.*[@#$%]) : This matches the presence of at least one special character.
	 * ((?=.*[A-Z]) : This matches the presence of at least one capital letter.
	 * {6,16} : This limits the length of password from minimum 6 letters to maximum
	 * 16 letters.
	 */

	public static boolean passwordValidator(String password) throws PasswordException {
		boolean validPassword = false;
		String regex = InputValidatorConstants.PASSWORD_VALIDATOR_PATTERN_CHECK;
		if (!Pattern.compile(regex).matcher(password).matches())
			throw new PasswordException(InputValidatorConstants.INVALID_PASSWORD_EXCEPTION_MESSAGE);
		else {
			validPassword = true;
		}
		return validPassword;
	}

	public static void userNameValidator(String userName) throws UserNameException {
		String regex = InputValidatorConstants.USER_NAME_VALIDATOR_PATTERN_CHECK;
		if (!Pattern.compile(regex).matcher(userName).matches())
			throw new UserNameException(InputValidatorConstants.USER_NAME_EXCEPTION_MESSAGE);

	}

	public static void phoneNoValidator(String phoneNo) throws PhoneNoException {
		String regex = InputValidatorConstants.PHONE_NO_VALIDATOR_PATTERN_CHECK;
		if (!Pattern.compile(regex).matcher(phoneNo).matches())
			throw new PhoneNoException(InputValidatorConstants.PHONE_NO_EXCEPTION_MESSAGE);

	}

	public static void fullNameValidator(String fullName) throws FullNameException {
		String regex = InputValidatorConstants.FULL_NAME_VALIDATOR_PATTERN_CHECK;
		if (!Pattern.compile(regex).matcher(fullName).matches())
			throw new FullNameException(InputValidatorConstants.FULL_NAME_EXCEPTION_MESSAGE);

	}

}
