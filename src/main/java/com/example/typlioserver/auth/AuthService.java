package com.example.typlioserver.auth;

import com.example.typlioserver.auth.exception.EmailAlreadyExistsException;
import com.example.typlioserver.auth.exception.UsernameAlreadyExistsException;
import com.example.typlioserver.user.User;
import com.example.typlioserver.user.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public String register(RegisterCredentialsDto credentials) {
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
        return "Success";
    }

    private boolean checkIfEmailExists(String email) {
        return userRepository.existsByEmail(email);
    }

    private boolean checkIfUsernameExists(String username) {
        return userRepository.existsByUsername(username);
    }
}
