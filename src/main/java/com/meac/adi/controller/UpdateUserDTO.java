package com.meac.adi.controller;

import java.util.UUID;

public record UpdateUserDTO(UUID userId, String username, String email, String password) {

}
