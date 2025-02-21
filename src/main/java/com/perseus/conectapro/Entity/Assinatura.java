package com.perseus.conectapro.Entity;

import com.perseus.conectapro.Entity.Enuns.PrazoEnum;
import com.perseus.conectapro.Entity.Enuns.TipoAssinaturaEnum;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.text.DateFormat;
import java.util.List;

@Getter
    @Setter
    @Entity
    @Table(name = "ASSINATURA")
    public class Assinatura {
        @Id
        @Column(name = "ID_ASSINATURA")
        private Long id;

        @Enumerated(EnumType.STRING)
        @Column(name = "TIPO_ASSINATURA")
        private TipoAssinaturaEnum tipoAssinaturaEnum;

        @OneToOne
        @JoinColumn(name = "ID_PRESTADOR")
        private Prestador prestador;

        @OneToOne
        @JoinColumn(name = "ID_EMPRESA_CLIENTE")
        private EmpresaCliente empresaCliente;

        @OneToMany
        @JoinColumn(name = "ID_CONTRATO")
        private List<Contrato> contrato;

        @Column(name = "DATA_INICIAL")
        private DateFormat dataInicial;

        @Column(name = "DATA_FINAL")
        private DateFormat dataFinal;

        @Column(name = "PRAZO_ENUM")
        @Enumerated(EnumType.STRING)
        private PrazoEnum prazo;

        @Column(name= "VALOR_TOTAL")
        private double valorTotal;

    }

