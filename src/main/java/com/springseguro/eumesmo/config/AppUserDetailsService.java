package com.springseguro.eumesmo.config;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.springseguro.eumesmo.entity.Role;
import com.springseguro.eumesmo.entity.User;
import com.springseguro.eumesmo.service.UserService;

@Service
public class AppUserDetailsService implements UserDetailsService{
	
	private UserService userService;	
	
	@Autowired
	public AppUserDetailsService(UserService userService) {
		this.userService = userService;
	}
	
	@Override
	@Transactional
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userService.findUserByUserName(username);
		List<GrantedAuthority> authorities = getUserAuthority(user.getRoles());
		return buildUserForAuthentication(user, authorities);
	}
	
	private List<GrantedAuthority> getUserAuthority(Set<Role> userRoles){
		Set<GrantedAuthority> roles = new HashSet<>();
		for(Role role : userRoles) {
			roles.add(new SimpleGrantedAuthority(role.getRole()));
		}
		List<GrantedAuthority> grantedAuthorities = new ArrayList<>(roles);
		return grantedAuthorities;
	}
	
	private UserDetails buildUserForAuthentication(User user, List<GrantedAuthority> authorities) {
		return new org.springframework.security.core.userdetails.User(
			user.getNome(), user.getPassword(), user.getActive(), true, true, true, authorities
		);
	}
	
}
