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
    private List<String> especialidades;
    private List<TipoCategoriaEnum> tipoCategoria;

    public PrestadorBuscaDTO(Prestador prestador) {
        this.nome = prestador.getNome();
        this.caminhoFoto = prestador.getCaminhoFoto();
        this.especialidades = prestador.getEspecialidades();
        this.tipoCategoria = prestador.getTipoCategoria();
    }
}
