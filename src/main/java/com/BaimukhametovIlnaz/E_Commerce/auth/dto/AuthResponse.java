package com.BaimukhametovIlnaz.E_Commerce.auth.dto;

import lombok.Data;

@Data
public class AuthResponse {
    private String accessToken;
    private String tokenType;
    private long isExpiresIn;
}
