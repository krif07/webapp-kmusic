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

    public Artista obteneArtistaPorId(Long id) {
        return artistaRepository
            .findById(id)
            .orElseThrow(() -> new EntityNotFoundException(String.format("Artista con id {id} no encontrado", id)));
    }

    public List<Artista> listarArtistas() {
        return artistaRepository.findAll();
    }

    public Artista guardarArtista(Artista artista) {
        return artistaRepository.save(artista);
    }
}
