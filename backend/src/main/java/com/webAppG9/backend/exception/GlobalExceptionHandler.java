package com.webAppG9.backend.exception;

import com.webAppG9.backend.dto.ResponseDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    // Maneja errores de negocio (RuntimeException)
    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<ResponseDTO<Object>> handleRuntimeException(RuntimeException ex) {
        return ResponseEntity.status(400).body(new ResponseDTO<>(null, ex.getMessage()));
    }

    // Maneja errores inesperados
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ResponseDTO<Object>> handleException(Exception ex) {
        return ResponseEntity.status(500).body(new ResponseDTO<>(null, "Error interno del servidor"));
    }
}
