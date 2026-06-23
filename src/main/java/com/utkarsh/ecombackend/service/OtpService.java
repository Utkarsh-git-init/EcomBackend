package com.utkarsh.ecombackend.service;

import com.github.benmanes.caffeine.cache.Cache;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.util.concurrent.ThreadLocalRandom;

@Slf4j
@Service
public class OtpService {
    private final SecureRandom random=new SecureRandom();
    private final Cache<String,String> otpCache;

    public OtpService(Cache<String, String> otpCache) {
        this.otpCache = otpCache;
    }

    public String generateOtp(String email) {
        String otp=String.format("%06d", ThreadLocalRandom.current().nextInt(100000, 1000000));
        otpCache.put(email,otp);
        return otp;
    }

    public boolean verify(String username, String otp) {
        String storedOtp= otpCache.getIfPresent(username);
        if(storedOtp==null){
            log.info("no otp stored in cache");
            return false;
        }else{
            if(storedOtp.equals(otp)){
                otpCache.invalidate(username);
                return true;
            }
            return false;
        }
    }
}
