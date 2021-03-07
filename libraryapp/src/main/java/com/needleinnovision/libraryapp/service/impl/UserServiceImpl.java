package com.needleinnovision.libraryapp.service.impl;

import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.needleinnovision.libraryapp.bo.UserRegistrationBo;
import com.needleinnovision.libraryapp.entities.Roles;
import com.needleinnovision.libraryapp.entities.UserCredentials;
import com.needleinnovision.libraryapp.entities.UserEntity;
import com.needleinnovision.libraryapp.exception.AppException;
import com.needleinnovision.libraryapp.exception.ErrorDetails;
import com.needleinnovision.libraryapp.exception.ExceptionUtil;
import com.needleinnovision.libraryapp.repository.RoleRepository;
import com.needleinnovision.libraryapp.repository.UserRepository;
import com.needleinnovision.libraryapp.service.UserService;

@Transactional(rollbackOn = Throwable.class)
public class UserServiceImpl implements UserService{

	private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private RoleRepository roleRepository;
	
	@Autowired
	private ModelMapper modelmapper;
	
	@Override
	public void registerUser(UserRegistrationBo userBo) throws Exception, AppException {
		logger.info("registerUser: started");
		
		try {
			// Checking business level validations
			checkIfUserExist(userBo.getMobileNo(), userBo.getEmailId(), userBo.getUsername());
			Roles role = checkIfRoleExist(userBo.getRole());
			
			// Create new User
			long userId = createNewUser(userBo);
			
			// Create new Credentials
			createNewCredentials(userBo, userId);
			
			
		}
		catch(AppException ex) {
			logger.error("Business validation exception occured at register user service", ex);
			throw ex;
		}catch(Exception ex) {
			logger.error("Unexpected exception occured at register user service", ex);
			ExceptionUtil.handleException(ex);
		}
		
	}

	private void createNewCredentials(UserRegistrationBo userBo, long userId) {
		UserCredentials cred = new UserCredentials();
		cred.setUsername(userBo.getUsername());
		cred.setUserId(userId);
		cred.setPassword(PasswordEncoderUtils.encode(userBo.getPassword()));
		cred.setPasswordExpiryDate(DateUtils.getFutureDate(new Date(), "day", 30));
		cred.setCreationDate(new Date());
		cred.setModificationDate(new Date());
		cred.setModifiedBy("Register Api");
		cred.setCreatedBy("Register Api");
	}

	private long createNewUser(UserRegistrationBo userBo) {
		UserEntity newUser = new UserEntity();
		modelmapper.map(userBo, newUser);
		newUser.setCreationDate(new Date());
		newUser.setModificationDate(new Date());
		newUser.setModifiedBy("Register Api");
		newUser.setCreatedBy("Register Api");
		return userRepository.save(newUser).getUserId();
	}

	private Roles checkIfRoleExist(String role) throws AppException {
		List<Roles> roles = roleRepository.findByName(role);
		if(roles.size() == 0) {
			throw new AppException(new ErrorDetails(1011, 4, "data validation error", 
					"Role does not exist"));
		}
		return roles.get(0);
	}

	private void checkIfUserExist(String mobileNo, String emailId, String username) throws AppException {
		List<UserEntity> users = userRepository.findByMobileNoOrEmailIdOrUsername(mobileNo, 
				emailId, username);
		if(users.size() > 0) {
			throw new AppException(new ErrorDetails(1010, 4, "data validation error", 
					"User already exists with same mobile number or email Id or username"));
		}
	}

}
