package org.example.psrauth.mapper;

import org.example.psrauth.dto.competition.RequestCompetitionDTO;
import org.example.psrauth.dto.competition.ResponseCompetitionDTO;
import org.example.psrauth.model.entity.Competition;
import org.mapstruct.Mapper;

@Mapper(config = GenericMapper.class)
public interface CompetitionMapper extends GenericMapper<Competition, RequestCompetitionDTO, ResponseCompetitionDTO> {
}
