package com.utkarsh.ecombackend.service;

import com.utkarsh.ecombackend.Model.UserModel;
import com.utkarsh.ecombackend.Repo.UserRepo;
import com.utkarsh.ecombackend.dto.OtpDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class UserService {
    private final UserRepo userRepo;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;
    private final OtpService otpService;
    private final EmailService emailService;

    public UserService(UserRepo userRepo, AuthenticationManager authenticationManager, JwtService jwtService, OtpService otpService, EmailService emailService) {
        this.userRepo = userRepo;
        this.authenticationManager = authenticationManager;
        this.jwtService = jwtService;
        this.otpService = otpService;
        this.emailService = emailService;
    }

    BCryptPasswordEncoder encoder=new BCryptPasswordEncoder(5);
    public ResponseEntity<?> registerIntent(UserModel user) {
        if(userRepo.findByUsername(user.getUsername())!=null){
            log.info("user Already exists");
            return ResponseEntity.ok()
                    .body("user Already exists");
        }else {
            String otp=otpService.generateOtp(user.getUsername());
            emailService.sendOtp(user.getUsername(),otp);
            return ResponseEntity.ok()
                    .body("Otp sent");
        }

    }

    public ResponseEntity<?> verify(UserModel user) {
        Authentication authentication=authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(user.getUsername(),user.getPassword()));
        String token=jwtService.generate(user.getUsername());
        return ResponseEntity.ok()
                .body(token);
    }

    public ResponseEntity<?> register(OtpDto otpDto) {
        if(otpService.verify(otpDto.getUsername(),otpDto.getOtp())){
            UserModel user=new UserModel();
            user.setUsername(otpDto.getUsername());
            user.setPassword(encoder.encode(otpDto.getPassword()));
            userRepo.save(user);
            return ResponseEntity.ok()
                    .body(user);
        }
        return ResponseEntity.ok()
                .body("Registration Failed");
    }
}
