package co.dev.cfd.kmusic.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import co.dev.cfd.kmusic.model.Album;
import co.dev.cfd.kmusic.service.AlbumService;
import co.dev.cfd.kmusic.service.CancionService;
import lombok.AllArgsConstructor;





@Controller
@AllArgsConstructor
@RequestMapping(value="/album")
public class AlbumViewController {
  
    private final AlbumService albumService;
    private final CancionService cancionService;

    @GetMapping("/listar")
    public String listar(Model model) {
        model.addAttribute("albumes", albumService.obtenerAlbumes());

        return "listarAlbumes";
    }

    @GetMapping("/agregar")
    public String mostrarFormularioNuevoAlbum(Model model) {
        model.addAttribute("album", new Album());
        model.addAttribute("canciones", cancionService.listarCanciones());

        return "agregarAlbumForm";
    }

    @PostMapping("/guardar")
    public String guardarAlbum(@ModelAttribute Album album, 
            @RequestParam Long idArtista, @RequestParam(required=false) List<Long> idCanciones) {
        
        albumService.guardarAlbum(album, idArtista, idCanciones);
        return "redirect:/album/listar";
    }

    @GetMapping("/actualizar/{id}")
    public String mostrarFormActualizarAlbum(@PathVariable Long id, Model model) {
        model.addAttribute("album", albumService.obtenerAlbumPorId(id));
        model.addAttribute("canciones", cancionService.listarCanciones());
        
        return "actualizarAlbumForm";
    }

    @PostMapping("/actualizar/{idAlbum}")
    public String actualizarAlbum(@PathVariable Long idAlbum, 
                                @ModelAttribute Album albumActualizado, 
                                @RequestParam Long idArtista,
                                @RequestParam(required=false) List<Long> idCanciones) {
        
        albumService.actualizarAlbum(idAlbum, albumActualizado, idArtista, idCanciones);
        return "redirect:/album/listar";
    }
    
    @GetMapping("/eliminar/{id}")
    public String eliminarAlbum(@PathVariable Long id) {
        albumService.eliminarAlbum(id);
        return "redirect:/album/listar";
    }
}
