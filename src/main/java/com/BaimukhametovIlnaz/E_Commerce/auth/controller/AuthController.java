package com.BaimukhametovIlnaz.E_Commerce.auth.controller;

import com.BaimukhametovIlnaz.E_Commerce.auth.dto.AuthResponse;
import com.BaimukhametovIlnaz.E_Commerce.auth.dto.LoginRequest;
import com.BaimukhametovIlnaz.E_Commerce.auth.dto.RegisterRequest;
import com.BaimukhametovIlnaz.E_Commerce.auth.service.AuthService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {

    private final AuthService authService;


    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping
    public ResponseEntity<AuthResponse> register(@RequestBody @Valid RegisterRequest req) {
        return ResponseEntity.ok(authService.register(req));
    }

    @PostMapping
    public ResponseEntity<AuthResponse> login(@RequestBody @Valid LoginRequest req) {
        return ResponseEntity.ok(authService.login(req))
    }
}
