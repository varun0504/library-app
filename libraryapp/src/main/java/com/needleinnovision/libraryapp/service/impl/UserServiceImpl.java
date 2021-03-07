package com.needleinnovision.libraryapp.service.impl;

import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.needleinnovision.libraryapp.bo.UserRegistrationBo;
import com.needleinnovision.libraryapp.entities.Roles;
import com.needleinnovision.libraryapp.entities.UserCredentials;
import com.needleinnovision.libraryapp.entities.UserEntity;
import com.needleinnovision.libraryapp.exception.AppException;
import com.needleinnovision.libraryapp.exception.ErrorDetails;
import com.needleinnovision.libraryapp.exception.ExceptionUtil;
import com.needleinnovision.libraryapp.repository.RoleRepository;
import com.needleinnovision.libraryapp.repository.UserCredentialsRepository;
import com.needleinnovision.libraryapp.repository.UserRepository;
import com.needleinnovision.libraryapp.service.UserService;
import com.needleinnovision.libraryapp.utils.DateUtils;
import com.needleinnovision.libraryapp.utils.PasswordEncoderUtils;

@Service
@Transactional(rollbackOn = Throwable.class)
public class UserServiceImpl implements UserService{

	private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private UserCredentialsRepository userCredentialsRepository;
	
	@Autowired
	private RoleRepository roleRepository;
	
	@Autowired
	private ModelMapper modelmapper;
	
	@Override
	public void registerUser(UserRegistrationBo userBo) throws Exception, AppException {
		logger.info("registerUser: started");
		
		try {
			// Checking business level validations
			checkIfMobileOrEmailIdExist(userBo.getMobileNo(), userBo.getEmailId());
			checkIfUsernameExist(userBo.getUsername());
			List<Roles> roles = checkIfRoleExist(userBo.getRole());
			
			// Create new User
			UserEntity user = createNewUser(userBo, roles);
			
			// Create new Credentials
			createNewCredentials(userBo, user);
		}
		catch(AppException ex) {
			logger.error("Business validation exception occured at register user service", ex);
			throw ex;
		}catch(Exception ex) {
			logger.error("Unexpected exception occured at register user service", ex);
			ExceptionUtil.handleException(ex);
		}
		
	}

	private void checkIfUsernameExist(String username) throws AppException {
		List<UserCredentials> usersCred = userCredentialsRepository.findByUsername(username);
		if(usersCred.size() > 0) {
			throw new AppException(new ErrorDetails(1012, 4, "data validation error", 
					"Username already exist"));
		}
	}

	private void createNewCredentials(UserRegistrationBo userBo, UserEntity user) {
		UserCredentials cred = new UserCredentials();
		cred.setUsername(userBo.getUsername());
		cred.setUser(user);
		cred.setPassword(PasswordEncoderUtils.encode(userBo.getPassword()));
		cred.setPasswordExpiryDate(DateUtils.getFutureDate(1));
		cred.setCreationDate(new Date());
		cred.setModificationDate(new Date());
		cred.setModifiedBy("Register Api");
		cred.setCreatedBy("Register Api");
		userCredentialsRepository.save(cred);
	}

	private UserEntity createNewUser(UserRegistrationBo userBo, List<Roles> roles) {
		UserEntity newUser = new UserEntity();
		modelmapper.map(userBo, newUser);
		newUser.setCreationDate(new Date());
		newUser.setModificationDate(new Date());
		newUser.setModifiedBy("Register Api");
		newUser.setCreatedBy("Register Api");
		newUser.setRoles(roles);
		return userRepository.save(newUser);
	}

	private List<Roles> checkIfRoleExist(String role) throws AppException {
		List<Roles> roles = roleRepository.findByName(role);
		if(roles.size() == 0) {
			throw new AppException(new ErrorDetails(1011, 4, "data validation error", 
					"Role does not exist"));
		}
		return roles;
	}

	private void checkIfMobileOrEmailIdExist(String mobileNo, String emailId) throws AppException {
		List<UserEntity> users;
		if(emailId == null) users = userRepository.findByMobileNo(mobileNo);
		else if(mobileNo == null) users = userRepository.findByEmailId(emailId);
		else users = userRepository.findByMobileNoOrEmailId(mobileNo, emailId);
		logger.debug("Users retrieved from database: "+users);
		if(users.size() > 0) {
			throw new AppException(new ErrorDetails(1010, 4, "data validation error", 
					"User already exists with same mobile number or email Id"));
		}
	}

}
