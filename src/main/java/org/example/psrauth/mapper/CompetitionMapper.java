package org.example.psrauth.mapper;

import org.example.psrauth.dto.competition.RequestCompetitionDTO;
import org.example.psrauth.dto.competition.ResponseCompetitionDTO;
import org.example.psrauth.model.entity.Competition;
import org.mapstruct.Mapper;

import org.mapstruct.Mapping;

@Mapper(config = GenericMapper.class)
public interface CompetitionMapper extends GenericMapper<Competition, RequestCompetitionDTO, ResponseCompetitionDTO> {

    @Override
    Competition toEntity(RequestCompetitionDTO requestdto);

    @Override
    ResponseCompetitionDTO toDTO(Competition entity);
}
