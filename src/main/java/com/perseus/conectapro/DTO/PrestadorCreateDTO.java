package com.perseus.conectapro.DTO;

import com.perseus.conectapro.Entity.Avaliacao;
import com.perseus.conectapro.Entity.Enuns.StatusDisponibilidadeEnum;
import com.perseus.conectapro.Entity.Plano;
import com.perseus.conectapro.Entity.Servico;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class PrestadorCreateDTO extends UsuarioCreateDTO{

    private String cpf;
    private String descPrestador;
    private List<String> especialidades;
    private StatusDisponibilidadeEnum statusDisponibilidade;
    private Long idPlano;

}
