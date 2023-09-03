package com.example.typlioserver.auth;

import com.example.typlioserver.auth.dto.LoginCredentialsDto;
import com.example.typlioserver.auth.dto.RegisterCredentialsDto;
import com.example.typlioserver.auth.jwt.JwtTokenDto;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/auth")
public class AuthController {

    private final AuthService authService;

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public void register(@RequestBody @Valid RegisterCredentialsDto credentials) {
        authService.register(credentials);
    }

    @PostMapping("/login")
    @ResponseStatus(HttpStatus.OK)
    public JwtTokenDto login(@RequestBody @Valid LoginCredentialsDto credentials) {
        return authService.login(credentials);
    }

    @PostMapping("/refresh-token")
    @ResponseStatus(HttpStatus.OK)
    public JwtTokenDto refreshToken(HttpServletRequest request, HttpServletResponse response) {
        return authService.refreshToken(request, response);
    }
}
