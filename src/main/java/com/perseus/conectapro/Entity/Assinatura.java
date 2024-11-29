package com.perseus.conectapro.Entity;

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
        @JoinColumn(name = "ID_PESSOA")
        private Pessoa pessoa;

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

