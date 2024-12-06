package org.example.psrauth.dto.errors;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@Builder
public class ErrorDTO {
    private int status;
    private Date timestamp;
    private String message;
}
