package co.dev.cfd.kmusic.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import co.dev.cfd.kmusic.model.Cancion;

@Repository
public interface CancionRepository extends JpaRepository<Cancion, Long>{

}
