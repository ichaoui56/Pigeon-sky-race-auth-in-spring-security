package org.example.psrauth.model.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;

@Entity

@NoArgsConstructor
@AllArgsConstructor
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(unique=true)
    @NotNull(message = "Username cannot be null")
    @Size(min = 3, max = 50, message = "Username must be between 3 and 50 characters")
    private String username;

    @NotNull(message = "Password cannot be null")
    @Size(min = 8, message = "Password must be at least 8 characters")
    private String password;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "role_id")
    @NotNull(message = "Role cannot be null")
    private Role role;


    public long getId() {
        return id;
    }

    public User setId(long id) {
        this.id = id;
        return this;
    }

    public @NotNull(message = "Username cannot be null") @Size(min = 3, max = 50, message = "Username must be between 3 and 50 characters") String getUsername() {
        return username;
    }

    public User setUsername(@NotNull(message = "Username cannot be null") @Size(min = 3, max = 50, message = "Username must be between 3 and 50 characters") String username) {
        this.username = username;
        return this;
    }

    public @NotNull(message = "Password cannot be null") @Size(min = 8, message = "Password must be at least 8 characters") String getPassword() {
        return password;
    }

    public User setPassword(@NotNull(message = "Password cannot be null") @Size(min = 8, message = "Password must be at least 8 characters") String password) {
        this.password = password;
        return this;
    }

    public @NotNull(message = "Role cannot be null") Role getRole() {
        return role;
    }

    public User setRole(@NotNull(message = "Role cannot be null") Role role) {
        this.role = role;
        return this;
    }
}
