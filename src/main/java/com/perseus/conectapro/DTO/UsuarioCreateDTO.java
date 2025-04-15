package com.perseus.conectapro.DTO;

import com.perseus.conectapro.Entity.Enuns.UfEnum;
import com.perseus.conectapro.Entity.Enuns.tipoUsuarioEnum;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UsuarioCreateDTO {

    private String nome;
    private String email;
    private String senha;
    private Long telefone;
    private tipoUsuarioEnum tipoUsuario;
    private String caminhoFoto;

    private String logradouro;
    private int numero;
    private String bairro;
    private String cidade;
    private UfEnum uf;
    private int cep;
    private String complemento;

}
