package com.needleinnovision.libraryapp.controller;

import javax.servlet.http.HttpServletResponse;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.needleinnovision.libraryapp.bo.UserRegistrationBo;
import com.needleinnovision.libraryapp.exception.AppException;
import com.needleinnovision.libraryapp.exception.ErrorDetails;
import com.needleinnovision.libraryapp.request.UserCreationRequest;
import com.needleinnovision.libraryapp.response.AppResponse;
import com.needleinnovision.libraryapp.response.ResponseBuilder;
import com.needleinnovision.libraryapp.service.UserService;
import com.needleinnovision.libraryapp.validators.ValidatorFactory;

@RestController
@RequestMapping("/user")
public class UserController {
	private static final Logger logger = LoggerFactory.getLogger(UserController.class);
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private ModelMapper modelmapper;
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String defaultPage(){
		return "Welcome to Library App";
	}
	
	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public AppResponse registerUser(@RequestBody UserCreationRequest registerUserRequest,
			HttpServletResponse response){
		logger.info("registerUser: started");
		
		AppResponse appResponse = null;
		UserRegistrationBo registerUserBo = null;
		try {
			ValidatorFactory.getUserCreationValidator().validate(registerUserRequest);
			
			registerUserBo = new UserRegistrationBo();
			modelmapper.map(registerUserRequest, registerUserBo);
			registerUserBo.setRole("ROLE_CUSTOMER");
			userService.registerUser(registerUserBo);
			
			appResponse = ResponseBuilder.getSuccessResponse();
		}
		catch (AppException ex) {
			appResponse = ResponseBuilder.getErrorResponse(ex.getErrorDetails(), response);
		}
		catch (Throwable ex) {
			logger.info("Error occured in registerUser", ex);
			
			ErrorDetails errorDetails = new ErrorDetails(5000,2,"error","Internal Server Error");
			appResponse = ResponseBuilder.getErrorResponse(errorDetails, response);
		}
		logger.info("registerUser: ended");
		return appResponse;
	}
}
