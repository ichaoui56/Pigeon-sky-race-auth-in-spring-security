package org.example.psrauth.service.impl;

import lombok.AllArgsConstructor;
import org.example.psrauth.dto.pigeon.RequestPigeonDTO;
import org.example.psrauth.dto.pigeon.ResponsePigeonDTO;
import org.example.psrauth.exception.AlreadyExistsException;
import org.example.psrauth.exception.NotFoundException;
import org.example.psrauth.mapper.PigeonMapper;
import org.example.psrauth.model.RoleType;
import org.example.psrauth.model.entity.Pigeon;
import org.example.psrauth.model.entity.User;
import org.example.psrauth.repository.PigeonRepository;
import org.example.psrauth.repository.UserRepository;
import org.example.psrauth.service.PigeonService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
public class PigeonServiceImpl implements PigeonService {

    private final PigeonRepository pigeonRepository;
    private final UserRepository userRepository;
    private final PigeonMapper pigeonMapper;

    @Transactional
    @Override
    public String addPigeon(RequestPigeonDTO requestPigeonDTO) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentUser = authentication.getName();

        User user = userRepository.findByUsername(currentUser)
                .orElseThrow(() -> new NotFoundException("User with username '" + currentUser + "' not found."));

        if (!user.getRole().getRoleType().equals(RoleType.ROLE_USER)) {
            throw new IllegalStateException("Only users with the USER role can create pigeons");
        }

        pigeonRepository.findByRingNumber(requestPigeonDTO.ringNumber())
                .ifPresent(pigeon -> {
                    throw new AlreadyExistsException("Pigeon with ring number '" + requestPigeonDTO.ringNumber() + "' already exists.");
                });

        Pigeon pigeon = new Pigeon();
        pigeon.setRingNumber(requestPigeonDTO.ringNumber());
        pigeon.setGender(requestPigeonDTO.gender());
        pigeon.setAge(requestPigeonDTO.age());
        pigeon.setColor(requestPigeonDTO.color());
        pigeon.setUser(user);

        Pigeon savedPigeon = pigeonRepository.save(pigeon);

        return "Pigeon with ring number '" + requestPigeonDTO.ringNumber() + "' added successfully.";
    }
}