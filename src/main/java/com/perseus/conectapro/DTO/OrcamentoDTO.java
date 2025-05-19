package com.perseus.conectapro.DTO;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.perseus.conectapro.Entity.Enuns.FormaPagtoEnum;
import com.perseus.conectapro.Entity.Enuns.NvlUrgenciaEnum;
import com.perseus.conectapro.Entity.Enuns.TipoCategoriaEnum;
import com.perseus.conectapro.Entity.Orcamento;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
public class OrcamentoDTO {
    private int idOrcamento;
    private PrestadorResumoDTO prestadorResumoDTO;
    private EmpresaClienteResumoDTO empresaClienteResumoDTO;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "0.000,00")
    private BigDecimal valorOrcamento;
    private LocalDate previsaoInicio;
    private int duracaoServico;
    private FormaPagtoEnum formaPagtoEnum;
    private NvlUrgenciaEnum nvlUrgencia;
    private TipoCategoriaEnum tipoCategoriaEnum;

    public OrcamentoDTO(Orcamento orcamento) {
        this.idOrcamento = orcamento.getIdOrcamento();
        if (orcamento.getIdPrestador() != null) {
            this.prestadorResumoDTO = new PrestadorResumoDTO(orcamento.getIdPrestador());
        } else if (orcamento.getIdEmpresaCliente() != null) {
            this.empresaClienteResumoDTO = new EmpresaClienteResumoDTO(orcamento.getIdEmpresaCliente());
        }
        this.valorOrcamento = orcamento.getValorOrcamento();
        this.previsaoInicio = orcamento.getPrevisaoInicio();
        this.duracaoServico = orcamento.getDuracaoServico();
        this.formaPagtoEnum = orcamento.getFormaPagtoEnum();
        this.nvlUrgencia = orcamento.getNvlUrgenciaEnum();
        this.tipoCategoriaEnum = orcamento.getTipoCategoriaEnum();
    }

    public OrcamentoDTO(int idOrcamento, BigDecimal valorOrcamento, int duracaoServico, FormaPagtoEnum formaPagtoEnum, LocalDate previsaoInicio, NvlUrgenciaEnum nvlUrgencia, TipoCategoriaEnum tipoCategoriaEnum) {
        this.idOrcamento = idOrcamento;
        this.valorOrcamento = valorOrcamento;
        this.duracaoServico = duracaoServico;
        this.formaPagtoEnum = formaPagtoEnum;
        this.previsaoInicio = previsaoInicio;
        this.nvlUrgencia = nvlUrgencia;
        this.tipoCategoriaEnum = tipoCategoriaEnum;
    }
}
