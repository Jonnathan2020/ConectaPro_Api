package com.perseus.conectapro.DTO;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.perseus.conectapro.Entity.Enuns.FormaPagtoEnum;
import com.perseus.conectapro.Entity.Enuns.NvlUrgenciaEnum;
import com.perseus.conectapro.Entity.Enuns.StatusOrcamentoEnum;
import com.perseus.conectapro.Entity.Enuns.TipoCategoriaEnum;
import com.perseus.conectapro.Entity.Orcamento;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
public class OrcamentoDTO {
    private int idOrcamento;
    private PrestadorResumoDTO prestadorResumoDTO;
    private EmpresaClienteResumoDTO empresaClienteResumoDTO;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "0.000,00")
    private BigDecimal valorOrcamento;
    private LocalDateTime dataInclusao;
    private LocalDate previsaoInicio;
    private int duracaoServico;
    private FormaPagtoEnum formaPagtoEnum;
    private NvlUrgenciaEnum nvlUrgenciaEnum;
    private TipoCategoriaEnum tipoCategoriaEnum;
    private StatusOrcamentoEnum statusOrcamentoEnum;

    public OrcamentoDTO(Orcamento orcamento) {
        this.idOrcamento = orcamento.getIdOrcamento();
        if (orcamento.getIdPrestador() != null) {
            this.prestadorResumoDTO = new PrestadorResumoDTO(orcamento.getIdPrestador());
        } else if (orcamento.getIdEmpresaCliente() != null) {
            this.empresaClienteResumoDTO = new EmpresaClienteResumoDTO(orcamento.getIdEmpresaCliente());
        }
        this.valorOrcamento = orcamento.getValorOrcamento();
        this.dataInclusao = orcamento.getDataInclusao();
        this.previsaoInicio = orcamento.getPrevisaoInicio();
        this.duracaoServico = orcamento.getDuracaoServico();
        this.formaPagtoEnum = orcamento.getFormaPagtoEnum();
        this.nvlUrgenciaEnum = orcamento.getNvlUrgenciaEnum();
        this.tipoCategoriaEnum = orcamento.getTipoCategoriaEnum();
        this.statusOrcamentoEnum = orcamento.getStatusOrcamentoEnum();
    }

    public OrcamentoDTO(int idOrcamento, BigDecimal valorOrcamento, LocalDateTime dataInclusao,  LocalDate previsaoInicio,int duracaoServico, FormaPagtoEnum formaPagtoEnum,NvlUrgenciaEnum nvlUrgenciaEnum,TipoCategoriaEnum tipoCategoriaEnum, StatusOrcamentoEnum statusOrcamentoEnum){
        this.idOrcamento = idOrcamento;
        this.valorOrcamento = valorOrcamento;
        this.dataInclusao = dataInclusao;
        this.previsaoInicio = previsaoInicio;
        this.duracaoServico = duracaoServico;
        this.formaPagtoEnum = formaPagtoEnum;
        this.nvlUrgenciaEnum = nvlUrgenciaEnum;
        this.tipoCategoriaEnum = tipoCategoriaEnum;
        this.statusOrcamentoEnum = statusOrcamentoEnum;
    }
}
