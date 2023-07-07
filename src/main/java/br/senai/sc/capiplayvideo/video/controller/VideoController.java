package br.senai.sc.capiplayvideo.video.controller;

import br.senai.sc.capiplayvideo.video.command.CriarVideoCommand;
import br.senai.sc.capiplayvideo.video.model.dto.VideoDTO;
import br.senai.sc.capiplayvideo.video.model.entity.Categoria;
import br.senai.sc.capiplayvideo.video.model.entity.Tag;
import br.senai.sc.capiplayvideo.video.model.entity.Video;
import br.senai.sc.capiplayvideo.video.projection.VideoMiniaturaProjection;
import br.senai.sc.capiplayvideo.video.projection.VideoProjection;
import br.senai.sc.capiplayvideo.video.service.VideoService;
import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/video")
public class VideoController {

    private final VideoService service;

    @PostMapping
    public void criar(
            @RequestParam("titulo") String titulo,
            @RequestParam("descricao") String descricao,
            @RequestParam("tags") List<String> tags,
            @RequestParam("categoria") String categoria,
            @RequestParam("video") MultipartFile video,
            @RequestParam("miniatura") MultipartFile miniatura
    ) {
        service.salvar(new VideoDTO(titulo, descricao, tags, categoria, video, miniatura));
    }

    @GetMapping("/{uuid}")
    public VideoProjection buscarUm(@PathVariable String uuid) {
        return service.buscarUm(uuid);
    }

    @GetMapping
    public Page<VideoMiniaturaProjection> buscarTodos(
            @RequestParam("pageable") Pageable pageable) {
        return service.buscarTodos(pageable);
    }

    @GetMapping("/buscar-por-categoria")
    public Page<VideoMiniaturaProjection> buscarPorCategoria(
            @RequestParam("categoria") Categoria categoria,
            @RequestParam("pageable") Pageable pageable
    ) {
        return service.buscarPorCategoria(pageable, categoria);
    }

    @DeleteMapping("/{uuid}")
    public void deletar(@PathVariable String uuid) {
        service.deletar(uuid);
    }

}
