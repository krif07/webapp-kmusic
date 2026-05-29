package co.dev.cfd.kmusic;

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import co.dev.cfd.kmusic.model.*;
import co.dev.cfd.kmusic.repository.AlbumRepository;
import co.dev.cfd.kmusic.repository.ArtistaRepository;
import co.dev.cfd.kmusic.service.CancionService;
import net.datafaker.Faker;

@SpringBootTest
public class CancionServiceTest {
      
    private ArtistaRepository artistaRepository;
    private AlbumRepository albumRepository;

    @Autowired
    private CancionService cancionService;

    Artista artistaGuardado;
    Album albumGuardado;
    Cancion cancionGuardada;

    @BeforeEach
    void setup() {
        Faker faker = new Faker();

        artistaGuardado = Artista.builder()
            .nacionalidad(faker.country().name().toLowerCase())    
            .email(faker.internet().emailAddress())
            .fechaNacimiento(LocalDate.now().minusYears(faker.number().numberBetween(18, 60)))
            .build();

        albumGuardado = Album.builder()
            .titulo(faker.book().title())
            .genero(faker.music().genre().toLowerCase())
            .fechaEstreno(LocalDate.now().minusYears(faker.number().numberBetween(1, 200)))
            .artista(artistaGuardado)
            .build();

        Cancion cancion = Cancion.builder()
            .nombre(faker.music().chord())
            .duracion(BigDecimal.valueOf(faker.number().numberBetween(1, 1000)))
            .album(albumGuardado)
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

    }

}
