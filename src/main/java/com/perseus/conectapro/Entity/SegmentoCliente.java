package com.perseus.conectapro.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "TBL_SEGMENTO_CLIENTE")
public class SegmentoCliente {

    @OneToOne
    @JoinColumn(name = "ID_SEGMENTO")
    public Segmento idSegmento;

    @OneToOne
    @JoinColumn(name = "ID_EMPRESA_CLIENTE")
    public EmpresaCliente idEmpresaCliente;

}
