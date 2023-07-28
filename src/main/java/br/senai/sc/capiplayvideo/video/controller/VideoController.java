package br.senai.sc.capiplayvideo.video.controller;

import br.senai.sc.capiplayvideo.filtro.modal.entity.Filtro;
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
import org.springframework.data.web.PageableDefault;
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
            @RequestParam("tags") List<Tag> tags,
            @RequestParam("categoria") Categoria categoria,
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

    @GetMapping("/videos")
    public Page<Video> filtrarVideos(@RequestParam(required = false) String pesquisa,
                                     @RequestParam(required = false) boolean filtroDia,
                                     @RequestParam(required = false) boolean filtroSemana,
                                     @RequestParam(required = false) boolean filtroMes,
                                     @RequestParam(required = false) boolean filtroAno,
                                     @RequestParam(required = false) boolean filtroMenosDe5Min,
                                     @RequestParam(required = false) boolean filtroEntre5E20Min,
                                     @RequestParam(required = false) boolean filtroMaisDe20Min,
                                     @RequestParam(required = false) boolean filtroVideo,
                                     @RequestParam(required = false) boolean filtroShorts,
                                     @PageableDefault(page = 0, size = 10) Pageable pageable) {

        Filtro filtro = new Filtro(filtroDia, filtroSemana, filtroMes, filtroAno,
                filtroMenosDe5Min, filtroEntre5E20Min, filtroMaisDe20Min,
                filtroVideo, filtroShorts);

        return service.filtrarVideos(pesquisa, filtro, pageable);
    }

    @DeleteMapping("/{uuid}")
    public void deletar(@PathVariable String uuid) {
        service.deletar(uuid);
    }

}
