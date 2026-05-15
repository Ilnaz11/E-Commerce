package com.BaimukhametovIlnaz.E_Commerce.auth.dto;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class LoginRequest {
    @NotBlank(message = "Email cannot be empty")
    @Email
    private String email;
    @NotBlank
    @Size(min = 8, max = 100, message = "Password must be between 2 or 100 characters")
    private String password;
}
