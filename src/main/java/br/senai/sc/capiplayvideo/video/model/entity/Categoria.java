package br.senai.sc.capiplayvideo.video.model.entity;

import br.senai.sc.capiplayvideo.video.model.enums.CategoriasEnum;
import jakarta.persistence.*;
import jdk.swing.interop.SwingInterOpUtils;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class Categoria {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private CategoriasEnum categoria;

    public Categoria(String categoria) {
        this.categoria = CategoriasEnum.valueOf(categoria);
    }

}