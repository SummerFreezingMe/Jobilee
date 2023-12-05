package com.bykov.jobilee.domain.dto;

import com.bykov.jobilee.domain.model.User;
import jakarta.persistence.Column;
import lombok.Data;
import lombok.NonNull;

/**
 * A DTO for the {@link User} entity.
 */
@Data
public class UserDTO {

    private Long id;


    @NonNull
    private String name;

    @NonNull
    //todo: regex
    private String mail;


    @NonNull
    private Integer rating;

    @NonNull
    private Integer age;

    private String description;

    private Long resumeId;

    private String image;
}
