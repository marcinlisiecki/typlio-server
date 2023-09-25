package com.example.typlioserver.user.password.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PasswordResetDto {

    private String token;

    @Length(min = 8, max = 256)
    @NotBlank
    private String newPassword;
}
