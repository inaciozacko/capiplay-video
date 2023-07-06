package br.senai.sc.capiplayvideo.usuario.service;

import br.senai.sc.capiplayvideo.usuario.model.Usuario;

public class UsuarioService {
    UsuarioService usuarioService;

    public Usuario findAll(){
        try {
            return usuarioService.findAll();
        }catch (Exception e){
            throw new RuntimeException("Não foi possivel achar nenhum usuário");
        }
    }

    public Usuario findById(Long id) {
        try {
            return usuarioService.findById(id);
        } catch (Exception e) {
            throw new RuntimeException("Não foi possivel achar nenhum usuário");
        }
    }
}
