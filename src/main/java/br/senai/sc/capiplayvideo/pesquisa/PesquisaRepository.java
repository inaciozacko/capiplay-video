package br.senai.sc.capiplayvideo.pesquisa;

import br.senai.sc.capiplayvideo.video.model.entity.Video;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PesquisaRepository extends JpaRepository<Video, String> {

    @Query(value = "SELECT * FROM video, tag, categoria " +
            "WHERE MATCH(titulo) AGAINST(:searchTerm) OR " +
            "MATCH(categoria) AGAINST(:searchTerm) OR " +
            "MATCH(tag) AGAINST(:searchTerm)",
            nativeQuery = true)
    List<Video> searchByTitulo(@Param("searchTerm") String searchTerm);

}
