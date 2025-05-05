package com.perseus.conectapro.DTO;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.perseus.conectapro.Entity.Enuns.FormaPagtoEnum;
import com.perseus.conectapro.Entity.Orcamento;
import com.perseus.conectapro.Entity.Servico;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
public class OrcamentoDTO {
    private int idOrcamento;
    private Servico idServico;
    private PrestadorResumoDTO prestadorResumoDTO;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "0.000,00")
    private BigDecimal valorOrcamento;
    private LocalDate previsaoInicio;
    private int duracaoServico;
    private FormaPagtoEnum formaPagtoEnum;

    public OrcamentoDTO(Orcamento orcamento) {
        this.idOrcamento = orcamento.getIdOrcamento();
        this.idServico = orcamento.getIdServico();
        this.prestadorResumoDTO = new PrestadorResumoDTO(orcamento.getIdPrestador());
        this.valorOrcamento = orcamento.getValorOrcamento();
        this.previsaoInicio = orcamento.getPrevisaoInicio();
        this.duracaoServico = orcamento.getDuracaoServico();
        this.formaPagtoEnum = orcamento.getFormaPagtoEnum();
    }
}
