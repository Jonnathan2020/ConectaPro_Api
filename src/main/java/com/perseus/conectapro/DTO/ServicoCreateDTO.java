package com.perseus.conectapro.DTO;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.perseus.conectapro.Entity.EmpresaCliente;
import com.perseus.conectapro.Entity.Enuns.FormaPagtoEnum;
import com.perseus.conectapro.Entity.Enuns.NvlUrgenciaEnum;
import com.perseus.conectapro.Entity.Enuns.SituacaoServicoEnum;
import com.perseus.conectapro.Entity.Enuns.TipoCategoriaEnum;
import com.perseus.conectapro.Entity.Prestador;
import com.perseus.conectapro.Entity.Segmento;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
public class ServicoCreateDTO {

    private int idOrcamento;
    private Segmento idSegmento;
    private String tituloServico;
    private String descServico;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "#.##0,00")
    private Float valorContratacao;
    private FormaPagtoEnum formaPagtoEnum;
    //Atribuido automaticamente, está aqui para retornar na exibição
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy - HH:mm", timezone = "America/Sao_Paulo")
    private LocalDateTime dataInclusao;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy - HH:mm", timezone = "America/Sao_Paulo")
    private LocalDateTime dataAprovacao;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy - HH:mm", timezone = "America/Sao_Paulo")
    private LocalDateTime dataExecucao;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy - HH:mm", timezone = "America/Sao_Paulo")
    private LocalDateTime dataPagamento;
    private SituacaoServicoEnum situacaoServicoEnum;
    private NvlUrgenciaEnum nvlUrgenciaEnum;
    private TipoCategoriaEnum tipoCategoriaEnum;

}
