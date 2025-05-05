package com.perseus.conectapro.DTO;

import com.perseus.conectapro.Entity.Enuns.SituacaoServicoEnum;
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
    private Float valorContratacao;
    private LocalDateTime dataInclusao;
    private LocalDateTime dataAprovacao;
    private LocalDateTime dataExecucao;
    private LocalDateTime dataPagamento;
    private SituacaoServicoEnum situacaoServico;

    public ServicoDTO(Servico servico){
        this.idServico = servico.getIdServico();
        this.prestadorResumoDTO = new PrestadorResumoDTO(servico.getIdPrestador());
        this.empresaClienteResumoDTO = new EmpresaClienteResumoDTO(servico.getIdEmpresaCliente());
        this.idSegmento = servico.getIdSegmento();
        this.valorContratacao = servico.getValorContratacao();
        this.dataInclusao = servico.getDataInclusao();
        this.dataAprovacao = servico.getDataAprovacao();
        this.dataExecucao = servico.getDataExecucao();
        this.dataPagamento = servico.getDataPagamento();
        this.situacaoServico = servico.getSituacaoServico();
    }
}
