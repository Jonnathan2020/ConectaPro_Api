package com.perseus.conectapro.DTO;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.perseus.conectapro.Entity.Enuns.FormaPagtoEnum;
import com.perseus.conectapro.Entity.Enuns.NvlUrgenciaEnum;
import com.perseus.conectapro.Entity.Enuns.SituacaoServicoEnum;
import com.perseus.conectapro.Entity.Enuns.TipoCategoriaEnum;
import com.perseus.conectapro.Entity.Segmento;
import com.perseus.conectapro.Entity.Servico;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDateTime;

@Getter
@Setter
public class ServicoDTO {
    private int idServico;
    private PrestadorResumoDTO prestadorResumoDTO;
    private EmpresaClienteResumoDTO empresaClienteResumoDTO;
    private Segmento idSegmento;
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
    private LocalDateTime dataPagamento;
    private SituacaoServicoEnum situacaoServico;
    private NvlUrgenciaEnum nvlUrgenciaEnum;
    private TipoCategoriaEnum tipoCategoriaEnum;

    public ServicoDTO(Servico servico){
        this.idServico = servico.getIdServico();
        this.prestadorResumoDTO = new PrestadorResumoDTO(servico.getIdPrestador());
        this.empresaClienteResumoDTO = new EmpresaClienteResumoDTO(servico.getIdEmpresaCliente());
        this.idSegmento = servico.getIdSegmento();
        this.descServico = servico.getDescServico();
        this.valorContratacao = servico.getValorContratacao();
        this.formaPagtoEnum = servico.getFormaPagtoEnum();
        this.dataInclusao = servico.getDataInclusao();
        this.dataAprovacao = servico.getDataAprovacao();
        this.dataExecucao = servico.getDataExecucao();
        this.dataPagamento = servico.getDataPagamento();
        this.situacaoServico = servico.getSituacaoServico();
        this.nvlUrgenciaEnum = servico.getNvlUrgenciaEnum();
        this.tipoCategoriaEnum = servico.getTipoCategoriaEnum();
    }
}
