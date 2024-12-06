package org.example.psrauth.service;

import jakarta.transaction.Transactional;
import org.example.psrauth.dto.user.RequestUserDTO;
import org.example.psrauth.model.entity.User;

import java.util.Optional;

public interface UserService {
    User register(RequestUserDTO userDTO);
    
    User changeRole(String username, String newRole);

    Optional<User> findByUsername(String name);
}
