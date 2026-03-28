package com.github.rubiksfood.chessstatsapi.player;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.UUID;

public interface PlayerRepository extends JpaRepository<Player, UUID> {
    boolean existsByUsernameIgnoreCase(String username);
}