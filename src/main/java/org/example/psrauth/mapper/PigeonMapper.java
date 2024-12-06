package org.example.psrauth.mapper;

import org.example.psrauth.dto.pigeon.RequestPigeonDTO;
import org.example.psrauth.dto.pigeon.ResponsePigeonDTO;
import org.example.psrauth.model.entity.Pigeon;
import org.mapstruct.Mapper;

@Mapper(config = GenericMapper.class)
public interface PigeonMapper extends GenericMapper<Pigeon, RequestPigeonDTO, ResponsePigeonDTO>{
}
