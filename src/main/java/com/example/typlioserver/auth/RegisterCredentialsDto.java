package com.example.typlioserver.auth;

import jakarta.validation.constraints.NotBlank;
import lombok.*;
import org.hibernate.validator.constraints.Length;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RegisterCredentialsDto {

    private String email;
    private String username;

    @Length(min = 8, max = 256)
    @NotBlank
    private String password;
}
