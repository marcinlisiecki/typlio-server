package com.example.typlioserver.user.password;

import com.example.typlioserver.mailer.MailerService;
import com.example.typlioserver.user.common.UserValidator;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.UUID;

@Service
@RequiredArgsConstructor
class UserPasswordService {

    private final MailerService mailerService;
    private final UserValidator userValidator;
    private final PasswordResetTokenRepository passwordResetTokenRepository;

    private final Duration PASSWORD_RESET_TOKEN_EXPIRATION = Duration.ofMinutes(15);

    @Value("${app.clientUrl}")
    private String clientUrl;

    @Transactional
    public void resetPassword(String email) {
        userValidator.checkIfEmailUserExists(email);

        PasswordResetToken resetToken = generatePasswordResetToken(email);
        passwordResetTokenRepository.save(resetToken);

        String resetLink = generatePasswordResetLink(resetToken);
        mailerService.sendSimpleMail(email, "Password reset", "Password reset link: " + resetLink);
    }

    private PasswordResetToken generatePasswordResetToken(String email) {
        String token = generateTokenString();

        return PasswordResetToken
                .builder()
                .expiration(LocalDateTime.now().plus(PASSWORD_RESET_TOKEN_EXPIRATION))
                .email(email)
                .token(token)
                .build();
    }

    private String generateTokenString() {
        return UUID.randomUUID().toString();
    }

    private String generatePasswordResetLink(PasswordResetToken token) {
        return clientUrl + "/reset-password/" + token.getToken();
    }
}
