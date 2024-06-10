package com.profitsoft.songservice.repository;

import com.profitsoft.songservice.entity.Artist;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ArtistRepository extends JpaRepository<Artist, Long> {
}
