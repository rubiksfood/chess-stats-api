package com.github.rubiksfood.chessstatsapi.player.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import java.time.OffsetDateTime;
import java.util.UUID;

@Schema(name = "PlayerResponse")
public class PlayerResponse {

    @Schema(example = "2f2b03d7-4cbf-4b36-a6c5-cf2c2e95d0b7")
    private final UUID id;

    @Schema(example = "rubiksfood")
    private final String username;

    @Schema(example = "2026-03-05T10:15:30Z")
    private final OffsetDateTime createdAt;

    private PlayerResponse(Builder builder) {
        this.id = builder.id;
        this.username = builder.username;
        this.createdAt = builder.createdAt;
    }

    public UUID getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public OffsetDateTime getCreatedAt() {
        return createdAt;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static final class Builder {
        private UUID id;
        private String username;
        private OffsetDateTime createdAt;

        public Builder id(UUID id) {
            this.id = id;
            return this;
        }

        public Builder username(String username) {
            this.username = username;
            return this;
        }

        public Builder createdAt(OffsetDateTime createdAt) {
            this.createdAt = createdAt;
            return this;
        }

        public PlayerResponse build() {
            return new PlayerResponse(this);
        }
    }
}