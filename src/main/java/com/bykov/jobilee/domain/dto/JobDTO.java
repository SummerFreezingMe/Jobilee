package com.bykov.jobilee.domain.dto;

import com.bykov.jobilee.domain.model.Job;
import lombok.Data;
import lombok.NonNull;

import java.io.Serializable;

/**
 * A DTO for the {@link Job} entity.
 */
@SuppressWarnings("common-java:DuplicatedBlocks")
@Data
public class JobDTO implements Serializable {

    private Long id;

    @NonNull
    private String jobName;

    @NonNull
    private Integer requiredAmount;

    private String location;

    private boolean isOffline;

    private Long companyId;
}
