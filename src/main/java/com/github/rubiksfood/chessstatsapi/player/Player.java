package com.github.rubiksfood.chessstatsapi.player;

import jakarta.persistence.*;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.UUID;

@Entity
@Table(name = "players")
public class Player {

    @Id
    @Column(name = "id", nullable = false, columnDefinition = "uuid")
    private UUID id;

    @Column(name = "username", nullable = false, unique = true, length = 30)
    private String username;

    @Column(name = "created_at", nullable = false, columnDefinition = "timestamptz")
    private OffsetDateTime createdAt;

    protected Player() {
        // JPA
    }

    public Player(UUID id, String username) {
        this.id = id;
        this.username = username;
    }

    @PrePersist
    void onCreate() {
        if (createdAt == null) {
            createdAt = OffsetDateTime.now(ZoneOffset.UTC);
        }
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
}