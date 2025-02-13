package com.meac.adi.entities.dtos;

import java.util.UUID;

public record ResponseUserDTO(UUID userId, String username, String email ) {
}
