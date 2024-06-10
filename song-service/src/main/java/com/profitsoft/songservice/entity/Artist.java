package com.profitsoft.songservice.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * Entity for database manipulation
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "artist")
@JsonIgnoreProperties
public class Artist {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "artist_id")
    private long artistId;

    @Column(name = "artist_name")
    private String artistName;

    @Column(name = "artist_country")
    private String artistCountry;

    /**
     * one artist - many songs
     */
    @OneToMany(mappedBy = "artist", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Song> songs;
}
