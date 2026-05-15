package com.BaimukhametovIlnaz.E_Commerce.auth.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class RegisterRequest {
    @NotBlank(message = "First name cannot be empty")
    @Size(min = 2, max = 100, message = "First name must be between 2 or 100 characters")
    private String first_name;
    @NotBlank(message = "Last name cannot be empty")
    @Size(min = 2, max = 100, message = "Last name must be between 2 or 100 characters")
    private String last_name;
    @NotBlank(message = "Email cannot be empty")
    @Size(min = 3, max = 20)
    @Email(message = "Email must be valid")
    private String email;
    @NotBlank(message = "Password cannot be empty")
    @Size(min = 8, max = 100, message = "The password must be at least 8 characters long.")
    private String password;

}
