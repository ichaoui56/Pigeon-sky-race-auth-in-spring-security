package org.example.psrauth.mapper;

import org.example.psrauth.dto.role.RequestRoleDTO;
import org.example.psrauth.dto.role.ResponseRoleDTO;
import org.example.psrauth.model.entity.Role;
import org.mapstruct.Mapper;

@Mapper(config = GenericMapper.class)
public interface RoleMapper extends GenericMapper<Role, RequestRoleDTO, ResponseRoleDTO>{
}
