package com.security.service;

import com.security.entiy.SysPermission;
import com.security.entiy.SysRole;
import com.security.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        com.security.entiy.User userNew= userRepository.findByUserName(username);
        StringBuffer stringBuffer = new StringBuffer();
        for (SysRole sysRole : userNew.getRoleList()) {
            for (SysPermission sp : sysRole.getPermissions()) {
                stringBuffer.append(sp.getPermission() + ",");
            }
        }
        if (userNew != null) {
            return new User(userNew.getUserName(), userNew.getPassword(),
                    true, true, true, true
                    , AuthorityUtils.commaSeparatedStringToAuthorityList(stringBuffer.toString()));
        }
        throw new UsernameNotFoundException("username '" + username + "' not found");
    }
}
