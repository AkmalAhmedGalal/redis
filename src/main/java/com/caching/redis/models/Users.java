package com.caching.redis.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.io.Serializable;

@Entity
@Table(name = "USERS")
@Data @AllArgsConstructor @NoArgsConstructor
public class Users implements Serializable {

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "FIRST_NAME")
    @NotEmpty(message = "first name should not be empty")
    private String firstName;

    @Column(name = "LAST_NAME")
    @NotEmpty(message = "last name should not be empty")
    private String lastName;

    @Column(name = "EMAIL")
    @Email(message = "email should be valid")
    private String email;

    @Column(name = "PHONE_NUMBER")
    @Digits(fraction = 0, integer = 10, message = "only up to 10 digits allowed")
    private String phoneNumber;

    @Column(name = "ADDRESS")
    private String address;

    @Column(name = "EDUCATION")
    private String education;

}
