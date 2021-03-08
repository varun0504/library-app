package com.needleinnovision.libraryapp.controller;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.needleinnovision.libraryapp.service.UserService;

@RestController
@RequestMapping("/admin")
public class AdminController {
	
	private static final Logger logger = LoggerFactory.getLogger(AdminController.class);
	
	@Autowired
	private ModelMapper modelmapper;
	
	@Autowired
	private UserService userService;
	
	/*@RequestMapping(value = "/librarian/register", method = RequestMethod.POST)
	public AppResponse registerUser(@RequestBody UserCreationRequest registerUserRequest,
			HttpServletResponse response){
		logger.info("registerUser: started");
		
		AppResponse appResponse = null;
		UserRegistrationBo registerUserBo = null;
		try {
			ValidatorFactory.getUserCreationValidator().validate(registerUserRequest);
			
			registerUserBo = new UserRegistrationBo();
			modelmapper.map(registerUserRequest, registerUserBo);
			registerUserBo.setRole("ROLE_LIBRARIN");
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
	}*/
}
