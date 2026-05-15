package com.BaimukhametovIlnaz.E_Commerce.auth.service;

import com.BaimukhametovIlnaz.E_Commerce.auth.dto.AuthResponse;
import com.BaimukhametovIlnaz.E_Commerce.auth.dto.LoginRequest;
import com.BaimukhametovIlnaz.E_Commerce.auth.dto.RegisterRequest;
import com.BaimukhametovIlnaz.E_Commerce.common.exception.BusinessException;
import com.BaimukhametovIlnaz.E_Commerce.common.security.JwtService;
import com.BaimukhametovIlnaz.E_Commerce.user.entity.Role;
import com.BaimukhametovIlnaz.E_Commerce.user.entity.User;
import com.BaimukhametovIlnaz.E_Commerce.user.repository.UserRepository;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManagermanager;

    public AuthServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder, JwtService jwtService, AuthenticationManager manager, AuthenticationManager authenticationManagermanager) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
        this.authenticationManagermanager = authenticationManagermanager;
    }


    @Override
    public AuthResponse register(RegisterRequest request) {
        String email = request.getEmail().trim().toLowerCase();

        if (userRepository.existsByEmail(email)) {
            throw new BusinessException("User with this email already exists");
        }

        User user = new User();
        user.setEmail(email);
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setRole(Role.CUSTOMER);
        user.setFirstName(request.getFirst_name().trim());
        user.setLastName(request.getLast_name().trim());

        userRepository.save(user);

        return new AuthResponse(null, "Berear", 0);
    }

    @Override
    public AuthResponse login(LoginRequest request) {
        String email = request.getEmail().trim().toLowerCase();

        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new BusinessException("Email or password invalid"));

        boolean passwordMatcher = passwordEncoder.matches(request.getPassword(), user.getPassword());

        if (!passwordMatcher) {
            throw new BusinessException("Email or password invalid");
        }

        return new AuthResponse(null, "Bearer", 0);

    }
}
