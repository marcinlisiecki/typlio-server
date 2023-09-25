package com.example.typlioserver.user.password;

import org.springframework.data.repository.CrudRepository;

interface PasswordResetTokenRepository extends CrudRepository<PasswordResetToken, Long> {
}
