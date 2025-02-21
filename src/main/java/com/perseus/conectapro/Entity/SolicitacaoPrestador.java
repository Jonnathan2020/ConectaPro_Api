package com.perseus.conectapro.Entity;

import com.perseus.conectapro.Entity.Enuns.FormaPagtoEnum;
import com.perseus.conectapro.Entity.Enuns.NvlUrgencia;
import com.perseus.conectapro.Entity.Enuns.StatusSolicitacaoEnum;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.awt.image.BufferedImage;
import java.text.DateFormat;
import java.util.List;

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

    @OneToMany
    @JoinColumn(name = "ID_EMPRESA_CLIENTE")
    private List<EmpresaCliente> idEmpresaCliente;

    @Column(name = "DESCRICAO")
    private String descricao;

    @Column(name = "FOTO")
    private BufferedImage foto;

    @Enumerated(EnumType.STRING)
    @Column(name = "STATUS_SOLICITACAO")
    private StatusSolicitacaoEnum statusSolicitacao;

    @OneToMany
    @JoinColumn(name = "CATEGORIA")
    private List<Categoria> categoria;

    @Column(name = "ESPECIALIDADE")
    private String especialidade;

    @Column(name = "LOCALIDADE")
    private String localidade;

    @Enumerated(EnumType.STRING)
    @Column(name = "NVL_URGENCIA")
    private NvlUrgencia nvlUrgencia;

    @Column(name = "ATIVIDADE")
    private String atividade;

    @Column(name = "VALOR_PROPOSTO")
    private Double valorProposto;

    @Column(name = "DATA_ATUALIZACAO")
    private DateFormat dataAtualizacao;

    @Column(name = "DATA_CRIACAO")
    private DateFormat dataCriacao;

    @Column(name = "DATA_FINALIZACAO")
    private DateFormat dataFinalizacao;

    /*@Column(name = "AVALIACAO_PRESTADOR")
    private AvaliacaoPrestador avaliacaoPrestador; Precisa da classe avaliação prestador*/

    @Column(name = "VISUALIZACAO")
    private int visualizacao;

    @Column(name = "CURTIDAS")
    private int curtidas;

    @Column(name = "COMENTARIOS")
    private String comentario;

    @Column(name = "COMPARTILHAMENTOS")
    public int compartilhamento;

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
