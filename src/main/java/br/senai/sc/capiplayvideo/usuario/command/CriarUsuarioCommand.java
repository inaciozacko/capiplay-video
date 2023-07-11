package br.senai.sc.capiplayvideo.usuario.command;

import jakarta.annotation.Nullable;
import jakarta.validation.constraints.NotNull;

public class CriarUsuarioCommand {

    @NotNull
    String nomeUsuario;

    @NotNull
    String nomeCanal;

    @Nullable
    String caminhoFoto;

}
