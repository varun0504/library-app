package com.needleinnovision.libraryapp.service;

import org.springframework.stereotype.Service;

import com.needleinnovision.libraryapp.bo.UserRegistrationBo;
import com.needleinnovision.libraryapp.exception.AppException;

@Service
public interface UserService {
	void registerUser(UserRegistrationBo userBo) throws Exception, AppException;
}
