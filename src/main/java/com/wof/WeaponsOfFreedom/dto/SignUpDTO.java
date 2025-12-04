package com.wof.WeaponsOfFreedom.dto;

import jakarta.validation.constraints.AssertTrue;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

// This is payload type from client to server req
public record SignUpDTO(
                @NotBlank(message = "First Name is required") String firstName,
                String lastName,

                @Email @NotNull(message = "Email must be valid") @NotBlank(message = "Email is required") String email,

                @NotBlank(message = "Phone is required") String phone,

                @NotNull(message = "Age is required") @Min(value = 18, message = "Age must be at least 18") Integer age,

                @NotBlank(message = "Occupation is required") String occupation,

                @NotBlank(message = "Password is required") String password) {
        @AssertTrue(message = "Request payload is invalid or empty!")
        public boolean hasValidPayload() {
                // If ALL fields are null/empty, it's an empty payload
                boolean allEmpty = firstName == null && lastName == null && email == null &&
                                phone == null && age == null && occupation == null && password == null;
                if (allEmpty) {
                        return false; // Empty payload detected first!
                }
                return firstName != null && !firstName.trim().isEmpty() &&
                                lastName != null && !lastName.trim().isEmpty() &&
                                email != null && !email.trim().isEmpty() &&
                                phone != null && !phone.trim().isEmpty() &&
                                age != null &&
                                occupation != null && !occupation.trim().isEmpty() &&
                                password != null && !password.trim().isEmpty();
        }
}