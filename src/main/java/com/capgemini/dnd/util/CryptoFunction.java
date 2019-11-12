package com.capgemini.dnd.util;

import java.nio.charset.StandardCharsets;
import java.security.SecureRandom;
import java.util.Arrays;
import java.util.Random;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;

import com.capgemini.dnd.entity.EmployeeCredentialEntity;

public class CryptoFunction {
	private static final Random RANDOM = new SecureRandom();
	private static final int ITERATIONS = 10000;
	private static final int KEY_LENGTH = 256;

	private CryptoFunction() {
		
	}

	public static String getNextSalt() {
		byte[] salt = new byte[16];
		RANDOM.nextBytes(salt);
		String saltStr = new String(salt, StandardCharsets.UTF_8);

		return saltStr;
	}

	public static String hash(String pass, String saltStr) {
		byte[] salt = saltStr.getBytes(StandardCharsets.UTF_8);
		char[] password = pass.toCharArray();
		PBEKeySpec spec = new PBEKeySpec(password, salt, ITERATIONS, KEY_LENGTH);
		Arrays.fill(password, Character.MIN_VALUE);
		try {
			SecretKeyFactory skf = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
			String hashStr = new String(skf.generateSecret(spec).getEncoded(), StandardCharsets.UTF_8);
			return hashStr;
		} catch (Exception exception) {
			throw new AssertionError("Error while hashing a password: " + exception.getMessage(), exception);
		} finally {
			spec.clearPassword();
		}
	}

	public static boolean isExpectedPassword(String pass,  EmployeeCredentialEntity empCredential) {
		byte[] expectedHash = empCredential.getHash().getBytes(StandardCharsets.UTF_8);
		byte[] pwdHash = hash(pass, empCredential.getSalt()).getBytes(StandardCharsets.UTF_8);
		if (pwdHash.length != expectedHash.length)
			return false;
		for (int i = 0; i < pwdHash.length; i++) {
			if (pwdHash[i] != expectedHash[i])
				return false;
		}
		return true;
	}
	
	public static boolean isExpectedPassword(String pass,  String hash, String salt) {
		byte[] expectedHash = hash.getBytes(StandardCharsets.UTF_8);
		byte[] pwdHash = hash(pass, salt).getBytes(StandardCharsets.UTF_8);
		if (pwdHash.length != expectedHash.length)
			return false;
		for (int i = 0; i < pwdHash.length; i++) {
			if (pwdHash[i] != expectedHash[i])
				return false;
		}
		return true;
	}
}
