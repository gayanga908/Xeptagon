package com.test.xeptagon.services;

import com.test.xeptagon.repositories.UserRepository;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.security.auth.login.CredentialNotFoundException;
import java.util.List;

@Service
public class JwtUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    public JwtUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    public void authenticateUser(String username, String password) throws UsernameNotFoundException, CredentialNotFoundException {
        com.test.xeptagon.entities.User user = userRepository.findUserByUserName(username);
        if (user == null) {
            throw new UsernameNotFoundException("User not found with username: " + username);
        }
        if (!user.getPassword().equals(DigestUtils.sha256Hex(password))) {
            throw new BadCredentialsException("INVALID_CREDENTIALS");
        }
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        com.test.xeptagon.entities.User user = userRepository.findUserByUserName(username);
        if (user == null) {
            throw new UsernameNotFoundException("User not found with username: " + username);
        }
        return new User(user.getUserName(), user.getPassword(),
                List.of(new SimpleGrantedAuthority(user.getUserType())));
    }
}
