package co.dev.cfd.kmusic;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import co.dev.cfd.kmusic.model.*;
import co.dev.cfd.kmusic.repository.AlbumRepository;
import co.dev.cfd.kmusic.repository.ArtistaRepository;
import co.dev.cfd.kmusic.service.CancionService;
import jakarta.persistence.EntityNotFoundException;
import net.datafaker.Faker;

@SpringBootTest
public class CancionServiceTest {
      
    @Autowired
    private ArtistaRepository artistaRepository;
    @Autowired
    private AlbumRepository albumRepository;

    @Autowired
    private CancionService cancionService;

    Artista artistaGuardado;
    Album albumGuardado;
    Cancion cancionGuardada;

    @BeforeEach
    void setup() {
        Faker faker = new Faker();

        artistaGuardado = artistaRepository.save(Artista.builder()
            .nacionalidad(faker.country().name().toLowerCase())    
            .email(faker.internet().emailAddress())
            .fechaNacimiento(LocalDate.now().minusYears(faker.number().numberBetween(18, 60)))
            .build());

        albumGuardado = albumRepository.save(Album.builder()
            .titulo(faker.book().title())
            .genero(faker.music().genre().toLowerCase())
            .fechaEstreno(LocalDate.now().minusYears(faker.number().numberBetween(1, 200)))
            .artista(artistaGuardado)
            .build());

        Cancion cancion = Cancion.builder()
            .nombre(faker.music().chord())
            .duracion(BigDecimal.valueOf(faker.number().numberBetween(1, 1000)))
            .build();

        cancionGuardada = cancionService.guardarCancion(cancion, albumGuardado.getId());
    }
    
    @Test
    void testGuardarCancion() {
        assertNotNull(cancionGuardada.getId());
        assertNotNull(cancionGuardada.getAlbum());
        assertEquals(albumGuardado.getId(), cancionGuardada.getAlbum().getId());
        assertNotNull(cancionGuardada.getNombre());
        assertNotNull(cancionGuardada.getDuracion());
    }

    @Test
    void testListarCanciones() {
        List<Cancion> canciones = cancionService.listarCanciones();
        assertFalse(canciones.isEmpty());
    }

    @Test
    void testObtenerCancionPorId() {
        Cancion cancion = cancionService.obtenerCancionPorId(cancionGuardada.getId());
        assertNotNull(cancion.getId());
        assertEquals(cancionGuardada.getId(), cancion.getId());
        assertEquals(cancionGuardada.getNombre(), cancion.getNombre());
    }

    @Test
    void testObtenerCancionPorIdInexistente() {
        Long idCancionInexistente = Long.MAX_VALUE;
        
        EntityNotFoundException ex = assertThrows(
            EntityNotFoundException.class,
            () -> cancionService.obtenerCancionPorId(idCancionInexistente));
        
        assertNotNull(ex);
        assertTrue(ex.getMessage().contains(String.format("No se encontró la canción con el id: %d", idCancionInexistente)));
    }

    @Test
    void testEliminarCancion() {
        Long id = cancionGuardada.getId();

        assertNotNull(cancionService.obtenerCancionPorId(id));
        cancionService.eliminarCancion(id);
        EntityNotFoundException ex = assertThrows(
            EntityNotFoundException.class,
            () -> cancionService.obtenerCancionPorId(id));
        
        assertNotNull(ex);
        assertEquals(ex.getMessage(), String.format("No se encontró la canción con el id: %d", id));
    }
}
