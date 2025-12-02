package com.wof.WeaponsOfFreedom.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wof.WeaponsOfFreedom.dto.SignUpDTO;
import com.wof.WeaponsOfFreedom.model.UserModel;
import com.wof.WeaponsOfFreedom.service.UserService;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final UserService userService;

    // Spring automatically creates this constructor and injects the dependency for
    // you.
    public AuthController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/signup")
    public ResponseEntity<?> signup(@RequestBody SignUpDTO req) {
        try {
            UserModel created = userService.userSignUp(req);
            return ResponseEntity.ok(created);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }

    }

}
