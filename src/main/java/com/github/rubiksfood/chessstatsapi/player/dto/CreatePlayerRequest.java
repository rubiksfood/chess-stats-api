package com.github.rubiksfood.chessstatsapi.player.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

@Schema(name = "CreatePlayerRequest")
public record CreatePlayerRequest(
        @Schema(
                description = "Unique username (3-30 chars). Letters, digits, underscore only.",
                example = "rubiksfood"
        )
        @NotBlank(message = "username must not be blank")
        @Size(min = 3, max = 30, message = "username must be between 3 and 30 characters")
        @Pattern(regexp = "^[A-Za-z0-9_]+$", message = "username must contain only letters, digits, or underscore")
        String username
) {}