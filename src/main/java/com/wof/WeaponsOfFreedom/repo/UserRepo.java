package com.wof.WeaponsOfFreedom.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.wof.WeaponsOfFreedom.model.UserModel;

@Repository
public interface UserRepo extends JpaRepository<UserModel, Long> {

    // Optional<UserModel> findUserByEmail(String email) returns the user if found,
    // otherwise returns Optional.empty() (which is safer than null).
    Optional<UserModel> findUserByEmail(String email);

    boolean existsByEmail(String email);

    boolean existsByPhone(String phone);
}

// JPA accepts 1st arg as Model and 2ns as its primary key(id) type

// JPA : specification that defines how Java objects map to relational database
// tables and how you work with them without writing raw SQL