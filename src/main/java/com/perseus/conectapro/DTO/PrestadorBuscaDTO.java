package com.perseus.conectapro.DTO;

import com.perseus.conectapro.Entity.Enuns.TipoCategoriaEnum;
import com.perseus.conectapro.Entity.Prestador;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class PrestadorBuscaDTO {

    private int idPrestador;
    private String nome;
    private String caminhoFoto;
    private List<String> especialidades;
    private List<TipoCategoriaEnum> tipoCategoria;
    private String descPrestador;

    public PrestadorBuscaDTO(Prestador prestador) {
        this.idPrestador = prestador.getIdUsuario();
        this.nome = prestador.getNome();
        this.caminhoFoto = prestador.getCaminhoFoto();
        this.especialidades = prestador.getEspecialidades();
        this.tipoCategoria = prestador.getTipoCategoria();
        this.descPrestador = prestador.getDescPrestador();
    }
}
