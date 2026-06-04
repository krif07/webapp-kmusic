package co.dev.cfd.kmusic.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import co.dev.cfd.kmusic.model.Artista;
import co.dev.cfd.kmusic.model.Usuario;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    
    Usuario findByUsername(String username);
    Usuario findByArtista(Artista artista);
    List<Usuario> findAllByArtistaIsNotNull();

}
