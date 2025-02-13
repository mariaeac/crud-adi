package com.meac.adi.entities.dtos;

import jakarta.validation.constraints.NotBlank;

public record CreateUserDTO(
        @NotBlank String username,
        @NotBlank String email,
        @NotBlank String password) {
}
