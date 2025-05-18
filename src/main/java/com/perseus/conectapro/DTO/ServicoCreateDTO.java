package com.perseus.conectapro.DTO;


import com.perseus.conectapro.Entity.Enuns.FormaPagtoEnum;
import com.perseus.conectapro.Entity.Enuns.NvlUrgenciaEnum;
import com.perseus.conectapro.Entity.Enuns.SituacaoServicoEnum;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class ServicoCreateDTO {
    private int idSegmento;
    private int idEmpresaCliente;
    private Float valorContratacao;
    private LocalDateTime dataInclusao;
    private SituacaoServicoEnum situacaoServico;
    private String titulo;
    private String descricao;
    private String cep;
    private int numero;
    private String complemento;
    private FormaPagtoEnum formaPagto;
    private NvlUrgenciaEnum nvlUrgencia;
}
