package com.springseguro.eumesmo.service;

import java.util.Arrays;
import java.util.HashSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.springseguro.eumesmo.entity.Role;
import com.springseguro.eumesmo.entity.User;
import com.springseguro.eumesmo.repository.RoleRepository;
import com.springseguro.eumesmo.repository.UserRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class UserService {
	private UserRepository userRepository;
    private RoleRepository roleRepository;
   
    public User findUserByEmail(String email) {
    	return userRepository.findByEmail(email);
    }
    
    public User findUserByUserName(String name) {
    	return userRepository.findByNome(name);
    }
    
    public User saveUser(User user) {
    	user.setPassword(bcryptEncoder().encode(user.getPassword()));
    	Role userRole = roleRepository.findByRole("PG_REL_EQUIPE");
    	user.setActive(true);
    	user.setRoles(new HashSet<Role>(Arrays.asList(userRole)));
    	return userRepository.save(user);
    }
    
    @Bean
    private BCryptPasswordEncoder bcryptEncoder() {
    	return new BCryptPasswordEncoder();
    }
}
