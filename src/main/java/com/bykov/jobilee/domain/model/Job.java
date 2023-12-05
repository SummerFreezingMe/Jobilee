package com.bykov.jobilee.domain.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import org.hibernate.annotations.GenericGenerator;

/**
 * A Job.
 */
@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "job")
public class Job {

    @Id
    @GeneratedValue(generator = "increment")
    @GenericGenerator(name = "increment", strategy = "increment")
    @Column(name = "id")
    private Long id;

    @Column(name = "job_name")
    @NonNull
    private String jobName;

    @Column(name = "required_amount")
    @NonNull
    //@DefaultValue(value = "1")
    private Integer requiredAmount;

    @Column(name = "location")
    private String location;

    @Column(name = "is_offline")
    private boolean isOffline;

    @Column(name = "company_id")
    private Long companyId;

}
