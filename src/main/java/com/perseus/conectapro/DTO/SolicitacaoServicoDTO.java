package com.perseus.conectapro.DTO;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.perseus.conectapro.Entity.Enuns.FormaPagtoEnum;
import com.perseus.conectapro.Entity.Enuns.NvlUrgenciaEnum;
import com.perseus.conectapro.Entity.Enuns.StatusSolicitacaoEnum;
import com.perseus.conectapro.Entity.Enuns.TipoCategoriaEnum;
import com.perseus.conectapro.Entity.SolicitacaoServico;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
public class SolicitacaoServicoDTO {
    private int idSolicitacao;
    private String tituloSolicitacao;
    private String descSolicitacao;
    private PrestadorResumoDTO prestadorResumoDTO;
    private EmpresaClienteResumoDTO empresaClienteResumoDTO;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "#.##0,00")
    private BigDecimal valorProposto;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy - HH:mm", timezone = "America/Sao_Paulo")
    private LocalDateTime dataInclusao;
    private LocalDate previsaoInicio;
    private int duracaoServico;
    private FormaPagtoEnum formaPagtoEnum;
    private NvlUrgenciaEnum nvlUrgenciaEnum;
    private TipoCategoriaEnum tipoCategoriaEnum;
    private StatusSolicitacaoEnum statusSolicitacaoEnum;

    public SolicitacaoServicoDTO(SolicitacaoServico solicitacaoServico) {
        this.idSolicitacao = solicitacaoServico.getIdSolicitacao();
        if (solicitacaoServico.getIdPrestador() != null) {
            this.prestadorResumoDTO = new PrestadorResumoDTO(solicitacaoServico.getIdPrestador());
        } else if (solicitacaoServico.getIdEmpresaCliente() != null) {
            this.empresaClienteResumoDTO = new EmpresaClienteResumoDTO(solicitacaoServico.getIdEmpresaCliente());
        }
        this.tituloSolicitacao = solicitacaoServico.getTituloSolicitacao();
        this.descSolicitacao = solicitacaoServico.getDescSolicitacao();
        this.valorProposto = solicitacaoServico.getValorProposto();
        this.dataInclusao = solicitacaoServico.getDataInclusao();
        this.previsaoInicio = solicitacaoServico.getPrevisaoInicio();
        this.duracaoServico = solicitacaoServico.getDuracaoServico();
        this.formaPagtoEnum = solicitacaoServico.getFormaPagtoEnum();
        this.nvlUrgenciaEnum = solicitacaoServico.getNvlUrgenciaEnum();
        this.tipoCategoriaEnum = solicitacaoServico.getTipoCategoriaEnum();
        this.statusSolicitacaoEnum = solicitacaoServico.getStatusSolicitacaoEnum();
    }

    public SolicitacaoServicoDTO(int idSolicitacao, String tituloSolicitacao, String descSolicitacao, BigDecimal valorProposto, LocalDateTime dataInclusao, LocalDate previsaoInicio, int duracaoServico, FormaPagtoEnum formaPagtoEnum, NvlUrgenciaEnum nvlUrgenciaEnum, TipoCategoriaEnum tipoCategoriaEnum, StatusSolicitacaoEnum statusSolicitacaoEnum){
        this.idSolicitacao = idSolicitacao;
        this.tituloSolicitacao = tituloSolicitacao;
        this.descSolicitacao = descSolicitacao;
        this.valorProposto = valorProposto;
        this.dataInclusao = dataInclusao;
        this.previsaoInicio = previsaoInicio;
        this.duracaoServico = duracaoServico;
        this.formaPagtoEnum = formaPagtoEnum;
        this.nvlUrgenciaEnum = nvlUrgenciaEnum;
        this.tipoCategoriaEnum = tipoCategoriaEnum;
        this.statusSolicitacaoEnum = statusSolicitacaoEnum;
    }
}
