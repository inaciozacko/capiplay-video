package br.senai.sc.capiplayvideo.filtro.modal.dto;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class FiltroDTO {
    @Column(nullable = false)
    private boolean filtroDia;

    @Column(nullable = false)
    private boolean filtroSemana;

    @Column(nullable = false)
    private boolean filtroMes;

    @Column(nullable = false)
    private boolean filtroMenosDe5Min;

    @Column(nullable = false)
    private boolean filtroEntre5E20Min;

    @Column(nullable = false)
    private boolean filtroMaisDe20Min;

    @Column(nullable = false)
    private boolean filtroVideo;

    @Column(nullable = false)
    private boolean filtroShorts;

}
