package com.perseus.conectapro.DTO;

import com.perseus.conectapro.Entity.Enuns.StatusDisponibilidadeEnum;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class PrestadorUpdateDTO extends UsuarioCreateDTO {

    private String cpf;
    private String descPrestador;
    private List<String> especialidades;
    private StatusDisponibilidadeEnum statusDisponibilidade;

}
