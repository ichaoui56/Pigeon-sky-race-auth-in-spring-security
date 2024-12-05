package org.example.psrauth.dto.user;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.example.psrauth.model.RoleType;

public record RequestUserDTO(
        @NotBlank(message = "Username must be entered") String username,
        @NotBlank(message = "Please provide a password") String password
) {
}
