package com.profitsoft.songservice.controller;

import com.profitsoft.songservice.dto.artist.ArtistInfoDto;
import com.profitsoft.songservice.dto.artist.ArtistSaveDto;
import com.profitsoft.songservice.service.ArtistService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


/**
 * Secondary controller responsible for manipulating the artist
 */
@RestController
@RequestMapping("/api/artist")
@RequiredArgsConstructor
public class ArtistController {

    /**
     * Artist Service for interaction between the controller & DB
     */
    private final ArtistService artistService;

    /**
     * To create a new valid artist. Incorrect entity = 400 Bad request
     *
     * @param artist - valid entity of artist
     * @return - response with ArtistSaveDto data
     */
    @PostMapping
    @Operation(summary = "add new artist")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "The artist is added"),
            @ApiResponse(responseCode = "400", description = "Bad request")
    })
    public ResponseEntity<ArtistInfoDto> addArtist(@Valid @RequestBody ArtistSaveDto artist) {
        return ResponseEntity
                .ok(artistService.save(artist));
    }
}

