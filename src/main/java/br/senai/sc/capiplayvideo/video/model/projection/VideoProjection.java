package br.senai.sc.capiplayvideo.video.model.projection;

import br.senai.sc.capiplayvideo.video.model.entity.Categoria;
import br.senai.sc.capiplayvideo.video.model.entity.Tag;
import com.fasterxml.jackson.annotation.JsonIgnore;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public interface VideoProjection {

    String getUuid();

    String getTitulo();

    String getDescricao();

    @JsonIgnore
    String getCaminho();

    default List<String> getCaminhos() {
        List<Path> arquivos = new ArrayList<>();
        try {
            Path pasta = Paths.get(getCaminho());
            Files.walk(pasta).forEach(arquivos::add);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return arquivos.stream()
                .filter(caminho -> !Files.isDirectory(caminho))
                .map(Path::toString)
                .map(caminho -> caminho.replace("D:\\capiplay-video\\", "")).toList();
    }

    List<Tag> getTags();

    Categoria getCategoria();

}
