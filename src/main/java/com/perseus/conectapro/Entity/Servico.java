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
    public int idServico;

    @ManyToOne
    @JoinColumn(name = "ID_PRESTADOR")
    public Prestador idPrestador;

    @OneToOne
    @JoinColumn(name = "ID_SEGMENTO")
    public Segmento idSegmento;

    @ManyToOne
    @JoinColumn(name = "ID_EMPRESA_CLIENTE")
    public EmpresaCliente idEmpresaCliente;

    @Column(name = "VALOR_CONTRATACAO")
    public float valorContratacao;

    @Column(name = "DATA_INCLUSAO")
    public LocalDateTime dataInclusao;

    @Column(name = "DATA_APROVACAO")
    public LocalDateTime dataAprovacao;

    @Column(name = "DATA_EXECUCAO")
    public LocalDateTime dataExecucao;

    @Column(name = "DATA_PAGAMENTO")
    public LocalDateTime dataPagamento;

    @Enumerated(EnumType.STRING)
    @Column(name = "ID_SITUACAO_SERVICO")
    public SituacaoServicoEnum idSituacaoServico;
}
