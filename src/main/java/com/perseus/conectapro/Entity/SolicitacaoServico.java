package com.perseus.conectapro.Entity;

import com.perseus.conectapro.Entity.Enuns.StatusDisponibilidadeEnum;
import com.perseus.conectapro.Entity.Enuns.StatusSolicitacaoEnum;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.awt.image.BufferedImage;
import java.text.DateFormat;

@Entity
@Getter
@Setter
@Table(name = "TBL_SOLICITACAO_SERVICOS")
public class SolicitacaoServico
{
    @Id
    @Column(name = "ID_SOLICITACAO_SERVICO")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "ID_PRESTADOR")
    private Prestador prestador;

    @Column(name = "DESCRICAO")
    public String descricao;

    @Column(name = "FOTO ")
    public BufferedImage foto;

    @Column(name = "STATUS_SOLICITACAO_ENUM")
    @Enumerated(EnumType.STRING)
    public StatusSolicitacaoEnum statusSolicitacaoEnum;

    @Column(name = "CATEGORIA")
    public String categoria;

    @Column(name = "ESPECIALIDADE")
    public String especialidade;

    @Column(name = "LOCALIDADE")
    public String localidade;

    @Column(name = "ATIVIDADE")
    private String atividade;

    @Column(name = "VALOR_PROPOSTO")
    public Double valorProposto;

    @Column(name = "DATA_CRIACAO")
    public DateFormat dataCriacao;

    @Column(name = "DATA_ATUALIZACAO")
    public DateFormat dataAtualizacao;

    @Column(name = "DATA_FINALIZACAO")
    public DateFormat dataFinalizacao;

    @Column(name = "DATA_INICIO_EXECUCAO")
    public DateFormat dataInicioExecucao;

    /*@Column(name = "AVALIACAO_PRESTADOR")
    private AvaliacaoPrestador avaliacaoPrestador; Precisa da classe avaliação prestador*/

    @Column(name = "VISUALIZACAO")
    public int visualizacao;

    @Column(name = "CURTIDAS")
    public int curtidas;

    @Column(name = "COMENTARIO")
    public String comentario;

    @Column(name = "COMPARTILHAMENTO")
    public int compartilhamento;

    @Column(name = "FORMA_PAGTO")
    private Enum formaPagto;

    @Column(name = "PRAZO_EXECUCAO")
    private String prazoExecucao;

    @Column(name = "VALOR_ACORDADO")
    private Double valorAcordado;

    @Column(name = "TAXA_ADM")
    private Double taxaAdm;

}
