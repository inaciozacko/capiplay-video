
package br.senai.sc.capiplayvideo.video.model.dto;

import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public record VideoDTO(String titulo,
                       String descricao,
                       List<String> tags,
                       String categoria,
                       MultipartFile video,
                       MultipartFile miniatura) {

}
