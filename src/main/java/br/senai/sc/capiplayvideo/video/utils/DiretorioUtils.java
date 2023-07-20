package br.senai.sc.capiplayvideo.video.utils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class DiretorioUtils {

    private DiretorioUtils() {
    }

    public static List<String> gerarListaStringArquivos(String diretorio) {
        List<Path> arquivos = new ArrayList<>();
        try {
            Path pasta = Paths.get(diretorio);
            Files.walk(pasta).forEach(arquivos::add);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return arquivos.stream()
                .filter(caminho -> !Files.isDirectory(caminho))
                .map(Path::toString)
                .map(caminho -> caminho.replace("D:\\capiplay-video\\", "")).toList();
    }

}
