package iblog.oauth2.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import iblog.oauth2.payload.LoginRequest;
import iblog.oauth2.payload.SignUpRequest;
import iblog.oauth2.service.UserService;


@RestController
@RequestMapping("/auth")
// @Profile("One") // for running multiple instances, testing client side load balancing using ribbon
public class AuthController {    
    
    @Autowired
    UserService userService;

    @PostMapping("/signin")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {
    	return userService.loginUser(loginRequest);
    }
    
    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@Valid @RequestBody SignUpRequest signUpRequest) {
          return userService.createUser(signUpRequest);

    }
}
