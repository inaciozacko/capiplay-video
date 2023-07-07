package br.senai.sc.capiplayvideo.video.model.dto;

import br.senai.sc.capiplayvideo.video.model.entity.Categoria;
import br.senai.sc.capiplayvideo.video.model.entity.Tag;
import lombok.Getter;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Getter
public class VideoDTO {

    private final String titulo;

    private final String descricao;

    private final List<String> tags;

    private final String categoria;

    private final MultipartFile video;

    private final MultipartFile miniatura;

    public VideoDTO(String titulo, String descricao, List<String> tags,
                    String categoria, MultipartFile video, MultipartFile miniatura) {
        this.titulo = titulo;
        this.descricao = descricao;
        this.tags = tags;
        this.categoria = categoria;
        this.video = video;
        this.miniatura = miniatura;
    }
}
