package com.watch_inventory.exception;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.Instant;
import java.util.List;

@RestControllerAdvice
public class GlobalHandlerException {

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ErrorApi> treatNotFound(NotFoundException ex, HttpServletRequest req) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                new ErrorApi(Instant.now(), 404, "Not Found", ex.getMessage(), req.getRequestURI(),
                        List.of())
        );
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ErrorApi> treatInvalidRequest(IllegalArgumentException ex, HttpServletRequest req) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                new ErrorApi(Instant.now(), 400, "Invalid Request", ex.getMessage(), req.getRequestURI(),
                        List.of())
        );
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorApi> treatValidationRequest(MethodArgumentNotValidException ex, HttpServletRequest req) {
        List<ErrorApi.FieldError> fields = ex.getBindingResult().getFieldErrors().stream()
                .map(f -> new ErrorApi.FieldError(f.getField(), ex.getDetailMessageCode()))
                .toList();
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                new ErrorApi(Instant.now(), 400, "Invalid Request", "Error Validation", req.getRequestURI(),
                        fields)
        );
    }


}
