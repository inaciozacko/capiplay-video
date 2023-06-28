package br.senai.sc.capiplayvideo.controller;

import br.senai.sc.capiplayvideo.model.dto.VideoDTO;
import br.senai.sc.capiplayvideo.model.entity.Categoria;
import br.senai.sc.capiplayvideo.model.entity.Video;
import br.senai.sc.capiplayvideo.projection.VideoMiniaturaProjection;
import br.senai.sc.capiplayvideo.service.VideoService;
import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/api/video")
public class VideoController {

    private final VideoService videoService;

    @PostMapping
    public ResponseEntity<Void> criar(@RequestBody VideoDTO videoDTO) {
        Video video = new Video();
        BeanUtils.copyProperties(videoDTO, video);
        videoService.salvar(video);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{uuid}") //Fazer um  retorno com uma DTO
    public ResponseEntity<Video> buscarUm(@PathVariable String uuid) {
        return ResponseEntity.ok(videoService.buscarUm(uuid));
    }

    @GetMapping
    public ResponseEntity<Page<VideoMiniaturaProjection>> buscarTodos(
            @RequestParam("pageable") Pageable pageable) {
        return ResponseEntity.ok(videoService.buscarTodos(pageable));
    }

    @GetMapping("/buscar-por-categoria")
    public ResponseEntity<Page<VideoMiniaturaProjection>> buscarPorCategoria(
            @RequestParam("categoria") Categoria categoria,
            @RequestParam("pageable") Pageable pageable) {
        return ResponseEntity.ok(videoService.buscarPorCategoria(pageable, categoria));
    }

    @DeleteMapping("/{uuid}")
    public ResponseEntity<Void> deletar(@PathVariable String uuid) {
        videoService.deletar(uuid);
        return ResponseEntity.ok().build();
    }

}
