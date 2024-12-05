package org.example.psrauth.mapper;

import org.example.psrauth.dto.user.RequestUserDTO;
import org.example.psrauth.dto.user.ResponseUserDTO;
import org.example.psrauth.model.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.MapperConfig;

@Mapper(config = GenericMapper.class)
public interface UserMapper extends GenericMapper<User, RequestUserDTO, ResponseUserDTO>{
}
