package com.bykov.jobilee.domain.dto;


import lombok.Data;

import java.io.Serializable;

/**
 * A DTO for the {@link com.bykov.jobilee.domain.model.Resume} entity.
 */
@SuppressWarnings("common-java:DuplicatedBlocks")
@Data
public class ResumeDTO implements Serializable {

    private Long id;

    private String wantedJob;

    private String skills;

    private String aboutMe;

    private Integer experience;
}
