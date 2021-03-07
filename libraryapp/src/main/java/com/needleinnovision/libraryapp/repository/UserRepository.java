package com.needleinnovision.libraryapp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.needleinnovision.libraryapp.entities.UserEntity;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {
	List<UserEntity> findByMobileNoOrEmailId(String mobileNo, String emailId);
	List<UserEntity> findByMobileNo(String mobileNo);
	List<UserEntity> findByEmailId(String emailId);
}
