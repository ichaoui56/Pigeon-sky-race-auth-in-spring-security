package org.example.psrauth.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "pigeons")
public class Pigeon {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Ring number cannot be blank")
    @Column(unique = true, nullable = false)
    private String ringNumber;

    @NotBlank(message = "Gender cannot be blank")
    @Pattern(regexp = "^(MALE|FEMALE)$", message = "Gender must be either 'MALE' or 'FEMALE'")
    @Column(nullable = false)
    private String gender;

    @Min(value = 1, message = "Age must be at least 1")
    @Column(nullable = false)
    private int age;

    @NotBlank(message = "Color cannot be blank")
    @Column(nullable = false)
    private String color;

    @ManyToOne
    @JoinColumn(name = "competition_id")
    @NotNull(message = "Competition cannot be null")
    private Competition competition;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    @NotNull(message = "User cannot be null")
    private User user;
}
