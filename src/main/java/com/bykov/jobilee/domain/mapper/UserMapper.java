package com.bykov.jobilee.domain.mapper;

import com.bykov.jobilee.domain.dto.CreateUserRequestDTO;
import com.bykov.jobilee.domain.dto.UserDTO;
import com.bykov.jobilee.domain.model.User;
import org.mapstruct.Mapper;
import org.springframework.data.domain.Page;

import java.util.List;

/**
 * Mapper for the entity {@link User} and its DTO called {@link UserDTO}.
 */

@Mapper(componentModel = "spring")
public interface UserMapper extends EntityMapper<UserDTO, User> {
    UserDTO toDto(User s);

    User toEntity(UserDTO s);

    User toEntity(CreateUserRequestDTO s);


    List<UserDTO> toDto(Page<User> all);
}
