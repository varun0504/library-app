package com.needleinnovision.libraryapp.validators;

import com.needleinnovision.libraryapp.exception.AppException;
import com.needleinnovision.libraryapp.exception.ErrorDetails;

public class CommonValidator {
	public static void isNullOrEmpty(String fieldName, Object value) throws AppException {
		if(value == null || (value instanceof String && ((String) value).trim().equals("")))
			throw new AppException(new ErrorDetails(1006, 4, "data validation error", 
					fieldName+" cannot be empty"));
	}
}
