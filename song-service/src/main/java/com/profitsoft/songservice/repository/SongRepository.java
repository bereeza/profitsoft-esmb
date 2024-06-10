package com.profitsoft.songservice.repository;

import com.profitsoft.songservice.entity.Song;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SongRepository extends JpaRepository<Song, Long> {
}
