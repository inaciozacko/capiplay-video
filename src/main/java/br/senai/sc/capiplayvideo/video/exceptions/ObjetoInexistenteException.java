package br.senai.sc.capiplayvideo.video.exceptions;

public class ObjetoInexistenteException extends RuntimeException{
    public ObjetoInexistenteException() {
        super("Objeto não encontrado!");
    }
}
