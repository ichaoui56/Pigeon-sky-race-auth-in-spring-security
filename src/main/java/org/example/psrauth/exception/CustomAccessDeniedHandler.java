package org.example.psrauth.exception;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.example.psrauth.dto.errors.ErrorDTO;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;


import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component

@RequiredArgsConstructor
public class CustomAccessDeniedHandler implements AccessDeniedHandler {

    private final ObjectMapper objectMapper;

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException {
        ErrorDTO errorMessageDTO = ErrorDTO.builder()
                .message("Access denied: You do not have permission to access this resource.")
                .status(HttpServletResponse.SC_FORBIDDEN)
                .timestamp(new Date())
                .build();

        String jsonResponse = objectMapper.writeValueAsString(errorMessageDTO);

        response.setStatus(HttpServletResponse.SC_FORBIDDEN);
        response.setContentType("application/json");
        response.getWriter().write(jsonResponse);
    }
}
