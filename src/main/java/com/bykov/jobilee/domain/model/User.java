package com.bykov.jobilee.domain.model;


import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import org.hibernate.annotations.GenericGenerator;

import java.io.Serial;
import java.io.Serializable;

/**
 * A Password.
 */
@Data
@Entity
@NoArgsConstructor
@Table(name = "user")
@SuppressWarnings("common-java:DuplicatedBlocks")
public class User implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;


    @Id
    @GeneratedValue(generator="increment")
    @GenericGenerator(name="increment", strategy = "increment")
    @Column(name = "id")
    private Long id;


    @Column(name = "name")
    @NonNull
    private String name;

    @Column(name = "mail")
    @NonNull
    //todo: regex
    private String mail;


    @Column(name = "rating")
    @NonNull
    private Integer rating;

    @Column(name = "rating")
    @NonNull
    private Integer age;

    @Column(name = "description")
    private String description;

    @Column(name = "resume_id")
    private Long resumeId;

    @Column(name = "image")
    private String image;

    @ManyToOne
    @JoinColumn(name = "role_id", referencedColumnName = "role_id")
    private Role role;

}
