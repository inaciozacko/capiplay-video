package br.senai.sc.capiplayvideo.service;

import br.senai.sc.capiplayvideo.exceptions.ObjetoInexistenteException;
import br.senai.sc.capiplayvideo.model.entity.Categoria;
import br.senai.sc.capiplayvideo.model.entity.Video;
import br.senai.sc.capiplayvideo.projection.VideoMiniaturaProjection;
import br.senai.sc.capiplayvideo.repository.VideoRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class VideoService {

    private final VideoRepository videoRepository;

    public void salvar(Video video) {
        videoRepository.save(video);
    }

    public Page<VideoMiniaturaProjection> buscarTodos(Pageable pageable) {
        return videoRepository.findAllBy(pageable);
    }

    public Page<VideoMiniaturaProjection> buscarPorCategoria(Pageable pageable, Categoria categoria) {
        return videoRepository.findAllByCategoria(categoria, pageable);
    }

    public Video buscarUm(String uuid) {
        return videoRepository.findById(uuid).orElseThrow(ObjetoInexistenteException::new);
    }

    public void deletar(String uuid) {
        videoRepository.deleteById(uuid);
    }
}
