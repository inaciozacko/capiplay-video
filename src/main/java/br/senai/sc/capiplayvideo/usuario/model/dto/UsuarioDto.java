package br.senai.sc.capiplayvideo.usuario.model.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class UsuarioDto {
    @NotNull
    String nome;
    @NotNull
    String perfil;
    @NotNull
    String caminhoFoto;
}
