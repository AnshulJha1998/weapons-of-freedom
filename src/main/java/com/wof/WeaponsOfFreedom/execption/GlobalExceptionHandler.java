package com.wof.WeaponsOfFreedom.execption;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

// annotation used to define global configuration for controllers. It allows you to centralize logic that applies across multiple (or all) controllers
@ControllerAdvice
public class GlobalExceptionHandler {

    public record ErrorResponse(
            String type,
            String message,
            String field // Can be null
    ) {
    }

    // Handles errors for incorrect or missing fields in the request payload
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleValidation(MethodArgumentNotValidException ex) {
        FieldError error = ex.getBindingResult().getFieldError();
        return ResponseEntity.badRequest().body(
                new ErrorResponse("VALIDATION_ERROR", error.getDefaultMessage(), error.getField()));

    }
}
