package com.pers.security.mapper;

import com.pers.security.dto.UserDto;
import com.pers.security.entity.User;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserDto mapToDto(User user);

    @InheritInverseConfiguration
    User mapToEntity(UserDto userDto);
}
