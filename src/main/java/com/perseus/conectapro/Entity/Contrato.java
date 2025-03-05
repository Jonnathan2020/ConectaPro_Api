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
    private DateFormat dataCriacao;

    @Column(name = "DATA_FINALIZACAO")
    private DateFormat dataFinalizacao;

    @ManyToOne
    @JoinColumn(name = "ID_USUARIO_CLIENTE")
    private Usuario usuarioCliente;  // Representa o usuário do tipo cliente

    // Relacionamento com o prestador (campo ID_USUARIO_PRESTADOR)
    @ManyToOne
    @JoinColumn(name = "ID_USUARIO_PRESTADOR")
    private Usuario usuarioPrestador;

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
