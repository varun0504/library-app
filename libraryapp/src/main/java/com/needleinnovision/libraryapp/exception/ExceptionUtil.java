package com.needleinnovision.libraryapp.exception;

import org.springframework.stereotype.Component;

@Component
public class ExceptionUtil{
	public static void handleException(Exception ex) throws Exception{
		// save the exception to DB or send a alert
		throw ex;
	}
}