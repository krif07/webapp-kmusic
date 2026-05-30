package co.dev.cfd.kmusic.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import co.dev.cfd.kmusic.model.Cancion;
import co.dev.cfd.kmusic.service.AlbumService;
import co.dev.cfd.kmusic.service.CancionService;
import lombok.AllArgsConstructor;



@Controller
@AllArgsConstructor
@RequestMapping(value="/cancion")
public class CancionViewController {
    
    private final CancionService cancionService;
    private final AlbumService albumService;

    @GetMapping(value="/canciones")
    public String listar(Model model) {
        model.addAttribute("canciones", cancionService.listarCanciones());
        return "listaCanciones";
    }

    @GetMapping("/agregarCancion")
    public String mostrarFormularioNuevaCancion(Model model) {
        model.addAttribute("cancion", new Cancion());
        model.addAttribute("albumnes", albumService.obtenerAlbumes());

        return "agregarCancionForm";
    }
@PostMapping("/guardarCancion")
public String guardarCancion(@ModelAttribute Cancion cancion, @RequestParam Long albumId) {
    cancionService.guardarCancion(cancion, albumId);
    return "redirect:/cancion/canciones";
}

}
