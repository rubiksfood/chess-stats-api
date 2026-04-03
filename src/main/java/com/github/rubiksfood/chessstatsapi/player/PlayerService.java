package com.github.rubiksfood.chessstatsapi.player;

import com.github.rubiksfood.chessstatsapi.player.dto.CreatePlayerRequest;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.UUID;

import static org.springframework.http.HttpStatus.CONFLICT;
import static org.springframework.http.HttpStatus.NOT_FOUND;

@Service
public class PlayerService {

    private final PlayerRepository repository;

    public PlayerService(PlayerRepository repository) {
        this.repository = repository;
    }

    @Transactional
    public Player create(CreatePlayerRequest request) {
        if (repository.existsByUsernameIgnoreCase(request.username())) {
            throw new ResponseStatusException(CONFLICT, "username already exists");
        }

        Player player = new Player(UUID.randomUUID(), request.username());

        try {
            return repository.save(player);
        } catch (DataIntegrityViolationException ex) {
            // In case of race condition between exists-check and insert
            throw new ResponseStatusException(CONFLICT, "username already exists");
        }
    }

    @Transactional(readOnly = true)
    public Player getById(UUID id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(NOT_FOUND, "player not found"));
    }
}