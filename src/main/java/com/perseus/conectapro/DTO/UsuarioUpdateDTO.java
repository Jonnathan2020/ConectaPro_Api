package com.perseus.conectapro.DTO;

import com.perseus.conectapro.Entity.Enuns.RoleEnum;
import com.perseus.conectapro.Entity.Enuns.TipoUsuarioEnum;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UsuarioUpdateDTO {

    private int idUsuario;

    private String nome;

    private String email;

    private String senha;

    private String telefone;

    private TipoUsuarioEnum tipoUsuario;

    private RoleEnum role;

    private String caminhoFoto;

}
