package br.senai.sc.capiplayvideo.pesquisa.controller;

import br.senai.sc.capiplayvideo.pesquisa.service.PesquisaService;
import br.senai.sc.capiplayvideo.video.model.projection.VideoMiniaturaProjection;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/pesquisa")
public class PesquisaController {

    private PesquisaService pesquisaService;

    @GetMapping("/{pesquisa}")
    public List<VideoMiniaturaProjection> buscarVideos(@PathVariable String pesquisa) {
        return pesquisaService.buscarVideos(pesquisa);
    }
}
