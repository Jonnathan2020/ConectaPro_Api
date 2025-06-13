package com.perseus.conectapro.DTO;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.perseus.conectapro.Entity.Enuns.FormaPagtoEnum;
import com.perseus.conectapro.Entity.Enuns.NvlSatisfacaoEnum;
import com.perseus.conectapro.Entity.Enuns.NvlUrgenciaEnum;
import com.perseus.conectapro.Entity.Enuns.StatusServicoEnum;
import com.perseus.conectapro.Entity.Enuns.TipoCategoriaEnum;
import com.perseus.conectapro.Entity.Servico;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
public class ServicoDTO {
    private int idServico;
    private PrestadorResumoDTO prestadorResumoDTO;
    private EmpresaClienteResumoDTO empresaClienteResumoDTO;
    private String tituloServico;
    private String descServico;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "#.##0,00")
    private Float valorContratacao;
    private FormaPagtoEnum formaPagtoEnum;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy - HH:mm", timezone = "America/Sao_Paulo")
    private LocalDateTime dataInclusao;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy - HH:mm", timezone = "America/Sao_Paulo")
    private LocalDateTime dataAprovacao;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy - HH:mm", timezone = "America/Sao_Paulo")
    private LocalDateTime dataExecucao;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy - HH:mm", timezone = "America/Sao_Paulo")
    private LocalDateTime dataFinalizacao;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy - HH:mm", timezone = "America/Sao_Paulo")
    private LocalDateTime dataPagamento;
    private StatusServicoEnum situacaoServico;
    private NvlUrgenciaEnum nvlUrgenciaEnum;
    private TipoCategoriaEnum tipoCategoriaEnum;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy", timezone = "America/Sao_Paulo")
    private LocalDate previsaoInicio;
    private int duracaoServico;
    private NvlSatisfacaoEnum nvlSatisfacao;


    public ServicoDTO(Servico servico){
        this.idServico = servico.getIdServico();
        this.prestadorResumoDTO = new PrestadorResumoDTO(servico.getIdPrestador());
        this.empresaClienteResumoDTO = new EmpresaClienteResumoDTO(servico.getIdEmpresaCliente());
        this.tituloServico = servico.getTituloServico();
        this.descServico = servico.getDescServico();
        this.valorContratacao = servico.getValorContratacao();
        this.formaPagtoEnum = servico.getFormaPagto();
        this.dataInclusao = servico.getDataInclusao();
        this.dataAprovacao = servico.getDataAprovacao();
        this.dataExecucao = servico.getDataExecucao();
        this.dataFinalizacao = servico.getDataFinalizacao();
        this.dataPagamento = servico.getDataPagamento();
        this.situacaoServico = servico.getStatusServico();
        this.nvlUrgenciaEnum = servico.getNvlUrgencia();
        this.tipoCategoriaEnum = servico.getTipoCategoria();
        this.previsaoInicio = servico.getPrevisaoInicio();
        this.duracaoServico = servico.getDuracaoServico();
        this.nvlSatisfacao = servico.getNvlSatisfacao();
    }
}
