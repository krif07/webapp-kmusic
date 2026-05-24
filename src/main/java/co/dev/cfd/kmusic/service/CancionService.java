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

    /**
     * Busca y retorna una canción por su identificador único.
     *
     * @param id identificador de la canción
     * @return la canción encontrada
     * @throws EntityNotFoundException si no existe una canción con ese id
     */
    public Cancion obtenerCancionPorId(Long id) {
        return cancionRepository
            .findById(id)
            .orElseThrow(() -> new EntityNotFoundException(String.format("No se encontró la canción con el id: %d", id)));
    }

    /**
     * Retorna la lista completa de canciones registradas.
     *
     * @return lista de canciones; vacía si no hay ninguna
     */
    public List<Cancion> listarCanciones() {
        return cancionRepository.findAll();
    }

    /**
     * Crea y persiste una nueva canción asociándola al álbum indicado.
     *
     * @param cancion datos de la canción a guardar
     * @param albumId id del álbum al que pertenece la canción
     * @return la canción persistida con su id generado
     * @throws EntityNotFoundException si no existe un álbum con {@code albumId}
     */
    @Transactional
    public Cancion guardarCancion(Cancion cancion, Long albumId) {
        Album album = albumRepository
            .findById(albumId)
            .orElseThrow(() -> new EntityNotFoundException(String.format("No se encontró el album con id: %d", albumId)));
        
        cancion.setAlbum(album);
        return cancionRepository.save(cancion);
    }

    /**
     * Elimina la canción identificada por el id dado.
     *
     * @param id identificador de la canción a eliminar
     * @throws EntityNotFoundException si no existe una canción con ese id
     */
    @Transactional
    @Modifying
    public void eliminarCancion(Long id) {
        Cancion cancion = cancionRepository
            .findById(id)
            .orElseThrow(() -> new EntityNotFoundException(String.format("No se encontró la canción a eliminar con id: %d", id)));
        cancionRepository.delete(cancion);
    }
}
