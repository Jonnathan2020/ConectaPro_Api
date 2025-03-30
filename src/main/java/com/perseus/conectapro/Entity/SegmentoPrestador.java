package com.perseus.conectapro.Entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "TBL_SEGMENTO_PRESTADOR")
public class SegmentoPrestador {

    @EmbeddedId
    @OneToOne
    @JoinColumn(name = "ID_PRESTADOR")
    private Prestador idPrestador;

    @EmbeddedId
    @OneToOne
    @JoinColumn(name = "ID_SEGMENTO")
    private Segmento idSegemento;
}
