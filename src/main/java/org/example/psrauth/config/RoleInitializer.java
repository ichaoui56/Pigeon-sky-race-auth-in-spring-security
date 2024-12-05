package org.example.psrauth.config;

import org.example.psrauth.model.RoleType;
import org.example.psrauth.model.entity.Role;
import org.example.psrauth.repository.RoleRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.*;

@Configuration
public class RoleInitializer {

    @Bean
    public CommandLineRunner initializeRoles(RoleRepository roleRepository) {
        return args -> {
            if (roleRepository.count() == 0) {
                roleRepository.save(new Role(null, RoleType.ROLE_USER, new ArrayList<>()));
                roleRepository.save(new Role(null, RoleType.ROLE_ADMIN, new ArrayList<>()));
                roleRepository.save(new Role(null, RoleType.ROLE_ORGANIZER, new ArrayList<>()));
            }
        };
    }
}