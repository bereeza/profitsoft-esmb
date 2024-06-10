package com.profitsoft.songservice.service;

import com.profitsoft.songservice.dto.mail.Mail;
import com.profitsoft.songservice.dto.song.SongDetailInfoDto;
import com.profitsoft.songservice.dto.song.SongSaveDto;
import com.profitsoft.songservice.entity.Artist;
import com.profitsoft.songservice.entity.Song;
import com.profitsoft.songservice.exception.artist.ArtistNotFoundException;
import com.profitsoft.songservice.repository.ArtistRepository;
import com.profitsoft.songservice.repository.SongRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

import static com.profitsoft.songservice.config.RabbitConfig.EXCHANGE_MAIL;

/**
 * Service for manipulating the song
 */
@Service
@RequiredArgsConstructor
public class SongService {

    // song repository for performing entity operations
    private final SongRepository songRepository;

    // to get and save an artist by id or get exception
    private final ArtistRepository artistRepository;

    private final MailService mailService;
    private final RabbitTemplate rabbitTemplate;

    /**
     * To save songDetailInfo in repository & send data to mb
     *
     * @param songSaveDto - song to save
     * @return song info
     */
    public SongDetailInfoDto save(SongSaveDto songSaveDto) {
        Song savedSong = extractSong(songSaveDto);
        Artist artist = getArtistOrElseThrow(songSaveDto);

        Mail mail = mailService.sendMail();
        rabbitTemplate.convertAndSend(EXCHANGE_MAIL, "", mail);

        savedSong.setArtist(artist);
        songRepository.save(savedSong);
        return getBuild(savedSong);
    }

    // If the artist does not exist, an exception will be thrown
    private Artist getArtistOrElseThrow(SongSaveDto songSaveDto) {
        return artistRepository
                .findById(
                        songSaveDto.getArtistId()).orElseThrow(() ->
                        new ArtistNotFoundException("Artist with id " + songSaveDto.getArtistId() + " not found")
                );
    }

    private Song extractSong(SongSaveDto songSaveDto) {
        return Song.builder()
                .title(songSaveDto.getTitle())
                .duration(songSaveDto.getDuration())
                .album(songSaveDto.getAlbum())
                .genre(songSaveDto.getGenre())
                .artist(getArtistOrElseThrow(songSaveDto))
                .build();
    }

    private SongDetailInfoDto getBuild(Song savedSong) {
        return SongDetailInfoDto.builder()
                .id(savedSong.getId())
                .title(savedSong.getTitle())
                .duration(savedSong.getDuration())
                .album(savedSong.getAlbum())
                .genre(savedSong.getGenre())
                .artist(savedSong.getArtist())
                .build();
    }
}
