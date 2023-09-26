package com.example.typlioserver.user.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UpdateUsernameDto {

    @Length(min = 3, max = 256)
    @NotBlank
    private String username;
}
