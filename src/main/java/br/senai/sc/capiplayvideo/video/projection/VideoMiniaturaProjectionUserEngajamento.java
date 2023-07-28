package br.senai.sc.capiplayvideo.video.projection;

public interface VideoMiniaturaProjectionUserEngajamento {

    //Video
    String getUuid();
    String getTitulo();
    String getMiniatura();

    //User
    String getUuidUsuario();
    String getNome();
    String getFoto();

    //Engajamento
    String getUuidEngajamento();
    Integer getCurtidas();
    Integer getViews();
}
