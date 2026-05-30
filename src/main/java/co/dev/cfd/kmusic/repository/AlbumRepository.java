package co.dev.cfd.kmusic.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import co.dev.cfd.kmusic.model.Album;
import co.dev.cfd.kmusic.model.Artista;

@Repository
public interface AlbumRepository extends JpaRepository<Album, Long>{

    Optional<Album> findByGenero(String genero);

    Optional<Album> findByTitulo(String titulo);

    void deleteByArtista(Artista artista);

    void deleteByGenero(String genero);

    @Query("SELECT DISTINCT a FROM Album a JOIN FETCH a.artista LEFT JOIN FETCH a.canciones")
    List<Album> findAllWithCanciones();
}
