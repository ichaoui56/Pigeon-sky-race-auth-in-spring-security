package org.example.psrauth.controller;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.example.psrauth.dto.competition.RequestCompetitionDTO;
import org.example.psrauth.dto.competition.ResponseCompetitionDTO;
import org.example.psrauth.dto.pigeon.RequestPigeonDTO;
import org.example.psrauth.dto.pigeon.ResponsePigeonDTO;
import org.example.psrauth.model.entity.User;
import org.example.psrauth.service.CompetitionService;
import org.example.psrauth.service.PigeonService;
import org.example.psrauth.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
@RequestMapping("/breeder")
@AllArgsConstructor
public class PigeonController {

    private final PigeonService pigeonService;

    @PostMapping("/addPigeon")
    public ResponseEntity<String> addPigeon(@Valid @RequestBody RequestPigeonDTO requestPigeonDTO, Principal principal){
        String response = pigeonService.addPigeon(requestPigeonDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
}


