package com.example.typlioserver.auth;

import com.example.typlioserver.auth.dto.LoginCredentialsDto;
import com.example.typlioserver.auth.dto.RegisterCredentialsDto;
import com.example.typlioserver.auth.exception.EmailAlreadyExistsException;
import com.example.typlioserver.auth.exception.UserNotAuthenticatedException;
import com.example.typlioserver.user.exception.UserNotFoundException;
import com.example.typlioserver.auth.exception.UsernameAlreadyExistsException;
import com.example.typlioserver.auth.jwt.JwtService;
import com.example.typlioserver.auth.jwt.JwtTokenDto;
import com.example.typlioserver.common.constants.ErrorMessages;
import com.example.typlioserver.user.User;
import com.example.typlioserver.user.UserRepository;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.security.SignatureException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;

    @Transactional
    public void register(RegisterCredentialsDto credentials) {
        if (checkIfEmailExists(credentials.getEmail())) {
            throw new EmailAlreadyExistsException();
        }

        if (checkIfUsernameExists(credentials.getUsername())) {
            throw new UsernameAlreadyExistsException();
        }

        User user = User
                .builder()
                .email(credentials.getEmail())
                .username(credentials.getUsername())
                .password(passwordEncoder.encode(credentials.getPassword()))
                .build();

        userRepository.save(user);
    }

    public JwtTokenDto login(LoginCredentialsDto credentials) {
        Authentication authentication;

        try {
            authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            credentials.getUsername(),
                            credentials.getPassword()
                    )
            );
        } catch (AuthenticationException e) {
            throw new BadCredentialsException(ErrorMessages.BAD_CREDENTIALS);
        }

        User user = (User) authentication.getPrincipal();

        String accessToken = jwtService.generateAccessToken(user, generateExtraClaims(user));
        String refreshToken = jwtService.generateRefreshToken(user);

        return JwtTokenDto
                .builder()
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .build();
    }

    JwtTokenDto refreshToken(HttpServletRequest request, HttpServletResponse response) {
        String authHeader = request.getHeader(HttpHeaders.AUTHORIZATION);

        if (jwtService.isAuthHeaderInvalid(authHeader)) {
            throw new UserNotAuthenticatedException();
        }

        String refreshToken = authHeader.substring(7);
        String username;

        try {
            username = jwtService.extractUsername(refreshToken);
        } catch (ExpiredJwtException | SignatureException | MalformedJwtException e) {
            throw new UserNotAuthenticatedException();
        }

        if (username == null) {
            throw new UserNotAuthenticatedException();
        }

        User userData = userRepository
                .findByUsername(username)
                .orElseThrow(UserNotFoundException::new);

        if (!jwtService.isTokenValid(refreshToken, userData)) {
            throw new UserNotAuthenticatedException();
        }

        String jwtToken = jwtService.generateAccessToken(userData, generateExtraClaims(userData));

        return JwtTokenDto
                .builder()
                .accessToken(jwtToken)
                .refreshToken(refreshToken)
                .build();
    }

    private Map<String, Object> generateExtraClaims(User user) {
        Map<String, Object> extraClaims = new HashMap<>();
        extraClaims.put("id", user.getId());
        extraClaims.put("email", user.getEmail());

        return extraClaims;
    }

    private boolean checkIfEmailExists(String email) {
        return userRepository.existsByEmail(email);
    }

    private boolean checkIfUsernameExists(String username) {
        return userRepository.existsByUsername(username);
    }
}
