package br.senai.sc.capiplayvideo.video.repository;

import br.senai.sc.capiplayvideo.video.model.entity.Categoria;
import br.senai.sc.capiplayvideo.video.model.enums.CategoriasEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CategoriaRepository extends JpaRepository<Categoria, Long> {

    Optional<Categoria> findByCategoria(CategoriasEnum categoria);

}
