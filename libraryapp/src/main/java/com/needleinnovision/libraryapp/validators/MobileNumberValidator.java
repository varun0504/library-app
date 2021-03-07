package com.needleinnovision.libraryapp.validators;

import com.needleinnovision.libraryapp.exception.AppException;
import com.needleinnovision.libraryapp.exception.ErrorDetails;
import com.needleinnovision.libraryapp.request.UserCreationRequest;

public class MobileNumberValidator implements Validators{

	@Override
	public void validate(UserCreationRequest request) throws AppException {
		String mobileNo = request.getMobileNo();
		if(mobileNo == null || mobileNo.trim().equals("")) return;
		if(!request.getMobileNo().matches("[0-9]+") || request.getMobileNo().length() != 10) {
			throw new AppException(new ErrorDetails(1008, 4, "data validation error", 
					"Invalid Mobile Number"));
		}
	}
}
