package com.needleinnovision.libraryapp.validators;

import com.needleinnovision.libraryapp.exception.AppException;
import com.needleinnovision.libraryapp.request.UserCreationRequest;

public interface Validators {
	void validate(UserCreationRequest request) throws AppException;
}
