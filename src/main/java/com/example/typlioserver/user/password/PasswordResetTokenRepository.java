package com.example.typlioserver.user.password;

import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

interface PasswordResetTokenRepository extends CrudRepository<PasswordResetToken, Long> {

    Optional<PasswordResetToken> findByToken(String token);
}
