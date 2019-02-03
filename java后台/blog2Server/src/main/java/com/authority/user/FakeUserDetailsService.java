package com.authority.user;

import com.authority.authorization.RoleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Arrays;

public class FakeUserDetailsService implements UserDetailsService {

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		if ("admin".equals(username)) {
			return new User("admin", "123", Arrays.asList(new RoleGrantedAuthority("ROLE_Admin")));
		}
		throw new UsernameNotFoundException("username '" + username + "' not found");
	}

}
