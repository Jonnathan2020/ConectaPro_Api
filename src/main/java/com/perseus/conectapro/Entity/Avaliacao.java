package com.perseus.conectapro.Entity;

import com.perseus.conectapro.Entity.Enuns.NvlSatisfacaoEnum;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "AVALIACOES")
public class Avaliacao {
    @Id
    @Column(name = "ID_AVALIACAO")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "DESCRICAO")
    private String descricao;

    @Column(name = "ID_SOLICITACAO_SERVICO")
    private SolicitacaoServico solicitacaoServico;

    @Column(name = "ID_SOLICITACAO_PRESTADOR")
    private SolicitacaoPrestador solicitacaoPrestador;

    @OneToOne
    @Column(name = "ID_EMPRESA_CLIENTE")
    private EmpresaCliente idEmpresaCliente;

    @OneToOne
    @Column(name = "ID_NVL_SATISFACAO")
    private NvlSatisfacaoEnum nvlSatisfacao;
}