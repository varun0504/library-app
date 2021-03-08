package com.needleinnovision.libraryapp.validators;

import java.util.List;

import com.needleinnovision.libraryapp.exception.AppException;
import com.needleinnovision.libraryapp.request.UserCreationRequest;
import com.needleinnovision.libraryapp.utils.PropertyUtils;

public class UserCreationValidator implements Validators{
	
	private List<String> nonNullableFields; 
	private List<Validators> constraintsValidation;

	public UserCreationValidator(List<String> nonNullableFields, List<Validators> constraintsValidation) {
		this.nonNullableFields = nonNullableFields;
		this.constraintsValidation = constraintsValidation;
	}

	public void validate(UserCreationRequest request) throws AppException {
		for(String field: nonNullableFields) {
			CommonValidator.isNullOrEmpty(field, PropertyUtils.getProperty(request, field));
		}
		for(Validators validator: constraintsValidation) {
			validator.validate(request);
		}
	}
}
