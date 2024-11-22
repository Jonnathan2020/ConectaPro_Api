package com.perseus.conectapro.Entity;

import com.perseus.conectapro.Entity.Enuns.TipoUsuarioEnum;
import jakarta.persistence.Column;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Table(name = "USUARIOS")

public class Usuarios {

    @Id
    @Column(name = "ID_USUARIO")
    private Long id;

    @Column(name = "NOME_USUARIO")
    private String nome;

    @Column(name = "EMAIL")
    private String email;

    @Column(name = "SENHA")
    private String senha;

    @Column(name = "TIPOUSUARIO")
    private TipoUsuarioEnum tipoUsuarioEnum;

}
