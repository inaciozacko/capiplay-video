package br.senai.sc.capiplayvideo.pesquisa.repository;

import br.senai.sc.capiplayvideo.video.model.entity.Video;
import br.senai.sc.capiplayvideo.video.model.projection.VideoMiniaturaProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PesquisaRepository extends JpaRepository<Video, String> {

    @Query(value = "SELECT *, MATCH(video.titulo) AGAINST(CONCAT('*', :searchTerm, '*') IN BOOLEAN MODE) * 3 +" +
            " (SELECT MAX(MATCH(categoria.categoria_string) AGAINST(CONCAT('*', :searchTerm, '*') IN BOOLEAN MODE) * 2)" +
            " FROM categoria WHERE video.categoria_id = categoria.id) +" +
            " (SELECT MAX(MATCH(tag.tag) AGAINST(CONCAT('*', :searchTerm, '*') IN BOOLEAN MODE)) " +
            "FROM tag, video_tags WHERE video_tags.tags_tag = tag.tag and video_tags.video_uuid = video.uuid) " +
            "AS TotalScore FROM video group by video.titulo HAVING TotalScore > 0 ORDER BY TotalScore DESC;",
            nativeQuery = true)
    List<VideoMiniaturaProjection> searchBy(String searchTerm);

}
