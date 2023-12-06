package com.bykov.jobilee.service.impl;

import com.bykov.jobilee.exception.AuthException;
import com.bykov.jobilee.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Этот код обеспечивает аутентификацию пользователей в приложении.
 * Метод loadUserByUsername ищет информацию о пользователе с помощью репозитория
 * UserRepository и создаёт
 * объект UserDetails в случае успеха. Если пользователь не найден, метод бросает исключение
 * UsernameNotFoundException.
 */
@RequiredArgsConstructor
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        com.bykov.jobilee.domain.model.User user = userRepository
                .findByUsername(username)
                .orElseThrow(AuthException.CODE.NO_SUCH_USERNAME_OR_PWD::get);
        return new User(
                user.getUsername(),
                user.getPassword(),
                new ArrayList<>(Collections.singletonList(
                        new SimpleGrantedAuthority(user.getRole().getName())))
        );
    }
}
