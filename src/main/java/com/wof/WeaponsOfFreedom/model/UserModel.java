package com.wof.WeaponsOfFreedom.model;

import java.time.Instant;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.wof.WeaponsOfFreedom.common.Enums.StarLevel;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

// @NoArgsConstructor and @AllArgsConstructor are Lombok annotations that generate constructors for you.

// By default, the table name will be the class name (UserModel), unless you specify otherwise using @Table.

// This tells Hibernate (or any JPA provider) that it should map this class to a database table.
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "users") // naming this in db as "users"
public class UserModel {

    @Id // marking in JPA this as a unique
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Lets the database generate the primary key. Hibernate will
                                                        // insert the row, and the DB returns the generated key.
    private long id;

    @Column(nullable = false, unique = true)
    private String userId; // public identifier

    @Column(nullable = false)
    private String firstName;

    @Column(nullable = false)
    private String lastName;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(unique = true)
    private String phone;

    private Integer age; // because Integer is an object wrapper and can be null

    private String occupation;

    // tells JPA/Spring Data how to store your StarLevel enum in the database. means
    // “this field is an enum, not just a plain object”
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private StarLevel starLevel;

    @Column(nullable = false)
    private String passwordHash; // BCrypt hashed password

    @CreationTimestamp
    private Instant createdAt;

    @UpdateTimestamp
    private Instant updatedAt;

}
