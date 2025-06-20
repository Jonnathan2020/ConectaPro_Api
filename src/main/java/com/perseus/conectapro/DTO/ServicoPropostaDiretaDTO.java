package com.perseus.conectapro.DTO;

import com.perseus.conectapro.Entity.Enuns.FormaPagtoEnum;
import com.perseus.conectapro.Entity.Enuns.NvlUrgenciaEnum;
import com.perseus.conectapro.Entity.Enuns.TipoCategoriaEnum;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class ServicoPropostaDiretaDTO {
    private int idPrestador;
    private String tituloServico;
    private String descServico;
    private Float valorContratacao;
    private FormaPagtoEnum formaPagto;
    private LocalDate previsaoInicio;
    private int duracaoServico;
    private NvlUrgenciaEnum nvlUrgencia;
    private TipoCategoriaEnum tipoCategoria;
}
