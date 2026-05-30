package co.dev.cfd.kmusic;

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
import co.dev.cfd.kmusic.repository.ArtistaRepository;
import co.dev.cfd.kmusic.service.AlbumService;
import jakarta.persistence.EntityNotFoundException;
import net.datafaker.Faker;

@SpringBootTest
public class AlbumServiceTest {

    @Autowired
    private ArtistaRepository artistaRepository;

    @Autowired
    private AlbumService albumService;

    Artista artistaGuardado;
    Album albumGuardado;

    @BeforeEach
    void setup() {
        Faker faker = new Faker();

        artistaGuardado = artistaRepository.save(Artista.builder()
            .nacionalidad(faker.country().name().toLowerCase())
            .email(faker.internet().emailAddress())
            .fechaNacimiento(LocalDate.now().minusYears(faker.number().numberBetween(18, 60)))
            .build());

        Album album = Album.builder()
            .titulo(faker.book().title())
            .genero(faker.music().genre().toLowerCase())
            .fechaEstreno(LocalDate.now().minusYears(faker.number().numberBetween(1, 200)))
            .build();

        albumGuardado = albumService.guardarAlbum(album, artistaGuardado.getId(), null);
    }

    @Test
    void testGuardarAlbum() {
        assertNotNull(albumGuardado.getId());
        assertNotNull(albumGuardado.getArtista());
        assertEquals(artistaGuardado.getId(), albumGuardado.getArtista().getId());
        assertNotNull(albumGuardado.getTitulo());
        assertNotNull(albumGuardado.getGenero());
        assertNotNull(albumGuardado.getFechaEstreno());
    }

    @Test
    void testListarAlbumes() {
        List<Album> albumes = albumService.obtenerAlbumes();
        assertFalse(albumes.isEmpty());
    }

    @Test
    void testObtenerAlbumPorId() {
        Album album = albumService.obtenerAlbumPorId(albumGuardado.getId());
        assertNotNull(album.getId());
        assertEquals(albumGuardado.getId(), album.getId());
        assertEquals(albumGuardado.getTitulo(), album.getTitulo());
    }

    @Test
    void testObtenerAlbumPorIdInexistente() {
        Long idInexistente = Long.MAX_VALUE;

        EntityNotFoundException ex = assertThrows(
            EntityNotFoundException.class,
            () -> albumService.obtenerAlbumPorId(idInexistente));

        assertNotNull(ex);
        assertTrue(ex.getMessage().contains(String.format("Album con id %d no encontrado", idInexistente)));
    }

    @Test
    void testEliminarAlbum() {
        Long id = albumGuardado.getId();

        assertNotNull(albumService.obtenerAlbumPorId(id));
        albumService.eliminarAlbum(id);
        EntityNotFoundException ex = assertThrows(
            EntityNotFoundException.class,
            () -> albumService.obtenerAlbumPorId(id));

        assertNotNull(ex);
        assertEquals(ex.getMessage(), String.format("Album con id %d no encontrado", id));
    }

    @Test
    void testActualizarAlbum() {
        Faker faker = new Faker();
        Long id = albumGuardado.getId();

        Album albumActualizado = Album.builder()
            .titulo(faker.book().title())
            .genero(faker.music().genre().toLowerCase())
            .fechaEstreno(LocalDate.now().minusYears(faker.number().numberBetween(1, 200)))
            .build();

        albumService.actualizarAlbum(id, albumActualizado, artistaGuardado.getId(), null);

        Album album = albumService.obtenerAlbumPorId(id);
        assertEquals(albumActualizado.getTitulo(), album.getTitulo());
        assertEquals(albumActualizado.getGenero(), album.getGenero());
    }

    @Test
    void testActualizarAlbumInexistente() {
        Faker faker = new Faker();
        Long idInexistente = Long.MAX_VALUE;

        Album albumActualizado = Album.builder()
            .titulo(faker.book().title())
            .build();

        EntityNotFoundException ex = assertThrows(
            EntityNotFoundException.class,
            () -> albumService.actualizarAlbum(idInexistente, albumActualizado, artistaGuardado.getId(), null));

        assertNotNull(ex);
        assertTrue(ex.getMessage().contains(String.format("Album con id %d no existe", idInexistente)));
    }
}
