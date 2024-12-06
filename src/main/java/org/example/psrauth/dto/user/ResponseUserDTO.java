package org.example.psrauth.dto.user;

import org.example.psrauth.model.entity.Role;

public record ResponseUserDTO(
        Long id,
        String username,
        String password,
        Role role
) {
}
