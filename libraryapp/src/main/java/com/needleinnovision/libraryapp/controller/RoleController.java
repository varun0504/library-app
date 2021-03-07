package com.needleinnovision.libraryapp.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.needleinnovision.libraryapp.LibraryappApplication;
import com.needleinnovision.libraryapp.entities.Roles;
import com.needleinnovision.libraryapp.repository.RoleRepository;

@RestController
@RequestMapping("/api/v1")
public class RoleController {
	
	@Autowired
	private RoleRepository roleRepository;
	
	private static final Logger logger = LoggerFactory.getLogger(LibraryappApplication.class);
	
	@GetMapping("/phones")
	public List<Roles> getAllRoles() {
		logger.info("Logger is working");
		return roleRepository.findAll();
	}

}
