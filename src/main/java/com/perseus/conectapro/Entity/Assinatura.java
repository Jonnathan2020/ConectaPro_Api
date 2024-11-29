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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_ASSINATURA")
    private Long id;

    @OneToOne
    @JoinColumn(name = "PESSOA")
    private Pessoa idPessoa;
    //private TipoAssinaturaEnum tipoAssinatura; - TipoAssinaturaEnum não existe

    @OneToOne
    @JoinColumn(name = "CONTRATO")
    private Contrato idContrato;

    @Column(name = "DATA_INICIAL")
    private DateFormat dataInicial;
    @Column(name = "DATA_FINAL")
    private DateFormat dataFinal;

    //private PrazoEnum prazo; - PrazoEnum ainda não existe
    @Column(name="VALOR_TOTAL")
    private double valorTotal;



}
