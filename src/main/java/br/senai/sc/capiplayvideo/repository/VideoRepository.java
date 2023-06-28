package br.senai.sc.capiplayvideo.repository;

import br.senai.sc.capiplayvideo.model.entity.Categoria;
import br.senai.sc.capiplayvideo.model.entity.Video;
import br.senai.sc.capiplayvideo.projection.VideoMiniaturaProjection;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VideoRepository extends JpaRepository<Video, String> {

    Page<VideoMiniaturaProjection> findAllBy(Pageable pageable);

    Page<VideoMiniaturaProjection> findAllByCategoria(Categoria categoria, Pageable pageable);

}
