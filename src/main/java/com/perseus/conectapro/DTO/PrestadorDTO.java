package com.perseus.conectapro.DTO;

import com.perseus.conectapro.Entity.Enuns.StatusDisponibilidadeEnum;
import com.perseus.conectapro.Entity.Plano;
import com.perseus.conectapro.Entity.Prestador;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class PrestadorDTO {

    private int idPrestador;
    private String nome;
    private String descPrestador;
    private List<String> especialidades;
    private StatusDisponibilidadeEnum statusDisponibilidadeEnum;
    private List<ServicoDTO> servicos;
    private String caminhoFoto;
    private List<SolicitacaoServicoDTO> orcamentos;
    private Plano idPlano;

    public PrestadorDTO(Prestador prestador, List<SolicitacaoServicoDTO> orcamentos){
        this.idPrestador = prestador.getIdUsuario();
        this.nome = prestador.getNome();
        this.descPrestador = prestador.getDescPrestador();
        this.especialidades = prestador.getEspecialidades();
        this.statusDisponibilidadeEnum = prestador.getStatusDisponibilidade();
        this.servicos = servicos;
        this.orcamentos = orcamentos;
        this.caminhoFoto = prestador.getCaminhoFoto();
        this.idPlano = prestador.getIdPlano();
    }


}