package com.needleinnovision.libraryapp.validators;

import com.needleinnovision.libraryapp.exception.AppException;
import com.needleinnovision.libraryapp.exception.ErrorDetails;
import com.needleinnovision.libraryapp.request.UserCreationRequest;

public class EmailIdValidator implements Validators {

	// We can further abstract the validating of emailId into different Validators as
	// doing it in single method are violating SOLID principles, but doing it as it is
	// due to time crunch
	@Override
	public void validate(UserCreationRequest request) throws AppException {
		String emailId = request.getEmailId();
		if(emailId == null || emailId.trim().equals("")) return;
		boolean isValid = true;
		if(!emailId.contains("@")) isValid = false;
		String[] emailIdData = emailId.split("@");
		if(emailIdData.length > 2) isValid = false;
		if(emailIdData[0].trim().length() == 0 || emailIdData[0].trim().length() == 0) isValid = false;
		String domain = emailIdData[1];
		if(!domain.contains(".")) isValid = false;
		// To-Do later if time permits
		if(!isValid) {
			throw new AppException(new ErrorDetails(1009, 4, "data validation error", 
					"Invalid Email Id"));
		}
	}

}
