package com.needleinnovision.libraryapp.service;

import org.springframework.stereotype.Service;

import com.needleinnovision.libraryapp.bo.UserRegistrationBo;

@Service
public interface UserService {
	void registerUser(UserRegistrationBo userBo);
}
