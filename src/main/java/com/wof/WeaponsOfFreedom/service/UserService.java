package com.wof.WeaponsOfFreedom.service;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.wof.WeaponsOfFreedom.common.Enums.StarLevel;
import com.wof.WeaponsOfFreedom.dto.SignUpDTO;
import com.wof.WeaponsOfFreedom.model.UserModel;
import com.wof.WeaponsOfFreedom.repo.UserRepo;

// It tells Spring that this class is part of the service layer (business logic).
@Service
public class UserService {

    // Constructor injection
    private final UserRepo user;

    private final BCryptPasswordEncoder passEncoder = new BCryptPasswordEncoder();

    public UserService(UserRepo userRepo) {
        this.user = userRepo;
    }

    public UserModel userSignUp(SignUpDTO req) {
        if (user.existsByEmail(req.email())) {
            throw new IllegalArgumentException(String.format("User with email : %s already exists", req.email()));
        }

        if (req.phone() != null && user.existsByPhone(req.phone())) {
            throw new IllegalArgumentException(String.format("User with phone : %s already exists", req.phone()));
        }

        UserModel userModel = new UserModel();
        userModel.setFirstName(req.firstName());
        userModel.setLastName(req.lastName());
        userModel.setEmail(req.email());
        userModel.setAge(req.age());
        userModel.setPhone(req.phone());
        userModel.setOccupation(req.occupation());
        userModel.setStarLevel(StarLevel.ONE); // will be one by default
        userModel.setPasswordHash(passEncoder.encode(req.password()));
        userModel.setUserId(generatePublicUserId(req.firstName(), req.lastName(), req.email(), req.phone()));

        return user.save(userModel);

    }

    // Using user fields to generate a custom user unique id
    public String generatePublicUserId(String firstName, String lastName, String email, String phone) {
        String lastNameCheck = (lastName == null || lastName.isEmpty()) ? "" : lastName.substring(0, 1).toUpperCase();
        String prefix = (firstName.substring(0, 1) + lastNameCheck);
        String emailSplit = email.split("@")[0];
        String mid = emailSplit.substring(emailSplit.length() - 3, emailSplit.length());
        String suffix = phone.substring(phone.length() - 4, phone.length());
        String result = String.format("%s-%s-%s", prefix, mid, suffix);
        return result;
    }
}
