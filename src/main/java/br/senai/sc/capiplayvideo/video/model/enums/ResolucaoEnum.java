package br.senai.sc.capiplayvideo.video.model.enums;

public enum ResolucaoEnum {

    R340X193(340, 193),
    R380X193(380, 193),
    R154X268(154, 268),
    R200X348(200, 348),
    R230X388(230, 388);

    private final int largura;
    private final int altura;

    ResolucaoEnum(int largura, int altura) {
        this.largura = largura;
        this.altura = altura;
    }

    public int getLargura() {
        return largura;
    }

    public int getAltura() {
        return altura;
    }

}
