package com.perseus.conectapro.DTO;

import com.perseus.conectapro.Entity.Enuns.StatusDisponibilidadeEnum;
import com.perseus.conectapro.Entity.Prestador;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class PrestadorResumoDTO {
    private int idPrestador;
    private String nome;
    private List<String> especialidades;
    private StatusDisponibilidadeEnum statusDisponibilidade;
    private String caminhoFoto;

    public PrestadorResumoDTO(Prestador prestador) {
        this.idPrestador = prestador.getIdUsuario();
        this.nome = prestador.getNome();
        this.especialidades = new ArrayList<>(prestador.getEspecialidades());
        this.statusDisponibilidade = prestador.getStatusDisponibilidade();
        this.caminhoFoto = prestador.getCaminhoFoto();
    }
}