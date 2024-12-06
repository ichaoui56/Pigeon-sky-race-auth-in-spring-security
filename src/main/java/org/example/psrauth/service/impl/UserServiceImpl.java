package org.example.psrauth.service.impl;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.example.psrauth.dto.user.RequestUserDTO;
import org.example.psrauth.exception.UsernameAlreadyExistsException;
import org.example.psrauth.mapper.UserMapper;
import org.example.psrauth.model.RoleType;
import org.example.psrauth.model.entity.Role;
import org.example.psrauth.model.entity.User;
import org.example.psrauth.repository.RoleRepository;
import org.example.psrauth.repository.UserRepository;
import org.example.psrauth.service.UserService;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;
    private RoleRepository roleRepository;
    private PasswordEncoder passwordEncoder;
    private UserMapper userMapper;

    @Transactional
    @Override
    public User register(RequestUserDTO userDTO) {
        if (userRepository.existsByUsername(userDTO.username())) {
            throw new UsernameAlreadyExistsException("Username " + userDTO.username() + " already exists");
        }

        Role role = roleRepository.findByRoleType(RoleType.ROLE_USER)
                .orElseThrow(() -> new IllegalArgumentException("Role not found!"));

        User user = userMapper.toEntity(userDTO);
        user.setUsername(userDTO.username());
        user.setRole(role);
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        return userRepository.save(user);
    }

    @Override
    public User changeRole(String username, String newRole) {
        UserDetails currentUser = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if (currentUser == null || !currentUser.getAuthorities().stream()
                .anyMatch(grantedAuthority -> grantedAuthority.getAuthority().equals("ROLE_ADMIN"))) {
            throw new AccessDeniedException("Only admins can change user roles");
        }

        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));

        Role role = roleRepository.findByRoleType(RoleType.valueOf(newRole))
                .orElseThrow(() -> new IllegalArgumentException("Role not found"));

        user.setRole(role);
        return userRepository.save(user);
    }
}