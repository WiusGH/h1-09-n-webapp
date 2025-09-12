package com.webAppG9.backend.exception;

import com.webAppG9.backend.dto.ResponseDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    // Maneja todas las excepciones de negocio de JobApplication
    @ExceptionHandler({
            JobPostInactiveException.class,
            MaxCandidatesReachedException.class,
            JobPostNotFoundException.class,
            JobApplicationNotFoundException.class,
            CandidateNotFoundException.class,
            CandidateAlreadyAppliedException.class,
            ProfileAlreadyCompletedException.class,
            RecruiterNotFoundException.class,
            UserNotFoundException.class
    })
    public ResponseEntity<ResponseDTO<Object>> handleJobApplicationExceptions(RuntimeException ex) {
        return ResponseEntity.status(400).body(new ResponseDTO<>(null, ex.getMessage()));
    }

    // Maneja errores inesperados
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ResponseDTO<Object>> handleException(Exception ex) {
        return ResponseEntity.status(500).body(new ResponseDTO<>(null, "Error interno del servidor"));
    }
}
