package com.profitsoft.songservice.service;

import com.profitsoft.songservice.dto.artist.ArtistInfoDto;
import com.profitsoft.songservice.dto.artist.ArtistSaveDto;
import com.profitsoft.songservice.entity.Artist;
import com.profitsoft.songservice.repository.ArtistRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * Service for manipulating the artist
 */
@Service
@RequiredArgsConstructor
public class ArtistService {

    // artist repository for performing entity operations
    private final ArtistRepository artistRepository;

    // To save artist in repository
    public ArtistInfoDto save(ArtistSaveDto artist) {
        Artist savedArtist = extractArtist(artist);
        artistRepository.save(savedArtist);
        return getBuild(savedArtist);
    }

    private ArtistInfoDto getBuild(Artist savedArtist) {
        return ArtistInfoDto.builder()
                .artistName(savedArtist.getArtistName())
                .artistCountry(savedArtist.getArtistCountry())
                .build();
    }

    private Artist extractArtist(ArtistSaveDto artist) {
        return Artist.builder()
                .artistCountry(artist.getArtistCountry())
                .artistName(artist.getArtistName())
                .artistId(artist.getArtistId())
                .artistName(artist.getArtistName())
                .build();
    }
}
