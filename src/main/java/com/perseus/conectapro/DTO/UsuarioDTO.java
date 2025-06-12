package com.perseus.conectapro.DTO;

import com.perseus.conectapro.Entity.Enuns.RoleEnum;
import com.perseus.conectapro.Entity.Enuns.UfEnum;
import com.perseus.conectapro.Entity.Usuario;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UsuarioDTO {

    private Integer idUsuario;
    private String nome;
    private String email;
    private String telefone;
    private String tipoUsuario; // Ex: "EMPRESA", "PRESTADOR"
    private RoleEnum role;

    private String cidade;
    private UfEnum uf;
    private String cep;

    public UsuarioDTO(Usuario usuario) {
        this.idUsuario = usuario.getIdUsuario();
        this.nome = usuario.getNome();
        this.email = usuario.getEmail();
        this.telefone = usuario.getTelefone();
        this.tipoUsuario = usuario.getTipoUsuario().name(); // Se for enum
        this.role = usuario.getRole();

        if (usuario.getEndereco() != null) {
            this.cidade = usuario.getEndereco().getCidade();
            this.uf = usuario.getEndereco().getUf();
            this.cep = usuario.getEndereco().getCep();
        }
    }

}

