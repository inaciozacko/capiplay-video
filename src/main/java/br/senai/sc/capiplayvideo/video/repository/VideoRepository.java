package br.senai.sc.capiplayvideo.video.repository;

import br.senai.sc.capiplayvideo.video.model.entity.Categoria;
import br.senai.sc.capiplayvideo.video.model.entity.Video;
import br.senai.sc.capiplayvideo.video.projection.VideoMiniaturaProjection;
import br.senai.sc.capiplayvideo.video.projection.VideoProjection;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface VideoRepository extends JpaRepository<Video, String> {

    Page<VideoMiniaturaProjection> findAllBy(Pageable pageable);

    Page<VideoMiniaturaProjection> findAllByCategoria(Categoria categoria, Pageable pageable);

    Optional<VideoProjection> findByUuid(String uuid);

    List<Video> findByDataPublicacaoAfter(LocalDate data);
    List<Video> findByDuracaoLessThanEqual(int duracao);
    List<Video> findByTipo(String tipo);

}
