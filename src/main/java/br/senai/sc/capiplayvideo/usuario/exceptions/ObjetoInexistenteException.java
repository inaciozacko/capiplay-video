package br.senai.sc.capiplayvideo.usuario.exceptions;

public class ObjetoInexistenteException extends RuntimeException{
    public ObjetoInexistenteException() {
        super("Objeto não encontrado!");
    }
}
