package com.utkarsh.ecombackend.controller;

import com.utkarsh.ecombackend.Model.UserModel;
import com.utkarsh.ecombackend.dto.OtpDto;
import com.utkarsh.ecombackend.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/registerintent")
    public ResponseEntity<?> registerIntent(@RequestBody UserModel user){
        return userService.registerIntent(user);
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody OtpDto otpDto){
        return userService.register(otpDto);
    }

    @PostMapping("/login")
    public ResponseEntity<?> verify(@RequestBody UserModel user){
        return userService.verify(user);
    }
}
