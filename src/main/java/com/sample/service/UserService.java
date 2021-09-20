package com.sample.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sample.domain.dto.UserDto;
import com.sample.domain.model.UserModel;
import com.sample.domain.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ObjectMapper jacksonObjectMapper;



    public ResponseEntity<String> addUserToDb(UserDto userDto) throws  Exception {
        UserModel user= jacksonObjectMapper.convertValue(userDto, new TypeReference<UserModel>(){});
        Date date =new Date();
        user.setCreateDate(date);
        userRepository.save(user);
        return ResponseEntity.ok("new User Saved Successfully");
    }

    public ResponseEntity<String> blockUser (int userId) throws Exception
    {
        Optional<UserModel> user = userRepository.findById(userId);
        if (user != null)
        {
            user.get().setBlocked(true);
            userRepository.save(user.get());
            return ResponseEntity.ok("User Has Been Blocked Successfully");
        }
        else
            return ResponseEntity.badRequest().body("User Id Not Found");
    }


    public ResponseEntity<?> unBlockUser(int userId) {
        Optional<UserModel> user = userRepository.findById(userId);
        if (user != null)
        {
            user.get().setBlocked(false);
            userRepository.save(user.get());
            return ResponseEntity.ok("User Has Been UnBlocked Successfully");
        }
        else
            return ResponseEntity.badRequest().body("User Id Not Found");
    }
}