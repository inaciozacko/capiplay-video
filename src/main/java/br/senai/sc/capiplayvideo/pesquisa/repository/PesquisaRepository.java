package br.senai.sc.capiplayvideo.pesquisa.repository;

import br.senai.sc.capiplayvideo.video.model.entity.Video;
import br.senai.sc.capiplayvideo.video.projection.VideoMiniaturaProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PesquisaRepository extends JpaRepository<Video, String> {

    @Query(value = "SELECT * FROM video, categoria, tag " +
            "WHERE MATCH(titulo) AGAINST(:searchTerm IN BOOLEAN MODE) " +
            "OR MATCH(categoria_string) AGAINST(:searchTerm)" +
            "OR MATCH(tag) AGAINST(:searchTerm)" +
            "GROUP BY video.uuid" +
            " AND categoria_id != categoria.id",
            nativeQuery = true)
//    SELECT *, MATCH(video.titulo) AGAINST('titulo' IN BOOLEAN MODE) AS VideoScore,
//    MATCH(categoria.categoria_string) AGAINST('titulo' IN BOOLEAN MODE) AS CategoriaScore,
//    MATCH(tag.tag) AGAINST('titulo' IN BOOLEAN MODE) AS TagScore
//    FROM video, categoria, tag ORDER BY VideoScore DESC, CategoriaScore DESC, TagScore DESC;
    List<VideoMiniaturaProjection> searchBy(String searchTerm);

}
