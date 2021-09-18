package com.sample.api;

import com.sample.api.security.JwtTokenUtil;
import com.sample.domain.dto.AuthenticationRequest;
import com.sample.domain.dto.AuthenticatonResponse;
import com.sample.domain.dto.ProductDto;
import com.sample.domain.dto.UserDto;
import com.sample.service.JwtUserDetailsService;
import com.sample.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path="/api/user")
public class UserController {

    @Autowired
    private AuthenticationManager  authenticationManager;
    @Autowired
    private JwtUserDetailsService userDetailsService;

    @Autowired
    private UserService userService;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

   @RequestMapping(value="/auth",method =  RequestMethod.POST)
   public @ResponseBody
   ResponseEntity<?> auth (@RequestBody AuthenticationRequest  authenticationRequest ) throws  Exception {
       try {
           authenticationManager.authenticate( new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername(), authenticationRequest.getPassword()));
       }
       catch (BadCredentialsException e) {
           throw new Exception( "Incorrect Username and Password", e );
       }
       final UserDetails userDetails = userDetailsService.loadUserByUsername(authenticationRequest.getUsername());
       final String token = jwtTokenUtil.generateToken(userDetails);
       return ResponseEntity.ok(new AuthenticatonResponse(token));
   }

    //-------------------------------------------------------------------------------------------------

    @RequestMapping(value="/signup",method =  RequestMethod.POST)
    public @ResponseBody
    String signUp (@RequestBody UserDto userDto ) throws  Exception {
        return userService.addUserToDb(userDto);
    }

}
