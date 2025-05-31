package com.perseus.conectapro.DTO;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.perseus.conectapro.Entity.Enuns.FormaPagtoEnum;
import com.perseus.conectapro.Entity.Enuns.NvlUrgenciaEnum;
import com.perseus.conectapro.Entity.Enuns.StatusServicoEnum;
import com.perseus.conectapro.Entity.Enuns.TipoCategoriaEnum;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
public class ServicoCreateDTO {

    private int idSolicitacao;
    private String tituloServico;
    private String descServico;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "#.##0,00")
    private Float valorContratacao;
    private FormaPagtoEnum formaPagto;
    //Atribuido automaticamente, está aqui para retornar na exibição
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
    private StatusServicoEnum statusServico;
    private NvlUrgenciaEnum nvlUrgencia;
    private TipoCategoriaEnum tipoCategoria;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy", timezone = "America/Sao_Paulo")
    private LocalDate previsaoInicio;
    private int duracaoServico;

}
