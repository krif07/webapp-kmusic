package co.dev.cfd.kmusic.service;

import java.util.List;

import org.springframework.stereotype.Service;

import co.dev.cfd.kmusic.model.Artista;
import co.dev.cfd.kmusic.repository.ArtistaRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class ArtistaService {
    
    private final ArtistaRepository artistaRepository;

    /**
     * Busca y retorna un artista por su identificador único.
     *
     * @param id identificador del artista
     * @return el artista encontrado
     * @throws EntityNotFoundException si no existe un artista con ese id
     */
    public Artista obteneArtistaPorId(Long id) {
        return artistaRepository
            .findById(id)
            .orElseThrow(() -> new EntityNotFoundException(String.format("Artista con id {id} no encontrado", id)));
    }

    /**
     * Retorna la lista completa de artistas registrados.
     *
     * @return lista de artistas; vacía si no hay ninguno
     */
    public List<Artista> listarArtistas() {
        return artistaRepository.findAll();
    }

    /**
     * Persiste un nuevo artista en la base de datos.
     *
     * @param artista datos del artista a guardar
     * @return el artista persistido con su id generado
     */
    public Artista guardarArtista(Artista artista) {
        return artistaRepository.save(artista);
    }
}
