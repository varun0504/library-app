package com.needleinnovision.libraryapp.utils;

import org.mindrot.jbcrypt.BCrypt;

public class PasswordEncoderUtils {
	public static String encode(String password) {
		return BCrypt.hashpw(password, BCrypt.gensalt());
	}
}
