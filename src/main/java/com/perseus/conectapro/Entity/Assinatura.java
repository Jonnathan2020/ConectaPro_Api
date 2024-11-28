package com.perseus.conectapro.Entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
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

        //private Usuario id; - A classe Usuario não existe
        //private TipoAssinaturaEnum tipoAssinatura; - TipoAssinaturaEnum não existe
        //private Contrato idContrato; - Classe ainda não criada

        @Column(name = "DATA_INICIAL")
        private DateFormat dataInicial;
        @Column(name = "DATA_FINAL")
        private DateFormat dataFinal;

        //private PrazoEnum prazo; - PrazoEnum ainda não existe
        @Column(name="VALOR_TOTAL")
        private double valorTotal;



    }

