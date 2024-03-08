package com.dxc.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.dxc.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

	private final UserRepository userRepository = null;
	
	public UserDetailsService userDetailsService() {
		return new UserDetailsService() {
			
			@Override
			public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
			    return userRepository.findByEmail(username)
			            .orElseThrow(() -> new UsernameNotFoundException("User not found"));
			}

		};
	}

	@Override
	public UserDetailsService userDetailService() {
		// TODO Auto-generated method stub
		return null;
	}
	

}
