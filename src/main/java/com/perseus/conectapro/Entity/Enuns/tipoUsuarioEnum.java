package com.perseus.conectapro.Entity.Enuns;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

public enum tipoUsuarioEnum {
    PRESTADOR(1, "Prestador"),
    CLIENTE(2, "Cliente");

    private final int id;
    private final String descricao;

    // Construtor da enumeração
    tipoUsuarioEnum(int id, String descricao) {
        this.id = id;
        this.descricao = descricao;
    }

    public int getId() {
        return id;
    }

    public String getDescricao() {
        return descricao;
    }
}

