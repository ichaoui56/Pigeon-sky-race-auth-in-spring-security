package org.example.psrauth.exception;

import org.example.psrauth.dto.ErrorDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice
public class GlobalExceptionHandler {

    private ResponseEntity<ErrorDTO> buildErrorResponse(HttpStatus status, String error, String message) {
        ErrorDTO errorResponse = new ErrorDTO(
                status.value(),
                error,
                message
        );
        return ResponseEntity.status(status).body(errorResponse);
    }

    @ExceptionHandler(UsernameAlreadyExistsException.class)
    public ResponseEntity<ErrorDTO> handleUsernameAlreadyExists(UsernameAlreadyExistsException ex) {
        return buildErrorResponse(HttpStatus.CONFLICT, "Username already exists", ex.getMessage());
    }

    @ExceptionHandler(UsernameNotFoundException.class)
    public ResponseEntity<ErrorDTO> handleUsernameNotFound(UsernameNotFoundException ex) {
        return buildErrorResponse(HttpStatus.NOT_FOUND, "User not found", ex.getMessage());
    }

    @ExceptionHandler(IncorrectPasswordException.class)
    public ResponseEntity<ErrorDTO> handleIncorrectPassword(IncorrectPasswordException ex) {
        return buildErrorResponse(HttpStatus.BAD_REQUEST, "Incorrect password", ex.getMessage());
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorDTO> handleGenericException(Exception ex) {
        return buildErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR, "Internal Server Error", ex.getMessage());
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ErrorDTO> handleIllegalArgumentException(IllegalArgumentException ex) {
        return buildErrorResponse(HttpStatus.BAD_REQUEST, "Bad Request", ex.getMessage());
    }
}
