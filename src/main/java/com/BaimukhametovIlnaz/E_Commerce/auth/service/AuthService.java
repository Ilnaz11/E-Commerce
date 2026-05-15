package com.BaimukhametovIlnaz.E_Commerce.auth.service;

import com.BaimukhametovIlnaz.E_Commerce.auth.dto.AuthResponse;
import com.BaimukhametovIlnaz.E_Commerce.auth.dto.LoginRequest;
import com.BaimukhametovIlnaz.E_Commerce.auth.dto.RegisterRequest;


public interface AuthService {
    AuthResponse register(RegisterRequest request);
    AuthResponse login(LoginRequest request);
}
