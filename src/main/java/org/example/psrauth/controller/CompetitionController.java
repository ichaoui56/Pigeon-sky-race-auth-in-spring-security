package org.example.psrauth.controller;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.example.psrauth.dto.competition.RequestCompetitionDTO;
import org.example.psrauth.dto.competition.ResponseCompetitionDTO;
import org.example.psrauth.model.entity.User;
import org.example.psrauth.service.CompetitionService;
import org.example.psrauth.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
@RequestMapping("/organizer")
@AllArgsConstructor
public class CompetitionController {

    private CompetitionService competitionService;

    @PostMapping("/addCompetition")
    public ResponseEntity<ResponseCompetitionDTO> addCompetition(@Valid @RequestBody RequestCompetitionDTO requestCompetitionDTO, Principal principal){
        ResponseCompetitionDTO response = competitionService.addCompetition(requestCompetitionDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
}
