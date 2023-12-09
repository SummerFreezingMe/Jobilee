package com.bykov.jobilee.service;

import com.bykov.jobilee.domain.dto.CreateUserRequestDTO;
import com.bykov.jobilee.domain.dto.UserDTO;
import com.bykov.jobilee.domain.model.User;

import java.util.Optional;

public interface UserService {

    UserDTO findOne(Long id);

    UserDTO save(CreateUserRequestDTO user);

    UserDTO updateUser(Long id, UserDTO user);

    void deleteUser(Long id);

    Optional<User> findOne(String username);
}
