package org.example.psrauth.dto.pigeon;


import jakarta.validation.constraints.*;

public record RequestPigeonDTO(
        @NotBlank(message = "Ring number cannot be blank")
        String ringNumber,

        @NotBlank(message = "Gender cannot be blank")
        @Pattern(regexp = "^(MALE|FEMALE)$", message = "Gender must be either 'MALE' or 'FEMALE'")
        String gender,

        @Min(value = 1, message = "Age must be at least 1")
        int age,

        @NotBlank(message = "Color cannot be blank")
        String color
) {
}
