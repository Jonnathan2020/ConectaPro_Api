package com.perseus.conectapro.Entity.Enuns;

public enum TipoUsuarioEnum {
    PRESTADOR(1, "Prestador"),
    CLIENTE(2, "Cliente");

    private final int id;
    private final String descricao;

    // Construtor da enumeração
    TipoUsuarioEnum(int id, String descricao) {
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

