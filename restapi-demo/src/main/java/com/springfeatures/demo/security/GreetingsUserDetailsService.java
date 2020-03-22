package com.springfeatures.demo.security;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.EnvironmentAware;
import org.springframework.core.env.Environment;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Collections;

@Service
public class GreetingsUserDetailsService implements UserDetailsService, EnvironmentAware {

    private final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @Value("${username}")
    public String username;

    @Value("${password}")
    public String password;

    private UserDetails user;

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        if (user.getUsername().equals(userName)) {
            return new User(user.getUsername(), user.getPassword(),
                Arrays.asList(new SimpleGrantedAuthority("GREET_ADMIN")));
        } else {
            throw new UsernameNotFoundException(userName);
        }
    }

    @Override
    public void setEnvironment(Environment environment) {
        this.user = new User(username, passwordEncoder.encode(password), Collections.emptyList());

    }
}
