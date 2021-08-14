package com.springseguro.eumesmo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.springseguro.eumesmo.entity.Role;

public interface RoleRepository 
extends JpaRepository<Role, Integer>
{
	Role findByRole(String role);
}
