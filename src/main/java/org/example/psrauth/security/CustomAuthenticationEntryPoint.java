package org.example.psrauth.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.psrauth.dto.ErrorDTO;
import org.example.psrauth.exception.IncorrectPasswordException;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;


import java.io.IOException;

@Component
public class CustomAuthenticationEntryPoint implements AuthenticationEntryPoint {

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException)
            throws IOException {
        Throwable cause = authException.getCause();
        ErrorDTO errorResponse;
        if (cause instanceof IncorrectPasswordException) {
            errorResponse = new ErrorDTO(
                    HttpStatus.BAD_REQUEST.value(),
                    "Incorrect password",
                    cause.getMessage()
            );
            response.setStatus(HttpStatus.BAD_REQUEST.value());
        } else {
            errorResponse = new ErrorDTO(
                    HttpStatus.UNAUTHORIZED.value(),
                    "Unauthorized",
                    authException.getMessage()
            );
            response.setStatus(HttpStatus.UNAUTHORIZED.value());
        }
        response.setContentType("application/json");
        response.getWriter().write(objectMapper.writeValueAsString(errorResponse));
    }
}
