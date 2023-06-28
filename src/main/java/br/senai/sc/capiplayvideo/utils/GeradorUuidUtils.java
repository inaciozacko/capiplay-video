package br.senai.sc.capiplayvideo.utils;

import java.util.UUID;

public final class GeradorUuidUtils {

    private GeradorUuidUtils() {
    }

    public static String gerarUuid() {
        return UUID.randomUUID().toString();
    }

}
