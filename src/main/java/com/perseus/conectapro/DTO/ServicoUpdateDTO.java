package com.perseus.conectapro.DTO;

import com.perseus.conectapro.Entity.Enuns.SituacaoServicoEnum;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class ServicoUpdateDTO {

    private SituacaoServicoEnum idSituacaoServico;
    private double valorContratacao;
    private LocalDateTime dataAprovacao;
    private LocalDateTime dataExecucao;
    private LocalDateTime dataPagamento;
    private LocalDateTime dataInclusao;

}
