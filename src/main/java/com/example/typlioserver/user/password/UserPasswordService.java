package com.example.typlioserver.user.password;

import com.example.typlioserver.mailer.MailerService;
import com.example.typlioserver.user.User;
import com.example.typlioserver.user.UserRepository;
import com.example.typlioserver.user.UserUtils;
import com.example.typlioserver.user.exception.UserNotFoundException;
import com.example.typlioserver.user.UserValidator;
import com.example.typlioserver.user.password.dto.ChangePasswordDto;
import com.example.typlioserver.user.password.dto.PasswordResetDto;
import com.example.typlioserver.user.password.exception.IncorrectPasswordException;
import com.example.typlioserver.user.password.exception.PasswordResetTokenExpiredException;
import com.example.typlioserver.user.password.exception.PasswordResetTokenNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.UUID;

@Service
@RequiredArgsConstructor
class UserPasswordService {

    private final MailerService mailerService;
    private final UserValidator userValidator;
    private final UserUtils userUtils;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final PasswordResetTokenRepository passwordResetTokenRepository;

    private final Duration PASSWORD_RESET_TOKEN_EXPIRATION = Duration.ofMinutes(30);

    @Value("${app.clientUrl}")
    private String clientUrl;

    @Transactional
    public void sendResetPasswordLink(String email) {
        userValidator.checkIfEmailUserExists(email);

        PasswordResetToken resetToken = generatePasswordResetToken(email);
        passwordResetTokenRepository.save(resetToken);

        String resetLink = generatePasswordResetLink(resetToken);
        mailerService.sendSimpleMail(email, "Password reset", "Password reset link: " + resetLink);
    }

    @Transactional
    public void resetPassword(PasswordResetDto resetPasswordDto) {
        String tokenString = resetPasswordDto.getToken();
        PasswordResetToken resetToken = passwordResetTokenRepository
                .findByToken(tokenString)
                .orElseThrow(PasswordResetTokenNotFoundException::new);

        validateTokenDate(resetToken);

        User user = userRepository
                .findByEmail(resetToken.getEmail())
                .orElseThrow(UserNotFoundException::new);

        user.setPassword(passwordEncoder.encode(resetPasswordDto.getNewPassword()));
        resetToken.setIsActive(false);
    }

    @Transactional
    public void changePassword(Long userId, ChangePasswordDto passwordDto) {
        userValidator.checkIfIdUserExists(userId);
        userValidator.checkIfSameUser(userId);

        User user = userUtils.getLoggedInUser();
        if (!passwordEncoder.matches(passwordDto.getOldPassword(), user.getPassword())) {
            throw new IncorrectPasswordException();
        }

        String newEncodedPassword = passwordEncoder.encode(passwordDto.getNewPassword());
        user.setPassword(newEncodedPassword);
    }

    private void validateTokenDate(PasswordResetToken resetToken) {
        if (resetToken.getExpiration().isBefore(LocalDateTime.now()) || !resetToken.getIsActive()) {
            throw new PasswordResetTokenExpiredException();
        }
    }

    private PasswordResetToken generatePasswordResetToken(String email) {
        String token = generateTokenString();

        return PasswordResetToken
                .builder()
                .expiration(LocalDateTime.now().plus(PASSWORD_RESET_TOKEN_EXPIRATION))
                .email(email)
                .token(token)
                .isActive(true)
                .build();
    }

    private String generateTokenString() {
        return UUID.randomUUID().toString();
    }

    private String generatePasswordResetLink(PasswordResetToken token) {
        return clientUrl + "/auth/forgot-password/" + token.getToken();
    }
}
