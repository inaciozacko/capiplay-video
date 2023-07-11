package br.senai.sc.capiplayvideo.video.service;

import br.senai.sc.capiplayvideo.video.model.entity.Categoria;
import br.senai.sc.capiplayvideo.video.repository.CategoriaRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

@AllArgsConstructor
@Repository
public class CategoriaService {

    private CategoriaRepository repository;

    public void salvar(Categoria categoria) {
        repository.save(categoria);
    }

}
