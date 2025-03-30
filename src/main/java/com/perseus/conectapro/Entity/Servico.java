package com.perseus.conectapro.Entity;

import com.perseus.conectapro.Entity.Enuns.SituacaoServicoEnum;
import com.perseus.conectapro.Entity.Enuns.StatusDisponibilidadeEnum;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.text.DateFormat;
import java.time.LocalDate;
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

    @OneToOne
    @JoinColumn(name = "ID_SEGMENTO")
    private Segmento idSegmento;

    @ManyToOne
    @JoinColumn(name = "ID_EMPRESA_CLIENTE")
    private EmpresaCliente idEmpresaCliente;

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
}
