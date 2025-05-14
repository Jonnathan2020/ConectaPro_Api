package com.perseus.conectapro.Entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.perseus.conectapro.Entity.Enuns.FormaPagtoEnum;
import com.perseus.conectapro.Entity.Enuns.StatusOrcamentoEnum;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Table(name= "TBL_ORCAMENTO")
public class Orcamento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_ORCAMENTO")
    private int idOrcamento;

    @OneToOne
    @JoinColumn(name = "ID_SERVICO")
    private Servico idServico;

    @ManyToOne
    @JoinColumn(name = "ID_PRESTADOR")
    @JsonIgnore
    private Prestador idPrestador;

    @Column(name = "VALOR_ORCAMENTO")
    private BigDecimal valorOrcamento;

    @Column(name = "PREVISAO_INICIO")
    @JsonFormat(pattern = "dd-MM-yyyy")
    private LocalDate previsaoInicio;

    @Column(name = "DURACAO_SERVICO")
    private int duracaoServico;

    @Enumerated(EnumType.STRING)
    @Column(name = "STATUS_ORCAMENTO")
    private StatusOrcamentoEnum statusOrcamento;

    @Enumerated(EnumType.STRING)
    @Column(name = "FORMA_PAGTO")
    private FormaPagtoEnum formaPagtoEnum;
}
