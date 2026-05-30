package co.dev.cfd.kmusic;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import co.dev.cfd.kmusic.model.Artista;
import co.dev.cfd.kmusic.service.ArtistaService;
import jakarta.persistence.EntityNotFoundException;
import net.datafaker.Faker;

@SpringBootTest
public class ArtistaServiceTest {

    private Artista artistaGuardado;

    @Autowired
    private ArtistaService artistaService;


    @BeforeEach
    void setup() {
        Faker faker = new Faker();
        
        Artista artista = new Artista();
        artista.setEmail(faker.internet().emailAddress(faker.name().fullName().toLowerCase()));
        artista.setFechaNacimiento(LocalDate.now().minusYears(faker.number().numberBetween(18, 60)));
        artista.setNacionalidad(faker.country().name().toLowerCase());

        artistaGuardado = artistaService.guardarArtista(artista);
    }

    @Test
    void testListarArtistas(){
        assertFalse(artistaService.listarArtistas().isEmpty());
    }

    @Test
    void testValidarArtistaGuardado() {
        Artista artista = artistaService.obtenerArtistaPorId(artistaGuardado.getId());

        assertNotNull(artista);
        assertEquals(artistaGuardado.getId(), artista.getId());
    }

    @Test
    void testObtenerArtistaInexistente() {
        Long idInexistente = Long.MAX_VALUE;

        EntityNotFoundException exception = assertThrows(
            EntityNotFoundException.class,
            () -> artistaService.obtenerArtistaPorId(idInexistente));

        assertNotNull(exception);
        assertTrue(exception.getMessage().contains(String.format("Artista con id %d no encontrado", idInexistente)));
    }
}
