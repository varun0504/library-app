package com.needleinnovision.libraryapp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.needleinnovision.libraryapp.entities.UserCredentials;

@Repository
public interface UserCredentialsRepository extends JpaRepository<UserCredentials, Long> {
	List<UserCredentials> findByUsername(String username); 
}
