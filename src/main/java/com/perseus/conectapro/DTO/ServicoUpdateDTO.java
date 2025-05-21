package com.perseus.conectapro.DTO;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.perseus.conectapro.Entity.Enuns.FormaPagtoEnum;
import com.perseus.conectapro.Entity.Enuns.SituacaoServicoEnum;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class ServicoUpdateDTO {

    private String descServico;
    private SituacaoServicoEnum situacaoServicoEnum;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "#.##0,00")
    private Float valorContratacao;
    private FormaPagtoEnum formaPagtoEnum;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy - HH:mm", timezone = "America/Sao_Paulo")
    private LocalDateTime dataAprovacao;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy - HH:mm", timezone = "America/Sao_Paulo")
    private LocalDateTime dataExecucao;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy - HH:mm", timezone = "America/Sao_Paulo")
    private LocalDateTime dataPagamento;
}
