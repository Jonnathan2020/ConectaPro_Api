package com.perseus.conectapro.Entity;

import com.perseus.conectapro.Entity.Enuns.TipoAssinaturaEnum;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.text.DateFormat;

    @Getter
    @Setter
    @Entity
    @Table(name = "ASSINATURA")
    public class Assinatura {
        @Id
        @Column(name = "ID_ASSINATURA")
        private Long id;

        @OneToOne
        @JoinColumn(name = "ID_TIPO_ASSINATURA")
        private TipoAssinaturaEnum tipoAssinaturaEnum;

        @OneToOne
        @JoinColumn(name = "ID_PRESTADOR")
        private Prestador prestador;

        @OneToOne
        @JoinColumn(name = "ID_EMPRESA_CLIENTE")
        private EmpresaCliente empresaCliente;

        @OneToMany
        @JoinColumn(name = "ID_CONTRATO")
        private Contrato contrato;//

        @Column(name = "DATA_INICIAL")
        private DateFormat dataInicial;
        @Column(name = "DATA_FINAL")
        private DateFormat dataFinal;

        @Column(name = "PRAZO_ENUM")
        private Enum prazo;

        @Column(name="VALOR_TOTAL")
        private double valorTotal;



    }

