package org.example.psrauth.dto.competition;

import jakarta.validation.constraints.*;
import lombok.*;

import java.time.LocalDateTime;


public record RequestCompetitionDTO(
        @NotBlank(message = "Competition name must be entered")
        String name,

        @DecimalMin(value = "-90.0", message = "Latitude must be between -90 and 90")
        @DecimalMax(value = "90.0", message = "Latitude must be between -90 and 90")
        double latitude,

        @DecimalMin(value = "-180.0", message = "Longitude must be between -180 and 180")
        @DecimalMax(value = "180.0", message = "Longitude must be between -180 and 180")
        double longitude,

        @Future(message = "Departure time must be in the future")
        LocalDateTime departureTime,

        @Min(value = 1, message = "Pigeon count must be at least 1")
        int pigeonCount,

        @Min(value = 0, message = "Percentage must be at least 0")
        @Max(value = 100, message = "Percentage cannot exceed 100")
        int percentage,

        @NotBlank(message = "Release place must be entered")
        String releasePlace
) {
}
