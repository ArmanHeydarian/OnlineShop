package com.sample.service;

import java.util.ArrayList;

import com.sample.domain.model.UserModel;
import com.sample.domain.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class JwtUserDetailsService implements UserDetailsService {

    @Autowired
    UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {

        UserModel dbUser= userRepository.findByUsername(userName);
        return new User(dbUser.getUsername(),dbUser.getPassword(),  new ArrayList<>());

    }




}