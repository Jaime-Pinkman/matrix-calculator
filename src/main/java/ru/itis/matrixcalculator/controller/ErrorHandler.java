package ru.itis.matrixcalculator.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import ru.itis.matrixcalculator.model.ErrorModel;

import java.util.Objects;

@RestControllerAdvice
public class ErrorHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> methodArgumentNotValidException(MethodArgumentNotValidException e){
        return ResponseEntity
                .badRequest()
                .body(new ErrorModel(e.getBindingResult().getFieldError().getDefaultMessage()));
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<?> illegalArgumentException(RuntimeException e){
        e.printStackTrace();
        String message = Objects.nonNull(e.getMessage()) ? e.getMessage() : e.getClass().getSimpleName();
        return ResponseEntity
                .badRequest()
                .body(new ErrorModel(message));
    }
}
