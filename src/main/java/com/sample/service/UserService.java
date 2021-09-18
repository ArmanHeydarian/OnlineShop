package com.sample.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sample.domain.dto.UserDto;
import com.sample.domain.model.UserModel;
import com.sample.domain.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ObjectMapper jacksonObjectMapper;



    public String addUserToDb(UserDto userDto) {
        UserModel user= jacksonObjectMapper.convertValue(userDto, new TypeReference<UserModel>(){});
        Date date =new Date();
        user.setCreateDate(date);
        userRepository.save(user);
        return "Saved";

    }


}