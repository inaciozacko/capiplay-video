package br.senai.sc.capiplayvideo.video.projection;

import br.senai.sc.capiplayvideo.video.model.entity.Categoria;
import br.senai.sc.capiplayvideo.video.model.entity.Tag;

import java.util.List;

public interface VideoProjection {

    String getUuid();
    String getTitulo();
    String getDescricao();
    String getCaminho();
    String getMiniatura();
    List<Tag> getTags();
    Categoria getCategoria();

}
