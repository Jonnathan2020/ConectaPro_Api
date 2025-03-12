package com.perseus.conectapro.Entity.Enuns;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

public enum tipoUsuarioEnum {
    PRESTADOR(1, "Prestador"),
    CLIENTE(2, "Cliente");

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_TIPO_USUARIO")
    public int id;

    @Column(name = "DESC_TIPO_USUARIO")
    public String descricao;

    // Construtor da enumeração
    tipoUsuarioEnum(int id, String descricao) {
        this.id = id;
        this.descricao = descricao;
    }


}
