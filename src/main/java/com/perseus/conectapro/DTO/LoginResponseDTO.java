package com.perseus.conectapro.DTO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginResponseDTO {
    private int id;
    private String uf;
    private String tipoUsuario;
    private String nome;
    private String token;

    // Construtor
    public LoginResponseDTO( int id, String uf, String tipoUsuario, String nome, String token) {
        this.token = token;
        this.id = id;
        this.uf = uf;
        this.nome = nome;
        this.tipoUsuario = tipoUsuario;
    }
}
