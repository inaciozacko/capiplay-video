package br.senai.sc.capiplayvideo.usuario.repository;

import br.senai.sc.capiplayvideo.usuario.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario, String> {
}