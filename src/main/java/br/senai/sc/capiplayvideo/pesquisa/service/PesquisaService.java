package br.senai.sc.capiplayvideo.pesquisa.service;

import br.senai.sc.capiplayvideo.pesquisa.repository.PesquisaRepository;
import br.senai.sc.capiplayvideo.video.model.entity.Video;
import br.senai.sc.capiplayvideo.video.projection.VideoMiniaturaProjection;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class PesquisaService {

    private PesquisaRepository pesquisaRepository;

    public List<VideoMiniaturaProjection> buscarVideos(String pesquisa) {
//        return pesquisaRepository.searchByTituloOrTagsOrCategoria(pesquisa);
        return pesquisaRepository.searchBy(pesquisa);
    }

}
