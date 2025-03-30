package com.perseus.conectapro.Entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "TBL_SEGMENTO_CLIENTE")
public class SegmentoCliente {

    @EmbeddedId
    @OneToOne
    @JoinColumn(name = "ID_SEGMENTO")
    private Segmento idSegmento;

    @EmbeddedId
    @OneToOne
    @JoinColumn(name = "ID_EMPRESA_CLIENTE")
    private EmpresaCliente idEmpresaCliente;

}
