package br.senai.sc.capiplayvideo.video.service;

import br.senai.sc.capiplayvideo.filtro.modal.entity.Filtro;
import br.senai.sc.capiplayvideo.video.exceptions.ObjetoInexistenteException;
import br.senai.sc.capiplayvideo.video.model.dto.VideoDTO;
import br.senai.sc.capiplayvideo.video.model.entity.Categoria;
import br.senai.sc.capiplayvideo.video.model.entity.Video;
import br.senai.sc.capiplayvideo.video.projection.VideoMiniaturaProjection;
import br.senai.sc.capiplayvideo.video.projection.VideoProjection;
import br.senai.sc.capiplayvideo.video.repository.VideoRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class VideoService {

    private final VideoRepository repository;

    public void salvar(VideoDTO videoDTO) {
        String pasta = "D:\\capiplay-video";
        try {
            Path arquivoTemporario = Files.createTempFile(Path.of(pasta), "video_", "_temp");
            videoDTO.getVideo().transferTo(arquivoTemporario.toFile());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Page<VideoMiniaturaProjection> buscarTodos(Pageable pageable) {
        return repository.findAllBy(pageable);
    }

    public Page<VideoMiniaturaProjection> buscarPorCategoria(Pageable pageable, Categoria categoria) {
        return repository.findAllByCategoria(categoria, pageable);
    }

    public VideoProjection buscarUm(String uuid) {
        return repository.findByUuid(uuid).orElseThrow(ObjetoInexistenteException::new);
    }

    public void deletar(String uuid) {
        repository.deleteById(uuid);
    }


    public Page<Video> filtrarVideos(String pesquisa, Filtro filtro, Pageable pageable) {
        List<Video> videosFiltrados = new ArrayList<>();


        // Adicionar o que retornou da parte da pesquisa (Pedro) ao videosFiltrados antes da próxima parte

        // Filtrar por pesquisa
        if (pesquisa != null && !pesquisa.isEmpty()) {
            videosFiltrados.addAll(filtrarPorPesquisa(repository.findAll(), pesquisa));
        } else {
            videosFiltrados.addAll(repository.findAll());
        }

        // Verificar os filtros de data de publicação
        if (filtro.isFiltroDia()) {
            LocalDate dataPublicacao = LocalDate.now();
            List<Video> videosDoDia = repository.findByDataPublicacaoAfter(dataPublicacao);
            videosFiltrados.retainAll(videosDoDia);
        } else if (filtro.isFiltroSemana()) {
            LocalDate dataPublicacao = LocalDate.now().minusWeeks(1);
            List<Video> videosDaSemana = repository.findByDataPublicacaoAfter(dataPublicacao);
            videosFiltrados.retainAll(videosDaSemana);
        } else if (filtro.isFiltroMes()) {
            LocalDate dataPublicacao = LocalDate.now().minusMonths(1);
            List<Video> videosDoMes = repository.findByDataPublicacaoAfter(dataPublicacao);
            videosFiltrados.retainAll(videosDoMes);
        } else if (filtro.isFiltroAno()) {
            LocalDate dataPublicacao = LocalDate.now().minusYears(1);
            List<Video> videosDoAno = repository.findByDataPublicacaoAfter(dataPublicacao);
            videosFiltrados.retainAll(videosDoAno);
        }

        // Verificar o filtro de duração
        if (filtro.isFiltroMenosDe5Min()) {
            videosFiltrados.retainAll(filtrarPorDuracao(videosFiltrados, 0, 5));
        }
        if (filtro.isFiltroEntre5E20Min()) {
            videosFiltrados.retainAll(filtrarPorDuracao(videosFiltrados, 5, 20));
        }
        if (filtro.isFiltroMaisDe20Min()) {
            videosFiltrados.retainAll(filtrarPorDuracao(videosFiltrados, 20, Integer.MAX_VALUE));
        }

        // Verificar o filtro de tipo
        if (filtro.isFiltroVideo()) {
            videosFiltrados.retainAll(filtrarPorTipo(videosFiltrados, "video"));
        }
        if (filtro.isFiltroShorts()) {
            videosFiltrados.retainAll(filtrarPorTipo(videosFiltrados, "shorts"));
        }

        int start = (int) pageable.getOffset();
        int end = Math.min((start + pageable.getPageSize()), videosFiltrados.size());

        return new PageImpl<>(videosFiltrados.subList(start, end), pageable, videosFiltrados.size());
    }

    private List<Video> filtrarPorPesquisa(List<Video> videos, String pesquisa) {
        List<Video> videosFiltrados = new ArrayList<>();
        for (Video video : videos) {
            if (video.getTitulo().toLowerCase().contains(pesquisa.toLowerCase())) {
                videosFiltrados.add(video);
            }
        }
        return videosFiltrados;
    }

    private List<Video> filtrarPorDuracao(List<Video> videos, int duracaoMinima, int duracaoMaxima) {
        List<Video> videosFiltrados = new ArrayList<>();
        for (Video video : videos) {
            float duracao = video.getTempoDuracao();
            if (duracao >= duracaoMinima && duracao <= duracaoMaxima) {
                videosFiltrados.add(video);
            }
        }
        return videosFiltrados;
    }

    private List<Video> filtrarPorTipo(List<Video> videos, String tipo) {
        List<Video> videosFiltrados = new ArrayList<>();
        for (Video video : videos) {
            String tipoVideo = video.getTipo();
            if (tipoVideo.equals(tipo)) {
                videosFiltrados.add(video);
            }
        }
        return videosFiltrados;
    }
}