package com.perseus.conectapro.Entity;

import com.perseus.conectapro.Entity.Enuns.FormaPagtoEnum;
import com.perseus.conectapro.Entity.Enuns.NvlUrgenciaEnum;
import com.perseus.conectapro.Entity.Enuns.SituacaoServicoEnum;
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
    private Prestador prestador;

    @ManyToOne
    @JoinColumn(name = "ID_SEGMENTO")
    private Segmento segmento;

    @ManyToOne
    @JoinColumn(name = "ID_EMPRESA_CLIENTE")
    private EmpresaCliente empresaCliente;

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
    @Column(name = "SITUACAO_SERVICO")
    private SituacaoServicoEnum situacaoServico;

    @Column(name = "TITULO")
    private String titulo;

    @Column(name = "DESCRICAO")
    private String descricao;

    @ManyToOne
    @JoinColumn(name = "ID_ENDERECO")
    private Endereco endereco;

    @Enumerated(EnumType.STRING)
    @Column(name = "FORMA_PAGAMENTO")
    private FormaPagtoEnum formaPagtoEnum;

    @Enumerated(EnumType.STRING)
    @Column(name = "NIVEL_URGENCIA")
    private NvlUrgenciaEnum nvlUrgenciaEnum;


}
