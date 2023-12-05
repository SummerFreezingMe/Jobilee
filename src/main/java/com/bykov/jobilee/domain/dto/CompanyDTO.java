package com.bykov.jobilee.domain.dto;


import com.bykov.jobilee.domain.model.Company;
import lombok.Data;
import lombok.NonNull;

import java.io.Serializable;

/**
 * A DTO for the {@link Company} entity.
 */

@SuppressWarnings("common-java:DuplicatedBlocks")
@Data
public class CompanyDTO implements Serializable {

    private Long id;

    @NonNull
    private String name;

    @NonNull
    private Integer rating;

    private String address;

    @NonNull
    private Short minute;
}
