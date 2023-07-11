package br.senai.sc.capiplayvideo.usuario.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor

public class Usuario {
    @Id
    String uuid;

    @Column(nullable = false)
    String nomeUsuario;

    @Column(nullable = false)
    String nomeCanal;

    String caminhoFoto;
}