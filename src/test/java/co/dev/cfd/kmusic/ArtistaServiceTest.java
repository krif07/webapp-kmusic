package co.dev.cfd.kmusic;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import co.dev.cfd.kmusic.model.Artista;
import co.dev.cfd.kmusic.service.ArtistaService;
import jakarta.persistence.EntityNotFoundException;

@SpringBootTest
public class ArtistaServiceTest {

    private Artista artistaGuardado;

    @Autowired
    private ArtistaService artistaService;


    @BeforeEach
    void setup() {
        Artista artista = new Artista();
        artista.setEmail("krif07@gmail.com");
        artista.setFechaNacimiento(LocalDate.now());
        artista.setNacionalidad("Colombiano");

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
