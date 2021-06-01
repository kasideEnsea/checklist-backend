package ru.vsu.cs.checklist.converter;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import ru.vsu.cs.checklist.dto.UserDto;
import ru.vsu.cs.checklist.entity.User;

import java.util.List;

@Mapper
public interface UserMapper { //NOSONAR
    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    UserDto toDto(User entity);

    User toEntity(UserDto dto);

    List<UserDto> toDtoList(List<User> entities);

    List<User> toEntityList(List<UserDto> dtos);
}

