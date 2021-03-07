package com.needleinnovision.libraryapp.validators;

import com.needleinnovision.libraryapp.exception.AppException;
import com.needleinnovision.libraryapp.exception.ErrorDetails;
import com.needleinnovision.libraryapp.request.UserCreationRequest;

public class MobileEmailIdValidator implements Validators {

	@Override
	public void validate(UserCreationRequest request) throws AppException {
		if((request.getEmailId() == null || request.getEmailId().trim().equals(""))
				&& (request.getMobileNo() == null || request.getMobileNo().trim().equals(""))) {
			throw new AppException(new ErrorDetails(1007, 4, "data validation error", 
					"Both Mobile number and Email Id cannot be empty"));
		}
		
	}

}
