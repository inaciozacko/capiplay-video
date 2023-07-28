package br.senai.sc.capiplayvideo.filtro.repository;

import br.senai.sc.capiplayvideo.filtro.modal.entity.Filtro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FiltroRepository extends JpaRepository<Filtro, Long> {


}
