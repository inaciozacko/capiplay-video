package br.senai.sc.capiplayvideo.video.model.projection;

public interface VideoMiniaturaProjectionUserEngajamento {

    //Video
    String getUuid();
    String getTitulo();
    String getMiniatura();

    //User
    String getuuid();
    String getNome();
    String getFoto();

    //Engajamento
    Integer getDislikes();
    Integer getLikes();
    Integer getViews();
}
