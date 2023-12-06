package com.bykov.jobilee.domain.model;


import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
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

    @Column(name = "email")
    @Email
    @Size(min = 5, max = 254)
    private String email;

    @Column(name = "phone_number")
    @NotBlank
    @Size(min = 1, max = 20)
    private String phoneNumber;

    @Column(name = "username")
    @NotBlank
    private String username;

    @Column(name = "password")
    @NotBlank
    @Size(min = 60, max = 60)
    private String password;
    @ManyToOne
    @JoinColumn(name = "role_id", referencedColumnName = "role_id")
    private Role role;

}
