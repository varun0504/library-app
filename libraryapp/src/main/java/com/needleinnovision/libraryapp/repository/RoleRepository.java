package com.needleinnovision.libraryapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.needleinnovision.libraryapp.entities.Roles;

@Repository
public interface RoleRepository extends JpaRepository<Roles, Long> {

}
