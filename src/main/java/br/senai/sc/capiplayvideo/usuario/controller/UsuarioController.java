package br.senai.sc.capiplayvideo.usuario.controller;

import br.senai.sc.capiplayvideo.usuario.service.UsuarioService;

public class UsuarioController {
    UsuarioService usuarioService;

    public void findAll() {
        usuarioService.findAll();
    }

    public void findById(Long id) {
        usuarioService.findById(id);
    }
}
