package co.dev.cfd.kmusic.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import co.dev.cfd.kmusic.service.ArtistaService;
import lombok.AllArgsConstructor;

@Controller
@AllArgsConstructor
@RequestMapping(value="/artista")
public class ArtistaViewController {

    private final ArtistaService artistaService;

    @GetMapping("/artistas")
    public String listar(Model model) {
        model.addAttribute("artista", artistaService.listarArtistas());
        return "listaArtistas";
    }
}
