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

    public Album obtenerAlbumPorId(Long id) {
        return albumRepository
            .findById(id)
            .orElseThrow(() -> new EntityNotFoundException(String.format("Album con id {id} no encontrado", id)));
    }

    public List<Album> obtenerAlbumes() {
        return albumRepository.findAll();
    }

    public void eliminarAlbum(Long id) {
        Album album = albumRepository
            .findById(id)
            .orElseThrow(() -> new EntityNotFoundException(String.format("No se puede eliminar el album con {id}", id)));
        albumRepository.delete(album);
    }

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
