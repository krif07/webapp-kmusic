package co.dev.cfd.kmusic.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import co.dev.cfd.kmusic.model.Album;
import co.dev.cfd.kmusic.model.Artista;

@Repository
public interface AlbumRepository extends JpaRepository<Album, Long>{

    Optional<Album> findByGenero(String genero);

    void deleteByArtista(Artista artista);

    void deleteByGenero(String genero);
}
