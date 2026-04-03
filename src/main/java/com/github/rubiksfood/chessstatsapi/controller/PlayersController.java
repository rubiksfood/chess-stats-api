package com.github.rubiksfood.chessstatsapi.controller;

import com.github.rubiksfood.chessstatsapi.player.Player;
import com.github.rubiksfood.chessstatsapi.player.PlayerService;
import com.github.rubiksfood.chessstatsapi.player.dto.CreatePlayerRequest;
import com.github.rubiksfood.chessstatsapi.player.dto.PlayerResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.UUID;

@RestController
@RequestMapping("/players")
public class PlayersController {

    private final PlayerService service;

    public PlayersController(PlayerService service) {
        this.service = service;
    }

    @Operation(
            summary = "Create a player",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    required = true,
                    content = @Content(
                            schema = @Schema(implementation = CreatePlayerRequest.class),
                            examples = @ExampleObject(
                                    name = "CreatePlayer",
                                    value = """
                                            {
                                              "username": "rubiksfood"
                                            }
                                            """
                            )
                    )
            )
    )
    @ApiResponse(responseCode = "201", description = "Created")
    @ApiResponse(responseCode = "400", description = "Validation error")
    @ApiResponse(responseCode = "409", description = "Username already exists")
    @PostMapping
    public ResponseEntity<PlayerResponse> create(@Valid @RequestBody CreatePlayerRequest request) {
        Player created = service.create(request);
        PlayerResponse response = toResponse(created);
        return ResponseEntity
                .created(URI.create("/players/" + created.getId()))
                .body(response);
    }

    @Operation(summary = "Get a player by id")
    @ApiResponse(responseCode = "200", description = "OK")
    @ApiResponse(
            responseCode = "404",
            description = "Not found",
            content = @Content(
                    examples = @ExampleObject(
                            value = """
                                    {
                                      "timestamp": "2026-03-22T15:46:38Z",
                                      "status": 404,
                                      "error": "NOT_FOUND",
                                      "message": "player not found",
                                      "path": "/players/2f2b03d7-4cbf-4b36-a6c5-cf2c2e95d0b7"
                                    }
                                    """
                    )
            )
    )
    @GetMapping("/{id}")
    public ResponseEntity<PlayerResponse> getById(@PathVariable UUID id) {
        Player player = service.getById(id);
        return ResponseEntity.ok(toResponse(player));
    }

    private static PlayerResponse toResponse(Player player) {
        return PlayerResponse.builder()
                .id(player.getId())
                .username(player.getUsername())
                .createdAt(player.getCreatedAt())
                .build();
    }
}