package com.profitsoft.songservice.controller;

import com.profitsoft.songservice.dto.song.SongDetailInfoDto;
import com.profitsoft.songservice.dto.song.SongSaveDto;
import com.profitsoft.songservice.service.SongService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * The main controller that works with the main entity - Song
 */
@RestController
@RequestMapping("/api/song")
@RequiredArgsConstructor
public class SongController {

    /**
     * Song Service for interaction between the controller & DB
     */
    private final SongService songService;

    /**
     * To create a new valid song. Incorrect entity = 400 Bad request.
     *
     * @param songSaveDto - valid entity of songSaveDto
     * @return - response with SongDetailInfoDto data
     */
    @PostMapping
    @Operation(summary = "add new song")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "The song is added"),
            @ApiResponse(responseCode = "400", description = "Bad request")
    })
    public ResponseEntity<SongDetailInfoDto> addSong(@Valid @RequestBody SongSaveDto songSaveDto) {
        return ResponseEntity
                .ok(songService.save(songSaveDto));
    }
}
