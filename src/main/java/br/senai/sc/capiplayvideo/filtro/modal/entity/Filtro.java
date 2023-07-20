package br.senai.sc.capiplayvideo.filtro.modal.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Filtro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private boolean filtroDia;

    @Column(nullable = false)
    private boolean filtroSemana;

    @Column(nullable = false)
    private boolean filtroMes;

    @Column(nullable = false)
    private boolean filtroAno;

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

    public Filtro(boolean filtroDia, boolean filtroSemana, boolean filtroMes, boolean filtroAno, boolean filtroMenosDe5Min, boolean filtroEntre5E20Min, boolean filtroMaisDe20Min, boolean filtroVideo, boolean filtroShorts) {
    }
}
