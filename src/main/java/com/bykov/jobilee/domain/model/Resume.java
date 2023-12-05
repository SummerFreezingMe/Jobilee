package com.bykov.jobilee.domain.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import java.io.Serial;
import java.io.Serializable;

/**
 * A Resume.
 */
@Data
@Entity
@NoArgsConstructor
@Table(name = "resume")
public class Resume  implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(generator="increment")
    @GenericGenerator(name="increment", strategy = "increment")
    @Column(name = "id")
    private Long id;

    @Column(name = "wanted_job")
    private String wantedJob;

    @Column(name = "skills")
    private String skills;

    @Column(name = "about_me")
    private String aboutMe;

    @Column(name = "experience")
    private Integer experience;



}
