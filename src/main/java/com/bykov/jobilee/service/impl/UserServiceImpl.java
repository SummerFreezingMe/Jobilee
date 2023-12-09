package com.bykov.jobilee.service.impl;

import com.bykov.jobilee.domain.dto.CreateUserRequestDTO;
import com.bykov.jobilee.domain.dto.UserDTO;
import com.bykov.jobilee.domain.mapper.UserMapper;
import com.bykov.jobilee.domain.model.User;
import com.bykov.jobilee.exception.AuthException;
import com.bykov.jobilee.exception.UserException;
import com.bykov.jobilee.repository.RoleRepository;
import com.bykov.jobilee.repository.UserRepository;
import com.bykov.jobilee.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository employeeRepository;
    private final UserMapper employeeMapper;
    private final PasswordEncoder encoder;
    private final RoleRepository roleRepository;

    @Override
    @Transactional(readOnly = true)
    public UserDTO findOne(Long id) {
        return employeeMapper.toDto(employeeRepository
                .findById(id)
                .orElseThrow(UserException.CODE.NO_SUCH_USER::get));
    }

    @Override
    @Transactional
    public UserDTO save(CreateUserRequestDTO employeeDTO) {
        List<User> existingUsers = employeeRepository.findAll();
        User employee = employeeMapper.toEntity(employeeDTO);
        employee.setPassword(encoder.encode(employeeDTO.getPassword()));
        employee.setUsername(employeeDTO.getName());
        employee.setRole(roleRepository.findByName(employeeDTO.getRole().getName()).orElse(null));
        for (User e : existingUsers) {
            if ((e.getEmail().equals(employee.getEmail()))) {
                throw AuthException.CODE.EMAIL_IN_USE.get();
            }
        }

        employeeRepository.save(employee);
        return employeeMapper.toDto(employee);
    }

    @Override
    @Transactional
    public UserDTO updateUser(Long id, UserDTO employee) {
        return employeeRepository
                .findById(id)
                .map(existingEvent -> {
                    employeeMapper.partialUpdate(existingEvent, employee);
                    return existingEvent;
                })
                .map(employeeRepository::save)
                .map(employeeMapper::toDto)
                .orElseThrow(UserException.CODE.NO_SUCH_USER::get);
    }

    @Override
    @Transactional
    public void deleteUser(Long id) {
        employeeRepository
                .findById(id)
                .ifPresent(employeeRepository::delete);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<User> findOne(String username) {
        return employeeRepository.findByUsername(username);
    }
}
