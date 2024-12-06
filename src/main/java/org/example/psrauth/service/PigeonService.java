package org.example.psrauth.service;

import org.example.psrauth.dto.pigeon.RequestPigeonDTO;
import org.example.psrauth.dto.pigeon.ResponsePigeonDTO;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.security.Principal;

@Service
public interface PigeonService {
    String addPigeon(RequestPigeonDTO requestPigeonDTO);
}
