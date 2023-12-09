package com.bykov.jobilee.security.service;

import com.bykov.jobilee.domain.dto.CreateUserRequestDTO;
import com.bykov.jobilee.domain.dto.security.AuthDTO;
import com.bykov.jobilee.domain.dto.security.JwtResponse;
import com.bykov.jobilee.domain.mapper.UserMapper;
import com.bykov.jobilee.domain.model.Role;
import com.bykov.jobilee.domain.model.User;
import com.bykov.jobilee.exception.AuthException;
import com.bykov.jobilee.security.jwt.JwtCore;
import com.bykov.jobilee.service.impl.UserServiceImpl;
import io.jsonwebtoken.Claims;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Component;

import java.util.Objects;

/**
 * AuthService является компонентом Spring и использует другие компоненты, такие как JwtCore и AuthenticationManager.
 *
 * <p>Регистрация: Метод registration принимает объект UserDTO и создает нового сотрудника
 * с помощью
 * UserServiceImpl. Затем генерируются токены доступа и обновления с помощью jwtCore,
 * ивозвращается объект
 * JwtResponse, содержащий эти токены.
 *
 * <p>Вход: Метод login принимает объект AuthDto, содержащий имя пользователя и пароль. AuthenticationManager пытается
 * аутентифицировать пользователя, и в случае успешного входа генерируются токены доступа и обновления, как и при
 * регистрации. Затем возвращается объект JwtResponse с токенами.

 */

@Component
@RequiredArgsConstructor
public class AuthService {

    private final JwtCore jwtCore;
    private final AuthenticationManager authenticationManager;
    private final UserServiceImpl userService;
    private final UserMapper userMapper;

    public JwtResponse registration(CreateUserRequestDTO createUserRequestDTO) {
        if (!Objects.equals(createUserRequestDTO.getPassword(),
                createUserRequestDTO.getRepeatPassword())) {
            throw AuthException.CODE.INVALID_REPEAT_PASSWORD.get();
        }
        User user =
                userMapper.toEntity(userService.save(createUserRequestDTO));
        String accessToken = jwtCore.generateAccessToken(user);
        String refreshToken = jwtCore.generateRefreshToken(user);
        return new JwtResponse(accessToken, refreshToken);
    }

    public JwtResponse login(AuthDTO authDto) {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            authDto.getUsername(), authDto.getPassword()));
        } catch (BadCredentialsException e) {
            throw AuthException.CODE.NO_SUCH_USERNAME_OR_PWD.get();
        }
        final User user = userService
                .findOne(authDto.getUsername())
                .orElseThrow(AuthException.CODE.NO_SUCH_USERNAME_OR_PWD::get);
        final String accessToken = jwtCore.generateAccessToken(user);
        final String refreshToken = jwtCore.generateRefreshToken(user);
        return new JwtResponse(accessToken, refreshToken);
    }

    public JwtResponse getAccessToken(String refreshToken) {
        return generateAccessTokenOrRefresh(refreshToken, "getAccessToken");
    }

    public JwtResponse refresh(String refreshToken) {
        return generateAccessTokenOrRefresh(refreshToken, "refresh");
    }

    private JwtResponse generateAccessTokenOrRefresh(String refreshToken, String action) {
        if (jwtCore.validateRefreshToken(refreshToken)) {
            final Claims claims = jwtCore.extractRefreshClaims(refreshToken);
            final String username = claims.getSubject();
            final String roleName = (String) claims.get("role");
            final Integer id = (Integer) claims.get("user_id");
            userService.findOne(Long.valueOf(id));
            Role role = new Role();
            role.setName(roleName);
            User userForJwt = new User();
            userForJwt.setUsername(username);
            userForJwt.setRole(role);
            userForJwt.setId(Long.valueOf(id));
            final String accessToken = jwtCore.generateAccessToken(userForJwt);
            if (action.equals("refresh")) {
                final String newRefreshToken = jwtCore.generateRefreshToken(userForJwt);
                return new JwtResponse(accessToken, newRefreshToken);
            } else {
                return new JwtResponse(accessToken, null);
            }
        }
        return new JwtResponse(null, null);
    }
}
