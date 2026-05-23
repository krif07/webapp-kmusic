package co.dev.cfd.kmusic.service;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Service;

import co.dev.cfd.kmusic.model.Album;
import co.dev.cfd.kmusic.model.Cancion;
import co.dev.cfd.kmusic.repository.AlbumRepository;
import co.dev.cfd.kmusic.repository.CancionRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class CancionService {

    private final CancionRepository cancionRepository;
    private final AlbumRepository albumRepository;

    public Cancion obtenerCancionPorId(Long id) {
        return cancionRepository
            .findById(id)
            .orElseThrow(() -> new EntityNotFoundException(String.format("No se encontró la canción con el id: {id}", id)));
    }

    public List<Cancion> listarCanciones() {
        return cancionRepository.findAll();
    }

    @Transactional
    public Cancion guardarCancion(Cancion cancion, Long albumId) {
        Album album = albumRepository
            .findById(albumId)
            .orElseThrow(() -> new EntityNotFoundException(String.format("No se encontró el album con id: {id}", albumId)));
        
        cancion.setAlbum(album);
        return cancionRepository.save(cancion);
    }

    @Transactional
    @Modifying
    public void eliminarCancion(Long id) {
        Cancion cancion = cancionRepository
            .findById(id)
            .orElseThrow(() -> new EntityNotFoundException(String.format("No se encontró la canción a eliminar con id: {id}", id)));
        cancionRepository.delete(cancion);
    }
}
