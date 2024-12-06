package com.perseus.conectapro.Entity;

import com.perseus.conectapro.Entity.Enuns.FormaPagtoEnum;
import com.perseus.conectapro.Entity.Enuns.NvlUrgencia;
import com.perseus.conectapro.Entity.Enuns.StatusSolicitacaoEnum;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.awt.image.BufferedImage;
import java.text.DateFormat;

@Entity
@Getter
@Setter
@Table(name = "TBL_SOLICITACAO_PRESTADORES")
public class SolicitacaoPrestador
{
    @Id
    @Column(name = "ID_SOLICITACAO_PRESTADOR")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "ID_EMPRESA_CLIENTE")
    private EmpresaCliente idEmpresaCliente;

    @Column(name = "DESCRICAO")
    public String descricao;

    @Column(name = "FOTO ")
    public BufferedImage foto;

    @Column(name = "STATUS_SOLICITACAO")
    public StatusSolicitacaoEnum idStatusSolicitacao;

    @Column(name = "CATEGORIA")
    public String categoria;

    @Column(name = "ESPECIALIDADE")
    public String especialidade;

    @Column(name = "LOCALIDADE")
    public String localidade;

    @Column(name = "NVL_URGENCIA")
    public NvlUrgencia idNvlUrgencia;

    @Column(name = "ATIVIDADE")
    private String atividade;

    @Column(name = "VALOR_PROPOSTO")
    public Double valorProposto;

    @Column(name = "DATA_ATUALIZACAO")
    public DateFormat dataAtualizacao;

    @Column(name = "DATA_CRIACAO")
    public DateFormat dataCriacao;

    @Column(name = "DATA_FINALIZACAO")
    public DateFormat dataFinalizacao;

    /*@Column(name = "AVALIACAO_PRESTADOR")
    private AvaliacaoPrestador avaliacaoPrestador; Precisa da classe avaliação prestador*/

    @Column(name = "VISUALIZACAO")
    public int visualizacao;

    @Column(name = "CURTIDAS")
    public int curtidas;

    @Column(name = "COMENTARIOS")
    public String comentario;

    @Column(name = "COMPARTILHAMENTOS")
    public int compartilhamento;

    @Column(name = "FORMA_PAGTO")
    private FormaPagtoEnum idFormaPagto;

    @Column(name = "PRAZO_EXECUCAO")
    private String prazoExecucao;

    @Column(name = "VALOR_ACORDADO")
    private Double valorAcordado;

    @Column(name = "TAXA_ADM")
    private Double taxaAdm;



}
