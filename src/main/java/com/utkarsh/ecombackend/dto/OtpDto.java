package com.utkarsh.ecombackend.dto;

import lombok.Data;

@Data
public class OtpDto {
    private String username;
    private String password;
    private String otp;
}
