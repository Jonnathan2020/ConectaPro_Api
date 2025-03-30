package com.perseus.conectapro.DTO;

import com.perseus.conectapro.Entity.Enuns.NvlSatisfacaoEnum;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AvaliacaoUpdateDTO {

    private Long id;
    private String descricao;
    private NvlSatisfacaoEnum nvlSatisfacao;

}
