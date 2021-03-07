package com.needleinnovision.libraryapp.utils;

import org.springframework.security.crypto.bcrypt.BCrypt;

public class PasswordEncoderUtils {
	public static String encode(String password) {
		return BCrypt.hashpw(password, BCrypt.gensalt());
	}
}
