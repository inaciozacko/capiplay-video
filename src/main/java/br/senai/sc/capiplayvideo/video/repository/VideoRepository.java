package br.senai.sc.capiplayvideo.video.repository;

import br.senai.sc.capiplayvideo.video.model.entity.Categoria;
import br.senai.sc.capiplayvideo.video.model.entity.Video;
import br.senai.sc.capiplayvideo.video.projection.VideoMiniaturaProjection;
import br.senai.sc.capiplayvideo.video.projection.VideoProjection;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface VideoRepository extends JpaRepository<Video, String> {

    Page<VideoMiniaturaProjection> findAllBy(Pageable pageable);

    Page<VideoMiniaturaProjection> findAllByCategoria(Categoria categoria, Pageable pageable);

    Optional<VideoProjection> findByUuid(String uuid);

    @Query(value = "SELECT * FROM video WHERE MATCH(titulo,tags) AGAINST('teste')", nativeQuery = true)
    List<Video> searchByTitulo(@Param("searchTerm") String searchTerm);

}
