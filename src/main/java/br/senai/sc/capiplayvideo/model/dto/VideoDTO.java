package br.senai.sc.capiplayvideo.model.dto;

import br.senai.sc.capiplayvideo.model.entity.Categoria;
import br.senai.sc.capiplayvideo.model.entity.Tag;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class VideoDTO {

    @NotNull
    private String titulo;

    private String descricao;

    @NotNull
    private String caminho;

    @NotNull
    private String miniatura;

    @NotNull
    private List<Tag> tags;

    @NotNull
    private Categoria categoria;

}
