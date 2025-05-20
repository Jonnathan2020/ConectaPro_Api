package com.perseus.conectapro.DTO;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.perseus.conectapro.Entity.Enuns.FormaPagtoEnum;
import com.perseus.conectapro.Entity.Enuns.NvlUrgenciaEnum;
import com.perseus.conectapro.Entity.Enuns.StatusOrcamentoEnum;
import com.perseus.conectapro.Entity.Enuns.TipoCategoriaEnum;
import lombok.Getter;
import lombok.Setter;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
public class OrcamentoCreateDTO {

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "0.000,00")
    private BigDecimal valorOrcamento;
    private LocalDateTime dataInclusao;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    private LocalDate previsaoInicio;
    private int duracaoServico;
    private FormaPagtoEnum formaPagtoEnum;
    private NvlUrgenciaEnum nvlUrgenciaEnum;
    private TipoCategoriaEnum tipoCategoriaEnum;
    private StatusOrcamentoEnum statusOrcamentoEnum;

}
