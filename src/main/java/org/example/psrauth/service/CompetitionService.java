package org.example.psrauth.service;

import org.example.psrauth.dto.competition.RequestCompetitionDTO;
import org.example.psrauth.dto.competition.ResponseCompetitionDTO;
import org.example.psrauth.dto.user.RequestUserDTO;

public interface CompetitionService {
    ResponseCompetitionDTO addCompetition(RequestCompetitionDTO requestCompetitionDTO);
}
