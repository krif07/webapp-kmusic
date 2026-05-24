package co.dev.cfd.kmusic.service;

import java.util.List;

import org.springframework.stereotype.Service;

import co.dev.cfd.kmusic.model.Album;
import co.dev.cfd.kmusic.model.Artista;
import co.dev.cfd.kmusic.model.Cancion;
import co.dev.cfd.kmusic.repository.AlbumRepository;
import co.dev.cfd.kmusic.repository.ArtistaRepository;
import co.dev.cfd.kmusic.repository.CancionRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class AlbumService {

    private final AlbumRepository albumRepository;
    private final ArtistaRepository artistaRepository;
    private final CancionRepository cancionRepository;

    /**
     * Busca y retorna un álbum por su identificador único.
     *
     * @param id identificador del álbum
     * @return el álbum encontrado
     * @throws EntityNotFoundException si no existe un álbum con ese id
     */
    public Album obtenerAlbumPorId(Long id) {
        return albumRepository
            .findById(id)
            .orElseThrow(() -> new EntityNotFoundException(String.format("Album con id {id} no encontrado", id)));
    }

    /**
     * Retorna la lista completa de álbumes registrados.
     *
     * @return lista de álbumes; vacía si no hay ninguno
     */
    public List<Album> obtenerAlbumes() {
        return albumRepository.findAll();
    }

    /**
     * Elimina el álbum identificado por el id dado.
     *
     * @param id identificador del álbum a eliminar
     * @throws EntityNotFoundException si no existe un álbum con ese id
     */
    public void eliminarAlbum(Long id) {
        Album album = albumRepository
            .findById(id)
            .orElseThrow(() -> new EntityNotFoundException(String.format("No se puede eliminar el album con {id}", id)));
        albumRepository.delete(album);
    }

    /**
     * Crea y persiste un nuevo álbum asociándolo al artista y canciones indicados.
     * Las canciones recibidas quedan vinculadas al álbum (relación bidireccional).
     *
     * @param album       datos del álbum a guardar
     * @param idArtista   id del artista propietario del álbum
     * @param idCanciones lista de ids de canciones a incluir en el álbum; puede ser nula o vacía
     * @return el álbum persistido con su id generado
     * @throws EntityNotFoundException si no existe el artista con {@code idArtista}
     */
    @Transactional
    public Album guardarAlbum(Album album, Long idArtista, List<Long> idCanciones) {
        Artista artista = artistaRepository
            .findById(idArtista)
            .orElseThrow(() -> new EntityNotFoundException(String.format("Artista no encontrado con id {id}", idArtista)));
        album.setArtista(artista);

        if(idCanciones != null && !idCanciones.isEmpty()){
            List<Cancion> canciones = cancionRepository.findAllById(idCanciones);
            for(Cancion cancion: canciones) {
                cancion.setAlbum(album);
                album.getCanciones().add(cancion);
            }
        }
        
        return albumRepository.save(album);
    }

    /**
     * Actualiza los datos de un álbum existente.
     * Reemplaza título, género, fecha de estreno y artista; añade al álbum las canciones
     * de {@code idCanciones} que aún no estuvieran asociadas (no elimina las existentes).
     *
     * @param idAlbum         id del álbum a actualizar
     * @param albumActualizado objeto con los nuevos valores del álbum
     * @param idArtista        id del artista que se asignará al álbum
     * @param idCanciones      lista de ids de canciones a agregar; puede ser nula o vacía
     * @throws EntityNotFoundException si no existe el álbum con {@code idAlbum}
     *                                 o el artista con {@code idArtista}
     */
    public void actualizarAlbum(Long idAlbum, Album albumActualizado, Long idArtista, List<Long> idCanciones) {
        Album albumExistente = albumRepository
            .findById(idAlbum)
            .orElseThrow(() -> new EntityNotFoundException(String.format("Album con id {id} no existe", idAlbum)));
        
        Artista artista = artistaRepository
            .findById(idArtista)
            .orElseThrow(() -> new EntityNotFoundException(String.format("El artista con id {id}, no se encontró", idArtista)));

        albumExistente.setTitulo(albumActualizado.getTitulo());
        albumExistente.setGenero(albumActualizado.getGenero());
        albumExistente.setFechaEstreno(albumActualizado.getFechaEstreno());
        albumExistente.setArtista(artista);

        if(idCanciones != null && !idCanciones.isEmpty()){
            List<Cancion> listaCanciones = cancionRepository.findAllById(idCanciones);
            for(Cancion cancion: listaCanciones) {
                if(!albumExistente.getCanciones().contains(cancion)) {
                    cancion.setAlbum(albumExistente);
                    albumExistente.getCanciones().add(cancion);
                }
            }
        }

        albumRepository.save(albumExistente);
    }
}
