package br.senai.sc.capiplayvideo.model.entity;

import br.senai.sc.capiplayvideo.model.enums.CategoriasEnum;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Categoria {

    @Id
    private CategoriasEnum categoria;

}
