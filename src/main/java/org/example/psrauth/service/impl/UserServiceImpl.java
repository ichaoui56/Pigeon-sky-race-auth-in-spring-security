package org.example.psrauth.service.impl;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.example.psrauth.dto.user.RequestUserDTO;
import org.example.psrauth.mapper.UserMapper;
import org.example.psrauth.model.RoleType;
import org.example.psrauth.model.entity.Role;
import org.example.psrauth.model.entity.User;
import org.example.psrauth.repository.RoleRepository;
import org.example.psrauth.repository.UserRepository;
import org.example.psrauth.service.UserService;
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

        User user = userMapper.toEntity(userDTO);
        user.setUsername(userDTO.username());
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        return userRepository.save(user);
    }


}