package com.BaimukhametovIlnaz.E_Commerce.common.security;

import com.BaimukhametovIlnaz.E_Commerce.user.entity.User;
import lombok.Value;
import org.springframework.stereotype.Service;
import javax.crypto.SecretKey;

@Service
public class JwtService {

    @Value("${application.security.jwt.secret}")
    private String secret;

    @Value("${application.security.jwt.access-token-expiration}")

    public String generateToken(User user) {

    }

    public String extractUsername(String token) {

    }

    public boolean isTokenValid(String token, User user) {

    }
}
