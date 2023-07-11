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


    public List<Video> filtrarVideos(Filtro filtro) {
        List<Video> videosFiltrados = new ArrayList<>();

        // Verificar o filtro de data de publicação
        if (filtro.isFiltroDia()) {
            LocalDate dataPublicacao = LocalDate.now();
            List<Video> videosDoDia = repository.findByDataPublicacaoAfter(dataPublicacao);
            videosFiltrados.addAll(videosDoDia);
        } else if (filtro.isFiltroSemana()) {
            LocalDate dataPublicacao = LocalDate.now().minusWeeks(1);
            List<Video> videosDaSemana = repository.findByDataPublicacaoAfter(dataPublicacao);
            videosFiltrados.addAll(videosDaSemana);
        } else if (filtro.isFiltroMes()) {
            LocalDate dataPublicacao = LocalDate.now().minusMonths(1);
            List<Video> videosDoMes = repository.findByDataPublicacaoAfter(dataPublicacao);
            videosFiltrados.addAll(videosDoMes);
        }else if (filtro.isFiltroAno()) {
            LocalDate dataPublicacao = LocalDate.now().minusYears(1);
            List<Video> videosDoAno = repository.findByDataPublicacaoAfter(dataPublicacao);
            videosFiltrados.addAll(videosDoAno);
        } else {
            videosFiltrados.addAll(repository.findAll());
        }

        // Verificar o filtro de duração
        List<Video> videosComDuracaoFiltrada = new ArrayList<>();
        if (filtro.isFiltroMenosDe5Min()) {
            videosComDuracaoFiltrada.addAll(filtrarPorDuracao(videosFiltrados, 0, 5));
        }
        if (filtro.isFiltroEntre5E20Min()) {
            videosComDuracaoFiltrada.addAll(filtrarPorDuracao(videosFiltrados, 5, 20));
        }
        if (filtro.isFiltroMaisDe20Min()) {
            videosComDuracaoFiltrada.addAll(filtrarPorDuracao(videosFiltrados, 20, Integer.MAX_VALUE));
        }
        if (videosComDuracaoFiltrada.isEmpty()) {
            videosComDuracaoFiltrada.addAll(videosFiltrados); // Nenhum filtro de duração selecionado, manter vídeos filtrados até o momento
        }

        // Verificar o filtro de tipo
        List<Video> videosFiltradosPorTipo = new ArrayList<>();
        if (filtro.isFiltroVideo()) {
            videosFiltradosPorTipo.addAll(filtrarPorTipo(videosComDuracaoFiltrada, "video"));
        }
        if (filtro.isFiltroShorts()) {
            videosFiltradosPorTipo.addAll(filtrarPorTipo(videosComDuracaoFiltrada, "shorts"));
        }
        if (videosFiltradosPorTipo.isEmpty()) {
            videosFiltradosPorTipo.addAll(videosComDuracaoFiltrada); // Nenhum filtro de tipo selecionado, manter vídeos filtrados até o momento
        }

        return videosFiltradosPorTipo;
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