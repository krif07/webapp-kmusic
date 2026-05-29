package co.dev.cfd.kmusic.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import co.dev.cfd.kmusic.service.CancionService;
import lombok.AllArgsConstructor;

@Controller
@AllArgsConstructor
public class CancionViewController {
    
    private final CancionService cancionService;

    @GetMapping(value="canciones")
    public String listar(Model model) {
        model.addAttribute("canciones", cancionService.listarCanciones());
        return "listaCanciones";
    }
}
