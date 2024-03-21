package com.dxc.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public interface UserService extends UserDetailsService{

UserDetails loadUserByUsername(String username) throws UsernameNotFoundException;

<<<<<<< HEAD
 UserDetailsService userDetailsService();
 

=======
>>>>>>> 9083e49908be6b2278f251a13d4aec9b7b969535
}
