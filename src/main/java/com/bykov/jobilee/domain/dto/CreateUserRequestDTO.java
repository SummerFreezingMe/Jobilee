package com.bykov.jobilee.domain.dto;

import com.bykov.jobilee.domain.dto.security.RoleDTO;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

/**
 * A DTO for the User.
 */
@SuppressWarnings("common-java:DuplicatedBlocks")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "Описание модели запроса для создания \"Сотрудник\"")
public class CreateUserRequestDTO {



    @Schema(description = "Почта пользователя", example = "tupichok@mail.ru")
    private String email;

    @Schema(description = "Номер телефона пользователя", example = "895436848")
    private String phoneNumber;
    
    @Schema(description = "Пароль пользователя", example = "Qwer123as")
    private String password;

    @Schema(description = "Повторение пароля пользователя", example = "Qwer123as")
    private String repeatPassword;

    @Schema(description = "Роль пользователя")
    private RoleDTO role;

    @NonNull
    private Integer age;

    private String description;

    @NonNull
    private String name;


}

