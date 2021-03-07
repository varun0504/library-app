package com.needleinnovision.libraryapp.validators;

import java.util.ArrayList;
import java.util.List;

public class ValidatorFactory {
	public static Validators getUserCreationValidator() {
		List<String> nonNullableFields = new ArrayList<>();
		nonNullableFields.add("firstName");
		nonNullableFields.add("username");
		nonNullableFields.add("password");
		
		List<Validators> constraintsValidation = new ArrayList<>();
		constraintsValidation.add(new MobileEmailIdValidator());
		constraintsValidation.add(new MobileNumberValidator());
		constraintsValidation.add(new EmailIdValidator());
		
		return new UserCreationValidator(nonNullableFields, constraintsValidation);
	}
}
