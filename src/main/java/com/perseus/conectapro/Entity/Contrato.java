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
    private int id;

    @OneToOne
    @JoinColumn(name = "ID_SOLICITACAO_SERVICO")
    private SolicitacaoServico solicitacaoServico;

    @Column(name = "DATA_CRIACAO")
    public DateFormat dataCriacao;

    @Column(name = "DATA_FINALIZACAO")
    public DateFormat dataFinalizacao;

    @Column(name = "DADO_CLIENTE")
    public EmpresaCliente dadoCliente;

    /*Criei o atributo dado cobrador e dado cliente*/
    @Column(name = "DADO_COBRADOR")
    public Prestador dadoCobrador;

    @Column(name = "OBJETO_CONTRATO")
    public String objetoContrato;

    @Column(name = "CLASULAS")
    public String clasulas;

    @Column(name = "DESCRICAO")
    public String descricao;

    @Column(name = "PRAZO")
    public String prazo;

    @Column(name = "VALORES_EXPLICITOS")
    public double valoresExplicitos;

    @Column(name = "Assinatura")
    public String assinatura;





}
