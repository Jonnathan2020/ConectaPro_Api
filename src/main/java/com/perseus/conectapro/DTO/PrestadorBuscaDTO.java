package com.perseus.conectapro.DTO;

import com.perseus.conectapro.Entity.Enuns.TipoCategoriaEnum;
import com.perseus.conectapro.Entity.Prestador;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class PrestadorBuscaDTO {

    private String nome;
    private String caminhoFoto;
    private List<TipoCategoriaEnum> tipoCategoria;

    public PrestadorBuscaDTO(Prestador prestador) {
        this.nome = prestador.getNome();
        this.caminhoFoto = prestador.getCaminhoFoto();
        this.tipoCategoria = prestador.getTipoCategoria();
    }
}
