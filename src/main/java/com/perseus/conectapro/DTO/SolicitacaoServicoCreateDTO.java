package com.perseus.conectapro.DTO;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.perseus.conectapro.Entity.Enuns.FormaPagtoEnum;
import com.perseus.conectapro.Entity.Enuns.NvlUrgenciaEnum;
import com.perseus.conectapro.Entity.Enuns.StatusSolicitacaoEnum;
import com.perseus.conectapro.Entity.Enuns.TipoCategoriaEnum;
import lombok.Getter;
import lombok.Setter;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
public class SolicitacaoServicoCreateDTO {
    private int idPrestador;
    private String tituloSolicitacao;
    private String descSolicitacao;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "#.##0,00")
    private BigDecimal valorProposto;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy - HH:mm", timezone = "America/Sao_Paulo")
    private LocalDateTime dataInclusao;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    private LocalDate previsaoInicio;
    private int duracaoServico;
    private FormaPagtoEnum formaPagto;
    private NvlUrgenciaEnum nvlUrgencia;
    private TipoCategoriaEnum tipoCategoria;
    private StatusSolicitacaoEnum statusSolicitacao;


}
