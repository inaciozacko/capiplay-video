package br.senai.sc.capiplayvideo.video.repository;

import br.senai.sc.capiplayvideo.video.model.entity.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoriaRepository extends JpaRepository<Categoria, Long> {
}
