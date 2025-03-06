package com.perseus.conectapro.Entity;

import com.perseus.conectapro.Entity.Enuns.FormaPagtoEnum;
import com.perseus.conectapro.Entity.Enuns.StatusDisponibilidadeEnum;
import com.perseus.conectapro.Entity.Enuns.StatusSolicitacaoEnum;
import com.perseus.conectapro.Entity.Enuns.TipoSegmentoEnum;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.awt.image.BufferedImage;
import java.text.DateFormat;
import java.util.List;

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
    private String descricao;

    @Column(name = "FOTO ")
    private BufferedImage foto;

    @Column(name = "STATUS_SOLICITACAO")
    @Enumerated(EnumType.STRING)
    private StatusSolicitacaoEnum statusSolicitacao;

    @OneToMany
    @JoinColumn(name = "CATEGORIA")
    private TipoSegmentoEnum categoria;

    @Column(name = "ESPECIALIDADE")
    private String especialidade;

    @Column(name = "LOCALIDADE")
    private String localidade;

    @Column(name = "ATIVIDADE")
    private String atividade;

    @Column(name = "VALOR_PROPOSTO")
    private Double valorProposto;

    @Column(name = "DATA_CRIACAO")
    private DateFormat dataCriacao;

    @Column(name = "DATA_ATUALIZACAO")
    private DateFormat dataAtualizacao;

    @Column(name = "DATA_FINALIZACAO")
    private DateFormat dataFinalizacao;

    @Column(name = "DATA_INICIO_EXECUCAO")
    private DateFormat dataInicioExecucao;

    /*@Column(name = "AVALIACAO_PRESTADOR")
    private AvaliacaoPrestador avaliacaoPrestador; Precisa da classe avaliação prestador*/

    @Column(name = "VISUALIZACAO")
    private int visualizacao;

    @Column(name = "CURTIDAS")
    private int curtidas;

    @Column(name = "COMENTARIO")
    private String comentario;

    @Column(name = "COMPARTILHAMENTO")
    private int compartilhamento;

    @Enumerated(EnumType.STRING)
    @Column(name = "FORMA_PAGAMENTO")
    private FormaPagtoEnum formaPagto;

    @Column(name = "PRAZO_EXECUCAO")
    private String prazoExecucao;

    @Column(name = "VALOR_ACORDADO")
    private Double valorAcordado;

    @Column(name = "TAXA_ADM")
    private Double taxaAdm;

}
