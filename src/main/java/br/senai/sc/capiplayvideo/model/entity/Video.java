package br.senai.sc.capiplayvideo.model.entity;

import br.senai.sc.capiplayvideo.utils.GeradorUuidUtils;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@Entity
@AllArgsConstructor
public class Video {

    @Id
    private String uuid;

    private String titulo;

    private String descricao;

    private String caminho;

    private String miniatura;

    @OneToMany(cascade = CascadeType.ALL)
    private List<Tag> tags;

    @ManyToOne(cascade = CascadeType.ALL)
    private Categoria categoria;

    public Video() {
        this.uuid = GeradorUuidUtils.gerarUuid();
    }
}
