package br.senai.sc.capiplayvideo.video.model.entity;

import br.senai.sc.capiplayvideo.video.utils.GeradorUuidUtils;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;
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

    private Integer visualizacoes;

    private float tempoDuracao;

    private String tipo;

    private Integer curtidas;

    private Date dataPublicacao;

    @OneToMany(cascade = CascadeType.ALL)
    private List<Tag> tags;

    @ManyToOne(cascade = CascadeType.ALL)
    private Categoria categoria;

    public Video() {
        this.uuid = GeradorUuidUtils.gerarUuid();
    }

}
