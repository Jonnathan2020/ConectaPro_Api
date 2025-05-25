package com.perseus.conectapro.Entity;

import com.perseus.conectapro.Entity.Enuns.FormaPagtoEnum;
import com.perseus.conectapro.Entity.Enuns.NvlUrgenciaEnum;
import com.perseus.conectapro.Entity.Enuns.SituacaoServicoEnum;
import com.perseus.conectapro.Entity.Enuns.TipoCategoriaEnum;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Table(name = "TBL_SERVICOS")
public class Servico {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_SERVICO")
    private int idServico;

    @ManyToOne
    @JoinColumn(name = "ID_PRESTADOR")
    private Prestador idPrestador;

    @ManyToOne
    @JoinColumn(name = "ID_EMPRESA_CLIENTE")
    private EmpresaCliente idEmpresaCliente;

    @OneToOne
    @JoinColumn(name = "ID_SEGMENTO")
    private Segmento idSegmento;

    @Column(name = "TITULO_SERVICO")
    private String tituloServico;

    @Column(name = "DESC_SERVICO")
    private String descServico;

    @ManyToOne
    @JoinColumn(name = "ID_SOLICITACAO_SERVICO")
    private SolicitacaoServico solicitacaoServico;

    @Column(name = "VALOR_CONTRATACAO")
    private Float valorContratacao;

    @Column(name = "DATA_INCLUSAO")
    private LocalDateTime dataInclusao;

    @Column(name = "DATA_APROVACAO")
    private LocalDateTime dataAprovacao;

    @Column(name = "DATA_EXECUCAO")
    private LocalDateTime dataExecucao;

    @Column(name = "DATA_PAGAMENTO")
    private LocalDateTime dataPagamento;

    @Enumerated(EnumType.STRING)
    @Column(name = "FORMA_PAGTO")
    private FormaPagtoEnum formaPagtoEnum;

    @Enumerated(EnumType.STRING)
    @Column(name = "SITUACAO_SERVICO")
    private SituacaoServicoEnum situacaoServico;

    @Enumerated(EnumType.STRING)
    @Column(name = "NIVEL_URGENCIA")
    private NvlUrgenciaEnum nvlUrgenciaEnum;

    @Enumerated(EnumType.STRING)
    @Column(name = "TIPO_CATEGORIA")
    private TipoCategoriaEnum tipoCategoriaEnum;


}
