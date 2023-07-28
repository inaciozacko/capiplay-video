package br.senai.sc.capiplayvideo.usuario.model.entity;

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
    String nome;

    @Column(nullable = false)
    String perfil;

    String caminhoFoto;

}