package co.dev.cfd.kmusic.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import co.dev.cfd.kmusic.model.Artista;

@Repository
public interface ArtistaRepository extends JpaRepository<Artista, Long>{

}
