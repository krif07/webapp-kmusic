package co.dev.cfd.kmusic.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import co.dev.cfd.kmusic.model.Album;
import co.dev.cfd.kmusic.service.AlbumService;
import co.dev.cfd.kmusic.service.ArtistaService;
import co.dev.cfd.kmusic.service.CancionService;
import lombok.AllArgsConstructor;



@Controller
@AllArgsConstructor
@RequestMapping(value="/album")
public class AlbumViewController {
 
    private final AlbumService albumService;
    private final ArtistaService artistaService;
    private final CancionService cancionService;

    @GetMapping("/albumes")
    public String listar(Model model) {
        model.addAttribute("albumes", albumService.obtenerAlbumes());

        return "listarAlbumes";
    }

    @GetMapping("/agregarAlbum")
    public String mostrarFormularioNuevoAlbum(Model model) {
        model.addAttribute("album", new Album());
        model.addAttribute("artistas", artistaService.listarArtistas());
        model.addAttribute("canciones", cancionService.listarCanciones());

        return "agregarAlbumForm";
    }

    @PostMapping("/guardarAlbum")
    public String postMethodName(@ModelAttribute Album album, 
            @RequestParam Long idArtista, @RequestParam List<Long> idCanciones) {
        albumService.guardarAlbum(album, idArtista, idCanciones);
        return "redirect:/album/albumes";
    }
}
