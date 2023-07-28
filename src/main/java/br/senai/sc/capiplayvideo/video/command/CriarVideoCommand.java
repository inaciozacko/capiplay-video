package br.senai.sc.capiplayvideo.video.command;

import br.senai.sc.capiplayvideo.video.model.entity.Categoria;
import br.senai.sc.capiplayvideo.video.model.entity.Tag;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class CriarVideoCommand {

    @NotNull
    private String titulo;

    private String descricao;

    @NotNull
    private String caminho;

    @NotNull
    private String miniatura;

    @NotNull
    private Integer visualizacoes;

    @NotNull
    private Integer curtidas;

    @NotNull
    private List<Tag> tags;

    @NotNull
    private Categoria categoria;

}
