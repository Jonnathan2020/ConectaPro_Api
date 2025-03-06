package com.perseus.conectapro.Entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.aspectj.weaver.ast.Or;

import java.text.DateFormat;

@Entity
@Getter
@Setter
@Table(name = "CONTRATO")
public class Contrato
{
    @Id
    @Column(name = "ID_CONTRATO")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idContrato;

    @OneToOne
    @JoinColumn(name = "ID_SOLICITACAO_SERVICO")
    private SolicitacaoServico solicitacaoServico;

    @Column(name = "DATA_CRIACAO")
    private DateFormat dataCriacao;

    @Column(name = "DATA_FINALIZACAO")
    private DateFormat dataFinalizacao;

    @ManyToOne
    @JoinColumn(name = "DADO_CLIENTE")
    private EmpresaCliente dadoCliente;

    /*Criei o atributo dado cobrador e dado cliente*/
    @ManyToOne
    @JoinColumn(name = "DADO_COBRADOR")
    private Prestador dadoCobrador;

    @Column(name = "OBJETO_CONTRATO")
    private String objetoContrato;

    @Column(name = "CLAUSULAS")
    private String clausulas;

    @Column(name = "DESCRICAO")
    private String descricao;

    @Column(name = "PRAZO")
    private String prazo;

    @Column(name = "VALORES_EXPLICITOS")
    private double valoresExplicitos;

    @Column(name = "Assinatura")
    private String assinatura;





}
