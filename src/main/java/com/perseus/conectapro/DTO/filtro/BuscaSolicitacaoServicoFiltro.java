package com.perseus.conectapro.DTO.filtro;

import com.perseus.conectapro.Entity.Enuns.TipoCategoriaEnum;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BuscaSolicitacaoServicoFiltro {
    private String termo;
    private String uf;
    private TipoCategoriaEnum tipoCategoria;
}
